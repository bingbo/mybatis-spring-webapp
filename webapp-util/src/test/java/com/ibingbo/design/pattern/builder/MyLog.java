package com.ibingbo.design.pattern.builder;

import java.util.Date;

/**
 * @author zhangbingbing
 * @title MyLog
 * @date 17/10/9
 */
public class MyLog {
    private Integer id;
    private String key;
    private String value;
    private Integer type;
    private Integer level;
    private Date time;

    public MyLog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
