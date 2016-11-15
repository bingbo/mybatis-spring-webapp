package com.ibingbo.test;

import com.ibingbo.mapper.UserMapper;
import com.ibingbo.models.User;
import junit.framework.Assert;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by zhangbingbing on 2016/11/11.
 */
public class UserMapperTest {

    @Test
    public void findUserById(){
        SqlSession sqlSession=getSessionFactory().openSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        User user=userMapper.getUser(2);
        Assert.assertNotNull("not find data",user);
    }
    private static SqlSessionFactory getSessionFactory(){
        SqlSessionFactory sqlSessionFactory=null;
        String resource="configuration-test.xml";
        try {
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
        }catch (IOException e){
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }
}
