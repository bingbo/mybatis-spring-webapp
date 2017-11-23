package com.ibingbo.render.adapter;

import com.ibingbo.render.model.AbstractModel;
/**
 * RenderAdapter
 *
 * @author zhangbingbing
 * @date 17/11/23
 */
public interface RenderAdapter<M extends AbstractModel> extends Render {
    boolean supportsModelType(Class<M> modelType);
}
