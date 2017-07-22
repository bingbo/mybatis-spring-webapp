package com.ibingbo.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibingbo.dao.UserDao;
import com.ibingbo.mapper.UserMapper;
import com.ibingbo.models.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		return (User)this.getSqlSession().selectOne("com.ibingbo.mapper.UserMapper.getUser",userId);
	}
	
	@Override
	public List<User> getUserList(){
//		return (List)this.getSqlSession().selectList("com.ibingbo.mapper.UserMapper.getUserList");
		return this.userMapper.getUserList();
	}

}
