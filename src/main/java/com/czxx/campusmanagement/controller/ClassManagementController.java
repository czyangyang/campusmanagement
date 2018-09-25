package com.czxx.campusmanagement.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.czxx.campusmanagement.util.CzxxHelper;

@Controller
@RequestMapping(value = "/classmanagement")
public class ClassManagementController {
	
	@RequestMapping(value = "/index2")
	public ModelAndView Index2(HttpServletResponse response) throws IOException
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classmanagement");
		return modelAndView;
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView Index(HttpServletResponse response) throws IOException, InterruptedException
	{
		String filePath = "/Users/yangyang/Documents/cache/classmanagement.html";
		File file = new File(filePath);
		if (file.exists())
		{
			System.out.println("获取缓存数据");
			String html = CzxxHelper.readToString(filePath);
			response.setContentType("text/html");
			response.getWriter().write(html);
			return null;
		}
		else {
			System.out.println("第一次加载，缓存数据");
			Thread.sleep(3000);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("classmanagement");
			Document document = Jsoup.parse(new URL("http://localhost:8080/campusmanagement/classmanagement/index2"), 3000);
			String html = document.toString();
			CzxxHelper.writeToFile(filePath, html);
			return modelAndView;
		}
	}
	
}
