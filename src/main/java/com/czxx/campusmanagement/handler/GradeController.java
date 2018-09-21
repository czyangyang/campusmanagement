package com.czxx.campusmanagement.handler;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.czxx.campusmanagement.in.GradeService;
import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.grade.GetAllGradeInput;

@Controller
@RequestMapping(value = "/grade")
@SessionAttributes(value = "ACCOUNT_SESSION")
public class GradeController {
	
	@Resource(name = "gradeService")
	private GradeService gradeService;
	
	@RequestMapping(value = "/getallgrade", method = RequestMethod.POST)
	@ResponseBody
	public Result GetAllGrade(@Valid @RequestBody GetAllGradeInput input, BindingResult br)
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
			result = gradeService.GetAllGrade(input);
		}catch (Exception ex) {
			
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
}
