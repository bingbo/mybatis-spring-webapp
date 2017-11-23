package com.ibingbo.support.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.StatementCreatorUtils;

import com.ibingbo.support.jdbc.condition.QueryCondition;
import com.ibingbo.support.jdbc.po.AbstractPO;
import com.ibingbo.support.jdbc.po.MetaPOCache;
import com.ibingbo.support.jdbc.po.POClassInfo;
import com.ibingbo.support.jdbc.router.Router;
import com.ibingbo.support.jdbc.sqlgenerator.Generator;
import com.ibingbo.support.jdbc.sqlgenerator.SimpleSqlGenerator;

/**
 * DaoSupport
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public class DaoSupport extends JdbcTemplate {

    private Router router;
    private final Generator generator = new SimpleSqlGenerator();
    private final int DEFAULT_BATCH_SIZE = -1;

    public <T extends AbstractPO> List<T> query(Class<T> poClass, QueryCondition condition) {
        POClassInfo<T> poClassInfo = MetaPOCache.getPOClassInfo(poClass);
        Pair<String, Object[]> sqlParamsPair = generator.selectSql(poClassInfo, condition);
        RowMapper<T> rowMapper = new BeanPropertyRowMapper<>(poClass);
        if (sqlParamsPair.getRight().length > 0) {
            return super.query(sqlParamsPair.getLeft(), sqlParamsPair.getRight(), rowMapper);
        }
        return super.query(sqlParamsPair.getLeft(), rowMapper);
    }

    public <T extends AbstractPO> int insert(T po) {
        POClassInfo<T> poClassInfo = (POClassInfo<T>) MetaPOCache.getPOClassInfo(po.getClass());
        Pair<String, Object[]> sqlParamsPair = this.generator.insertSql(poClassInfo, po);
        return super.update(sqlParamsPair.getLeft(), sqlParamsPair.getRight());
    }

    public <T extends AbstractPO> int[] batchInsert(List<T> pos) {
        POClassInfo<T> poClassInfo = (POClassInfo<T>) MetaPOCache.getPOClassInfo(pos.iterator().next().getClass());
        List<Pair<String, Object[]>> list = new ArrayList<>();
        final List<Object[]> params = new ArrayList<>();
        for (T po : pos) {
            Pair<String, Object[]> sqlParamsPair = this.generator.insertSql(poClassInfo, po);
            list.add(sqlParamsPair);
            params.add(sqlParamsPair.getRight());
        }
        int[] result = super.batchUpdate(list.iterator().next().getLeft(), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Object[] param = params.get(i);
                for (int j = 0; j < param.length; j++) {
                    StatementCreatorUtils.setParameterValue(ps, j + 1, SqlTypeValue.TYPE_UNKNOWN, param[j]);
                }
            }

            @Override
            public int getBatchSize() {
                return params.size();
            }
        });
        return result;
    }

    public <T extends AbstractPO> int update(T po, QueryCondition condition) {
        POClassInfo<T> poClassInfo = (POClassInfo<T>) MetaPOCache.getPOClassInfo(po.getClass());
        Pair<String, Object[]> sqlParamsPair = this.generator.updateSql(poClassInfo, po, condition);
        return super.update(sqlParamsPair.getLeft(), sqlParamsPair.getRight());
    }

    public <T extends AbstractPO> int update(T po) {
        return this.update(po, null);
    }

    public <T extends AbstractPO> int[] batchUpdate(List<T> pos, QueryCondition condition) {
        POClassInfo<T> poClassInfo = (POClassInfo<T>) MetaPOCache.getPOClassInfo(pos.iterator().next().getClass());
        List<Pair<String, Object[]>> list = new ArrayList<>();
        final List<Object[]> params = new ArrayList<>();
        for (T po : pos) {
            Pair<String, Object[]> sqlParamsPair = this.generator.updateSql(poClassInfo, po, condition);
            list.add(sqlParamsPair);
            params.add(sqlParamsPair.getRight());
        }
        int[] result = super.batchUpdate(list.iterator().next().getLeft(), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Object[] param = params.get(i);
                for (int j = 0; j < param.length; j++) {
                    StatementCreatorUtils.setParameterValue(ps, j + 1, SqlTypeValue.TYPE_UNKNOWN, param[j]);
                }
            }

            @Override
            public int getBatchSize() {
                return params.size();
            }
        });
        return result;
    }

    public <T extends AbstractPO> int[] batchUpdate(List<T> pos) {
        return this.batchUpdate(pos, null);
    }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    public Generator getGenerator() {
        return generator;
    }

}
