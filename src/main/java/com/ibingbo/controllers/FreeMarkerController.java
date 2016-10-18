package com.ibingbo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/freemarker")
public class FreeMarkerController {

	@RequestMapping("/welcome")
	public String welcome(Model model){
		return "welcome";
	}
}
