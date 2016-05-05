package com.service.impl;

import com.dao.CommonDAO;
import com.entity.Tag;
import com.service.TagService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SCX on 2015/12/8.
 */
@Service
public final class TagServiceImpl implements TagService{

    @Resource
    private CommonDAO<Tag> commonDAO;

    @Override
    public List<Tag> findAllTag() {
        String hql="from Tag";
        return commonDAO.find(hql);
    }
}
