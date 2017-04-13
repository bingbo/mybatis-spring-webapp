package com.ibingbo.boot.dao.impl;

import com.ibingbo.boot.bean.User;
import com.ibingbo.boot.dao.UserDao;
import com.ibingbo.boot.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bing on 2017/4/12.
 */
@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private UserMapper mapper;
    @Autowired
    private SqlSession sqlSession;

    public List<User> getUsers() {
        return this.mapper.getUsers();
    }

    public User getUserById(int id) {
        return this.sqlSession.selectOne("findUserById", id);
    }
}
