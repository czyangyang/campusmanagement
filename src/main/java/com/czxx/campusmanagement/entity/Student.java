package com.czxx.campusmanagement.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Student implements Serializable {
    private Integer id;

    private String studentname;

    private Integer sex;

    private Integer age;

    private Integer classid;

    private String headimage;

    private Integer gradeid;

    private Integer isdeleted;

    private Date deletetime;

    private Date createtime;

    private Date modifytime;
    
    private SchoolClass schoolClass;
    
    private Grade grade;
    
    private static final long serialVersionUID = 1L;

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

    public Integer getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
    }

    public Date getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(Date deletetime) {
        this.deletetime = deletetime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }
    
    public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}
    
    public SchoolClass getSchoolClass() {
		return schoolClass;
	}
    
    public void setGrade(Grade grade) {
		this.grade = grade;
	}
    
    public Grade getGrade() {
		return grade;
	}

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Student other = (Student) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStudentname() == null ? other.getStudentname() == null : this.getStudentname().equals(other.getStudentname()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
            && (this.getClassid() == null ? other.getClassid() == null : this.getClassid().equals(other.getClassid()))
            && (this.getHeadimage() == null ? other.getHeadimage() == null : this.getHeadimage().equals(other.getHeadimage()))
            && (this.getGradeid() == null ? other.getGradeid() == null : this.getGradeid().equals(other.getGradeid()))
            && (this.getIsdeleted() == null ? other.getIsdeleted() == null : this.getIsdeleted().equals(other.getIsdeleted()))
            && (this.getDeletetime() == null ? other.getDeletetime() == null : this.getDeletetime().equals(other.getDeletetime()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getModifytime() == null ? other.getModifytime() == null : this.getModifytime().equals(other.getModifytime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStudentname() == null) ? 0 : getStudentname().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getClassid() == null) ? 0 : getClassid().hashCode());
        result = prime * result + ((getHeadimage() == null) ? 0 : getHeadimage().hashCode());
        result = prime * result + ((getGradeid() == null) ? 0 : getGradeid().hashCode());
        result = prime * result + ((getIsdeleted() == null) ? 0 : getIsdeleted().hashCode());
        result = prime * result + ((getDeletetime() == null) ? 0 : getDeletetime().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getModifytime() == null) ? 0 : getModifytime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", studentname=").append(studentname);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", classid=").append(classid);
        sb.append(", headimage=").append(headimage);
        sb.append(", gradeid=").append(gradeid);
        sb.append(", isdeleted=").append(isdeleted);
        sb.append(", deletetime=").append(deletetime);
        sb.append(", createtime=").append(createtime);
        sb.append(", modifytime=").append(modifytime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}