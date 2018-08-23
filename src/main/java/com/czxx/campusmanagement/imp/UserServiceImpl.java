package com.czxx.campusmanagement.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czxx.campusmanagement.dao.UserMapper;
import com.czxx.campusmanagement.entity.User;
import com.czxx.campusmanagement.entity.UserExample;
import com.czxx.campusmanagement.entity.UserExample.Criteria;
import com.czxx.campusmanagement.in.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> getUser() {
		// TODO Auto-generated method stub
		UserExample userExample = new UserExample();
		Criteria criteria = userExample.createCriteria();
	
		return userMapper.selectByExample(userExample);
	}

}
