package com.czxx.campusmanagement.io.account;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GetAllAccountInput {
	
	@NotNull(message="page不能为null")
	@Min(value = 0)
	private Integer page;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}
