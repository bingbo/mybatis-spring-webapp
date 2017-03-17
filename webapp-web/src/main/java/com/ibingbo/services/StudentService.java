package com.ibingbo.services;

import java.util.List;
import java.util.Map;

import com.ibingbo.models.VO.StudentVO;

public interface StudentService {

	public List<StudentVO> getStudents();
	
	public StudentVO getStudentById(Integer id);
	
	public int addStudent(StudentVO studentVO);
	
	public int updateStudent(StudentVO studentVO);
	
	public int deleteStudent(Map<String, Object> map);
}
