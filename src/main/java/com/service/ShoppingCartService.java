package com.service;

import com.entity.AbstractShoppingCart;

/**
 * Created by SCX on 2015/12/22.
 */
public interface ShoppingCartService {

    /**
     * 保存购物车信息
     * @param shoppingCart 购物车对象
     */
    void save(AbstractShoppingCart shoppingCart);

    void delete(AbstractShoppingCart shoppingCart);
}
