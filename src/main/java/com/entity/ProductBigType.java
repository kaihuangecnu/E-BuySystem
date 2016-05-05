package com.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "t_bigType")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class ProductBigType implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 20)
    private String name;
    private String remark;

    @OneToMany(mappedBy = "productBigType")
    private List<Product> productList=new ArrayList<>(0);

    @OneToMany(mappedBy = "productBigType",fetch = FetchType.EAGER)
    private List<ProductSmallType> smallTypeList=new ArrayList<>(0);

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<ProductSmallType> getSmallTypeList() {
        return smallTypeList;
    }

    public void setSmallTypeList(List<ProductSmallType> smallTypeList) {
        this.smallTypeList = smallTypeList;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
