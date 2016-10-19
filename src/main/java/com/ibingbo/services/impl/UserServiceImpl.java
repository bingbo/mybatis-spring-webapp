package com.ibingbo.services.impl;

import java.util.List;

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
	
	public List<User> getUserList(){
		return this.userDao.getUserList();
		
	}

}
