package com.czxx.campusmanagement.in;

import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.calltheroll.*;


public interface CallTheRollService {
	
	//创建或更新点名信息
	public Result CreateOrEditCallTheRoll(CreateOrEditCallTheRollInput input) throws Exception;
	
	//根据ID删除点名信息
	public Result DeleteCallTheRollById(DeleteCallTheRollByIdInput input) throws Exception;
	
	//获取所有点名信息
	public Result GetAllCallTheRoll(GetAllCallTheRollInput input) throws Exception;
	
	public Result GetCallTheRollById(GetCallTheRollByIdInput input) throws Exception;
}
