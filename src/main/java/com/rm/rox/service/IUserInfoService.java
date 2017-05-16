package com.rm.rox.service;

import com.rm.rox.bean.UserInfo;

public interface IUserInfoService {
	   public UserInfo getUserById(int userId);  
	   public UserInfo getUserByLoginName(String loginName);  
}
