package com.czxx.campusmanagement.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemConfig {
	
	private static Integer pagesize;

	public static Integer getPagesize() {
		return SystemConfig.pagesize;
	}
	
	@Value("#{configProperties['pagesize']}")
	public void setPagesize(String pagesize) {
		try {
			SystemConfig.pagesize = Integer.parseInt(pagesize);
		}catch (Exception e) {
			
			SystemConfig.pagesize = 0;
		}
	}
}
