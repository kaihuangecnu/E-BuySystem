package com.action;

import com.entity.News;
import com.opensymphony.xwork2.ActionSupport;
import com.service.NewsService;
import com.util.NavUtil;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public final class NewsAction extends ActionSupport implements ServletRequestAware{

    @Resource
    private NewsService newsService;
    private News news;
    private int newsId;
    private String mainPage;
    private String navCode;
    private HttpServletRequest request;

    public String showNews(){
        news=newsService.getNewsById(newsId);
        mainPage="news/newsDetails.jsp";
        navCode= NavUtil.getNavCode(request,"新闻详情");
        return SUCCESS;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public News getNews() {
        return news;
    }

    public String getMainPage() {
        return mainPage;
    }

    public String getNavCode() {
        return navCode;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }
}
