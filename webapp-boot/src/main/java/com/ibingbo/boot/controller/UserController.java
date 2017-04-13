package com.ibingbo.boot.controller;

import com.ibingbo.boot.bean.User;
import com.ibingbo.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by bing on 2017/4/11.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String hello() {
        return this.userService.say();
    }

    @RequestMapping("/index")
    public String index() {
        return "this is index page...!!!";
    }

    @RequestMapping("/count")
    public Integer count() {
        return this.userService.getUserCount();
    }

    @RequestMapping("/list")
    public List<User> list() {
        return this.userService.getUsers();
    }
}
