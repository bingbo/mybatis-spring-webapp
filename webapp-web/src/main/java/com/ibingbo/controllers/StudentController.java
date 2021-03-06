package com.ibingbo.controllers;

import java.util.HashMap;
import java.util.Map;

import com.ibingbo.annotation.Log;
import com.ibingbo.enums.impl.CustomExceptionEnums;
import com.ibingbo.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibingbo.models.VO.StudentVO;
import com.ibingbo.services.StudentService;

@Controller
@RequestMapping("/student")
@Scope("prototype")
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService service;
	
	@RequestMapping("/list")
	@ResponseBody
	@Log(remark = "list api")
	public Map<String, Object> list(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("errno", 0);
		map.put("errmsg", "success");
		try{
			map.put("data", this.service.getStudents());
			System.out.println("student_controller: " + this);
		}catch(Exception exception){
			map.put("errno", -1);
			map.put("errmsg", exception.getMessage());
		}
		return map;
	}
	
	@RequestMapping("/get/{id}")
	@ResponseBody
	@Log
	public Map<String, Object> get(@PathVariable("id") Integer id){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("errno", 0);
		map.put("errmsg", "success");
		try{
			map.put("data", this.service.getStudentById(id));
		}catch(Exception exception){
			map.put("errno", -1);
			map.put("errmsg", exception.getMessage());
		}
		return map;
	}
	
	@RequestMapping(path="/add/{name}/{password}", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(@PathVariable("name") String name, @PathVariable("password") String password){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("errno", 0);
		map.put("errmsg", "success");
		try{
			StudentVO studentVO=new StudentVO(null, name, password, null);
			map.put("data", this.service.addStudent(studentVO));
		}catch(Exception exception){
			map.put("errno", -1);
			map.put("errmsg", exception.getMessage());
		}
		return map;
	}
	
	@RequestMapping(path="/save/{id}/{name}/{password}",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(@PathVariable("id") Integer id, @PathVariable("name") String name, @PathVariable("password") String password){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("errno", 0);
		map.put("errmsg", "success");
		try{
			StudentVO studentVO=new StudentVO(id, name, password, null);
			map.put("data", this.service.updateStudent(studentVO));
		}catch(Exception exception){
			map.put("errno", -1);
			map.put("errmsg", exception.getMessage());
		}
		return map;
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	@Log(remark = "delete user")
	public Map<String, Object> delete(@PathVariable("id") Integer id){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("errno", 0);
		map.put("errmsg", "success");
		try {
			if (id == 0) {
				throw new CustomException(CustomExceptionEnums.NOTNULLOREMPTY);
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", id);
			map.put("data", this.service.deleteStudent(params));
		} catch (CustomException e) {
			map.put("errno", e.getCode());
			map.put("errmsg", e.getMessage());
		}catch(Exception exception){
			map.put("errno", -1);
			map.put("errmsg", exception.getMessage());
		}
		return map;
	}
}
