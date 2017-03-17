package com.ibingbo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.ibingbo.models.Account;
import com.ibingbo.models.DO.StudentDO;

public interface StudentMapper {

	@Select({
        "select",
        "id, name, password, create_time",
        "from student",
        "where id = #{id,jdbcType=INTEGER}"
    })
	@ResultMap("BaseResultMap")
	public StudentDO getStudentById(Integer id);
	
	public List<StudentDO> getStudents();
	
	public int insertStudent(StudentDO studentDO);
	
	public int updateStudent(StudentDO studentDO);
	
	public int deleteStudent(Map<String, Object> map);
}
