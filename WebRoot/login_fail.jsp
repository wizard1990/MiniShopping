<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login_fail.jsp' starting page</title>
    
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
		if (val == "")
		{
		    var txt = "input should not be empty";
		    alert(txt);
		    window.location.href="login.jsp";
		}
	  }
	  </script>
   <h1>LOG IN</h1>
   <hr>
   <%String name = request.getParameter("name"); %>
   The provided name "<%=name%>" is not known.<br>
   <form action="Login.action" method="post">
        <table align="center">
            <tr>
                <td>UserName:<input type="text" name="name" id="name"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="submit" onclick="checkUser()"/>
                <input type="reset" value="reset"/></td>
            </tr>
        </table>
    </form>
  </body>
</html>