package com.ibingbo.annotation;

import java.lang.annotation.*;

/**
 * Created by bing on 2016/12/29.
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String remark() default "";
}
