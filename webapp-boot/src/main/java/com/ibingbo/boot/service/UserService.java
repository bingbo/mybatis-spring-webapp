package com.ibingbo.boot.service;

import com.ibingbo.boot.bean.User;

import java.util.List;

/**
 * Created by bing on 2017/4/11.
 */
public interface UserService {

    String say();

    Integer getUserCount();

    List<User> getUsers();
}
