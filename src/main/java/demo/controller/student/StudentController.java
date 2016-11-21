package demo.controller.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import demo.model.Student;
import demo.service.StudentServices;
import io.netty.handler.codec.http.HttpRequest;

@Controller
@RequestMapping("/student" )
public class StudentController {
	@Autowired
	private StudentServices studentServices;
	
	@RequestMapping("/showInfo/{id}")
	public String showUserInfo(ModelMap modelMap, @PathVariable int id){
		Student student = studentServices.getStudentById(id);
		String json = JSONArray.toJSONString(student);
		modelMap.addAttribute("student", student);
		modelMap.addAttribute("json", json);
		int i=1/0;
		return "/student/student";
	}
	@RequestMapping(value="/showInfos",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object showUserInfos(){
		//List<Student> userInfos = studentServices.getUsers();
		List<Student> studentList=studentServices.selectAll();
		System.out.println("here");
		return studentList;
	}
	@RequestMapping("/showInfo/ajaxjson")
	@ResponseBody
	public Object json(@RequestBody JavaBean javabean){
		
		
		System.out.println(javabean.getMobile());
		System.out.println(javabean.getPassword());
	
		return "ok";
	}
	
	@RequestMapping("/showInfo/jsp")
	public Object jsp(){
		
	
		return "/turntemplate";
	}
	
}
