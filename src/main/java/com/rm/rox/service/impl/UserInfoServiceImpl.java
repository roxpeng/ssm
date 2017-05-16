package com.rm.rox.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rm.rox.bean.UserInfo;
import com.rm.rox.dao.UserInfoDao;
import com.rm.rox.service.IUserInfoService;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
	@Resource
	private UserInfoDao userDao;

	@Override
	public UserInfo getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}

	@Override
	public UserInfo getUserByLoginName(String loginName) {
		return this.userDao.selectByLoginName(loginName);
	}
}
