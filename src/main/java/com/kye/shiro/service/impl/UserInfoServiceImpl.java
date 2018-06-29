package com.kye.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kye.shiro.dao.UserInfoDao;
import com.kye.shiro.entity.UserInfo;
import com.kye.shiro.service.UserInfoService;

/**
* @author zhangyuan
* @version 创建时间：2018年6月28日 下午4:36:37
*/
@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoDao userInfoDao;
	@Override
	public UserInfo findByUsername(String username) {
		// TODO Auto-generated method stub
		return userInfoDao.findByUsername(username);
	}

}
