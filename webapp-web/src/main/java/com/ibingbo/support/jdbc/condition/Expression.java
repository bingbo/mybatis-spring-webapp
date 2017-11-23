package com.ibingbo.support.jdbc.condition;

import java.io.Serializable;

/**
 * Expression
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public interface Expression extends Serializable{
    String toSqlString();

    Object[] toParameterArray();
}
