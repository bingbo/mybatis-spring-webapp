package com.ibingbo.support.multi.datasource;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.ibingbo.support.multi.datasource.DataSourceHolder;

/**
 * Created by bing on 17/7/22.
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private AtomicInteger counter = new AtomicInteger();

    private DataSource master;
    private List<DataSource> slaves;



    @Override
    protected Object determineCurrentLookupKey() {
        return null;
    }

    @Override
    public void afterPropertiesSet() {
        //super.afterPropertiesSet();
    }

    @Override
    protected DataSource determineTargetDataSource() {
        DataSource dataSource = null;
        if (DataSourceHolder.isMaster()) {
            dataSource = master;
        } else if (DataSourceHolder.isSlave()) {
            // 简单轮循
            int count = counter.getAndIncrement();
            if (count > 100000) {
                counter.set(0);
            }
            int index = count % slaves.size();
            dataSource = slaves.get(index);
        } else {
            dataSource = master;
        }
        return dataSource;
    }

    public DataSource getMaster() {
        return master;
    }

    public void setMaster(DataSource master) {
        this.master = master;
    }

    public List<DataSource> getSlaves() {
        return slaves;
    }

    public void setSlaves(List<DataSource> slaves) {
        this.slaves = slaves;
    }
}
