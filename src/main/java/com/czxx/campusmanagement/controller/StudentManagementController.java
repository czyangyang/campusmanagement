package com.czxx.campusmanagement.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.czxx.campusmanagement.in.GradeService;
import com.czxx.campusmanagement.in.SchoolClassService;
import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.grade.GetAllGradeInput;
import com.czxx.campusmanagement.io.schoolclass.GetAllSchoolClassWithOutPagedInput;

@Controller
@RequestMapping(value = "/studentmanagement")
public class StudentManagementController {
	
	@Resource(name = "schoolClassService")
	private SchoolClassService schoolClassService;
	
	@Resource(name = "gradeService")
	private GradeService gradeService;
	
	@Cacheable(value="cacheTest")
	@RequestMapping(value = "/index")
	public ModelAndView Index() throws Exception
	{
		System.out.println("访问了Index");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("studentmanagement");
		
		//获取班级和年级信息
		Result schoolClassResult = schoolClassService.GetAllSchoolClassWithOutPaged(new GetAllSchoolClassWithOutPagedInput());
		modelAndView.addObject("schoolclassList", schoolClassResult.getResult());
		
		Result gradeResult = gradeService.GetAllGrade(new GetAllGradeInput());
		modelAndView.addObject("gradeList", gradeResult.getResult());
		
		return modelAndView;
	}
	
}
