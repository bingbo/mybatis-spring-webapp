package com.ibingbo.test;

import com.ibingbo.support.jdbc.annotation.DBColumn;
import com.ibingbo.support.jdbc.annotation.DataSource;
import com.ibingbo.support.jdbc.annotation.Table;
import com.ibingbo.support.jdbc.po.AbstractPO;

/**
 * TestPO
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
@Table("test.user")
@DataSource
public class TestPO extends AbstractPO {
    @DBColumn(value = "id", isPrimaryKey = true)
    private Integer id;
    private String name;
    private String password;
    private String email;

    @Override
    public Long getPrimaryKey() {
        return this.id.longValue();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
