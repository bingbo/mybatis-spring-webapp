package com.ibingbo.design.pattern.abstract_factory;

/**
 * Created by bing on 17/5/31.
 */
public class OracleDataBase implements IDatabase{
    public IUserTable createUser() {
        return new OracleUserTable();
    }

    public IDepartmentTable createDepartment() {
        return new OracleDepartmentTable();
    }
}
