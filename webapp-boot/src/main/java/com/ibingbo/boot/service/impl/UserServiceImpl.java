package com.ibingbo.boot.service.impl;

import com.ibingbo.boot.bean.User;
import com.ibingbo.boot.bean.UserBean;
import com.ibingbo.boot.dao.UserDao;
import com.ibingbo.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bing on 2017/4/11.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserBean userBean;
    @Autowired
    private UserDao userDao;

    public String say() {
        return "hi,bill";
    }

    public List<User> getUsers() {
        return this.userDao.getUsers();
    }

    public Integer getUserCount() {
        return this.userBean.getUserSize();
    }

}
