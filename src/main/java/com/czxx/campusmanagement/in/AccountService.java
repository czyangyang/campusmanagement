package com.czxx.campusmanagement.in;

import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.account.CreateOrEditAccountInput;
import com.czxx.campusmanagement.io.account.DeleteAccountByIdInput;
import com.czxx.campusmanagement.io.account.GetAllAccountInput;
import com.czxx.campusmanagement.io.account.LoginInput;


public interface AccountService {
	
	//登录接口
	public Result Login(LoginInput input) throws Exception;
	
	//创建或更新用户信息
	public Result CreateOrEditAccount(CreateOrEditAccountInput input) throws Exception;
	
	//根据ID删除用户信息
	public Result DeleteAccountById(DeleteAccountByIdInput input) throws Exception;
	
	//获取所有用户信息
	public Result GetAllAccount(GetAllAccountInput input) throws Exception;
	
}
