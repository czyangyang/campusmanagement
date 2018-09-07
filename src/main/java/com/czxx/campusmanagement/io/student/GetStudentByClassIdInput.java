package com.czxx.campusmanagement.io.student;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GetStudentByClassIdInput {
	
	@NotNull(message="classid不能为null")
	@Min(value = 0)
	private Integer classid;

	public Integer getClassid() {
		return classid;
	}
	
	public void setClassid(Integer classid) {
		this.classid = classid;
	}
}
