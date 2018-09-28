package com.czxx.campusmanagement.controller;

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
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classmanagement");
		return modelAndView;
		//通过本地文件实现缓存，20180927注释。为了方便后续开发和演示
//		//缓存页存放位置
//		String filePath = "/Users/yangyang/Documents/cache/classmanagement.html";
//		File file = new File(filePath);
//		//1.选判断缓存文件是否存在
//		if (file.exists())
//		{
//			//如果存在，获取缓存文件，直接返回给浏览器
//			System.out.println("获取缓存数据");
//			String html = CzxxHelper.readToString(filePath);
//			response.setContentType("text/html");
//			response.getWriter().write(html);
//			return null;
//		}
//		else {
//			//如果不存在，正常流程
//			//并且，自己再请求一次页面，然后把回去的HTML内容保存在缓存路径
//			System.out.println("第一次加载，缓存数据");
//			//模拟取数据5S
//			Thread.sleep(5000);
//			ModelAndView modelAndView = new ModelAndView();
//			modelAndView.setViewName("classmanagement");
//			Document document = Jsoup.parse(new URL("http://localhost:8080/campusmanagement/classmanagement/index2"), 3000);
//			String html = document.toString();
//			CzxxHelper.writeToFile(filePath, html);
//			return modelAndView;
//		}
	}
	
}
