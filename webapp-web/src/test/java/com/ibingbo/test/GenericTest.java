package com.ibingbo.test;

import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.ibingbo.models.User;

/**
 * Created by zhangbingbing on 2016/12/5.
 */
public class GenericTest {
    @Test
    public void testDraw() {
        List<Cycle> shapes = new ArrayList<Cycle>();
        shapes.add(new Cycle());
        //shapes.add(new Reat());
        Canvas canvas = new Canvas();
        canvas.drawAll(shapes);

        System.out.println(TimeZone.getDefault().getDisplayName() + TimeZone.getDefault().getID());
        Matcher matcher = Pattern.compile("a*b*c").matcher("abc");
        System.out.println(matcher.matches() + "|" + matcher.group());
        Locale[] res = Locale.getAvailableLocales();
        for (Locale locale : res) {
            System.out.println(
                    locale.getCountry() + ":" + locale.getDisplayCountry() + "|" + locale.getLanguage() + ":" + locale
                            .getDisplayLanguage());
        }

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(
                    "/Users/zhangbingbing/Work/github/mybatis-spring-webapp/src/main/resources/log4j.properties"));
            System.out.println(properties.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testChange() {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = new User();
        user.setName("bill");
        map.put("1", user);
        User user1 = new User();
        user1.setName("bing");
        map.put("2", user1);

        List<User> list = changeMap2List(map, User.class);
        System.out.println(list);
    }

    public <T> List<T> changeMap2List(Map<String, Object> map, Class<T> tClass) {
        List<T> result = new ArrayList<T>();
        for (Map.Entry entry : map.entrySet()) {
            T o = (T) entry.getValue();
            result.add(o);
        }
        return result;
    }

    @Test
    public void testA() throws IllegalAccessException, InstantiationException {
        RootModel model = RootModel.class.newInstance();
        Field[] fields = RootModel.class.getDeclaredFields();
        for (Field field : fields) {
            Class type = field.getType();
            if (Demo.class.isAssignableFrom(type)) {
                System.out.println("it is demo class");
                field.setAccessible(true);
                field.set(model, type.newInstance());
            } else if (List.class.isAssignableFrom(type)) {
                ParameterizedType tp = (ParameterizedType) field.getGenericType();
                Class cls = (Class) tp.getActualTypeArguments()[0];
                System.out.println("it is list class");
                field.setAccessible(true);
                field.set(model, Lists.newArrayList());
                model.getDemos().add(new Demo());
            } else if (type.isArray()) {
                System.out.println("it is array");
                field.setAccessible(true);
                field.set(model, Array.newInstance(type.getComponentType(), 0));
            }
        }
    }

}
