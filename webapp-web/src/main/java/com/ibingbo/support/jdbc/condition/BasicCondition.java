package com.ibingbo.support.jdbc.condition;

/**
 * BasicCondition
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public class BasicCondition extends AbstractCondition{

    private String operator;
    private Object value;

    public BasicCondition(String column, String operator, Object value) {
        super(column);
        this.operator = operator;
        this.value = value;
    }

    @Override
    public String toSqlString() {
        StringBuffer sb = new StringBuffer("`");
        sb.append(this.getColumn()).append("`").append(this.operator).append("?");
        return sb.toString();
    }

    @Override
    public Object[] toParameterArray() {
        return new Object[] {value};
    }
}
