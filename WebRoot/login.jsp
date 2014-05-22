<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
  	<link href="topright.css" type="text/css" rel="stylesheet">
  
    <base href="<%=basePath%>">
    
    <title>log in page</title>
    
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
    <div class="container">
    <form class="form-signin" action="Login.action" method="post">
      <h2 class="form-signin-heading">Please sign in</h2>
      <input type="text" class="form-control" name="name" id="name" placeholder="username" required autofocus>
      <label class="checkbox">
        <input type="checkbox" value="remember-me"> Remember me
      </label>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
  <div class="mainpg">
 	<button type="button" onclick="window.location='mainPage.jsp'">mainPage</button>
 	</div>
  <div class="container">
  </body>
</html>
