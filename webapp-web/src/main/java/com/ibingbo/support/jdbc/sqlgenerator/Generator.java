package com.ibingbo.support.jdbc.sqlgenerator;

import org.apache.commons.lang3.tuple.Pair;

import com.ibingbo.support.jdbc.condition.QueryCondition;
import com.ibingbo.support.jdbc.po.AbstractPO;
import com.ibingbo.support.jdbc.po.POClassInfo;

/**
 * Generator
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public interface Generator {

    <T extends AbstractPO> Pair<String, Object[]> selectSql(POClassInfo<T> poClassInfo, QueryCondition condition);

    <T extends AbstractPO> Pair<String, Object[]> updateSql(POClassInfo<T> poClassInfo, T po, QueryCondition condition);

    <T extends AbstractPO> Pair<String,Object[]> insertSql(POClassInfo<T> poClassInfo, T po);
}
