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
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
		    var txt = "username should not be empty";
		    alert(txt);
		    return false;
		}
	  }
	  </script>
   <h1>LOG IN</h1>
   <hr>
   <form action="Login.action" method="post" onsubmit="return checkUser();">
        <table align="center">
            <tr>
                <td>UserName:<input type="text" name="name" id="name"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="submit"/>
                <input type="reset" value="reset"/></td>
            </tr>
        </table>
    </form>
  	<div class="mainpg">
 	<button type="button" onclick="window.location='mainPage.jsp'">mainPage</button>
 	</div>
  </body>
</html>
