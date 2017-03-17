package com.ibingbo.test;

import com.ibingbo.models.User;
import com.ibingbo.proxy.ITest;
import com.ibingbo.util.Common;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangbingbing on 2016/11/11.
 */
public class UserMapperTest {

    @Before
    public void runBefore(){
        System.out.println("before");
    }
    @After
    public void runAfter(){
        System.out.println("after");
    }
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

    @Test
    public void testSome(){
        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
//            LocalDate date = LocalDate.parse("", formatter);
            Class p = Class.forName("com.ibingbo.models.User");
            Map<String, Object> map = new HashMap<String, Object>();
            User user=new User();
            user.setName("bill");
            user.setEmail("bill@126.com");
            map.put("user", user);
            Object object = map.get("user");
            boolean b=p.isInstance(object);
            if (b) {
                System.out.println(p.cast(object));
            }
            Class<User> userClass=User.class;
            Field nameField = userClass.getDeclaredField("name");
            Field emailField = userClass.getDeclaredField("email");
            emailField.setAccessible(true);
            nameField.setAccessible(true);
            System.out.println(nameField.get(user));
            System.out.println(emailField.get(user));
            Field id = userClass.getDeclaredField("id");
            id.setAccessible(true);
            id.set(user, "30");
            Field pwd=userClass.getDeclaredField("password");
            pwd.setAccessible(true);
            pwd.set(user,"1111");
            System.out.println(user);
            ITest test=(ITest) Proxy.newProxyInstance(ITest.class.getClassLoader(), new Class[]{ITest.class}, new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println(method);
                    if (null != args) {
                        for (Object arg : args) {
                            System.out.println(arg);
                        }
                    }
                    return null;
                }
            });
            test.say();
            test.sayHi("bill");
            System.out.println(((Type)p));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInteger(){
        Integer a=100;
        System.out.println(a == 100);
        System.out.println(a.equals(100));
        Integer b = 140;
        System.out.println(b == 140);
        System.out.println(b.equals(140));
        long var = 10l;

    }

    @Test
    public void testSome1() {
        Common.sayHello("bill");
    }
}
