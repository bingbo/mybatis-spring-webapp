package com.ibingbo.dao;

import java.util.List;

import com.ibingbo.models.User;

public interface UserDao {

	public User getUser(String userId);
	
	public List<User> getUserList();
}
