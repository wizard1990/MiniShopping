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
    
    <title>product order</title>
    
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
	  function checkQty()
	  {
		var val = document.getElementById("qty").value;
		if (val < 0 )
		{
		    var txt = "illegal quantity";
		    alert(txt);
		    return false;
		}
	  }
	  </script>
  
    <h1>PRODUCT ORDER</h1>
    
    <%
  if(session.getAttribute("username") != null && session.getAttribute("userrole").equals("customer")) { %>
    <h2>Hello, <%=session.getAttribute("username")%></h2> 
    <p>-----------insert product order------------------</p>  

    <table border="1">
	<tr>
		<th>product name</th>
		<th>price</th>
		<th>quantity</th>
		<th>total price</th>
	</tr>
	
	<form action="InsertCart" method="post" onsubmit="return checkQty();">
	<tr>
	<%String name = request.getParameter("name");
	name = name.substring(0, name.length() - 1);
	String price = request.getParameter("price");
	price = price.substring(0, price.length() - 1);
	String pid = request.getParameter("id");
	pid = pid.substring(0, pid.length() - 1); %>
		<input type="hidden" name="pid" value="<%=pid%>"/>
		<td width="300"><%=name %></td>
		<td width="300"><%=price %></td>
		<td width="300"><input type="text" name="quantity" id="qty"></td>
		<td width="300"><input type="submit" value="Add"></td>
	</tr>
	</form>
	
	<s:if test="#request.transactions!=null">
	<s:set var="totprc" value="0"/>

	<s:bean name="SortTransactions" var="sortref"></s:bean>
	<s:sort comparator="sortref" source="#request.transactions" var="newList">
	<s:iterator var="newL" value="#attr.newList">
	<tr>
	    <td width="300"><s:property value="#newL.products.name"/></td>	 
		<td width="300"><s:property value="#newL.price"/></td>	    
		<s:set var="prc" value="#newL.price"/>
		<td width="300"><s:property value="#newL.quantity"/></td>
		<td width="300"><s:property value="%{#newL.quantity * #attr.prc}"/></td>
		<s:set var="totprc" value="%{#attr.totprc + #newL.quantity * #attr.prc}"/>
	</tr>
	</s:iterator> 
	</s:sort>
	<tr><td>current total price:</td>
	<td><s:property value="#attr.totprc"/></td>
	</tr>
	
	</s:if>
	</table>  

  <%} else {%>
    <h3>You are not customer, lease log in as customer first.</h3>
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
