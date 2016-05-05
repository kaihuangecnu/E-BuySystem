package com.dao;

import com.entity.PageBean;
import java.io.Serializable;
import java.util.List;

public interface CommonDAO<T> {
    /**
     * 保存一个对象
     * @param t
     */
    Serializable save(T t);

    /**
     * 删除一个对象
     * @param t
     */
    void delete(T t);

    /**
     * 更新一个对象
     * @param t
     */
    void update(T t);

    /**
     * 保存或更新对象
     * @param t
     */
    void saveOrUpdate(T t);

    /**
     * 保存或合并对象
     * @param t
     */
    void merge(T t);

    /**
     * 根据hql语句进行查询
     * @param hql
     * @return
     */
    List<T> find(String hql);

    /**
     * 根据hql语句并附带参数进行查询
     * @param hql
     * @param params
     * @return
     */
    List<T> find(String hql, Object[] params);

    /**
     * 根据hql语句进行分页查询
     * @param hql
     * @return
     */
    List<T> find(String hql, PageBean pageBean);

    /**
     * 根据hql语句并附带参数进行分页查询
     * @param hql
     * @param params
     * @param pageBean
     * @return
     */
    List<T> find(String hql, Object[] params, PageBean pageBean);

    /**
     * 通过主键查询一个对象
     * @param clazz
     * @param id
     * @return
     */
    T get(Class<T> clazz, Serializable id);

    /**
     * 通过hql语句并附带参数查询一个对象，如果有多个对象返回，则只返回第一个
     * @param hql
     * @param params
     * @return
     */
    T get(String hql, Object[] params);

    /**
     * 统计数据总记录数
     * @param hql
     * @return
     */
    long count(String hql);

    /**
     * 统计符合条件的数据总记录数
     * @param hql
     * @param params
     * @return
     */
    long count(String hql, Object[] params);

    /**
     * 执行hql语句
     * @param hql
     * @return
     */
    int executeHql(String hql);

    /**
     * 执行带参数的hql语句
     * @param hql
     * @param params
     * @return
     */
    int executeHql(String hql, Object[] params);

    /**
     * 执行sql语句
     * @param sql
     * @return
     */
    int executeSql(String sql);
}
