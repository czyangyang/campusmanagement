package com.czxx.campusmanagement.handler;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.validator.constraints.EAN;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.czxx.campusmanagement.in.AccountService;
import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.account.CreateOrEditAccountInput;
import com.czxx.campusmanagement.io.account.DeleteAccountByIdInput;
import com.czxx.campusmanagement.io.account.GetAllAccountInput;
import com.czxx.campusmanagement.io.account.LoginInput;

@Controller
@RequestMapping(value = "/account")
@SessionAttributes(value = "ACCOUNT_SESSION")
public class AccountController {
	
	@Resource(name = "accountService")
	private AccountService accountService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Result Login(@Valid @RequestBody LoginInput input, BindingResult br, Map<String, Object> map) throws Exception
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
			result = accountService.Login(input);
			map.put("ACCOUNT_SESSION", result.getResult());
		}catch (Exception ex) {
			// TODO: handle exception
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/createoreditaccount", method = RequestMethod.POST)
	@ResponseBody
	public Result CreateOrEditAccount(@Valid @RequestBody CreateOrEditAccountInput input, BindingResult br)
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
			result = accountService.CreateOrEditAccount(input);
		}catch (Exception ex) {
			// TODO: handle exception
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/getallaccount", method = RequestMethod.POST)
	@ResponseBody
	public Result GetAllAccount(@Valid @RequestBody GetAllAccountInput input, BindingResult br)
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
			result = accountService.GetAllAccount(input);
		}catch (Exception ex) {
			// TODO: handle exception
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/deleteaccountbyid", method = RequestMethod.POST)
	@ResponseBody
	public Result DeleteAccountById(@Valid @RequestBody DeleteAccountByIdInput input, BindingResult br)
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
			result = accountService.DeleteAccountById(input);
		}catch (Exception ex) {
			// TODO: handle exception
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/test")
	public Result Test()
	{
		accountService.Test();
		return null;
	}
}
