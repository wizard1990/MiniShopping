<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'signup.jsp' starting page</title>
    
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
    <form action="Register.action" method="post">
        <table align="center">
            <tr>
                <td>UserName:<input type="text" name="name"/></td>
                <td>Role:
                <select name="role">
                	<option value="0">owner</option>
                	<option value="1">customer</option>
                </select>
                </td>
                <td>Age:<input type="text" name="age"/></td>
                <td>State:<input type="text" name="state"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="submit"/>
                <input type="reset" value="reset"/></td>
            </tr>
        </table>
    </form>
  </body>
</html>
