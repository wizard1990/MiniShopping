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
    
    <title>My JSP 'product_browsing.jsp' starting page</title>
    
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
  <h1>PRODUCT BROWSING</h1>
    <%
  if(session.getAttribute("username") != null) { %>
    <h2>Hello, <%=session.getAttribute("username")%></h2> 
    <p>-----------insert product browsing------------------</p>
    
    
	<table>
	<tr>
	<form action="SearchProd.action" method="post">
	<td>
	<select name="cate1">
		<option value="allprod">All products</option>
	<s:iterator value="#request.categories">
    	<option value=<s:property value="name"/>><s:property value="name"/></option>
    </s:iterator>
    </select>
	</td>
	<td>search here: <input value="" name="keyword" size="20"/></td>
	<td><input type="submit" value="search"/></td>
	</form>
	</tr>
	</table>

	<table border="1">
	<tr>
		<th>id</th>
		<th>name</th>
		<th>SKU</th>
		<th>category</th>
		<th>price</th>
	</tr>
	
	<s:if test="#request.products!=null">

	<s:bean name="SortCategory" var="sortref"></s:bean>
	<s:sort comparator="sortref" source="#request.products" var="newList">
	<s:iterator var="newL" value="#attr.newList">
	
	<tr>
		<form action="OrderProd.action" method="post">
		<input type="hidden" name="id" value=<s:property value="#newL.id" />/>
		<td><input value=<s:property value="#newL.id"/> name="id" size="10" disabled=true/></td>
		<td><input value=<s:property value="#newL.name" escape="false"/> name="name" size="10"/></td>
		<td><input value=<s:property value="#newL.sku" escape="false"/> name="sku" size="20"/></td>
		<td><input value=<s:property value="#newL.category.name" escape="false"/> name="sku" size="20"/></td>
		<td><input value=<s:property value="price" escape="false"/> name="price" size="10"/></td>
		<td><input type="submit" value="Order"></td>
		</form>
	</tr>
	</s:iterator>
	</s:sort>
	</s:if>
	</table>  
    
    <a href="product_order.jsp">product order</a>
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
