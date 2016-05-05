package com.service;

import com.entity.Comment;
import com.entity.PageBean;
import java.util.List;

/**
 * Created by SCX on 2015/12/16.
 */
public interface CommentService {

    /**
     * 查找所有的评论
     * @param comment 查询样本对象
     * @param pageBean 分页
     * @return 返回符合条件的当前页的数据集合
     */
    List<Comment> findAllComments(Comment comment, PageBean pageBean);

    /**
     * 查询符合条件的数据总条数
     * @param comment 查询样本对象
     * @return 返回符合条件的数据总条数
     */
    long getCommentCount(Comment comment);

    /**
     * 保存评论
     * @param comment
     */
    void saveComment(Comment comment);
}
