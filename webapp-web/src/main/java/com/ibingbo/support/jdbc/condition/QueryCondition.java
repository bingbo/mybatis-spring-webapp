package com.ibingbo.support.jdbc.condition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.tuple.Pair;

/**
 * QueryCondition
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public class QueryCondition implements Serializable, Cloneable {
    private List<Condition> conditions = new ArrayList<>();
    private OrderBy orderBy = null;
    private Limit limit = null;

    public QueryCondition add(Condition condition) {
        this.conditions.add(condition);
        return this;
    }

    public QueryCondition orderBy(String column) {
        this.orderBy = new OrderBy(Pair.of(column, "asc"));
        return this;
    }

    public QueryCondition orderByAsc(String column) {
        this.orderBy = new OrderBy(Pair.of(column, "asc"));
        return this;
    }

    public QueryCondition orderByDesc(String column) {
        this.orderBy = new OrderBy(Pair.of(column, "desc"));
        return this;
    }

    public QueryCondition limit(int limit) {
        this.limit = new Limit(limit);
        return this;
    }

    public QueryCondition limit(int offset, int limit) {
        this.limit = new Limit(offset, limit);
        return this;
    }

    public List<Condition> getConditions() {
        return this.conditions;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public Limit getLimit() {
        return limit;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public void setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setOrderBy(this.getOrderBy());
        queryCondition.setLimit(this.getLimit());
        queryCondition.setConditions(new ArrayList<>(this.getConditions()));
        return queryCondition;
    }
}
