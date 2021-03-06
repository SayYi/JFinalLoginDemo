package com.imooc.service;

import com.imooc.model.User;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**提供用户相关服务
 * 1、用户登录验证
 * 2、用户注册服务
 * @author lenovo
 *
 */
public class UserService {

	/**检测用户信息<br>
	 * 验证失败：用户/密码不能为空（拦截器已实现）；用户/密码错误<br>
	 * 验证成功：null<br>
	 * @return
	 */
	public String checkLogin(User user) {
		//获取页面表单传递的信息
		String username = user.getStr("user");
		String password = user.getStr("password");
		
		//通过username尝试从数据库读取信息；需要设置user为主键
		//User temp = User.user.findById(username);
		String sql = User.user.getSql("getUser");
		User temp = User.user.findFirst(sql, username);
		
		//未找到该用户，或者密码不匹配
		if (temp == null || 
				!temp.getStr("password").equals(password)){
			return "用户/密码错误";
		}
		//通过验证
		return null;
	}

	/**用户注册<br>
	 * 注册失败：用户/密码不能为空（拦截器已实现）；用户已存在<br>
	 * 注册成功：null<br>
	 * @param user
	 * @return
	 */
	public String register(User user) {
		//获取页面表单传递的信息
		String username = user.getStr("user");
		String password = user.getStr("password");
		
		//用户已存在
		if (User.user.findById(username) != null){
			return "用户已存在";
		}
		//注册用户
//		new User().set("user", username).set("password", password).save();
		Record re = new Record().set("user", username).set("password", password);
		Db.save("user", re);
		return null;
	}

}
