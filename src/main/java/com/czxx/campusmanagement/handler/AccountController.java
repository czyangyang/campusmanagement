package com.czxx.campusmanagement.handler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czxx.campusmanagement.entity.Account;
import com.czxx.campusmanagement.in.AccountService;
import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.account.CreateOrEditAccountInput;
import com.czxx.campusmanagement.io.account.DeleteAccountByIdInput;
import com.czxx.campusmanagement.io.account.GetAccountByIdInput;
import com.czxx.campusmanagement.io.account.GetAllAccountInput;
import com.czxx.campusmanagement.io.account.LoginInput;
import com.czxx.campusmanagement.io.account.ModifyHeadImageInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(tags="账户管理相关接口")
@RequestMapping(value = "/account")
//@SessionAttributes(value = "ACCOUNT_SESSION")
public class AccountController {
	
	@Resource(name = "accountService")
	private AccountService accountService;
	
	@ApiOperation(value="登录",notes = "用户登录", httpMethod = "POST")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Result Login(@Valid @RequestBody LoginInput input, BindingResult br, Map<String, Object> map,HttpSession httpSession) throws Exception
	{ 
		Result result = new Result();
		//手动验证示例
		if(input.getUsername().isEmpty())
		{
			result.setCode(-1);
			result.setMessage("用户名不能为空");
			return result;
		}
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
			
			result.setCode(-1);
			result.setMessage(ex.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/submitlogin", method = RequestMethod.POST)
	public void submitLogin(@Valid LoginInput input, BindingResult br, Map<String, Object> map,HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) throws Exception
	{ 
		Result result = new Result();
		
		result = accountService.Login(input);

		httpSession.setAttribute("ACCOUNT_SESSION", result.getResult());

		request.getRequestDispatcher("../main/index").forward(request, response);
		//response.sendRedirect("../main/index");
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
	
	@RequestMapping(value = "/getheadimagebyid/{userid}", method = RequestMethod.GET)
	public String GetHeadImageById(@PathVariable Integer userid,HttpServletRequest request,HttpServletResponse response) throws IOException {
		ServletOutputStream out = null;
		FileInputStream ips = null;
		try {

//			String referer = request.getHeader("referer");
//			System.out.println(referer);
			GetAccountByIdInput getAccountByIdInput = new GetAccountByIdInput();
			getAccountByIdInput.setId(userid);
			//获取图片存放路径
			Account account = (Account)accountService.GetAccountServiceById(getAccountByIdInput).getResult();
			
			String webinfPath = this.getClass().getClassLoader().getResource("/").getPath().replace("classes", "");
		
			String imgPath =  webinfPath + account.getHeadimage();
			
			ips = new FileInputStream(new File(imgPath));
			//response.setContentType("multipart/form-data");
			response.setContentType("image/jpeg");
			out = response.getOutputStream();
			//读取文件流
			int len = 0;
			byte[] buffer = new byte[1024 * 10];
			while ((len = ips.read(buffer)) != -1){
				out.write(buffer,0,len);
			}
			out.flush();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			out.close();
			ips.close();
		}
		return null;
	}
}
