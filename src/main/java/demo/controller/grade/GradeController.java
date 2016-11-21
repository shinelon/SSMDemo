package demo.controller.grade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.model.Grade;
import demo.model.Student;
import demo.service.GradeServices;

@Controller
@RequestMapping("/grade" )
public class GradeController {
	
	@Autowired
	private GradeServices gradeServices;
	
	@RequestMapping(value="/showInfos")
	@ResponseBody
	public Object showInfos(){
		System.out.println("grade");
		Grade g=gradeServices.selectGradeAndStudents(1);
		System.out.println(g.getStudents().size());
		
		
		
		return g ;
	}
	@RequestMapping(value="/showAllInfos")
	@ResponseBody
	public Object showAllInfos(){
		System.out.println("gradeAll");
		List<Grade> g=gradeServices.selectAllGradeAndStudents();
		System.out.println(g.size());
		return g ;
	}
	@RequestMapping(value="/showAllInfosN1")
	@ResponseBody
	public Object showAllInfosN1(){
		System.out.println("gradeAllN1");
		List<Grade> g=gradeServices.selectAllGradeAndStudentsN1();
		System.out.println(g.size());
		return g ;
	}

}
