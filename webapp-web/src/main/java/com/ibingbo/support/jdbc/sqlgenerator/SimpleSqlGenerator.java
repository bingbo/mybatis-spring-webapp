package com.ibingbo.support.jdbc.sqlgenerator;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibingbo.support.jdbc.condition.Condition;
import com.ibingbo.support.jdbc.condition.Expr;
import com.ibingbo.support.jdbc.condition.Limit;
import com.ibingbo.support.jdbc.condition.OrderBy;
import com.ibingbo.support.jdbc.condition.QueryCondition;
import com.ibingbo.support.jdbc.po.AbstractPO;
import com.ibingbo.support.jdbc.po.POClassInfo;

/**
 * SimpleSqlGenerator
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public class SimpleSqlGenerator implements Generator {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleSqlGenerator.class);

    @Override
    public <T extends AbstractPO> Pair<String, Object[]> selectSql(POClassInfo<T> poClassInfo,
                                                                   QueryCondition condition) {
        Object[] params = new Object[0];
        StringBuffer sqlBuffer = new StringBuffer("select ");
        sqlBuffer.append(poClassInfo.getBasicColumnStr()).append(" from ").append(poClassInfo.getTableName());
        String sql = sqlBuffer.toString();
        StringBuffer sqlBufferSuffix = new StringBuffer();
        if (condition != null) {
            List<Condition> cons = condition.getConditions();
            if (cons.size() > 0) {
                sqlBuffer.append(" where ");
                for (Condition con : condition.getConditions()) {
                    sqlBuffer.append(con.toSqlString()).append(" and ");
                    params = ArrayUtils.addAll(params, con.toParameterArray());
                }
            }

            OrderBy orderBy = condition.getOrderBy();
            if (orderBy != null) {
                sqlBufferSuffix.append(" ").append(orderBy.toSqlString());
                params = ArrayUtils.addAll(params, orderBy.toParameterArray());
            }

            Limit limit = condition.getLimit();
            if (limit != null) {
                sqlBufferSuffix.append(" ").append(limit.toSqlString());
                params = ArrayUtils.addAll(params, limit.toParameterArray());
            }
            sql = sqlBuffer.substring(0, sqlBuffer.length() - 5) + sqlBufferSuffix.toString();
        }
        LOG.info("sql: {}, params: {}", sql, params);
        return Pair.of(sql, params);
    }

    @Override
    public <T extends AbstractPO> Pair<String, Object[]> updateSql(POClassInfo<T> poClassInfo, T po, QueryCondition
            condition) {
        StringBuffer sb = new StringBuffer("update ").append(poClassInfo.getTableName()).append(" set");
        Object[] params = new Object[0];
        Map<String, Field> fieldName2FieldMap = poClassInfo.getFieldName2FieldMap();
        for (Map.Entry<String, Field> entry : fieldName2FieldMap.entrySet()) {
            String fieldName = entry.getKey();
            Field field = entry.getValue();
            Object value = null;
            try {
                value = field.get(po);
            } catch (IllegalAccessException e) {
                LOG.warn("get value of field {} in {} fail", fieldName, po);
            }
            if (value != null) {
                sb.append("`").append(fieldName).append("`=?,");
                params = ArrayUtils.addAll(params, value);
            }
        }
        if (condition == null) {
            condition = new QueryCondition();
        }
        StringBuffer sqlWhereBuffer = new StringBuffer(" where ");
        condition.add(Expr.eq(poClassInfo.getPrimaryKeys().iterator().next(), po.getPrimaryKey()));
        for (Condition con : condition.getConditions()) {
            sqlWhereBuffer.append(con.toSqlString());
            params = ArrayUtils.addAll(params, con.toParameterArray());
        }
        String sql = sb.substring(0, sb.length() - 1) + sqlWhereBuffer.toString();
        LOG.info("sql: {}, params: {}", sql, params);
        return Pair.of(sql, params);
    }

    @Override
    public <T extends AbstractPO> Pair<String, Object[]> insertSql(POClassInfo<T> poClassInfo, T po) {
        Object[] params = new Object[0];
        StringBuffer sb = new StringBuffer("insert into ").append(poClassInfo.getTableName()).append("(");
        StringBuffer sqlValueSb = new StringBuffer(") values (");
        Map<String, Field> fieldName2FieldMap = poClassInfo.getFieldName2FieldMap();
        for (Map.Entry<String, Field> entry : fieldName2FieldMap.entrySet()) {
            String fieldName = entry.getKey();
            Field field = entry.getValue();
            Object value = null;
            try {
                value = field.get(po);
            } catch (IllegalAccessException e) {
                LOG.warn("get value of field {} in {} fail", fieldName, po);
            }
            if (value != null) {
                sb.append("`").append(fieldName).append("`,");
                sqlValueSb.append("?,");
                params = ArrayUtils.addAll(params, value);
            }
        }
        String sql = sb.substring(0, sb.length() - 1) + sqlValueSb.substring(0, sqlValueSb.length() - 1) + ")";
        LOG.info("sql: {}, params: {}", sql, params);
        return Pair.of(sql, params);
    }
}
