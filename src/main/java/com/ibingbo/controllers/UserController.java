package com.ibingbo.controllers;

import com.ibingbo.models.User;
import com.ibingbo.services.UserService;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	private UserService userService;

	@RequestMapping("/get")
	public String getUser(@RequestParam(value = "id", required = false, defaultValue = "1") String id, Model model) {
		User user=this.userService.getUserById(id);
		model.addAttribute("user", user);
		return "user";
	}
	
	@RequestMapping("/list")
	public String getUserList(Model model){
		List<User> users = this.userService.getUserList();
		LOGGER.info(users.toString());
		model.addAttribute("users", users);
		LOGGER.debug("user_log_test_{}_{}_{}","slf4j","user","list");
		return "list";
	}
}
