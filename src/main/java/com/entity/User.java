package com.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "t_User")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 20)
    private String trueName;
    @Column(length = 20)
    private String userName;
    @Column(length = 20)
    private String password;
    @Column(length = 4)
    private String sex;
    private Date birthday;
    @Column(length = 20)
    private String identityCode;
    @Column(length = 20)
    private String email;
    @Column(length = 20)
    private String mobile;
    @Column(length = 200)
    private String address;
    private int status;     //普通用户是0，管理员用户是1

    @OneToMany(targetEntity = Order.class,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userId",updatable = false)
    private List<Order> orderList=new ArrayList<>();

    @OneToMany(targetEntity = AbstractShoppingCart.class,mappedBy ="user",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<AbstractShoppingCart> shoppingCarts=new ArrayList<>(0);

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<AbstractShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<AbstractShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }
}
