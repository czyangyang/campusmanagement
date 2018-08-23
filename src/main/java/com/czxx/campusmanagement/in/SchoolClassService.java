package com.czxx.campusmanagement.in;

import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.schoolclass.*;


public interface SchoolClassService {
	
	//创建或更新班级信息
	public Result CreateOrEditSchoolClass(CreateOrEditSchoolClassInput input) throws Exception;
	
	//根据ID删除班级信息
	public Result DeleteSchoolClassById(DeleteSchoolClassByIdInput input) throws Exception;
	
	//获取所有班级信息
	public Result GetAllSchoolClass(GetAllSchoolClassInput input) throws Exception;
	
	//获取所有班级信息(不分页)
	public Result GetAllSchoolClassWithOutPaged(GetAllSchoolClassWithOutPagedInput input) throws Exception;
}
