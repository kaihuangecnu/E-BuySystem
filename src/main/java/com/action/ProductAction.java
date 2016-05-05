package com.action;

import com.entity.PageBean;
import com.entity.Product;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ProductService;
import com.util.NavUtil;
import com.util.SplitPage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
public final class ProductAction extends ActionSupport implements ServletRequestAware{

    @Resource
    private ProductService productService;
    private List<Product> products;
    private Product s_product;    //用于传输查询条件的样本对象
    private int page;    //当前页
    private String pageCode;    //生成分页代码
    private String mainPage;    //商品展示页
    private String navCode; //导航条
    private HttpServletRequest request;
    private Product product;    //通过id查询得到的product对象

    public String execute(){
        if(page==0) page = 1;
        PageBean pageBean=new PageBean(page,8);
        products=productService.findRequiredProduct(s_product,pageBean);
        long totalItem = productService.getProductCount(s_product);
        StringBuilder param=new StringBuilder(0);
        if(s_product!=null){
            if(s_product.getProductBigType()!=null)
                param.append("s_product.productBigType.id=").append(s_product.getProductBigType().getId());
            if(s_product.getProductSmallType()!=null)
                param.append("s_product.productSmallType.id=").append(s_product.getProductSmallType().getId());
            if(s_product.getName()!=null&&s_product.getName().length()>0)
                param.append("s_product.name=").append(s_product.getName());
        }
        pageCode= SplitPage.pageCode(request.getContextPath() + "/product.action", totalItem, page, 8, param.toString());
        navCode= NavUtil.getNavCode(request,"商品列表");
        mainPage="product/products.jsp";
        return SUCCESS;
    }

    public String showProductDetails(){
        product=productService.getProductById(s_product.getId());
        navCode= NavUtil.getNavCode(request,"商品详情");
        mainPage="product/productDetails.jsp";
        this.saveRecentlyBrowse(product);
        return SUCCESS;
    }

    /**
     * 保存最近浏览的商品
     */
    private void saveRecentlyBrowse(Product product){
        HttpSession session=request.getSession();
        @SuppressWarnings("unchecked")
        List<Product> recentlyBrowsedProducts= (List<Product>) session.getAttribute("recentlyBrowsedProducts");
        if(recentlyBrowsedProducts==null){
            recentlyBrowsedProducts=new LinkedList<>();
            recentlyBrowsedProducts.add(product);
        }else{
            for(int i=0;i<recentlyBrowsedProducts.size();i++){
                if(recentlyBrowsedProducts.get(i).getId()==product.getId()){
                    recentlyBrowsedProducts.remove(i);
                    break;
                }
            }
            recentlyBrowsedProducts.add(0,product);
        }
        if(recentlyBrowsedProducts.size()==6) recentlyBrowsedProducts.remove(5);
        session.setAttribute("recentlyBrowsedProducts",recentlyBrowsedProducts);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Product getS_product() {
        return s_product;
    }

    public void setS_product(Product s_product) {
        this.s_product = s_product;
    }

    public String getPageCode() {
        return pageCode;
    }

    public String getMainPage() {
        return mainPage;
    }

    public String getNavCode() {
        return navCode;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request=request;
    }
}
