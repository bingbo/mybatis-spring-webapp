package com.ibingbo.support.jdbc.condition;

import java.io.Serializable;

import org.apache.commons.lang3.tuple.Pair;

/**
 * OrderBy
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public class OrderBy implements Expression,Serializable{
    private Pair<String, String>[] orderBys = null;

    public OrderBy(Pair<String, String>... orderBys) {
        this.orderBys = orderBys;
    }

    @Override
    public String toSqlString() {
        StringBuffer sb = new StringBuffer("order by ");
        for (Pair<String, String> orderBy : orderBys) {
            sb.append(orderBy.getLeft()).append(" ").append(orderBy.getRight()).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    @Override
    public Object[] toParameterArray() {
        return null;
    }
}
