package com.ibingbo.test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ibingbo.models.User;
import com.ibingbo.support.jdbc.condition.Expr;
import com.ibingbo.support.jdbc.condition.QueryCondition;
import com.ibingbo.support.jdbc.dao.DaoSupport;

/**
 * Created by zhangbingbing on 2016/11/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/dispatcher-servlet.xml"})
@WebAppConfiguration
public class JdbcTemplateTest {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcTemplateTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DaoSupport daoSupport;

    @Test
    public void testQuery() {
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        List<User> userList = this.jdbcTemplate.query("select * from user", rowMapper);
        LOG.info("result is: {}", userList);
        List<Long> ids = this.jdbcTemplate.queryForList("select id from USER ",Long.class);
        LOG.info("result is: {}", ids);
    }

    @Test
    public void testAdd() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[] {"aaa", "aaa", "aaa@126.com"});
        list.add(new Object[] {"bbb", "bbb", "bbb@126.com"});
        list.add(new Object[] {"ccc", "ccc", "ccc@126.com"});
        list.add(new Object[] {"ddd", "ddd", "ddd@126.com"});
        int[] res = this.jdbcTemplate.batchUpdate("insert into user(name,password,email) VALUES (?,?,?)",
                list);

        LOG.info("result is: {}", res);

    }

    @Test
    public void testBatchUpdate() {
        List<Object[]> list = new ArrayList<>();
        for (int i = 10; i < 37; i++) {

            list.add(new Object[] {i, "dddddd", "yyyyyy",i});
        }
        int s = Instant.now().getNano();
//        int[] res = this.jdbcTemplate.batchUpdate("update user set id=?,password=?,name=? where id=?",list);
        for(int i=0;i<list.size();i++) {
            this.jdbcTemplate.update("update user set id=?,password=?,name=? where id=?", list.get(i));
        }
        int e = Instant.now().getNano();
        LOG.info("res take {} ns", (e - s)/1000000);
    }

    @Test
    public void testDaoQuery() {

        QueryCondition condition = new QueryCondition();
        condition.add(Expr.in("id", new Integer[] {1, 2, 3, 4, 5})).orderByDesc("id").limit(3);
        List<TestPO> testPOS = this.daoSupport.query(TestPO.class, condition);
        LOG.info("res is: {}", testPOS);
    }

    @Test
    public void testDaoAdd() {
        TestPO po = new TestPO();
        po.setEmail("111@mail.com");
        po.setName("111");
        po.setPassword("111");

        int res = this.daoSupport.insert(po);
        LOG.info("res is: {}", res);
    }

    @Test
    public void testDaoUpdate() {
        TestPO po = new TestPO();
        po.setId(6);
        po.setPassword("666");
        int res = this.daoSupport.update(po);
        LOG.info("res is: {}", res);
    }

    @Test
    public void testDaoBatchAdd() {
        List<TestPO> pos = new ArrayList<>();
        for (int i = 300; i < 320; i++) {
            TestPO po = new TestPO();
            po.setName(String.valueOf(i));
            po.setPassword(String.valueOf(i));
            po.setEmail(i + "@mail.com");
            pos.add(po);
        }
        long s = Instant.now().getEpochSecond();
        int[] res = this.daoSupport.batchInsert(pos);
        long e = Instant.now().getEpochSecond();
        LOG.info("res is: {} ,take {} seconds", res, e - s);
    }

    @Test
    public void testDaoBatchUpdate() {
        List<TestPO> pos = new ArrayList<>();
        for (int i = 10; i < 37; i++) {
            TestPO po = new TestPO();
            po.setId(i);
            po.setPassword("eeee");
            po.setName("ffffff");
            pos.add(po);
        }
        int s = Instant.now().getNano();
        int[] res = this.daoSupport.batchUpdate(pos);
        int e = Instant.now().getNano();
        LOG.info("res is: {}, take {} ns", res, (e - s)/1000000);
    }

}
