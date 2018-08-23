package com.czxx.campusmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "classmanagement")
public class ClassManagementController {
	
	@RequestMapping(value = "index")
	public ModelAndView Index()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classmanagement");
		return modelAndView;
	}
}
