package com.ibingbo.test;

import com.ibingbo.mapper.StudentMapper;
import com.ibingbo.mapper.UserMapper;
import com.ibingbo.models.DO.StudentDO;
import com.ibingbo.models.User;
import junit.framework.Assert;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.Buffer;

/**
 * Created by zhangbingbing on 2016/11/11.
 */
public class UserMapperTest {

    @Test
    public void findUserById(){
//        SqlSession sqlSession=getSessionFactory().openSession();
//        StudentMapper userMapper=sqlSession.getMapper(StudentMapper.class);
//        StudentDO user=userMapper.getStudentById(2);
//        Assert.assertNotNull("not find data",user);
        System.out.println(3+4+"hello");
        System.out.println("hello" + 3 + 4);
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

    @Test
    public void testConfigPath(){
        try {
            char[] buffer=new char[1024];
            Resources.getResourceAsReader("com/ibingbo/config/mapper/AccountMapper.xml").read(buffer);
            System.out.println(String.valueOf(buffer));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
