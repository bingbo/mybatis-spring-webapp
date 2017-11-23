package com.ibingbo.test.handler;

import org.springframework.core.GenericTypeResolver;

/**
 * AbstractModelHandler
 *
 * @author zhangbingbing
 * @date 17/11/23
 */
public abstract class AbstractModelHandler<M extends Model> implements ModelHandler<M> {
    @Override
    public boolean supportsModelType(Class<M> modelType) {
        Class<?> typeArg = GenericTypeResolver.resolveTypeArgument(this.getClass(), ModelHandler.class);
        /*
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] paramType = type.getActualTypeArguments();
        Class cls = (Class) paramType[0];
        return cls.isAssignableFrom(modelType);
        */
        return (typeArg == null || typeArg.isAssignableFrom(modelType));
    }

    @Override
    public Class<?> getSupportsModelType() {
        Class<?> typeArg = GenericTypeResolver.resolveTypeArgument(this.getClass(), ModelHandler.class);
        return typeArg;
    }

}
