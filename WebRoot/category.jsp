<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" %>

<%
response.setCharacterEncoding("utf-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<LINK REL=StyleSheet HREF="topright.css" TYPE="text/css">
    <base href="<%=basePath%>">
    
    <title>My JSP 'category.jsp' starting page</title>
    
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
  <h1>CATEGORY</h1>
  <%if(session.getAttribute("username") == null || session.getAttribute("userrole").equals("1")) {%>
  <h2>You are not owner and you will be redirected to main page now.</h2>
  <%response.setHeader("refresh", "2; URL=mainPage.jsp");} else{ 
    if(request.getAttribute("isSucc") != null && request.getAttribute("isSucc").equals(0))
  {%>
  <h2>Modified failed.</h2>
  <%}%>
  <h2>Hello, <%=session.getAttribute("username")%></h2> 
    This is CATEGORY. <br>
    <p>----------insert category here---------------</p>
	<table border="1">
	<tr>
		<th>id</th>
		<th>name</th>
		<th>description</th>
	</tr>

<!-- InsertCate.action -> para: action(insert) -->
	<tr>
		<form action="InsertCate.action" method="post">
		<!-- <input type="hidden" name="action" value="insert"/> -->
		<th><input value="" name="id" size="10" disabled=true/></th>
		<th><input value="" name="name" size="10"/></th>
		<th><input value="" name="descrip" size="20"/></th>
		<th><input type="submit" value="Insert"/></th>
		</form>
	</tr>
	
<!-- 	UpdateCate.action -> para: action(update), uname() -->
<!-- 	DeleteCate.action -> para: action(delete), uname() -->

	<s:bean name="SortCategory" var="sortref"></s:bean>
	<s:sort comparator="sortref" source="#request.categories" var="newList">
	<s:iterator var="newL" value="#attr.newList">
	<tr>
	  <script type="text/javascript">
	  function checkCate()
	  {
		var val = document.getElementById("name").value;
		var dsp = document.getElementById("descrip").value;
		if (val == "" || dsp == "")
		{
		    var txt = "input should not be empty";
 		    alert(txt);  
		    return false;
		}
	  }
	  </script>
		<form action="UpdateCate.action" method="post" onsubmit="return checkCate();">
		<!-- <input type="hidden" name="action" value="update"/> -->
		<input type="hidden" name="id" value=<s:property value="#newL.id" />/>
		<td><input value=<s:property value="#newL.id"/> name="id" size="10" disabled=true/></td>
		<td><input value=<s:property value="#newL.name" escape="false"/> name="name" id="name" size="10"/></td>
		<td><input value=<s:property value="#newL.descrip" escape="false"/> name="descrip" id="descrip" size="20"/></td>
		
		<td><input type="submit" value="Update"></td>
		</form>
        <form action="DeleteCate.action" method="post">
			<!-- <input type="hidden" name="action" value="delete"/> -->
			<input type="hidden" name="name" value=<s:property value="#newL.name" />/>
			<%-- Button --%>
			<td><input type="submit" value="Delete"/></td>
 		</form>
	</tr>
	</s:iterator> 
	</s:sort>

	</table>
	    <%} %>
 	<div class="mainpg">
 	<button type="button" onclick="window.location='mainPage.jsp'">mainPage</button>
 	</div>
 	<div class="cartpg">
 	<button type="button" onclick="window.location='shopping_cart.jsp'">myCart</button>
 	</div>
  </body>
</html>
