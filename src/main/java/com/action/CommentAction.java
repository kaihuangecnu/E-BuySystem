package com.action;

import com.entity.Comment;
import com.entity.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.service.CommentService;
import com.util.SplitPage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public final class CommentAction extends ActionSupport implements ServletRequestAware{

    @Resource
    private CommentService commentService;
    private HttpServletRequest request;
    private List<Comment> comments;
    private Comment s_comment;      //查询样本对象
    private Comment comment;        //需保存的评论对象
    private int page;    //当前页
    private String pageCode;    //生成分页代码

    /**
     * 倒序查询所有的评论
     */
    public String execute(){
        if(page==0) page=1;
        this.comments=commentService.findAllComments(s_comment,new PageBean(page,8));
        long totalItem=commentService.getCommentCount(s_comment);
        StringBuilder params=new StringBuilder(0);
//        此处为以后功能扩展预留余地
//        if(s_comment!=null){
//
//        }
        this.pageCode= SplitPage.pageCode(request.getContextPath() + "/comment.action", totalItem, page, 8, params.toString());
        return SUCCESS;
    }

    /**
     * 保存评论
     */
    public String saveOrUpdateComment(){
        if(comment.getCreateTime()==null)
            comment.setCreateTime(new Date());
        commentService.saveComment(comment);
        return this.execute();
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request=request;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getPageCode() {
        return pageCode;
    }

    public Comment getS_comment() {
        return s_comment;
    }

    public void setS_comment(Comment s_comment) {
        this.s_comment = s_comment;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
