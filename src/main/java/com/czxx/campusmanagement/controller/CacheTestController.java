package com.czxx.campusmanagement.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("cachetest")
public class CacheTestController {
	
	@RequestMapping("list")
	public String list()
	{
		
		return "list cache:" + new Date().toString();
	}
	
	@RequestMapping("delete")
	public String delete()
	{
		return "cache deleted";
	}
}
