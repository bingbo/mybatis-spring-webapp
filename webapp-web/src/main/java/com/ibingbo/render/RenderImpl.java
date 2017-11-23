package com.ibingbo.render;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.ibingbo.render.adapter.Render;
import com.ibingbo.render.adapter.RenderAdapter;
import com.ibingbo.render.model.AbstractModel;

/**
 * RenderImpl
 *
 * @author zhangbingbing
 * @date 17/11/23
 */
public class RenderImpl implements Render,ApplicationContextAware {
    private static final List<RenderAdapter> adapters = new ArrayList<>();
    private static final Map<Class<?>, RenderAdapter> modelType2AdapterMap = new ConcurrentHashMap<>();

    private ApplicationContext applicationContext;

    @Override
    public <M extends AbstractModel> Map<Long, M> buildModel(Class<M> rootModelClass, Class<?>[] modelClass) {
        return this.getRenderAdapter(rootModelClass).buildModel(rootModelClass, modelClass);
    }

    @Override
    public <M extends AbstractModel> Map<Long, M> rendRootModel(Class<M> rootModelClass, List<Long> ids,
                                                                Class<?>[] modelClass) {
        return this.getRenderAdapter(rootModelClass).rendRootModel(rootModelClass, ids, modelClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        Map<String,RenderAdapter> adapterMap = this.applicationContext.getBeansOfType(RenderAdapter.class);
        adapters.addAll(adapterMap.values());
    }

    protected RenderAdapter getRenderAdapter(Class modelClass) {
        if (!modelType2AdapterMap.containsKey(modelClass)) {
            for (RenderAdapter adapter : adapters) {
                if (adapter.supportsModelType(modelClass)) {
                    modelType2AdapterMap.putIfAbsent(modelClass, adapter);
                }
            }
        }
        return modelType2AdapterMap.get(modelClass);
    }
}
