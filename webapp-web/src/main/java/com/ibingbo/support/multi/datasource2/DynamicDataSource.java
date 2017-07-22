package com.ibingbo.support.multi.datasource2;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by bing on 17/7/22.
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceKey = DynamicDataSourceHolder.getDataSource();
        return dataSourceKey;
    }
}
