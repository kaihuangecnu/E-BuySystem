package com.service;

import com.entity.Tag;
import java.util.List;

/**
 * Created by SCX on 2015/12/8.
 */
public interface TagService {
    /**
     * 获取所有的标签
     * @return
     */
    List<Tag> findAllTag();
}
