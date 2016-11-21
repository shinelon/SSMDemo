package demo.service;

import java.util.List;

import demo.model.Grade;

public interface GradeServices {
	Grade selectGradeAndStudents(Integer id);
	
	List<Grade> selectAllGradeAndStudents();
	
	List<Grade> selectAllGradeAndStudentsN1();
}
