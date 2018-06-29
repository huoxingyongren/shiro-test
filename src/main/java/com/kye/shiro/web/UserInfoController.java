package com.kye.shiro.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author zhangyuan
* @version 创建时间：2018年6月28日 下午5:30:28
*/
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
	/**
	 * 用户查询
	 * @return 
	 * 
	 */
	@RequestMapping("/userList")
	@RequiresPermissions("userInfo:view")//权限管理
	public String userInfo() {
		return "userInfo";
	}
	 /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfoAdd(){
        return "userInfoAdd";
    }

    /**
     * 用户删除;
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(){
        return "userInfoDel";
    }
}
