package com.ibingbo.boot.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by bing on 2017/4/11.
 */
@Component
public class UserBean {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getUserSize() {
        return this.jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
    }


}
