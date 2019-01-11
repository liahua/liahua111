package com.jt.idea.manage.service.impl;

import java.util.List;

import com.jt.idea.manage.mapper.UserMapper;
import com.jt.idea.manage.pojo.User;
import com.jt.idea.manage.pojo.UserExample;
import com.jt.idea.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> findAll() {
		UserExample userExample = new UserExample();
		userExample.or().andIdIsNotNull();
		List<User> userList = userMapper.selectByExample(userExample);
		return userList;
	}




}
