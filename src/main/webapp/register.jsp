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
<title>注册页面</title>
</head>
<body>
 <h4>注册页面</h4>
 <form action="<%=basePath %>login/register" method="post">
  <input name="user" type="text" value="${param.user }" /><br />
  <input name="password" type="password" /><br />
  <input type="submit" value="submit" />
 </form>
 <h5>${requestScope.message }</h5>
</body>
</html>