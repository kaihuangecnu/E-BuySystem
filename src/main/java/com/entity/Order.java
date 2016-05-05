package com.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.Cache;

@Entity
@Table(name = "t_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 50)
    private String orderNo;
    private Date createTime;
    private double cost;
    private int status;     //待审核、审核通过、已发货、已收货，1234

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private List<Order_Product> order_productList=new ArrayList<>(0);

    public List<Order_Product> getOrder_productList() {
        return order_productList;
    }

    public void setOrder_productList(List<Order_Product> order_productList) {
        this.order_productList = order_productList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Order() {
    }

    public Order(String orderNo, Date createTime, double cost, int status, User user, List<Order_Product> order_products) {
        this.orderNo = orderNo;
        this.createTime = createTime;
        this.cost = cost;
        this.status = status;
        this.user = user;
        this.order_productList = order_products;
    }
}
