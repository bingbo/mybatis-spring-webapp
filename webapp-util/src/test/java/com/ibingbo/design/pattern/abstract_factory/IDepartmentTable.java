package com.ibingbo.design.pattern.abstract_factory;

/**
 * Created by bing on 17/5/31.
 */
public interface IDepartmentTable {

    void insert(Department department);

    Department getDepartment(int id);
}
