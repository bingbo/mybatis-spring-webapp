package com.ibingbo.design.pattern.abstract_factory;

/**
 * Created by bing on 17/5/31.
 */
public interface IUserTable {

    void insert(User user);

    User getUser(int id);
}
