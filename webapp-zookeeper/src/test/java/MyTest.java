import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * Created by bing on 2017/3/23.
 */
public class MyTest {
    @Test
    public void testTime() {

        List<User> users = new ArrayList<User>();
        users.add(new User(1, "111"));
        users.add(new User(2, "222"));
        users.add(new User(3, "333"));
        users.add(new User(4, "444"));
        users.add(new User(5, "555"));

        for (int i=0;i<users.size();i++) {
            if (users.get(i).getId() % 2 == 0) {
                users.set(i, new User(22, "bbb"));
            }
        }
//        Iterator<User> iterator = users.iterator();
//        while (iterator.hasNext()) {
//            User user=iterator.next();
//            if (user.getId() % 2 == 0) {
//                user = new User(22, "bbb");
//            }
//        }

        System.out.println(JSON.toJSONString(users));

        String aaa="aaaa&gt;bbbbb";
        aaa.replace("\\&gt\\;", "xxx");
        System.out.println(aaa);

        System.out.println(JSON.toJSONString(this.getObject(1,User.class)));
        System.out.println(JSON.toJSONString(this.getObject(1,Student.class)));






    }

    private Map<Class, Dao> map = new HashMap<Class, Dao>(){{
        put(User.class, new UserDao());
        put(Student.class, new StudentDao());
    }};


    public <M> M getObject(int id, Class<M> doClass) {
        Dao<M> dao = map.get(doClass);
        return dao.getObject(id);
    }
}
