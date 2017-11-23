package com.ibingbo.support.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * DBColumn
 *
 * @author zhangbingbing
 * @date 17/11/10
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBColumn {
    String value() default "";

    boolean isPrimaryKey() default false;

    boolean isNull() default false;

    boolean isAutoIncrement() default false;
}
