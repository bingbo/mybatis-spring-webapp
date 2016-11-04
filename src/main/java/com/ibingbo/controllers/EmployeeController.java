package com.ibingbo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ibingbo.services.EmployeeService;

/**
 * 手动注册controller,需要实现Controller接口
 * @author zhangbingbing
 *
 */
public class EmployeeController implements Controller {

	//会依赖注入
	private EmployeeService employeeService;
	
	public EmployeeService getEmployeeService(){
		return employeeService;
	}
	public void setEmployeeService(EmployeeService service){
		this.employeeService = service;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView mv=new ModelAndView("employeeList");
		mv.addObject("employeeList", this.employeeService.getEmployeeList());
		return mv;
	}

}
