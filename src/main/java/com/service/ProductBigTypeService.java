package com.service;

import com.entity.ProductBigType;
import java.util.List;

public interface ProductBigTypeService {
    /**
     * 获得所有的商品大类
     * @return 商品大类集合
     */
    List<ProductBigType> findAllProductBigType();
}
