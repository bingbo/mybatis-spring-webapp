import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ibingbo.annotation.Log;
import com.ibingbo.test.APIField;
import com.ibingbo.test.POField;
import com.ibingbo.test.Student;
import com.ibingbo.test.User;

import freemarker.ext.util.IdentityHashMap;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.ReflectUtils;

/**
 * Created by bing on 2017/4/26.
 */
public class SomeTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(SomeTest.class);

    @Test
    public void testLog() throws Exception {
        try {
            String str = null;
            System.out.println(str.length());
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
        LOGGER.info("hello,{}", "bill");
    }

    @Test
    public void testCollection() {
        Map<String, Object> map = new HashedMap();
        String name = new String("name");
        String name1 = new String("name");
        map.put(name, "bill");
        map.put("age", 30);
        map.put(name1, "bing");
        Map<String, Object> m = new IdentityHashMap();
        m.put(name, "bill");
        m.put("age", 30);
        m.put(name1, "bing");
        System.out.println(map);
        System.out.println(m);
    }

    @Test
    public void testBeanToBean() {

        BeanCopier copier = BeanCopier.create(User.class, Student.class, false);
        User user = new User();
        user.setId(1);
        user.setName("user1");
        user.setEmail("email1");
        Student student = (Student) ReflectUtils.newInstance(Student.class);
        copier.copy(user, student, null);
        System.out.println(com.alibaba.fastjson.JSON.toJSONString(student));
    }

    @Test
    public void testSome() throws IllegalAccessException, InstantiationException, NoSuchFieldException {

        int depth = 16;
        long userid = 630152L;
        int mask = (int) (Math.pow(2, depth) - 1) << 8;
        int useridcode = (int) (((userid & mask) >>> 6) | (userid & 0x3));
        System.out.println(useridcode);
        System.out.println(userid % 16);
        A:
        for (int i = 0; i < 10; i++) {
            B:
            for (int j = 0; j < 10; j++) {
                C:
                for (int m = 0; m < 10; m++) {
                    System.out.println(i * j * m);
                    if (i * j * m % 2 == 0) {
                        break A;
                    }
                }
            }
        }

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 100, TimeUnit.SECONDS,new LinkedBlockingDeque
                <Runnable>(10));
        executor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
    }

    @Test
    public void testJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("mtid", 123456L);
        map.put("pic_url", "http://www.baidu.com/a.jpg");
        map.put("pictures", new String[] {"http://www.baidu.com/1.jpg", "http://www.baidu.com/2.jpg"});
        map.put("bid", 5.0);
        String jstr = JSON.toJSONString(map);
        System.out.println(jstr);

        JSONObject jsonObject = JSON.parseObject(jstr);
        System.out.println(jsonObject.getInteger("mtid"));
        System.out.println(jsonObject.getString("pic_url"));
        System.out.println(jsonObject.getString("pictures"));
        System.out.println(jsonObject.getDouble("bid"));

    }

    @Test
    public void testEnum() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        APIField field = POField.test;
        System.out.println(field.name());
        System.out.println(field.of("test"));
        System.out.println(field.ordinal());

        A a = new A();
        a.setId(1);
        a.setName("aaa");
        a.setPassword("aaa");
        B b = new B();
        copy(a, b);
        B bb = this.copy(a, B.class);
        LOGGER.info("result is: {}",b);
        LOGGER.info("result is: {}",bb);

        List<String> test = Arrays.asList("hell", "bill");
        Enumeration<String> aa = Collections.enumeration(test);
        while (aa.hasMoreElements()) {
            System.out.println(aa.nextElement());
        }

        System.out.println(double[].class);
        System.out.println(Class.forName("[D"));
        System.out.println(String[][].class);
        System.out.println(Class.forName("[[Ljava.lang.String;"));

    }

    @Test
    public void testA() throws NoSuchFieldException {
        Field field = A.class.getDeclaredField("id");
        System.out.println(field.isAnnotationPresent(Log.class));
        long num = 1234_5678L;
        System.out.println(num);
        int bitmask = 0x000F;
        int val = 0x2222;
        // prints "2"
        System.out.println(val & bitmask);
        for (int i=0;i<3;i++) {
            System.out.println((int) Math.random() * 10);
        }

        System.out.println(Paths.get("/Users"));

    }

    private void copy(A a,B b) {
        BeanUtils.copyProperties(a,b);
    }

    private <S,T> T copy(S a,Class<T> bClass) throws IllegalAccessException, InstantiationException {
        T b = bClass.newInstance();
        BeanUtils.copyProperties(a,b);
        return b;
    }



    public static class A{
        @Log
        private Integer id;
        private String name;
        private String password;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class B{
        private Integer id;
        private String name;
        private String password;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


}
