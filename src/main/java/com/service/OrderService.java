package com.service;

import com.entity.Order;
import com.entity.PageBean;
import java.util.List;

/**
 * Created by SCX on 2015/12/26.
 */
public interface OrderService {

    /**
     * 保存订单
     * @param order 要保存的订单对象
     */
    void save(Order order);

    /**
     * 得到当前用户的所有订单
     * @param s_order 查询条件
     * @param pageBean 分页
     * @return 满足条件的集合
     */
    List<Order> getRequiredOrders(Order s_order, PageBean pageBean);

    /**
     * 修改订单状态
     * @param order
     */
    void updateStatus(Order order);
}
