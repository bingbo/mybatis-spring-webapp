package com.ibingbo.test.handler;

import java.lang.reflect.ParameterizedType;

/**
 * ModelHandler
 *
 * @author zhangbingbing
 * @date 17/11/23
 */
public interface ModelHandler<M extends Model>{
    void handle(M model);
    boolean supportsModelType(Class<M> modelType);

    Class<?> getSupportsModelType();
}
