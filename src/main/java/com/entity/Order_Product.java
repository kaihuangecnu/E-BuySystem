package com.entity;

import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "t_order_Product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Order_Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int num;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Order_Product(int num, Product product) {
        this.num = num;
        this.product = product;
    }

    public Order_Product() {
    }
}
