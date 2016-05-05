package com.service;

import com.entity.PageBean;
import com.entity.Product;
import java.util.List;

public interface ProductService {
    /**
     * 获取需要显示的特卖/热卖商品
     * @param product 作为条件查询的样本对象
     * @param pageBean 分页Bean
     * @return 获得当前页的数据集合
     */
    List<Product> findRequiredProduct(Product product, PageBean pageBean);

    /**
     * 通过id获得商品对象
     * @param productId 商品实体的主键
     * @return 商品对象
     */
    Product getProductById(int productId);

    /**
     * 获得根据product样本查询对象进行查询得到的总数据条数
     * @param product 样本查询对象
     * @return 总数据条数
     */
    long getProductCount(Product product);
}
