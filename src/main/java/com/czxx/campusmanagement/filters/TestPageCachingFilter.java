package com.czxx.campusmanagement.filters;

import java.net.URL;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

public class TestPageCachingFilter extends SimplePageCachingFilter {
	
	@Override
	protected CacheManager getCacheManager() {
		System.out.println("---------CacheManager---------");
		//return ((CacheManager)SpringContextUtil.getBean("cacheManager"));
		URL url = getClass().getResource("/cache/ehcache-setting.xml");
        return CacheManager.create(url);
	}
}
