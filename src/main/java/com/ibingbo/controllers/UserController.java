package com.ibingbo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibingbo.models.User;
import com.ibingbo.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/get")
	public String getUser(@RequestParam(value = "id", required = false, defaultValue = "1") String id, Model model) {
		User user=this.userService.getUserById(id);
		model.addAttribute("user", user);
		return "user";
	}
}
