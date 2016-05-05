package com.service.impl;

import com.dao.CommonDAO;
import com.entity.News;
import com.entity.PageBean;
import com.service.NewsService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public final class NewsServiceImpl implements NewsService{

    @Resource
    private CommonDAO<News> commonDAO;

    @Override
    public List<News> findRequiredNews(PageBean pageBean) {
        String hql="from News";
        return commonDAO.find(hql,null,pageBean);
    }

    @Override
    public News getNewsById(int newsId) {
        return commonDAO.get(News.class,newsId);
    }
}
