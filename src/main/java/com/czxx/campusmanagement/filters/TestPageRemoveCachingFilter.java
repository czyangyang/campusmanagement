package com.czxx.campusmanagement.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

public class TestPageRemoveCachingFilter extends SimplePageCachingFilter {
	
	@Override
    protected void doFilter(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse, final FilterChain chain) throws ServletException, IOException {
	    Ehcache ehcache = getCacheManager().getEhcache(getCacheName());
	    //清除缓存
	    ehcache.removeAll();
	    //请求继续执行
	    chain.doFilter(httpRequest, httpResponse);
	  }
}
