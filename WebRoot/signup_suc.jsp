<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>sign up successfully</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%String name = request.getParameter("name");
      String role = request.getParameter("role");
      if(name != null)
      {
      	session.setAttribute("username", name);
      	session.setAttribute("userrole", role);
      }
      response.setHeader("refresh", "2; URL=mainPage.jsp");
    %>
    <img align="center" src="ico/suc.jpg" />
    <h1 align="center">You have successfully signed up!</h1>
  </body>
</html>
