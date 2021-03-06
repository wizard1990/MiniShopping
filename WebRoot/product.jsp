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
    
    <title>product</title>
    
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
  function checkProd()
  {
	var val = document.getElementById("iname").value;
	var sku = document.getElementById("isku").value;
	var price = document.getElementById("iprice").value;
	if (val == "" || sku == "" || price == "")
	{
		alert("do not be empty");
	    return false;
	}
  }
  </script>
  
  
  <h1>PRODUCT</h1>
  <%if(session.getAttribute("username") == null || session.getAttribute("userrole").equals("customer")) {%>
  <h2>You are not owner and you will be redirected to main page now.</h2>
  <%response.setHeader("refresh", "2; URL=mainPage.jsp");} else{ 
    if(request.getAttribute("isSucc") != null && request.getAttribute("isSucc").equals(0))
  {%>
  <h2>Modified failed.</h2>
  <%} else if(request.getAttribute("isSucc") != null && request.getAttribute("isSucc").equals(2)) {%>
  <h2>Failure to insert new product.</h2>
  <%} else if(request.getAttribute("inserted") != null) {%>
  <h3>you just inserted successfully as below</h3>
  <table border="1">
  <tr>
  <td>name</td>
  <td>sku</td>
  <td>category</td>
  <td>price</td>
  </tr>
  <tr>
  <td width="100"><s:property value="#request.inserted.name" escape="false"/></td>
  <td width="100"><s:property value="#request.inserted.sku" escape="false"/></td>
  <td width="100"><s:property value="#request.cname" escape="false"/></td>
  <td width="100"><s:property value="#request.inserted.price" escape="false"/></td>
  </tr>
  </table>
  
  <%} %>
  <h2>Hello, <%=session.getAttribute("username")%></h2> 
    This is PRODUCT. <br>
    
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

	<tr>
		<form action="InsertProd.action" method="post" onsubmit="return checkProd();">
		<!-- <th><input value="" name="id" size="10" disabled=true/></th> -->
		<th><input value="" name="name" id="iname" size="10"/></th>
		<th><input value="" name="sku" id="isku" size="20"/></th>
		<th>
		<select name="cid">
		<s:iterator value="#request.categories">
	    	<option value=<s:property value="id"/>><s:property value="name"/></option>
	    </s:iterator>
	    </select>
		</th>
		<th><input value="" name="price" id="iprice" size="10"/></th>
		<th><input type="submit" value="Insert"/></th>
		</form>
	</tr>
	
	<s:if test="#request.products!=null">

	<s:bean name="SortProduct" var="sortref"></s:bean>
	<s:sort comparator="sortref" source="#request.products" var="newList">
	<s:iterator var="newL" value="#attr.newList">
	
	<tr>
		<form action="UpdateProd.action" method="post">
 		<input type="hidden" name="id" value=<s:property value="#newL.id" />/>
 		<!-- <td><input value=<s:property value="#newL.id"/> name="id" size="10" disabled=true/></td> -->
		<td><input value=<s:property value="#newL.name" escape="false"/> name="name" size="10"/></td>
		<td><input value=<s:property value="#newL.sku" escape="false"/> name="sku" size="20"/></td>
<!-- 		<s:set name="oldcid" value="#newL.cid"></s:set> -->
		<input type="hidden" name="oldCid" value=<s:property value="#newL.categories.id" />/>
		<td>
		<select name="cid">
		<s:iterator value="#request.categories">
			<s:if test="id==#newL.categories.id">
			<option value=<s:property value="id"/> selected="selected"><s:property value="name"/></option>
			</s:if>
			<s:else>
	    	<option value=<s:property value="id"/>><s:property value="name"/></option>
	    	</s:else>
	    </s:iterator>
	    </select>
		</td>
		<td><input value=<s:property value="#newL.price" escape="false"/> name="price" size="10"/></td>
		<td><input type="submit" value="Update"></td>
		</form>
        <form action="DeleteProd.action" method="post">
			<input type="hidden" name="sku" value=<s:property value="#newL.sku" />/>
			<%-- Button --%>
			<td><input type="submit" value="Delete"/></td>
 		</form>
	</tr>
	</s:iterator>
	</s:sort>
	</s:if>
	</table>
	</td>
	</tr>
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
