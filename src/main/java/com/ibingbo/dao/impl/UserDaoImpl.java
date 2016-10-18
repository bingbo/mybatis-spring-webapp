package com.ibingbo.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ibingbo.dao.UserDao;
import com.ibingbo.models.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	public User getUser(String userId) {
		// TODO Auto-generated method stub
		return (User)this.getSqlSession().selectOne("com.ibingbo.mappers.user.getUser",userId);
	}

}
