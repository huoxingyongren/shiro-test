package com.kye.shiro.service;

import com.kye.shiro.entity.UserInfo;

/**
* @author zhangyuan
* @version 创建时间：2018年6月28日 下午4:36:08
*/
public interface UserInfoService {
	/**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}
