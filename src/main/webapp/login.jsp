<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
    String path = request.getContextPath(); 
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
</head>
<body>
 <a href="<%=basePath %>login/login">测试controller</a>
 <h4>登陆页面</h4>
 <form action="<%=basePath %>login/login" method="post">
  <input name="user" type="text" value="${param.user }" /><br />
  <input name="password" type="password" /><br />
  <input type="submit" value="submit" />
  <a href="<%=basePath %>register.jsp">注册</a>
 </form>
 <h5>${requestScope.message }</h5>
</body>
</html>