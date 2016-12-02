package com.ibingbo.test;

import com.ibingbo.models.User;
import com.ibingbo.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.SystemProfileValueSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by zhangbingbing on 2016/11/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/dispatcher-servlet.xml"})
@WebAppConfiguration
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Test
    public void testGetUserById(){
        User user = this.userService.getUserById("1");
        Assert.assertNotNull(user);
        System.out.println(user.getName());
    }
}