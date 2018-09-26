package com.czxx.campusmanagement.in;

import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.student.*;


public interface StudentService {
	
	//创建或更新班级信息
	public Result CreateOrEditStudent(CreateOrEditStudentInput input) throws Exception;
	
	//根据ID删除班级信息
	public Result DeleteStudentById(DeleteStudentByIdInput input) throws Exception;
	
	//获取所有班级信息
	public Result GetAllStudent(GetAllStudentInput input) throws Exception;
	
	//获取所有班级信息
	public Result GetAllStudentWithOutPaged() throws Exception;
	
	//根据班级获取学生
	public Result GetStudentByClassId(GetStudentByClassIdInput input) throws Exception;
	
}
