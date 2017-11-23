package com.ibingbo.render.adapter;

import java.util.List;
import java.util.Map;

import com.ibingbo.render.model.AbstractModel;
import com.ibingbo.render.model.StudentModel;

/**
 * StudentRenderAdapter
 *
 * @author zhangbingbing
 * @date 17/11/23
 */
public class StudentRenderAdapter extends AbstractRenderAdapter<StudentModel> {

    @Override
    public <M extends AbstractModel> Map<Long, M> buildModel(Class<M> rootModelClass, Class<?>[] modelClass) {
        return null;
    }

    @Override
    public <M extends AbstractModel> Map<Long, M> rendRootModel(Class<M> rootModelClass, List<Long> ids,
                                                                Class<?>[] modelClass) {
        return null;
    }
}
