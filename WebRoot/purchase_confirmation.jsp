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
    
    <title>My JSP 'purchase_confirmation.jsp' starting page</title>
    
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
  if(session.getAttribute("username") != null) { %>
    <h2>Hello, <%=session.getAttribute("username")%></h2> 
    <p>-----------insert product browsing------------------</p>
    <a href="product_browsing.jsp">product browsing</a>
    
    <table border="1">
	<tr>
		<th>product name</th>
		<th>price</th>
		<th>quantity</th>
		<th>total price</th>
	</tr>

	<s:if test="#request.transactions!=null">

	<s:bean name="SortTransactions" var="sortref"></s:bean>
	<s:sort comparator="sortref" source="#request.transactions" var="newList">
	<s:iterator var="newL" value="#attr.newList">
	<tr>
		<input type="hidden" name="id" value=<s:property value="#newL.id" />/>
		<s:set var="break" value="%{false}"/>
		<s:iterator value="#request.products">
		<s:if test="!#break">
			<s:if test="id==#newL.id">
			<s:set var = "break" value="%{true}"/>
			<td>
			<s:property value="name"/>
			</td>
			<td>
			<s:property value="price"/>
			</td>
			</s:if>
		</s:if>
	    </s:iterator>
		<td width="300"><s:property value="#newL.quantity"/></td>
		<td width="300"><s:property value="#newL.price" escape="false"/></td>
		<td><input type="submit" value="Order"></td>
	</tr>
	</s:iterator> 
	</s:sort>
	</s:if>
	</table>
    
  <%} else {%>
    <h3>Please log in first.</h3>
    <a href="login.jsp">login</a>
   <%} %>
   
 	<div class="mainpg">
 	<button type="button" onclick="window.location='mainPage.jsp'">mainPage</button>
 	</div>
 	<div class="cartpg">
 	<button type="button" onclick="window.location='shopping_cart.jsp'">myCart</button>
 	</div>
   
  </body>
</html>
