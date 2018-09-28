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
	
	private static String environment;
	
	public static String getEnvironment() {
		return SystemConfig.environment;
	}
	
	@Value("#{configProperties['environment']}")
	public void setEnvironment(String environment) {
		try {
			SystemConfig.environment = environment;
		}catch (Exception e) {
			
			SystemConfig.environment = "debug";
		}
	}
}
