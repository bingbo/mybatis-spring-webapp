package com.ibingbo.support.jdbc.condition;

/**
 * Expr
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public class Expr {

    public static Condition eq(String column, Object value) {
        return new BasicCondition(column, " = ", value);
    }

    public static Condition ne(String column, Object value) {
        return new BasicCondition(column, " != ", value);
    }

    public static Condition gt(String column, Object value) {
        return new BasicCondition(column, " > ", value);
    }

    public static Condition ge(String column, Object value) {
        return new BasicCondition(column, " >= ", value);
    }

    public static Condition lt(String column, Object value) {
        return new BasicCondition(column, " < ", value);
    }

    public static Condition le(String column, Object value) {
        return new BasicCondition(column, " <= ", value);
    }

    public static Condition between(String column, Object value1, Object value2) {
        return new BetweenCondition(column, value1, value2);
    }

    public static Condition in(String column, Object... values) {
        return new InCondition(column, values);
    }
}
