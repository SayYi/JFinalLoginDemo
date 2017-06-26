package com.imooc.config;

import com.imooc.interceptor.LoginValidator;
import com.imooc.model.User;
import com.imooc.service.UserService;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class MyController extends Controller {

	/**增强User服务类，添加表单验证拦截器
	 * @return
	 */
	private UserService getUserService() {
		//return enhance(UserService.class, LoginValidator.class);
		return new UserService();
	}
	
	/**
	 * 登陆验证<br>
	 * 成功：/WEB-INF/jsp/success.jsp<br>
	 * 失败：/login.jsp<br>
	 */
	@Before(LoginValidator.class)
	public void login() {
		//获取表单数据
		User user = getModel(User.class, "");
		//renderText("hello World. Hello " + user.getStr("user"));
		
		//获取验证结果
		String message = getUserService().checkLogin(user);
		//renderText("验证结果:" + message);
		
		/*
		 * 验证成功：
		 * 1、session内user存储用户名；2、转发至success.jsp
		 * 验证失败：
		 * 1、request内message存储验证信息；2、转发至/login.jsp
		 */
		if (message == null) {
			setSessionAttr("user", user.getStr("user"));
			render("success.jsp");
			return;
		} else {
			setAttr("message", message);
			render("/login.jsp");
			return;
		}
	}
	
	@Before(LoginValidator.class)
	public void register() {
		//获取表单信息
		User user = getModel(User.class, "");
		//获取注册结果
		String message = getUserService().register(user);
		/*
		 * 注册成功：
		 * 重定向至登陆页面/login.jsp
		 * 注册失败：
		 * request内message存储信息，转发至注册页面/register.jsp
		 */
		if (message == null) {
			redirect("/login.jsp");
			return;
		} else {
			setAttr("message", message);
			render("/register.jsp");
			return;
		}
	}
}
