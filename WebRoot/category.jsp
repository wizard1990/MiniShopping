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
    
    <title>category</title>
    
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
  function checkCate()
  {
	var val = document.getElementById("iname").value;
	var dsp = document.getElementById("idsp").value;
	if (val == "" || dsp=="")
	{
		alert("do not be empty");
	    return false;
	}
  }
  </script>
  
  
  <h1>CATEGORY</h1>
  <%if(session.getAttribute("username") == null || session.getAttribute("userrole").equals("customer")) {%>
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
		<!-- <th>id</th> -->
		<th>name</th>
		<th>description</th>
	</tr>

	<tr>
		<form action="InsertCate.action" method="post" onsubmit="return checkCate();">
		<!-- <th><input value="" name="id" size="10" disabled=true/></th> -->
		<th><input value="" name="name" id="iname" size="10"/></th>
		<th><input value="" name="descrip" id="idsp" size="20"/></th>
		<th><input type="submit" value="Insert"/></th>
		</form>
	</tr>

	<s:if test="#request.categories!=null">

	<s:bean name="SortCategory" var="sortref"></s:bean>
	<s:sort comparator="sortref" source="#request.categories" var="newList">
	<s:iterator var="newL" value="#attr.newList">
	<tr>

		<form action="UpdateCate.action" method="post">
		<!-- <input type="hidden" name="action" value="update"/> -->
		<input type="hidden" name="id" value=<s:property value="#newL.id" />/>
		<!-- <td><input value=<s:property value="#newL.id"/> name="id" size="10" disabled=true/></td> -->
		<td><input value=<s:property value="#newL.name" escape="false"/> name="name" size="10"/></td>
		<td><input value=<s:property value="#newL.description" escape="false"/> name="descrip" size="20"/></td>
		
		<td><input type="submit" value="Update"></td>
		</form>
		
		<s:if test="#newL.productses.size()==0">
        <form action="DeleteCate.action" method="post">
			<input type="hidden" name="name" value=<s:property value="#newL.name" />/>
			<%-- Button --%>
			<td><input type="submit" value="Delete"/></td>
 		</form>
 		</s:if>
 		
	</tr>
	</s:iterator> 
	</s:sort>
	</s:if>
	</table>
	
	    <%} %>
 	<div class="mainpg">
 	<button type="button" onclick="window.location='mainPage.jsp'">mainPage</button>
 	</div>
 	<%if(session.getAttribute("username") != null && session.getAttribute("userrole").equals("customer")) {%>
 	<div class="cartpg">
 	<form action="ListCart.action" method="get">
 	<input type="submit" value="shopping_cart"/>
 	</form>
 	</div>
 	<%} %>
  </body>
</html>
