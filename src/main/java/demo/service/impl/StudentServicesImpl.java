package demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import demo.dao.StudentMapper;
import demo.ehcache.utils.ReportCache;
import demo.model.Student;
import demo.service.StudentServices;

@Service("studentServices")
public class StudentServicesImpl implements StudentServices {

	@Autowired
	private StudentMapper studentMapper;

	@Cacheable(value = "messageCache",key="#id")
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return studentMapper.selectByPrimaryKey(id);
	}

	public List<Student> selectAll() {
		// TODO Auto-generated method stub
		
		ReportCache reportCache = ReportCache.getInstance();
		if(reportCache.getReportCache("ALL")==null){
			reportCache.setReportCache("ALL", studentMapper.selectAll());
		}
		//强制类型转换不安全，需要优化
		List<Student> retList=(List<Student>) reportCache.getReportCache("ALL");		
		return retList;
	}

	public int insert(Student student) {
		// TODO Auto-generated method stub
		return 0;
	}

}
