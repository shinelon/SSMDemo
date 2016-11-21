package demo.service;

import java.util.List;

import demo.model.Student;

public interface StudentServices {

	Student getStudentById(int id);
	
	List<Student> selectAll();
	
	int insert(Student student);

}
