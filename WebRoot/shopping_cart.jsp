<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shopping_cart.jsp' starting page</title>
    
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
  <h1>SHOPPING CART</h1>
    <%
  if(session.getAttribute("username") != null) { %>
    <h2>Hello, <%=session.getAttribute("username")%></h2> 
    <p>-----------insert product browsing------------------</p>
    <a href="product_browsing.jsp">product browsing</a>
    <a href="purchase_confirmation.jsp">purchase confirmation</a>
  <%} else {%>
    <h3>Please log in first.</h3>
    <a href="login.jsp">login</a>
   <%} %>
  </body>
</html>