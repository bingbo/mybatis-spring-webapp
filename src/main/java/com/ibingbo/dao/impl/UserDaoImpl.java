package com.ibingbo.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ibingbo.dao.UserDao;
import com.ibingbo.models.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	public User getUser(String userId) {
		// TODO Auto-generated method stub
		return (User)this.getSqlSession().selectOne("com.ibingbo.mappers.user.getUser",userId);
	}
	
	public List<User> getUserList(){
		return (List)this.getSqlSession().selectList("com.ibingbo.mappers.user.getUserList");
		
	}

}
