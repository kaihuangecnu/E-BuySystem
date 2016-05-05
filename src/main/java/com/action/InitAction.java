package com.action;

import com.entity.*;
import com.service.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;
import java.util.*;

@Component
public final class InitAction implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent){
        ServletContext application = servletContextEvent.getServletContext();
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(application);

        //查询商品大类
        ProductBigTypeService productBigTypeService= (ProductBigTypeService)applicationContext.getBean("productBigTypeServiceImpl");
        List<ProductBigType> productBigTypes=productBigTypeService.findAllProductBigType();
        application.setAttribute("productBigTypes",productBigTypes);

        //查询Tag
        TagService tagService= (TagService)applicationContext.getBean("tagServiceImpl");
        List<Tag> allTags=tagService.findAllTag();
        application.setAttribute("tags",allTags);

        //查询Notice
        NoticeService noticeService= (NoticeService)applicationContext.getBean("noticeServiceImpl");
        List<Notice> notices=noticeService.findRequiredNotice(new PageBean(1,7));
        application.setAttribute("notices",notices);

        //查询News
        NewsService newsService= (NewsService)applicationContext.getBean("newsServiceImpl");
        List<News> news=newsService.findRequiredNews(new PageBean(1,7));
        application.setAttribute("news",news);

        //查询Product
        ProductService productService=(ProductService)applicationContext.getBean("productServiceImpl");
        Product product=new Product();
        product.setSpecialPrice(1);
        List<Product> specialProducts=productService.findRequiredProduct(product,new PageBean(1,8));
        application.setAttribute("specialProducts",specialProducts);
        product.setSpecialPrice(0);
        product.setHot(1);
        List<Product> hotProducts=productService.findRequiredProduct(product,new PageBean(1,6));
        application.setAttribute("hotProducts",hotProducts);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // First close any background tasks which may be using the DB ...
        // Then close any DB connection pools ...

        // Now unregister JDBC drivers in this context's ClassLoader:
        // Get the WebApp's ClassLoader
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        // Loop through all drivers
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == contextClassLoader) {
                // This driver was registered by the WebApp's ClassLoader, so unregister it:
                try {
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                // driver was not registered by the WebApp's ClassLoader and may be in use elsewhere.
                System.out.println("driver was not registered by the WebApp's ClassLoader and may be in use elsewhere.");
            }
        }
    }
}
