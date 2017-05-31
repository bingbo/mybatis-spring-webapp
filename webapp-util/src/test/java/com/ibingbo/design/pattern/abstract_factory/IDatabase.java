package com.ibingbo.design.pattern.abstract_factory;

/**
 * Created by bing on 17/5/31.
 */
public interface IDatabase {

    IUserTable createUser();

    IDepartmentTable createDepartment();

}
