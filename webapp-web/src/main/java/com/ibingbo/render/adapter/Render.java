package com.ibingbo.render.adapter;

import java.util.List;
import java.util.Map;

import com.ibingbo.render.model.AbstractModel;

/**
 * Render
 *
 * @author zhangbingbing
 * @date 17/11/23
 */
public interface Render {

    <M extends AbstractModel> Map<Long, M> buildModel(Class<M> rootModelClass, Class<?>... modelClass);

    <M extends AbstractModel> Map<Long, M> rendRootModel(Class<M> rootModelClass, List<Long> ids, Class<?>...
            modelClass);

}
