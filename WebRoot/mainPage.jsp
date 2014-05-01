<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <LINK REL=StyleSheet HREF="topright.css" TYPE="text/css">
    <base href="<%=basePath%>">
    
    <title>MAIN PAGE</title>
    
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
  <h1>MINI SHOPPING</h1>
  <%
  if(session.getAttribute("username") != null) { %>
    <h2>Hello, <%=session.getAttribute("username")%></h2> 
    <% if(session.getAttribute("userrole").equals("0")) {%>
    <form action="ListCate.action" method="get">
    <input type="submit" value="category"/>
    </form>
    <form action="ListProd.action" method="get">
    <input type="submit" value="product"/>
    </form>
  	<% } else {%> 
  	<form action="BrowseProd.action" method="get">
    <input type="submit" value="see product"/>
    </form>
    <%} %>
  	<button type="button" onclick="window.location='logout.jsp'">logout</button><br>
  	<% } else { %>
    <h3>Hello, guest.</h3>
    <button type="button" onclick="window.location='signup.jsp'">sign up</button><br>
    <button type="button" onclick="window.location='login.jsp'">login</button><br>
   <%} %>

 	<%if(session.getAttribute("username") != null && session.getAttribute("userrole").equals("1")) {%>
 	<div class="cartpg">
 	<form action="ListCart.action" method="get">
 	<input type="submit" value="shopping_cart"/>
 	</form>
 	</div>
 	<%} %>
  </body>
</html>
