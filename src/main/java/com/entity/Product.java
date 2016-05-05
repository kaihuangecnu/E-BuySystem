package com.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "t_Product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 50)
    private String name;
    private int price;
    private int stock;
    private String picture;
    @Column(length = 1000)
    private String description;
    private int hot;//1表示是热卖
    private Date hotTime;
    private int specialPrice;
    private Date specialPriceTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bigTypeId")
    private ProductBigType productBigType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "smallTypeId")
    private ProductSmallType productSmallType;

    @OneToMany
    @JoinColumn(name = "productId")
    private List<Order_Product> order_productList=new ArrayList<>(0);

    @OneToMany(targetEntity = AbstractShoppingCart.class,mappedBy = "product")
    private List<AbstractShoppingCart> abstractShoppingCarts=new ArrayList<>(0);

    public List<Order_Product> getOrder_productList() {
        return order_productList;
    }

    public void setOrder_productList(List<Order_Product> order_productList) {
        this.order_productList = order_productList;
    }

    public ProductBigType getProductBigType() {
        return productBigType;
    }

    public void setProductBigType(ProductBigType productBigType) {
        this.productBigType = productBigType;
    }

    public ProductSmallType getProductSmallType() {
        return productSmallType;
    }

    public void setProductSmallType(ProductSmallType productSmallType) {
        this.productSmallType = productSmallType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public Date getHotTime() {
        return hotTime;
    }

    public void setHotTime(Date hotTime) {
        this.hotTime = hotTime;
    }

    public int getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(int specialPrice) {
        this.specialPrice = specialPrice;
    }

    public Date getSpecialPriceTime() {
        return specialPriceTime;
    }

    public void setSpecialPriceTime(Date specialPriceTime) {
        this.specialPriceTime = specialPriceTime;
    }

    public List<AbstractShoppingCart> getAbstractShoppingCarts() {
        return abstractShoppingCarts;
    }

    public void setAbstractShoppingCarts(List<AbstractShoppingCart> abstractShoppingCarts) {
        this.abstractShoppingCarts = abstractShoppingCarts;
    }
}
