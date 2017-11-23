package com.ibingbo.support.jdbc.condition;

import java.io.Serializable;

/**
 * GroupBy
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public class GroupBy implements Expression,Serializable{
    private String[] groupBys = null;

    public GroupBy(String... groupBys) {
        this.groupBys = groupBys;
    }

    @Override
    public String toSqlString() {
        StringBuffer sb = new StringBuffer("group by ");
        for (String groupBy : groupBys) {
            sb.append(groupBy).append(", ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    @Override
    public Object[] toParameterArray() {
        return null;
    }
}
