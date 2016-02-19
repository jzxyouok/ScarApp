package com.zero2ipo.mobile.services.student.impl;

import com.zero2ipo.common.entity.Student;
import com.zero2ipo.mobile.dao.student.IStudentDao;
import com.zero2ipo.mobile.services.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("studentService")
public class StudentServiceImpl implements IStudentService{

	@Autowired
	protected IStudentDao studentDao ;
	@Override
	public int add(Student bo) {
		return studentDao.add(bo);
	}

	@Override
	public List<Student> findAllList(Map<String, Object> queryMap) {
		return studentDao.findAllList(queryMap);
	}

	@Override
	public Student findById(Map<String, Object> queryMap) {
		return studentDao.findById(queryMap);
	}
	@Override
	public boolean update(Student student) {
		return studentDao.update(student);
	}
	
}

