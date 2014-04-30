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
    
    <title>My JSP 'product_order.jsp' starting page</title>
    
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
    <h1>PRODUCT ORDER</h1>
    <a href="product_browsing.jsp">product browsing</a>
    
    <%
  if(session.getAttribute("username") != null) { %>
    <h2>Hello, <%=session.getAttribute("username")%></h2> 
    <p>-----------insert product order------------------</p>  

	<table border="1">
	<tr>
		<th>id</th>
		<th>name</th>
		<th>SKU</th>
		<th>category</th>
		<th>price</th>
		<th>quantity</th>
	</tr>
	<tr>
		<s:iterator value="#request.justBuy">
		<form action="OrderProd.action" method="post">
		<input type="hidden" name="id" value=<s:property value="id" />/>
		<td width="300"><s:property value="id"/></td>
		<td width="300"><s:property value="name" escape="false"/></td>
		<td width="300"><s:property value="sku" escape="false"/></td>
		<td width="300">
		<s:iterator value="#request.categories">
			<s:if test="id==cid">
			<s:property value="name"/>
			</s:if>
	    </s:iterator>
		</td>
		<td width="300"><s:property value="price" escape="false"/></td>
		<td width="300"><input name="qty"></td>
		<td><input type="submit" value="submit"></td>
		</form>
		</s:iterator>
	</tr>
	
	<s:if test="#request.cartProd!=null">
	<s:bean name="SortBuy" var="sortref"></s:bean>
	<s:sort comparator="sortref" source="#request.cartProd" var="newList">
	<s:iterator var="newL" value="#attr.newList">
	<tr>
		<td width="300"><s:property value="#newL.id"/></td>
		<td width="300"><s:property value="#newL.name" escape="false"/></td>
		<td width="300"><s:property value="#newL.sku" escape="false"/></td>
		<td width="300">
		<s:iterator value="#request.categories">
			<s:if test="id==#newL.cid">
			<s:property value="name"/>
			</s:if>
	    </s:iterator>
		</td>
		<td width="300"><s:property value="#newL.price" escape="false"/></td>
		<td width="300"><s:property value="#newL.qty" escape="false"/></td>
	</tr>
	</s:iterator>
	</s:sort>
	</s:if>
	</table>  
    
    <a href="product_order.jsp">product order</a>
  <%} else {%>
    <h3>Please log in first.</h3>
    <a href="mainPage.jsp">mainPage</a>
   <%} %>
   
 	<div class="mainpg">
 	<button type="button" onclick="window.location='mainPage.jsp'">mainPage</button>
 	</div>
 	<div class="cartpg">
 	<form action="ListCart.action" method="get">
 	<input type="submit" value="shopping_cart"/>
 	</form>
 	</div>
  </body>
</html>
