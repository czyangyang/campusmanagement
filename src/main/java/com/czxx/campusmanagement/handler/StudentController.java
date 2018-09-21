package com.czxx.campusmanagement.handler;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czxx.campusmanagement.in.StudentService;
import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.student.CreateOrEditStudentInput;
import com.czxx.campusmanagement.io.student.DeleteStudentByIdInput;
import com.czxx.campusmanagement.io.student.GetAllStudentInput;
import com.czxx.campusmanagement.io.student.GetStudentByClassIdInput;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	
	@Resource(name = "studentService")
	private StudentService studentService;
	
	
	@RequestMapping(value = "/createoreditstudent", method = RequestMethod.POST)
	@ResponseBody
	public Result CreateOrEditStudent(@Valid @RequestBody CreateOrEditStudentInput input, BindingResult br)
	{
		Result result = new Result();
		try {
	        if(br.hasErrors()){
	        	String valStr = "";
	        	List<ObjectError> ls=br.getAllErrors();
	            for (int i = 0; i < ls.size(); i++) {
	            	valStr += " " + ls.get(i).getDefaultMessage();
	            }
	            result.setCode(-1);
	            result.setMessage(valStr);
	            return result;
	        }
			result = studentService.CreateOrEditStudent(input);
		}catch (Exception ex) {
			
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/getallstudent", method = RequestMethod.POST)
	@ResponseBody
	public Result GetAllStudent(@Valid @RequestBody GetAllStudentInput input, BindingResult br)
	{
		Result result = new Result();
		try {
	        if(br.hasErrors()){
	        	String valStr = "";
	        	List<ObjectError> ls=br.getAllErrors();
	            for (int i = 0; i < ls.size(); i++) {
	            	valStr += " " + ls.get(i).getDefaultMessage();
	            }
	            result.setCode(-1);
	            result.setMessage(valStr);
	            return result;
	        }
			result = studentService.GetAllStudent(input);
		}catch (Exception ex) {
			
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/deletestudentbyid", method = RequestMethod.POST)
	@ResponseBody
	public Result DeleteStudentById(@Valid @RequestBody DeleteStudentByIdInput input, BindingResult br)
	{
		Result result = new Result();
		try {
	        if(br.hasErrors()){
	        	String valStr = "";
	        	List<ObjectError> ls=br.getAllErrors();
	            for (int i = 0; i < ls.size(); i++) {
	            	valStr += " " + ls.get(i).getDefaultMessage();
	            }
	            result.setCode(-1);
	            result.setMessage(valStr);
	            return result;
	        }
			result = studentService.DeleteStudentById(input);
		}catch (Exception ex) {
			
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/getstudentbyclassid", method = RequestMethod.POST)
	@ResponseBody
	public Result GetStudentByClassId(@Valid @RequestBody GetStudentByClassIdInput input, BindingResult br)
	{
		Result result = new Result();
		try {
	        if(br.hasErrors()){
	        	String valStr = "";
	        	List<ObjectError> ls=br.getAllErrors();
	            for (int i = 0; i < ls.size(); i++) {
	            	valStr += " " + ls.get(i).getDefaultMessage();
	            }
	            result.setCode(-1);
	            result.setMessage(valStr);
	            return result;
	        }
			result = studentService.GetStudentByClassId(input);
		}catch (Exception ex) {
			
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
}
