package com.czxx.campusmanagement.handler;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.czxx.campusmanagement.entity.Account;
import com.czxx.campusmanagement.in.AccountService;
import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.account.CreateOrEditAccountInput;
import com.czxx.campusmanagement.io.account.DeleteAccountByIdInput;
import com.czxx.campusmanagement.io.account.GetAllAccountInput;
import com.czxx.campusmanagement.io.account.LoginInput;
import com.czxx.campusmanagement.io.account.ModifyHeadImageInput;
import com.czxx.campusmanagement.util.PhotoUtil;

@Controller
@RequestMapping(value = "/account")
//@SessionAttributes(value = "ACCOUNT_SESSION")
public class AccountController {
	
	@Resource(name = "accountService")
	private AccountService accountService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Result Login(@Valid @RequestBody LoginInput input, BindingResult br, Map<String, Object> map,HttpSession httpSession) throws Exception
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
			//map.put("ACCOUNT_SESSION", result.getResult());
			httpSession.setAttribute("ACCOUNT_SESSION", result.getResult());
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
	
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	@ResponseBody
    public Result ModifyHeadImage(ModifyHeadImageInput input, Map<String, Object> map,HttpSession httpSession){
		Result result = new Result();
		try {
			result = accountService.ModifyHeadImage(input);
			//Account account = (Account)map.get("ACCOUNT_SESSION");
			Account account = (Account)httpSession.getAttribute("ACCOUNT_SESSION");
			account.setHeadimage(result.getResult().toString());
			//map.put("ACCOUNT_SESSION", account);
			httpSession.setAttribute("ACCOUNT_SESSION", account);
		}catch (Exception ex) {
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
        return result;
    }
	
}
