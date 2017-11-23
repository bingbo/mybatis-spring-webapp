package com.ibingbo.support.jdbc.condition;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AbstractCondition
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public abstract class AbstractCondition implements Condition{

    protected static final Logger LOG = LoggerFactory.getLogger(AbstractCondition.class);
    private String column;

    public AbstractCondition(String column) {
        this.column = column;
    }

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public String getColumn() {
        return this.column;
    }


    @Override
    public int compareTo(Condition o) {
        if (this.getPriority() > o.getPriority()) {
            return 1;
        } else if (this.getPriority() < o.getPriority()) {
            return -1;
        } else {
            return this.getColumn().compareTo(o.getColumn());
        }
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }
}
