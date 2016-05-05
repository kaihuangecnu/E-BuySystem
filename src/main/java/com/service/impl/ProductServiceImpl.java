package com.service.impl;

import com.dao.CommonDAO;
import com.entity.PageBean;
import com.entity.Product;
import com.service.ProductService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public final class ProductServiceImpl implements ProductService {

    @Resource
    private CommonDAO<Product> commonDAO;

    @Override
    public List<Product> findRequiredProduct(Product product, PageBean pageBean) {
        StringBuilder hql=new StringBuilder("from Product");
        if(product!=null){
            if(product.getProductBigType()!=null){
                hql.append(" and productBigType.id=").append(product.getProductBigType().getId());
            }
            if(product.getProductSmallType()!=null){
                hql.append(" and productSmallType.id=").append(product.getProductSmallType().getId());
            }
            if(product.getName()!=null&&product.getName().length()>0){
                hql.append(" and name like '%").append(product.getName()).append("%'");
            }
            if(product.getSpecialPrice()==1){
                hql.append(" and specialPrice=1 order by specialPriceTime desc");
            }
            if(product.getHot()==1){
                hql.append(" and hot=1 order by hotTime desc");
            }
        }
        if(pageBean!=null){
            return commonDAO.find(hql.toString().replaceFirst("and","where"),pageBean);
        }
        return commonDAO.find(hql.toString().replaceFirst("and","where"));
    }

    @Override
    public Product getProductById(int productId) {
        return commonDAO.get(Product.class,productId);
    }

    @Override
    public long getProductCount(Product product){
        StringBuilder hql=new StringBuilder("select count(1) from Product");
        if(product!=null){
            if(product.getProductBigType()!=null){
                hql.append(" and productBigType.id=").append(product.getProductBigType().getId());
            }
            if(product.getProductSmallType()!=null){
                hql.append(" and productSmallType.id=").append(product.getProductSmallType().getId());
            }
            if(product.getName()!=null&&product.getName().length()>0){
                hql.append(" and name like '%").append(product.getName()).append("%'");
            }
            if(product.getSpecialPrice()==1){
                hql.append(" and specialPrice=1 order by specialPriceTime desc");
            }
            if(product.getHot()==1){
                hql.append(" and hot=1 order by hotTime desc");
            }
        }
        return commonDAO.count(hql.toString().replaceFirst("and", "where"));
    }
}
