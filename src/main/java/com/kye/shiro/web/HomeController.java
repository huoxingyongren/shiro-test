package com.kye.shiro.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author zhangyuan
* @version 创建时间：2018年6月28日 下午4:45:09
*/
@Controller
public class HomeController {
	@RequestMapping({"/","/index"})
	public String index() {
		return "/index";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,Map<String,Object> map) throws Exception{
		System.out.println("HomeController.login()");
		//登录失败从request中获取shiro处理的异常信息
		//shiroLoginFailure:就是shiro异常类的全类名
		String exception = (String) request.getAttribute("shiroLoginFailure");
		System.out.println("exception="+ exception);
		String msg="";
		if(exception != null) {
			if(UnknownAccountException.class.getName().equals(exception)) {
				System.out.println("UnknowAccountException---> 账号不存在：");
				msg ="UnknowAccountException--->账号不存在:";
			}else if(IncorrectCredentialsException.class.getName().equals(exception)) {
				System.out.println("IncoreectCreaentialsException---->密码不正确");
				msg ="IncorrectCredentialsException----->密码不正确：";
			}else if("KaptchaValidateFailed".equals(exception)) {
				System.out.println("KaptchaValidateFailed--->验证码错误");
				msg="KaptchaValidateFailed--->验证码错误";
			}else {
				msg = "else >>" +exception;
				System.out.println("else-->"+exception);
			}
		}
		map.put("msg", msg);
		return "/login";
	}
	
	@RequestMapping("/403")
	public String unauthorizedRole() {
		System.out.println("-----没有权限------");
		return "403";
	}

}
