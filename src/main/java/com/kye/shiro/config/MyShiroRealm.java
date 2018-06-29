package com.kye.shiro.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.kye.shiro.entity.SysPermission;
import com.kye.shiro.entity.SysRole;
import com.kye.shiro.entity.UserInfo;
import com.kye.shiro.service.UserInfoService;

/**
* @author zhangyuan
* @version 创建时间：2018年6月28日 下午3:45:16
*/
public class MyShiroRealm extends AuthorizingRealm {
	
	
	@Autowired
	private UserInfoService userInfoService;
	//权限授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
		//遍历所有的角色
		for (SysRole role : userInfo.getRoleList()) {
			//添加角色
			authorizationInfo.addRole(role.getRole());
			//根据角色获取所有的权限
			for(SysPermission p : role.getPermissions()) {
				authorizationInfo.addStringPermission(p.getPermission());
			}
		}
		return authorizationInfo;
	}
	
	//登录认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
		//获取用户输入的账号
		String userName = (String)token.getPrincipal();
		System.out.println(token.getCredentials());
		
		//通过username从数据库中查找User对象，如果找到，
		 //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		UserInfo userInfo = userInfoService.findByUsername(userName);
		System.err.println("------->userInfo="+userInfo);
		if(userInfo == null) {
			return null;
		}
		
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				userInfo,
				userInfo.getPassword(),
				ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
				getName()
				);
		return authenticationInfo;
	}

}
