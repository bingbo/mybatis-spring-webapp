package com.ibingbo.design.pattern.builder;

import java.util.Date;

/**
 * MyLogBuilder
 *
 * @author zhangbingbing
 * @date 2017/9/27
 */
public class MyLogBuilder extends AbstractBuilder<MyLog> {

    public MyLogBuilder() {
        super(new MyLog());
    }

    public MyLogBuilder id(Integer id) {
        this.data.setId(id);
        return this;
    }

    public MyLogBuilder key(String key) {
        this.data.setKey(key);
        return this;
    }

    public MyLogBuilder value(String value) {
        this.data.setValue(value);
        return this;
    }

    public MyLogBuilder level(Integer level) {
        this.data.setLevel(level);
        return this;
    }

    public MyLogBuilder type(Integer type) {
        this.data.setType(type);
        return this;
    }

    public MyLogBuilder time(Date time) {
        this.data.setTime(time);
        return this;
    }

}
