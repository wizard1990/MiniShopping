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
    
    <title>My JSP 'shopping_cart.jsp' starting page</title>
    
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
  <h1>SHOPPING CART</h1>
    <%
  if(session.getAttribute("username") != null) { %>
    <h2>Hello, <%=session.getAttribute("username")%></h2> 
    <p>-----------insert product browsing------------------</p>
    <a href="product_browsing.jsp">product browsing</a>
    <a href="purchase_confirmation.jsp">purchase confirmation</a>
    
    
    <table border="1">
	<tr>
		<th>id</th>
		<th>product name</th>
		<th>SKU</th>
		<th>quantity</th>
		<th>price</th>
		<th>tot price?</th>
	</tr>
    <s:if test="#request.cartProd!=null">
	<s:bean name="SortBuy" var="sortref"></s:bean>
	<s:sort comparator="sortref" source="#request.cartProd" var="newList">
	<s:iterator var="newL" value="#attr.newList">
	<tr>
		<td width="300"><s:property value="#newL.id"/></td>
		<td width="300"><s:property value="#newL.name" escape="false"/></td>
		<td width="300"><s:property value="#newL.sku" escape="false"/></td>
		<td width="300"><s:property value="#newL.qty" escape="false"/></td>
		<td width="300"><s:property value="#newL.price" escape="false"/></td>
		<td width="300"><s:property value="#newL.price" escape="false"/></td>
	</tr>
	</s:iterator>
	</s:sort>
	<tr>total price:</tr>
	<tr>Pay for it</tr>
	<form action="PayProd.action" method="post">
	<tr>
	<td>credit card: <input name="creditCard"/></td>
	<td><input type="hidden" name="username" value=<%=session.getAttribute("username") %>/></td>
	</tr>
	<tr>
	<td><input type="submit" value="purchase"></td>
	</tr>
	</form>
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
