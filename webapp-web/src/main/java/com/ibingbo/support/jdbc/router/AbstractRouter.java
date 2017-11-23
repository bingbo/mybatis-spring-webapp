package com.ibingbo.support.jdbc.router;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.support.ApplicationObjectSupport;

/**
 * AbstractRouter
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public abstract class AbstractRouter extends ApplicationObjectSupport implements Router{
    protected static Map<String,DataSource> dataSourceMap;

    @Override
    public DataSource getDataSource(Object key) {
        String k = key.toString();
        return dataSourceMap.get(k);
    }

    public int getDataSourceCount() {
        return dataSourceMap.size();
    }

    public static Map<String, DataSource> getDataSourceMap() {
        return dataSourceMap;
    }

    public static void setDataSourceMap(Map<String, DataSource> dataSourceMap) {
        AbstractRouter.dataSourceMap = dataSourceMap;
    }
}
