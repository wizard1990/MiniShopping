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
  
  <h1>CATEGORY</h1>
  <%String name = request.getParameter("name");
      if(name != null) {
      	session.setAttribute("username", name);
      }
  %>
  <%if(session.getAttribute("username") == null || session.getAttribute("userrole").equals("1")) {%>
  <h2>You are not owner and you will be redirected to main page now.</h2>
  <%response.setHeader("refresh", "2; URL=mainPage.jsp");} else{ %>
  <h2>Hello, <%=session.getAttribute("username")%></h2> 
    This is CATEGORY. <br>

    
	<table border="1">
	<tr>
		<th>username</th>
		<th>role</th>
		<th>age</th>
		<th>state</th>
	</tr>

<!-- insertCate.action -> para: action(insert) -->
	<tr>
		<form action="insertCate.action" method="post">
		<input type="hidden" name="action" value="insert"/>
		<th><input value="" name="username" size="10"/></th>
		<th><input value="" name="role" size="10"/></th>
		<th><input value="" name="age" size="10"/></th>
		<th><input value="" name="state" size="10"/></th>
		<th><input type="submit" value="Insert"/></th>
		</form>
	</tr>
	
<!-- 	updateCate.action -> para: action(update), uname() -->
<!-- 	deleteCate.action -> para: action(delete), uname() -->
	<s:iterator value="#request.alllist">
	<tr>
		<form action="updateCate.action" method="post">
		<input type="hidden" name="action" value="update"/>
		<input type="hidden" name="uname" value=<s:property value="name" />/>
		
		<td><input value=<s:property value="name" /> name="username" size="10"/></td>
		<td><input value=<s:property value="role"/> name="role" size="10"/></td>
		<td><input value=<s:property value="age"/> name="age" size="10"/></td>
		<td><input value=<s:property value="state"/> name="state" size="10"/></td>
		<td><input type="submit" value="Update"></td>
		</form>
        <form action="deleteCate.action" method="post">
			<input type="hidden" name="action" value="delete"/>
			<input type="hidden" name="uname" value=<s:property value="name" />/>
			<%-- Button --%>
			<td><input type="submit" value="Delete"/></td>
 		</form>
	</tr>
	</s:iterator>
	</table>
	
	    <%} %>
	
	
	<!-- ------------------------------------------------------------------------  -->
	   <div>
	    <ul>
	     <li>user</li>
	     <s:iterator value="#request.alllist">
	      <hr>
	      <li>
	       username:<input value=<s:property value="name" /> name="username" size="10"/>
	      </li> 
	      <li>
	       role:<input value=<s:property value="role"/> name="role" size="10"/>
	      </li>
	      <li>
	       age:<input value=<s:property value="age"/> name="age" size="10"/>
	      </li>
	      <li>
	       state:<input value=<s:property value="state"/> name="state" size="10"/>
	      </li>
	     </s:iterator>
	    </ul>
	   </div>
  </body>
</html>
