package com.ibingbo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@RequestMapping("/json")
@EnableWebMvc
public class TestJsonController {
	
	private static final Log logger= LogFactory.getLog(TestJsonController.class.getName());

	@RequestMapping("/get")
	@ResponseBody
	public Map<String, String> getJson(){
		Map<String, String> res = new HashMap<String, String>();
		res.put("name", "bill");
		res.put("age", "30");
		res.put("email", "bill@126.com");
		logger.debug("test log");
		return res;
	}
	
	@RequestMapping("/out")
	@ResponseBody
	public String outJson(){
		String result = "{\"name\":\"bill\"}";
		logger.debug(result);
		return result;
	}
	
	@RequestMapping("test")
	@ResponseBody
	public String test(){
		return "hello";
		
	}
}
