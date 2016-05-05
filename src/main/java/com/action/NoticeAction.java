package com.action;

import com.entity.Notice;
import com.opensymphony.xwork2.ActionSupport;
import com.service.NoticeService;
import com.util.NavUtil;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public final class NoticeAction extends ActionSupport implements ServletRequestAware{

    @Resource
    private NoticeService noticeService;
    private Notice notice;
    private int noticeId;
    private String mainPage; //主页
    private String navCode;     //导航条
    private HttpServletRequest request;

    public String showNotice(){
        notice=noticeService.getNoticeById(noticeId);
        mainPage="notice/noticeDetails.jsp";
        navCode= NavUtil.getNavCode(request,"公告信息");
        return SUCCESS;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public Notice getNotice() {
        return notice;
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
