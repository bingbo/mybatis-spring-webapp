package com.ibingbo.support.jdbc.router;

import javax.sql.DataSource;

/**
 * Router
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
public interface Router {
    DataSource getDataSource(Object key);
}
