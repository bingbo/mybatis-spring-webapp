package com.ibingbo.design.pattern.abstract_factory;

/**
 * Created by bing on 17/5/31.
 */
public class OracleUserTable implements IUserTable{
    public void insert(User user) {
        System.out.println("oracle insert user");
    }

    public User getUser(int id) {
        System.out.println("oracle get user");
        return null;
    }
}
