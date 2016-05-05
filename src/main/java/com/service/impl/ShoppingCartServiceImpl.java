package com.service.impl;

import com.dao.CommonDAO;
import com.entity.AbstractShoppingCart;
import com.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by SCX on 2015/12/22.
 */
@Service
public final class ShoppingCartServiceImpl implements ShoppingCartService{

    @Resource
    private CommonDAO<AbstractShoppingCart> commonDAO;

    @Override
    public void save(AbstractShoppingCart shoppingCart) {
        commonDAO.merge(shoppingCart);
    }

    @Override
    public void delete(AbstractShoppingCart shoppingCart) {
        commonDAO.delete(shoppingCart);
    }
}
