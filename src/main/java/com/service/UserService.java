package com.service;

import com.entity.PageBean;
import com.entity.User;
import java.util.List;

public interface UserService {

    /**
     * 保存一个用户对象
     * @param user
     */
    void saveOrUpdateUser(User user);

    /**
     * 验证用户名是否已存在
     * @param userName
     * @return
     */
    boolean userNameIsExisted(String userName);

    /**
     * 用户登录
     * @param user User对象用于传输用户名和密码
     * @return 返回登录成功的用户对象
     */
    User userLogin(User user);

    /**
     * 查询用户
     * @param user 查询样本
     * @param pageBean 分页
     * @return 符合指定样本的用户集合
     */
    List<User> queryUser(User user, PageBean pageBean);

    /**
     * 获取用户总数
     * @param user 查询样本
     * @return 用户总数
     */
    long getUserCount(User user);

    /**
     * 删除User
     * @param ids 要删除用户的id
     */
    int deleteUser(String ids);
}
