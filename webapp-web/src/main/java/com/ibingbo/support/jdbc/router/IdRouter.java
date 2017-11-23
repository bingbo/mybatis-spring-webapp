package com.ibingbo.support.jdbc.router;

import javax.sql.DataSource;

/**
 * IdRouter
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public class IdRouter extends AbstractRouter {
    @Override
    public DataSource getDataSource(Object key) {
        Long id = Long.parseLong(key.toString());
        int count = this.getDataSourceCount();
        Long k = id % count;
        return super.getDataSource(k);
    }
}
