package com.ibingbo.services;

import java.util.List;

import com.ibingbo.models.User;

public interface UserService {

	public User getUserById(String id);
	
	public List<User> getUserList();
}
