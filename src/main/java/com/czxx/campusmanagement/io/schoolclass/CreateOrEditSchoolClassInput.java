package com.czxx.campusmanagement.io.schoolclass;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class CreateOrEditSchoolClassInput {
	
	private Integer id = 0;
	
	@NotBlank(message="班级名不能为空")
	private String classname;
	
	@Length(min = 0, max = 200, message = "长度不得大于200")
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
