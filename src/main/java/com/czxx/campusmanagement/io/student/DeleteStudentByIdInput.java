package com.czxx.campusmanagement.io.student;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DeleteStudentByIdInput {
	
	@NotNull(message="id不能为null")
	@Min(value = 1)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
