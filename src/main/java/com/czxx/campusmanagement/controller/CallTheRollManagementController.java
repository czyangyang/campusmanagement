package com.czxx.campusmanagement.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.czxx.campusmanagement.in.CallTheRollService;
import com.czxx.campusmanagement.in.GradeService;
import com.czxx.campusmanagement.in.SchoolClassService;
import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.grade.GetAllGradeInput;
import com.czxx.campusmanagement.io.schoolclass.GetAllSchoolClassWithOutPagedInput;

@Controller
@RequestMapping(value = "/calltherollmanagement")
public class CallTheRollManagementController {	
	@Resource(name = "schoolClassService")
	private SchoolClassService schoolClassService;
	
	@RequestMapping(value = "/index")
	public ModelAndView Index() throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("calltherollmanagement");
		
		//获取班级和年级信息
		Result schoolClassResult = schoolClassService.GetAllSchoolClassWithOutPaged(new GetAllSchoolClassWithOutPagedInput());
		modelAndView.addObject("schoolclassList", schoolClassResult.getResult());
		
		return modelAndView;
	}
}
