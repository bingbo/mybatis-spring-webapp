package com.ibingbo.boot.dao;

import com.ibingbo.boot.bean.User;

import java.util.List;

/**
 * Created by bing on 2017/4/12.
 */
public interface UserDao {
    List<User> getUsers();

    User getUserById(int id);
}
