package com.ibingbo.design.pattern.abstract_factory;

/**
 * Created by bing on 17/5/31.
 */
public class Client {

    public static void main(String[] args) {
        IDatabase sqlDataBase = new SqlServerDataBase();
        IDepartmentTable departmentTable = sqlDataBase.createDepartment();
        IUserTable userTable = sqlDataBase.createUser();

        departmentTable.getDepartment(0);
        departmentTable.insert(null);
        userTable.getUser(0);
        userTable.insert(null);


        IDatabase oracleDataBase = new OracleDataBase();
        IDepartmentTable departmentTable1 = oracleDataBase.createDepartment();
        IUserTable userTable1 = oracleDataBase.createUser();
        departmentTable1.getDepartment(0);
        departmentTable1.insert(null);
        userTable1.getUser(0);
        userTable1.insert(null);
    }
}
