package com.ibingbo.support.jdbc.po;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * AbstractPO
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public abstract class AbstractPO implements Serializable, Cloneable {
    private static final long serialVersionUID = -1L;

    public abstract Long getPrimaryKey();

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }
}
