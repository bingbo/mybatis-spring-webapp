package com.ibingbo.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibingbo.dao.AccountDao;
import com.ibingbo.dao.StudentDao;
import com.ibingbo.mapper.AccountMapper;
import com.ibingbo.mapper.StudentMapper;
import com.ibingbo.models.Account;
import com.ibingbo.models.DO.StudentDO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private StudentMapper studentMapper;
	

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<StudentDO> getStudents() {
		// TODO Auto-generated method stub
		return this.studentMapper.getStudents();
	}

	@Override
	public StudentDO getStudentById(Integer id) {
		// TODO Auto-generated method stub
		return this.studentMapper.getStudentById(id);
	}

	@Override
	public int insertStudent(StudentDO studentDO) {
		// TODO Auto-generated method stub
		return this.studentMapper.insertStudent(studentDO);
	}

	@Override
	public int updateStudent(StudentDO studentDO) {
		// TODO Auto-generated method stub
		return this.studentMapper.updateStudent(studentDO);
	}

	@Override
	public int deleteStudent(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.studentMapper.deleteStudent(map);
	}
	
	

}
