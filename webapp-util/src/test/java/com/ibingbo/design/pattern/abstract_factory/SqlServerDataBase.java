package com.ibingbo.design.pattern.abstract_factory;

/**
 * Created by bing on 17/5/31.
 */
public class SqlServerDataBase implements IDatabase{
    public IUserTable createUser() {
        return new SqlServerUserTable();
    }

    public IDepartmentTable createDepartment() {
        return new SqlServerDepartmentTable();
    }
}
