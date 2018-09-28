package com.czxx.campusmanagement.io.calltheroll;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class CreateOrEditCallTheRollInput {
	
	private Integer id = 0;
	
	@NotBlank(message="标题不能为空")
	private String title;
	
	@NotNull(message="班级不能为空")
	private Integer classid;
	
	@NotNull(message="学生不能为空")
	private List<Integer> studentids;
	
	private List<Integer> confirms;
	
	public List<Integer> getConfirms() {
		return confirms;
	}

	public void setConfirms(List<Integer> confirms) {
		this.confirms = confirms;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getClassid() {
		return classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public List<Integer> getStudentids() {
		return studentids;
	}

	public void setStudentids(List<Integer> studentids) {
		this.studentids = studentids;
	}
	
	
	
	

	
	
}
