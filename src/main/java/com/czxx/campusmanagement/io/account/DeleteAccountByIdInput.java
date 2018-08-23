package com.czxx.campusmanagement.io.account;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DeleteAccountByIdInput {
	
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
