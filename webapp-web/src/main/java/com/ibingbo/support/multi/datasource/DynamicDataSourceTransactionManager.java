package com.ibingbo.support.multi.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import com.ibingbo.support.multi.datasource.DataSourceHolder;

/**
 * Created by bing on 17/7/22.
 */
public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        boolean readOnly = definition.isReadOnly();
        if (readOnly) {
            DataSourceHolder.setSlave();
            logger.info("use slave datasource .....");
        } else {
            DataSourceHolder.setMaster();
            logger.info("use master datasource .....");
        }
        super.doBegin(transaction, definition);
    }

    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        DataSourceHolder.clearDataSource();
    }
}
