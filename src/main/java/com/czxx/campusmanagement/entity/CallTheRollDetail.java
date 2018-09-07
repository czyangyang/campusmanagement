package com.czxx.campusmanagement.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class CallTheRollDetail implements Serializable {
    private Integer id;

    private Integer studentid;

    private Integer calltherollid;

    private Integer confirm;
    
    private Student student;
    
    private CallTheRoll callTheRoll;

    public CallTheRoll getCallTheRoll() {
		return callTheRoll;
	}

	public void setCallTheRoll(CallTheRoll callTheRoll) {
		this.callTheRoll = callTheRoll;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public Integer getCalltherollid() {
        return calltherollid;
    }

    public void setCalltherollid(Integer calltherollid) {
        this.calltherollid = calltherollid;
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
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
        CallTheRollDetail other = (CallTheRollDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStudentid() == null ? other.getStudentid() == null : this.getStudentid().equals(other.getStudentid()))
            && (this.getCalltherollid() == null ? other.getCalltherollid() == null : this.getCalltherollid().equals(other.getCalltherollid()))
            && (this.getConfirm() == null ? other.getConfirm() == null : this.getConfirm().equals(other.getConfirm()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStudentid() == null) ? 0 : getStudentid().hashCode());
        result = prime * result + ((getCalltherollid() == null) ? 0 : getCalltherollid().hashCode());
        result = prime * result + ((getConfirm() == null) ? 0 : getConfirm().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", studentid=").append(studentid);
        sb.append(", calltherollid=").append(calltherollid);
        sb.append(", confirm=").append(confirm);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}