package com.czxx.campusmanagement.io.student;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class CreateOrEditStudentInput {
	
	private Integer id = 0;
	
	@NotBlank(message="学生名不能为空")
	private String studentname ;
	
	@NotNull(message="性别不能为空")
	@Max(value=2,message="性别不正确")
	@Min(value=1,message="性别不正确")
	private Integer sex;
	
	@NotNull(message="年龄不能为空")
	@Digits(integer=3,fraction=0,message="年龄数据类型不正确")
	@Max(value=200,message="年龄不正确")
	@Min(value=0,message="年龄不正确")
	private Integer age;
	
	@NotNull(message="班级不能为空")
	private Integer classid;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getClassid() {
		return classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public String getHeadimage() {
		return headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}

	public Integer getGradeid() {
		return gradeid;
	}

	public void setGradeid(Integer gradeid) {
		this.gradeid = gradeid;
	}

	private String headimage;
	
	@NotNull(message="年级不能为空")
	private Integer gradeid;
	
	
	
	

	
	
}
