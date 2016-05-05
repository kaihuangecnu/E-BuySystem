package com.service.impl;

import com.dao.CommonDAO;
import com.entity.Order;
import com.entity.PageBean;
import com.service.OrderService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by SCX on 2015/12/26.
 */
@Service
public final class OrderServiceImpl implements OrderService{

    @Resource
    private CommonDAO<Order> commonDAO;

    @Override
    public void save(Order order) {
        commonDAO.merge(order);
    }

    @Override
    public List<Order> getRequiredOrders(Order s_order,PageBean pageBean) {
        StringBuilder hql=new StringBuilder(0);
        hql.append("from Order");
        if(s_order!=null){
            if(s_order.getUser()!=null)
                hql.append(" and user.id=").append(s_order.getUser().getId());
            if(s_order.getOrderNo()!=null&&!s_order.getOrderNo().equals(""))
                hql.append(" and orderNo like '%").append(s_order.getOrderNo()).append("%'");
        }
        hql.append(" order by createTime desc");
        return pageBean != null ? commonDAO.find(hql.toString().replaceFirst("and", "where"),pageBean) : commonDAO.find(hql.toString().replaceFirst("and", "where"));
    }

    @Override
    public void updateStatus(Order order) {
        commonDAO.executeHql("update Order set status=" + order.getStatus() + " where id=" + order.getId());
    }
}
