<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>MAIN PAGE!</title>
    
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
  <%
/*   session.invalidate();  */
  if(session.getAttribute("username") != null) { %>
    <h3>Hello, <%=session.getAttribute("username")%></h3> 
    <a href="logout.jsp">logout</a>
  <%} else {%>
    <h3>Hello, guest.</h3>
    <a href="signup.jsp">signup</a>
    <a href="login.jsp">login</a>
   <%} %>
    Welcome to main page!<br>

  </body>
</html>
