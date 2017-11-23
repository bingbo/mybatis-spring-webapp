package com.ibingbo.support.jdbc.condition;

/**
 * Condition
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public interface Condition extends Expression,Comparable<Condition> {

    int getPriority();

    String getColumn();
}
