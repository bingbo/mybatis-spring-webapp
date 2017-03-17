package com.ibingbo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibingbo.dao.StudentDao;
import com.ibingbo.models.DO.StudentDO;
import com.ibingbo.models.VO.StudentVO;
import com.ibingbo.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public List<StudentVO> getStudents() {
		// TODO Auto-generated method stub
		List<StudentDO> dos= this.studentDao.getStudents();
		List<StudentVO> vos=new ArrayList<StudentVO>();
		for(int i=0;i<dos.size();i++){
			StudentVO vo=new StudentVO(dos.get(i).getId(), dos.get(i).getName(), dos.get(i).getPassword(), dos.get(i).getCreate_time());
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public StudentVO getStudentById(Integer id) {
		// TODO Auto-generated method stub
		StudentDO do1=this.studentDao.getStudentById(id);
		StudentVO vo=new StudentVO(do1.getId(), do1.getName(), do1.getPassword(), do1.getCreate_time());
		return vo;
	}

	@Override
	public int addStudent(StudentVO studentVO) {
		// TODO Auto-generated method stub
		StudentDO studentDO=new StudentDO(null, studentVO.getName(), studentVO.getPassword(), null);
		return this.studentDao.insertStudent(studentDO);
	}

	@Override
	public int updateStudent(StudentVO studentVO) {
		// TODO Auto-generated method stub
		StudentDO studentDO=new StudentDO(studentVO.getId(), studentVO.getName(), studentVO.getPassword(), studentVO.getCreate_time());
		return this.studentDao.updateStudent(studentDO);
	}

	@Override
	public int deleteStudent(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.studentDao.deleteStudent(map);
	}

}
