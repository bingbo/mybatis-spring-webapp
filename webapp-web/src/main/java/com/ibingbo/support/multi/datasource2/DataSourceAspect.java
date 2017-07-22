package com.ibingbo.support.multi.datasource2;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bing on 17/7/22.
 */
public class DataSourceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);

    public void before(JoinPoint point) {
        LOGGER.info("...............pass DataSourceAspect ..........");
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?>[] classes = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature)point.getSignature()).getMethod().getParameterTypes();

        try {
            Method methodRef = classes[0].getMethod(method, parameterTypes);
            if (methodRef != null && methodRef.isAnnotationPresent(DataSource.class)) {
                DataSource dataSource = methodRef.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.putDataSource(dataSource.value());
                LOGGER.info("data source is {} .........", dataSource.value());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
