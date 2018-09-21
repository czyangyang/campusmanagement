package com.czxx.campusmanagement.handler;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czxx.campusmanagement.in.CallTheRollService;
import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.calltheroll.*;

@Controller
@RequestMapping(value = "/calltheroll")
public class CallTheRollController {
	
	@Resource(name = "callTheRollService")
	private CallTheRollService callTheRollService;
	
	
	@RequestMapping(value = "/createoreditcalltheroll", method = RequestMethod.POST)
	@ResponseBody
	public Result CreateOrEditCallTheRoll(@Valid @RequestBody CreateOrEditCallTheRollInput input, BindingResult br)
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
			result = callTheRollService.CreateOrEditCallTheRoll(input);
		}catch (Exception ex) {
			
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/getallcalltheroll", method = RequestMethod.POST)
	@ResponseBody
	public Result GetAllCallTheRoll(@Valid @RequestBody GetAllCallTheRollInput input, BindingResult br)
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
			result = callTheRollService.GetAllCallTheRoll(input);
		}catch (Exception ex) {
			
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/deletecalltherollbyid", method = RequestMethod.POST)
	@ResponseBody
	public Result DeleteCallTheRollById(@Valid @RequestBody DeleteCallTheRollByIdInput input, BindingResult br)
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
			result = callTheRollService.DeleteCallTheRollById(input);
		}catch (Exception ex) {
			
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/getcalltherollbyid", method = RequestMethod.POST)
	@ResponseBody
	public Result GetCallTheRollById(@Valid @RequestBody GetCallTheRollByIdInput input, BindingResult br)
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
			result = callTheRollService.GetCallTheRollById(input);
		}catch (Exception ex) {
			
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
}
