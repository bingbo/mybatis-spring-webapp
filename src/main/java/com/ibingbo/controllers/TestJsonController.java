package com.ibingbo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@RequestMapping("/json")
@EnableWebMvc
public class TestJsonController {

	/*@RequestMapping(path="/get",produces = "application/json",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> getJson(){
		
		Map<String, String> res = new HashMap<String, String>();
		res.put("name", "bill");
		res.put("age", "30");
		res.put("email", "bill@126.com");
		return res;
	}*/
	
	@RequestMapping("/out")
	@ResponseBody
	public String outJson(){
		return "{\"name\":\"bill\"}";
	}
}
