package com.czxx.campusmanagement.in;

import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.grade.GetAllGradeInput;


public interface GradeService {
	
	//获取所有年级信息
	public Result GetAllGrade(GetAllGradeInput input) throws Exception;
	
}
