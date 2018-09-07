package com.czxx.campusmanagement.io.account;

import org.springframework.web.multipart.MultipartFile;

public class ModifyHeadImageInput {
	private Integer id;
	
	private MultipartFile file;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
