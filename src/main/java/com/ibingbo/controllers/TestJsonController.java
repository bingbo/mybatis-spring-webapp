package com.ibingbo.controllers;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.alipmc.api.ProcessInstanceService;
import com.alibaba.alipmc.api.model.bpm.ProcessInstance;
import com.ibingbo.models.Response;
import com.ibingbo.util.AESForNodejs;
import com.ibingbo.util.AesUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private ProcessInstanceService processInstanceService;

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
//			String dname = AESForNodejs.decrypt(bname, "1234fghjnmlkiuhA");
//			String did = AESForNodejs.decrypt(bid, "1234fghjnmlkiuhA");
			System.out.println(dname);
			System.out.println(did);
			response.setData(dname + "|" + did);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return response;
	}

	@RequestMapping("/encrypt")
	@ResponseBody
	public Response getEncryptData2(@CookieValue("bname") String bname, @CookieValue("bid") String bid) {

		Response response = new Response();
		try {
			System.out.println(bname);
			System.out.println(bid);
			String dname = AESForNodejs.decrypt(bname, "1234fghjnmlkiuhA");
			String did = AESForNodejs.decrypt(bid, "1234fghjnmlkiuhA");
			System.out.println(dname);
			System.out.println(did);
			response.setData(dname + "|" + did);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return response;
	}

	@RequestMapping("/bpms")
	@ResponseBody
	public Response getBpms() {

		Response response = new Response();
		try {
			Map<String, String> initData = new HashMap<String, String>();
			initData.put("name", "天眼");
			initData.put("intent", "人群分析");
			initData.put("tags", "年龄/性别/学历");
			initData.put("humanMark", "潜在购车者");
			initData.put("service", "ODPS");
			initData.put("inputData", "input data");
			initData.put("outputData", "output data");
			initData.put("runCycle", "20天");
			initData.put("updateFrequency", "1天");
			initData.put("expiryDate", "20天");
			ProcessInstance instance = this.processInstanceService.startProcessInstance("insight-test-process","天眼权限申请流程hsf","055690", initData,"1$insight-service-app-key$hello1234");
			System.out.println(instance.toString());
			response.setData(instance);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return response;
	}
}
