package com.service;

import com.entity.News;
import com.entity.PageBean;
import java.util.List;

/**
 * Created by SCX on 2015/12/8.
 */
public interface NewsService {
    /**
     * 获取需要显示的新闻
     * @param pageBean
     * @return
     */
    List<News> findRequiredNews(PageBean pageBean);

    /**
     * 通过新闻的主键获取新闻对象
     * @param newsId
     * @return
     */
    News getNewsById(int newsId);
}
