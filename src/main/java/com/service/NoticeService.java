package com.service;

import com.entity.Notice;
import com.entity.PageBean;
import java.util.List;

/**
 * Created by SCX on 2015/12/8.
 */
public interface NoticeService {
    /**
     * 获取前八条通知
     * @param pageBean
     * @return
     */
    List<Notice> findRequiredNotice(PageBean pageBean);

    /**
     * 通过公告id获取公告对象
     * @param noticeId
     * @return
     */
    Notice getNoticeById(int noticeId);
}
