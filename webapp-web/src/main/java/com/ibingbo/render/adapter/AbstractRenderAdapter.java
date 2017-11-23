package com.ibingbo.render.adapter;

import org.springframework.core.GenericTypeResolver;

import com.ibingbo.render.model.AbstractModel;

/**
 * AbstractRenderAdapter
 *
 * @author zhangbingbing
 * @date 17/11/23
 */
public abstract class AbstractRenderAdapter<M extends AbstractModel> implements RenderAdapter<M> {
    @Override
    public boolean supportsModelType(Class<M> modelType) {
        Class<?> typeArg = GenericTypeResolver.resolveTypeArgument(this.getClass(), RenderAdapter.class);
        return typeArg == null || typeArg.isAssignableFrom(modelType);
    }
}
