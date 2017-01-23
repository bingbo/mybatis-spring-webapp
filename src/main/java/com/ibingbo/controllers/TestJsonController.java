package com.ibingbo.controllers;

import java.util.HashMap;
import java.util.Map;

import com.ibingbo.models.Response;
import com.ibingbo.util.AesUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@RequestMapping("/json")
@EnableWebMvc
public class TestJsonController {
	
	private static final Log logger= LogFactory.getLog(TestJsonController.class.getName());
	private final String KEY = "3d8be02c70665eaba6877a67ce3d49b2";
	private final String IV = "1234567890123456";

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

	@RequestMapping("/aes")
	@ResponseBody
	public Response getEncryptData(@CookieValue("bname") String bname, @CookieValue("bid") String bid) {

		Response response = new Response();
		try {
			System.out.println(bname);
			System.out.println(bid);
			String dname = AesUtil.cbcDecrypt(bname, KEY, IV);
			String did = AesUtil.cbcDecrypt(bid, KEY, IV);
			System.out.println(dname);
			System.out.println(did);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return response;
	}
}
