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
  <script>
  function checkUser()
  {
	var val = document.getElementById("name").value;
	var age = document.getElementById("age").value;;
	if (val == "")
	{
	    var txt = "input should not be empty";
	    alert(txt);
	    window.location.href='signup.jsp';
	}
	else if (age < 0)
	{
	    var txt = "age should not be negative";
	    alert(txt);
	    window.location.href='signup.jsp';
	}
  }
  </script>
  <h1>REGISTER</h1>
  <hr>
    <form action="Register.action" method="post">
        <table align="center">
            <tr><td>UserName:<input type="text" name="name" id="name"/>
            </td></tr>
            <tr><td>Role:
                <select name="role">
                	<option value="0">owner</option>
                	<option value="1">customer</option>
                </select>
            </td></tr>
            <tr><td>Age:<input type="text" name="age" id="age"/></td></tr>
            <tr><td>State:<input type="text" name="state"/></td></tr>
            <tr>
                <td><input type="submit" value="submit" onclick="checkUser()"/>
                <input type="reset" value="reset"/></td>
            </tr>
        </table>
    </form>
  </body>
</html>
