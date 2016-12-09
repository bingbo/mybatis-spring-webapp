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

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testSerialize(){
        ObjectOutputStream objectOutputStream=null;
        ObjectInputStream objectInputStream = null;
        try {
            OutputStream outputStream = new FileOutputStream("/Users/zhangbingbing/object.txt");
            objectOutputStream = new ObjectOutputStream(outputStream);
            User user=new User();
            user.setId("1");
            user.setName("冰");
            user.setPassword("111");
            user.setEmail("bing@126.com");
            objectOutputStream.writeObject(user);

            InputStream inputStream = new FileInputStream("/Users/zhangbingbing/object.txt");
            objectInputStream = new ObjectInputStream(inputStream);
            User iuser = (User) objectInputStream.readObject();
            System.out.println(iuser.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null != objectOutputStream) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void testList(){

        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<10;i++){
            list.add(i);
        }
        System.out.println(list.toString());
        deleteItems(list);
        System.out.println(list.toString());
    }
    private void deleteItems(List list){
        list.remove(1);
        list.remove(3);
        list.remove(5);
    }
}
