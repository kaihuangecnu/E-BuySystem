package com.dao.impl;

import com.dao.CommonDAO;
import com.entity.PageBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Repository
public final class CommonDAOImpl<T> implements CommonDAO<T>{

    @Resource
    private SessionFactory sessionFactory;

    /**
     * 获取当前的Session对象
     * @return 当前的Session对象
     */
    private Session getCurrentSession(){
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Serializable save(T t) {
        return this.getCurrentSession().save(t);
    }

    @Override
    public void delete(T t) {
        this.getCurrentSession().delete(t);
    }

    @Override
    public void update(T t) {
        this.getCurrentSession().update(t);
    }

    @Override
    public void saveOrUpdate(T t) {
        this.getCurrentSession().saveOrUpdate(t);
    }

    @Override
    public void merge(T t) {
        this.getCurrentSession().merge(t);
    }

    @Override
    public List<T> find(String hql){
        return this.getCurrentSession().createQuery(hql).setCacheable(true).list();
    }

    @Override
    public List<T> find(String hql,PageBean pageBean) {
        return this.getCurrentSession().createQuery(hql).setFirstResult(pageBean.getOffset()).setMaxResults(pageBean.getPageSize()).setCacheable(true).list();
    }

    @Override
    public List<T> find(String hql, Object[] params) {
        Query query=this.getCurrentSession().createQuery(hql).setCacheable(true);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(String.valueOf(i),params[i]);
        }
        return query.list();
    }

    @Override
    public List<T> find(String hql, Object[] params, PageBean pageBean) {
        Query query=this.getCurrentSession().createQuery(hql);
        if(params!=null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(String.valueOf(i), params[i]);
            }
        }
        return query.setFirstResult(pageBean.getOffset()).setMaxResults(pageBean.getPageSize()).setCacheable(true).list();
    }

    @Override
    public T get(Class<T> clazz, Serializable id) {
        return (T) this.getCurrentSession().get(clazz,id);
    }

    @Override
    public T get(String hql, Object[] params) {
        List list=this.find(hql,params);
        if(list!=null&&list.size()>0){
            return (T) list.get(0);
        }
        return null;
    }

    @Override
    public long count(String hql) {
        return (long) this.getCurrentSession().createQuery(hql).uniqueResult();
    }

    @Override
    public long count(String hql, Object[] params) {
        Query query=this.getCurrentSession().createQuery(hql);
        if(params!=null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(String.valueOf(i), params[i]);
            }
        }
        return (long) query.uniqueResult();
    }

    @Override
    public int executeHql(String hql) {
        return this.getCurrentSession().createQuery(hql).setCacheable(true).executeUpdate();
    }

    @Override
    public int executeHql(String hql, Object[] params) {
        Query query=this.getCurrentSession().createQuery(hql).setCacheable(true);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(String.valueOf(i),params[i]);
        }
        return query.executeUpdate();
    }

    @Override
    public int executeSql(String sql) {
        return this.getCurrentSession().createSQLQuery(sql).setCacheable(true).executeUpdate();
    }
}