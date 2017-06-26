package com.imooc.config;

import com.imooc.model.User;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class MyConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		//获取数据库配置信息
		PropKit.use("config.txt");//这个文件，放在src/main/resources内
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
	}

	@Override
	public void configRoute(Routes me) {
		/*controllerKey = /login
		 * baseViewPath = /WEB-INF，viewPath = /jsp
		 */
		me.setBaseViewPath("/WEB-INF");
		me.add("/login", MyController.class, "/jsp");
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub
	}

	@Override
	public void configPlugin(Plugins me) {
		C3p0Plugin cp = new C3p0Plugin(PropKit.get("url"), PropKit.get("user"), PropKit.get("password"));
		me.add(cp);
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		me.add(arp);
		
		//添加sql文件
		arp.setBaseSqlTemplatePath(PathKit.getRootClassPath());
		arp.addSqlTemplate("mysql.sql");
		
		//设置user为主键，可以使用fingById快速查询
		arp.addMapping("user", "user", User.class);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub

	}

}
