package com.ibingbo.design.pattern.abstract_factory;

/**
 * Created by bing on 17/5/31.
 */
public class OracleDepartmentTable implements IDepartmentTable{
    public void insert(Department department) {
        System.out.println("oracle insert department");
    }

    public Department getDepartment(int id) {
        System.out.println("oracle get department");
        return null;
    }
}
