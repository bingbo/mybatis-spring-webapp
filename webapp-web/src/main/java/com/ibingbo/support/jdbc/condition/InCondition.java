package com.ibingbo.support.jdbc.condition;

import java.util.Collection;

/**
 * InCondition
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public class InCondition extends AbstractCondition {
    private Object[] values;

    public InCondition(String column, Object... values) {
        super(column);
        if (values.length == 1 && values[0] instanceof Collection) {
            this.values = ((Collection) values[0]).toArray();
        } else if (values.length == 1 && values[0] instanceof Object[]) {
            this.values = (Object[]) values[0];
        } else {
            this.values = values;
        }
    }

    @Override
    public String toSqlString() {
        StringBuffer sb = new StringBuffer("`").append(this.getColumn()).append("` in (");
        int length = this.values.length;
        for (int i = 0; i < length; i++) {
            sb.append("?,");
        }
        return sb.substring(0, sb.length() - 1) + ")";
    }

    @Override
    public Object[] toParameterArray() {
        return this.values;
    }

    @Override
    public int getPriority() {
        return 30;
    }
}
