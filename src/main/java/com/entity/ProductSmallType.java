package com.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "t_smallType")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class ProductSmallType implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 20)
    private String name;
    private String remark;

    @OneToMany(mappedBy = "productSmallType")
    private List<Product> productList=new ArrayList<>(0);

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bigTypeId")
    private ProductBigType productBigType;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public ProductBigType getProductBigType() {
        return productBigType;
    }

    public void setProductBigType(ProductBigType productBigType) {
        this.productBigType = productBigType;
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
