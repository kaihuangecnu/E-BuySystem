package com.service.impl;

import com.dao.CommonDAO;
import com.entity.PageBean;
import com.entity.User;
import com.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public final class UserServiceImpl implements UserService{

    @Resource
    private CommonDAO<User> commonDAO;

    @Override
    public void saveOrUpdateUser(User user) {
        commonDAO.merge(user);
    }

    @Override
    public boolean userNameIsExisted(String userName) {
        String hql="select count(1) from User where userName='"+userName+"'";
        return commonDAO.count(hql)>0;
    }

    @Override
    public User userLogin(User user) {
        String hql="from User where userName=?0 and password=?1";
        Object[] params={user.getUserName(),user.getPassword()};
        return commonDAO.get(hql, params);
    }

    @Override
    public List<User> queryUser(User user,PageBean pageBean) {
        StringBuilder hql = new StringBuilder("from User");
        if(user!=null) {
            if (user.getUserName() != null) {
                hql.append(" and userName like '").append(user.getUserName()).append("%'");
            }
            if (user.getSex() != null) {
                hql.append(" and sex=").append(user.getSex());
            }
        }
        return commonDAO.find(hql.toString().replaceFirst("and", "where"), pageBean);
    }

    @Override
    public long getUserCount(User user) {
        StringBuilder hql=new StringBuilder("select count(1) from User");
        if(user!=null){
            if(user.getUserName()!=null){
                hql.append(" and userName like '").append(user.getUserName()).append("%'");
            }
            if(user.getSex()!=null){
                hql.append(" and sex=").append(user.getSex());
            }
        }
        return commonDAO.count(hql.toString().replaceFirst("and","where"));
    }

    @Override
    public int deleteUser(String ids) {
//        return commonDAO.executeHql("delete from User where id in (" + ids + ")");
        String[] id=ids.split(",");
        for(String is:id){
            commonDAO.delete(this.getUserById(Integer.parseInt(is)));
        }
        return id.length;
    }

    /**
     * 通过id获取User
     * @param id 用户id
     * @return User对象
     */
    private User getUserById(int id){
        return commonDAO.get(User.class,id);
    }
}
