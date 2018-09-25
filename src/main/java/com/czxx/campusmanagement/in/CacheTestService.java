package com.czxx.campusmanagement.in;

import com.czxx.campusmanagement.io.Result;
import com.czxx.campusmanagement.io.account.CreateOrEditAccountInput;
import com.czxx.campusmanagement.io.account.DeleteAccountByIdInput;
import com.czxx.campusmanagement.io.account.GetAccountByIdInput;
import com.czxx.campusmanagement.io.account.GetAllAccountInput;
import com.czxx.campusmanagement.io.account.LoginInput;
import com.czxx.campusmanagement.io.account.ModifyHeadImageInput;


public interface CacheTestService {
	
	public void TestCacheTest(Integer testid);
}
