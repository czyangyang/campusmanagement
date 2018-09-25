package com.czxx.campusmanagement.imp;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.czxx.campusmanagement.in.CacheTestService;

@Service
public class CacheTestServiceImpl implements CacheTestService {

	@Override
	@Cacheable(value="cacheTest", key="#testid")
	public void TestCacheTest(Integer testid) {
		System.out.println("--------执行了TestCacheTest------");

	}

}
