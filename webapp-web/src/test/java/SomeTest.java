import freemarker.ext.util.IdentityHashMap;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

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
    class User{
        private int id;
        private String name;
        private boolean running;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isRunning() {
            return running;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }
    }
}
