package com.entity;

import javax.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Created by SCX on 2015/12/16.
 * 抽象的购物车：不同于现实中的购物车，定义"一种只能存放一种商品的购物车"
 */
@Entity
@Table(name = "t_AbstractShoppingCart")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class AbstractShoppingCart implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int productAmount;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;

    /**
     * 由于@OneToOne不能实现懒加载，无奈之下改为@ManyToOne
     */
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "productId",referencedColumnName = "id")
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
