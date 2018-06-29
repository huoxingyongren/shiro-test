package com.kye.shiro.dao;
/**
* @author zhangyuan
* @version 创建时间：2018年6月28日 下午4:34:54
*/

import org.springframework.data.repository.CrudRepository;

import com.kye.shiro.entity.UserInfo;

public interface UserInfoDao extends CrudRepository<UserInfo,Long> {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}
