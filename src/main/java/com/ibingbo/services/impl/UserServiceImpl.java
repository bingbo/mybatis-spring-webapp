package com.ibingbo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibingbo.dao.UserDao;
import com.ibingbo.models.User;
import com.ibingbo.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return this.userDao.getUser(id);
	}

}
