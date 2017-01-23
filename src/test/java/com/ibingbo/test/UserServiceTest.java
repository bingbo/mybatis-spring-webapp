package com.ibingbo.test;

import com.ibingbo.models.User;
import com.ibingbo.services.UserService;
import org.jruby.RubyProcess;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    public void testGetUserById() {
        User user = this.userService.getUserById("1");
        Assert.assertNotNull(user);
        System.out.println(user.getName());
    }

    @Test
    public void testGetView() {
        Collections.sort(new ArrayList(), new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return 0;
            }
        });
    }

}
