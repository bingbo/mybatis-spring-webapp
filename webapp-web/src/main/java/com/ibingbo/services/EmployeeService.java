package com.ibingbo.services;

import java.util.ArrayList;
import java.util.List;

import com.ibingbo.models.Employee;

/**
 * 这里需要手动在xml里注册service bean
 * @author zhangbingbing
 *
 */
public class EmployeeService {

	private static List<Employee> employees;
	
	public EmployeeService(){
		employees=new ArrayList<Employee>();
		employees.add(new Employee("1", "bill", "zhang"));
		employees.add(new Employee("2", "bing", "zhang"));
		employees.add(new Employee("3", "bingbing", "zhang"));
	}
	
	public List<Employee> getEmployeeList(){
		return employees;
	}
}
