package com.czxx.campusmanagement.io.calltheroll;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GetCallTheRollByIdInput {
	
	@NotNull(message="id不能为null")
	@Min(value = 0)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
