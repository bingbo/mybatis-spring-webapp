package com.ibingbo.design.pattern.abstract_factory;

/**
 * Created by bing on 17/5/31.
 */
public class SqlServerUserTable implements IUserTable
{
    public void insert(User user) {
        System.out.println("sql server insert user");
    }

    public User getUser(int id) {
        System.out.println("sql server get user");
        return null;
    }
}
