package com.ibingbo.support.jdbc.condition;

import org.yecht.Data;

/**
 * BetweenCondition
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public class BetweenCondition extends AbstractCondition{
    private Object value1;
    private Object value2;

    public BetweenCondition(String column, Object value1, Object value2) {
        super(column);
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public String toSqlString() {
        StringBuffer sb = new StringBuffer("`");
        sb.append(this.getColumn()).append("` between ? and ?");
        return sb.toString();
    }

    @Override
    public Object[] toParameterArray() {
        return new Object[] {value1, value2};
    }

    @Override
    public int getPriority() {
        return 20;
    }
}
