package com.czxx.campusmanagement.io.account;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class CreateOrEditAccountInput {
	
	private Integer id = 0;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@NotBlank(message="用户名不能为空")
	private String username;
	
	@NotBlank(message="真实姓名不能为空")
	@Length(min = 0, max = 20)
	private String realname;
	
}
