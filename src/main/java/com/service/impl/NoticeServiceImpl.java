package com.service.impl;

import com.dao.CommonDAO;
import com.entity.Notice;
import com.entity.PageBean;
import com.service.NoticeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public final class NoticeServiceImpl implements NoticeService{

    @Resource
    private CommonDAO<Notice> commonDAO;

    @Override
    public List<Notice> findRequiredNotice(PageBean pageBean) {
        String hql="from Notice";
        return commonDAO.find(hql,null,pageBean);
    }

    @Override
    public Notice getNoticeById(int noticeId) {
        return commonDAO.get(Notice.class,noticeId);
    }
}
