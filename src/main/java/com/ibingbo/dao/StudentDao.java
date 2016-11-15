package com.ibingbo.dao;

import java.util.List;
import java.util.Map;

import com.ibingbo.models.DO.StudentDO;

public interface StudentDao {

	
	public List<StudentDO> getStudents();
	public StudentDO getStudentById(Integer id);
	public int insertStudent(StudentDO studentDO);
	public int updateStudent(StudentDO studentDO);
	public int deleteStudent(Map<String, Object> map);
}
