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
    
    <title>product browsing</title>
    
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
  if(session.getAttribute("username") != null && session.getAttribute("userrole").equals("customer")) { %>
    <h2>Hello, <%=session.getAttribute("username")%></h2> 

    <table>
    <tr>
    <td>
    
    <table>
     <tr>
	<form action="SearchProd.action" method="get">
	<input type="hidden" name="cid" value="<%=request.getAttribute("currentcid") %>">
	<td>search here: <input value="" name="keyWord" size="20"/></td>
	<td><input type="submit" value="search"/></td>
	</form>
	</tr>
    </table>
    
    <table>
    <tr>
    <td>
    <form action="SearchProd.action" method="get">
    	<input type="hidden" name="cid" value="">
    	<input type="hidden" name="keyWord" value="">
    	<input type="submit" value="all products" style="background: #CCCCCC">
    </form>
	<s:iterator value="#request.categories">
    	<form action="SearchProd.action" method="get">
    	<input type="hidden" name="cid" value=<s:property value="id"/>>
    	<input type="hidden" name="keyWord" value="">
    	<input type="submit" value=<s:property value="name"/> style="background: #CCCCCC">
    	</form>
    </s:iterator>
    </td>
    
    <td>

	<table border="1">
	<tr>
		<!-- <th>id</th> -->
		<th>name</th>
		<th>SKU</th>
		<th>category</th>
		<th>price</th>
	</tr>
	
	<s:if test="#request.products!=null">

	<s:bean name="SortProduct" var="sortref"></s:bean>
	<s:sort comparator="sortref" source="#request.products" var="newList">
	<s:iterator var="newL" value="#attr.newList">
	
	<tr>
		<form action="ListCart.action" method="post">
		<input type="hidden" name="id" value=<s:property value="#newL.id" />/>
		<input type="hidden" name="name" value=<s:property value="#newL.name"/>/>
		<input type="hidden" name="price" value=<s:property value="#newL.price" />/>
		<input type="hidden" name="po" value="1"/>
		<!-- <td width="300"><s:property value="#newL.id"/></td> -->
		<td width="300"><s:property value="#newL.name" escape="false"/></td>
		<td width="300"><s:property value="#newL.sku" escape="false"/></td>
		<td width="300">
		<s:iterator value="#request.categories">
			<s:if test="id==#newL.categories.id">
			<s:property value="name"/>
			</s:if>
	    </s:iterator>
		</td>
		<td width="300"><s:property value="#newL.price" escape="false"/></td>
		<td><input type="submit" value="Order"></td>
		</form>
	</tr>
	</s:iterator>
	</s:sort>
	</s:if>
	</table>  
	
	</td>
	</tr>
    </table>
    
  <%} else {%>
    <h3>You are not customer, please log in as customer first.</h3>
    <a href="mainPage.jsp">mainPage</a>
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
