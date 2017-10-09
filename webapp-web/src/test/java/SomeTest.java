import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
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
    public void testLog() throws Exception{
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
        User user=new User();
        user.setId(1);
        user.setName("user1");
        user.setEmail("email1");
        Student student = (Student) ReflectUtils.newInstance(Student.class);
        copier.copy(user, student, null);
        System.out.println(com.alibaba.fastjson.JSON.toJSONString(student));
    }

    @Test
    public void testSome() throws IllegalAccessException, InstantiationException, NoSuchFieldException {


        int depth=16;
        long userid=630152L;
        int mask = (int)(Math.pow(2, depth) - 1)<< 8;
        int useridcode = (int)(((userid & mask) >>> 6) | (userid & 0x3));
        System.out.println(useridcode);
        System.out.println(userid%16);
    }
}
