package com.ibingbo.test;

import com.ibingbo.models.User;
import com.ibingbo.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.*;

/**
 * Created by zhangbingbing on 2016/11/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/dispatcher-servlet.xml"})
@WebAppConfiguration
public class UserServiceTest {


    @Autowired
    private UserService userService;

//    @Autowired
//    private ProcessInstanceService processInstanceService;


    @Test
    public void testBpms() {
        Map<String, String> initData = new HashMap<String, String>();
        initData.put("name", "天眼");
        initData.put("intent", "人群分析");
        initData.put("tags", "年龄/性别/学历");
        initData.put("humanMark", "潜在购车者");
        initData.put("service", "ODPS");
        initData.put("inputData", "input data");
        initData.put("outputData", "output data");
        initData.put("runCycle", "20天");
        initData.put("updateFrequency", "1天");
        initData.put("expiryDate", "20天");
//        ProcessInstance instance = this.processInstanceService.startProcessInstance("insight-test-process","aaaaaaa","055690", initData,"1$insight-service-app-key$hello1234");
//        System.out.println(instance.toString());
    }
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
