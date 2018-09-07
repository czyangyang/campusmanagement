package com.czxx.campusmanagement.dto;

import java.io.Serializable;
import java.util.Date;

import com.czxx.campusmanagement.util.CzxxHelper;

/**
 * @author 
 */
public class AccountDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String username;

    private Date lastlogintime;

    private String realname;

    private String headimage;

    private String lastlogintimeStr;

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

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
		this.lastlogintimeStr = CzxxHelper.DateToString(lastlogintime);
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getHeadimage() {
		return headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}

	public String getLastlogintimeStr() {
		return lastlogintimeStr;
	}

	public void setLastlogintimeStr(String lastlogintimeStr) {
		this.lastlogintimeStr = lastlogintimeStr;
	}
}