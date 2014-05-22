<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<LINK REL=StyleSheet HREF="topright.css" TYPE="text/css">
    <base href="<%=basePath%>">
    
    <title>product confirmation</title>
    
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
  <h1>PURCHASE CONFIRMATION</h1>
    <%
  if(session.getAttribute("username") != null && session.getAttribute("userrole").equals("customer")) { %>
    <h2>Hello, <%=session.getAttribute("username")%></h2> 
    <h3>Please confirm the following transaction information:</h3>
    <table border="1">
	<tr>
		<th>product name</th>
		<th>price</th>
		<th>quantity</th>
		<th>total price</th>
	</tr>

	<s:if test="#request.transactions!=null">
	<s:set var="totprc" value="0"/>

	<s:bean name="SortTransactions" var="sortref"></s:bean>
	<s:sort comparator="sortref" source="#request.transactions" var="newList">
	<s:iterator var="newL" value="#attr.newList">
	<tr>
		<s:set var="break" value="%{false}"/>
		<s:iterator value="#request.products">
		<s:if test="!#break">
			<s:if test="id==#newL.pid">
			<s:set var = "break" value="%{true}"/>
			<td>
			<s:property value="name"/>
			</td>
			<td>
			<s:property value="price"/>
			<s:set var="prc" value="price"/>
			</td>
			</s:if>
		</s:if>
	    </s:iterator>
		<td width="300"><s:property value="#newL.quantity"/></td>
		<td width="300"><s:property value="%{#newL.quantity * #attr.prc}"/></td>
		<s:set var="totprc" value="%{#attr.totprc + #newL.quantity * #attr.prc}"/>
	</tr>
	</s:iterator> 
	</s:sort>
	<tr><td>total price:</td>
	<td><s:property value="#attr.totprc"/></td>
	</tr>
	</s:if>
	</table>
    <form action="BrowseProd.action" method="get">
    <input type="submit" value="see product"/>
    </form>
  <%} else {%>
    <h3>You are not customer, please log in as customer first.</h3>
    <a href="login.jsp">login</a>
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
