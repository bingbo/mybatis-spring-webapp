package com.ibingbo.support.jdbc.condition;

import java.io.Serializable;

/**
 * Limit
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public class Limit implements Expression, Serializable {
    private Integer[] limit = null;

    public Limit(Integer limit) {
        this.limit = new Integer[] {limit};
    }

    public Limit(Integer offset, Integer limit) {
        this.limit = new Integer[] {offset, limit};
    }

    @Override
    public String toSqlString() {
        StringBuffer sb = new StringBuffer("limit ");
        if (this.limit.length == 1) {
            sb.append("?");
        } else {
            sb.append("?, ?");
        }
        return sb.toString();
    }

    @Override
    public Object[] toParameterArray() {
        return this.limit;
    }
}
