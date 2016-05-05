package com.service.impl;

import com.dao.CommonDAO;
import com.entity.ProductBigType;
import com.service.ProductBigTypeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public final class ProductBigTypeServiceImpl implements ProductBigTypeService{

    @Resource
    private CommonDAO<ProductBigType> commonDAO;

    @Override
    public List<ProductBigType> findAllProductBigType() {
        return commonDAO.find("from ProductBigType");
    }
}
