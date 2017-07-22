package com.ibingbo.mapper;

import java.util.List;

import com.ibingbo.models.User;
import com.ibingbo.support.multi.datasource2.DataSource;

public interface UserMapper {

    @DataSource("slave")
    User getUser(Integer id);

    @DataSource("slave")
    List<User> getUserList();
}
