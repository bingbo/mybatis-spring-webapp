package com.ibingbo.design.pattern.abstract_factory;

/**
 * Created by bing on 17/5/31.
 */
public class SqlServerDepartmentTable implements IDepartmentTable{
    public void insert(Department department) {
        System.out.println("sql server insert department");
    }

    public Department getDepartment(int id) {
        System.out.println("sql server get department");
        return null;
    }
}
