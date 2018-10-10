package com.czxx.campusmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("signin")
public class SingInController {

	@RequestMapping("index")
	public String Index()
	{
		return "signin";
	}
}
