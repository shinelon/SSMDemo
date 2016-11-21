package demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.GradeMapper;
import demo.model.Grade;
import demo.service.GradeServices;

@Service("gradeServices")
public class GradeServicesImpl implements GradeServices {
	
	@Autowired
	private GradeMapper gradeMapper;
	
	public Grade selectGradeAndStudents(Integer id) {
		// TODO Auto-generated method stub
		return gradeMapper.selectGradeAndStudents(id);
	}

	public List<Grade> selectAllGradeAndStudents() {
		// TODO Auto-generated method stub
		return gradeMapper.selectAllGradeAndStudents();
	}
	public List<Grade> selectAllGradeAndStudentsN1() {
		// TODO Auto-generated method stub
		return gradeMapper.selectAllGradeAndStudentsN1();
	}

}
