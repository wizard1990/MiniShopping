<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control"  content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<!-- 	<s:action name="Login" executeResult="false"></s:action> -->
	   <div>
	    <ul>
	     <li>user</li>
	     <s:iterator value="#request.alllist">
	      <hr>
	      <li>
	       username:<s:property value="name" />
	      </li>
	      <li>
	       role:<s:property value="role"/>
	      </li>
	      <li>
	       age:<s:property value="age"/>
	      </li>
	      <li>
	       state:<s:property value="state"/>
	      </li>
	     </s:iterator>
	    </ul>
	   </div>
  </body>
</html>
