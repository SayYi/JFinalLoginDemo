package com.imooc.interceptor;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**用户/密码校验，不能为空<br>
 * 否则：<br>
 * 1、设置request属性"message" = "用户/密码不能为空"<br>
 * 2、返回请求页面
 * @author lenovo
 *
 */
public class LoginValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		// TODO Auto-generated method stub
		//用户/密码验证
		validateRequiredString("user", "message", "用户/密码不能为空");
		validateRequiredString("password", "message", "用户/密码不能为空");
	}

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
		//保留user
		c.keepPara("user");
		//获取actionKey
		String actionKey = getActionKey();
		String actionName = actionKey.substring(actionKey.lastIndexOf("/"));
		//返回对应页面
		c.render("/" + actionName + ".jsp");
	}

}
