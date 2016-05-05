package com.service.impl;

import com.dao.CommonDAO;
import com.entity.Comment;
import com.entity.PageBean;
import com.service.CommentService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SCX on 2015/12/16.
 */
@Service
public final class CommentServiceImpl implements CommentService {

    @Resource
    private CommonDAO<Comment> commonDAO;

    @Override
    public List<Comment> findAllComments(Comment comment, PageBean pageBean) {
        return commonDAO.find("from Comment order by createTime desc",pageBean);
    }

    @Override
    public long getCommentCount(Comment comment) {
        return commonDAO.count("select count(*) from Comment");
    }

    @Override
    public void saveComment(Comment comment) {
        commonDAO.merge(comment);
    }
}
