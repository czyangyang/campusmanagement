package com.czxx.campusmanagement.handler;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czxx.campusmanagement.in.CacheTestService;
import com.czxx.campusmanagement.in.SchoolClassService;
import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.schoolclass.CreateOrEditSchoolClassInput;
import com.czxx.campusmanagement.io.schoolclass.DeleteSchoolClassByIdInput;
import com.czxx.campusmanagement.io.schoolclass.GetAllSchoolClassInput;

@Controller
@RequestMapping(value = "/schoolclass")
public class SchoolClassController {
	
	@Resource(name = "schoolClassService")
	private SchoolClassService schoolClassService;
	
	@Resource
	private CacheTestService cacheTestService;
	
	@RequestMapping(value = "/createoreditschoolclass", method = RequestMethod.POST)
	@ResponseBody
	public Result CreateOrEditSchoolClass(@Valid @RequestBody CreateOrEditSchoolClassInput input, BindingResult br)
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
			result = schoolClassService.CreateOrEditSchoolClass(input);
		}catch (Exception ex) {
			
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/getallschoolclass", method = RequestMethod.POST)
	@ResponseBody
	@Cacheable(value="testCache")
	public Result GetAllSchoolClass(@Valid @RequestBody GetAllSchoolClassInput input, BindingResult br)
	{
		cacheTestService.TestCacheTest(5);
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
			result = schoolClassService.GetAllSchoolClass(input);
		}catch (Exception ex) {
			
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/deleteschoolclassbyid", method = RequestMethod.POST)
	@ResponseBody
	public Result DeleteSchoolClassById(@Valid @RequestBody DeleteSchoolClassByIdInput input, BindingResult br)
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
			result = schoolClassService.DeleteSchoolClassById(input);
		}catch (Exception ex) {
			
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
	
}
