package com.czxx.campusmanagement.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czxx.other.WangEditorUploadOutput;

@Controller
@RequestMapping("editor")
public class EditorController {
	
	@RequestMapping("wangeditor")
	public String wangeditor()
	{
		return "wangeditortest";
	}
	
	@RequestMapping("wangeditorupload")
	@ResponseBody
	public WangEditorUploadOutput wangeditorUpload() {
		WangEditorUploadOutput output = new WangEditorUploadOutput();
		output.setErrno(0);
		ArrayList<String> images = new ArrayList<>();
		images.add("http://localhost:8080/campusmanagement/static/fileUpload/headImages/defaultHeadImage.png");
		output.setData(images);
		return output;
	}
	
	@RequestMapping("ueditor")
	public String ueditor()
	{
		return "ueditor";
	}
	
	@RequestMapping("ueditorconfig")
	public String ueditorconfig()
	{
		return "controller";
	}
}
