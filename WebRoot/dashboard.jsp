<%@ page language="java" import="java.util.*, DBModel.CustomerListElement, DBModel.ProductListElement" pageEncoding="ISO-8859-1"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dashboard.jsp' starting page</title>
    
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
  <h1>DASHBOARD</h1>
    <%if(session.getAttribute("username") == null || session.getAttribute("userrole").equals("1")) {%>
  <h2>You are not owner and you will be redirected to main page now.</h2>
  <%response.setHeader("refresh", "2; URL=mainPage.jsp");} else{ %>
  
 	<form action="Filter.action" method="get">
    <table>
    
    <tr>
	<td>
	<select name="quarter">
		<option value="">All quarters</option>
		<option value="spring">spring</option>
		<option value="summer">summer</option>
		<option value="fall">fall</option>
		<option value="winter">winter</option>
    </select>
	</td>
	<td>
	<select name="age">
		<option value="">All ages</option>
		<option value="0">0~9</option>
		<option value="10">10~19</option>
		<option value="20">20~29</option>
		<option value="30">30~39</option>
		<option value="40">40~49</option>
		<option value="50">50~59</option>
		<option value="60">60~69</option>
		<option value="70">70~79</option>
		<option value="80">80~89</option>
		<option value="90">above 90</option>
    </select>
	</td>
	</tr>
	
	<tr>
	<td>
	<select name="cid">
		<option value="">All categories</option>
	<s:iterator value="#request.categories">
    	<option value=<s:property value="id"/>><s:property value="name"/></option>
    </s:iterator>
    </select>
	</td>
	<td>
	<select name="state">
		<option value="">All states</option>
		<option value="Alabama">Alabama</option>
		<option value="Alaska">Alaska</option>
		<option value="Arizona">Arizona</option>
		<option value="Arkansas">Arkansas</option>
		<option value="California">California</option>
		<option value="Colorado">Colorado</option>
		<option value="Connecticut">Connecticut</option>
		<option value="Delaware">Delaware</option>
		<option value="Florida">Florida</option>
		<option value="Georgia">Georgia</option>
		<option value="Hawaii">Hawaii</option>
		<option value="Idaho">Idaho</option>
		<option value="Illinois">Illinois</option>
		<option value="Indiana">Indiana</option>
		<option value="Iowa">Iowa</option>
		<option value="Kansas">Kansas</option>
		<option value="Kentucky">Kentucky</option>
		<option value="Louisiana">Louisiana</option>
		<option value="Maine">Maine</option>
		<option value="Maryland">Maryland</option>
		<option value="Massachusetts">Massachusetts</option>
		<option value="Michigan">Michigan</option>
		<option value="Minnesota">Minnesota</option>
		<option value="Mississippi">Mississippi</option>
		<option value="Missouri">Missouri</option>
		<option value="Montana">Montana</option>
		<option value="Nebraska">Nebraska</option>
		<option value="Nevada">Nevada</option>
		<option value="New Hampshire">New Hampshire</option>
		<option value="New Jersey">New Jersey</option>
		<option value="New Mexico">New Mexico</option>
		<option value="New York">New York</option>
		<option value="North Carolina">North Carolina</option>
		<option value="North Dakota">North Dakota</option>
		<option value="Ohio">Ohio</option>
		<option value="Oklahoma">Oklahoma</option>
		<option value="Oregon">Oregon</option>
		<option value="Pennsylvania">Pennsylvania</option>
		<option value="Rhode Island">Rhode Island</option>
		<option value="South Carolina">South Carolina</option>
		<option value="South Dakota">South Dakota</option>
		<option value="Tennessee">Tennessee</option>
		<option value="Texas">Texas</option>
		<option value="Utah">Utah</option>
		<option value="Vermont">Vermont</option>
		<option value="Virginia">Virginia</option>
		<option value="Washington">Washington</option>
		<option value="West Virginia">West Virginia</option>
		<option value="Wisconsin">Wisconsin</option>
		<option value="Wyoming">Wyoming</option>
	</select>
	</td>
	</tr>
	
	<tr>
	<td>
	<select name="rowType">
		<option value="customer">customer</option>
		<option value="state">state</option>
    </select>
    </td>
    </tr>
	<tr>
	<td>
	<input type="submit" value="Run Query">
	</td>
	</tr>
    </table>
    </form>
    
    <br>
    <br>

    <% if(request.getAttribute("colPage") != null) { %>
    <table>
    <tr>
    <td>&nbsp;</td>
    <!-- product -->
    <s:iterator value="#request.collist">
    <td width="200"><s:property value="name" escape="false"/></td>
    </s:iterator>
    <td>
    <form action="nextProd.action" method="get">
    <input type="hidden" name="pagenum" value="<%=(Integer)request.getAttribute("colPage")+1 %>"/>
    <%if((Integer)request.getAttribute("colPage") < (Integer)request.getAttribute("maxColPage")) {%>
    <input type="submit" value="next">
    <%} else{ %>
    <input type="submit" value="next" disabled="true">
    <%} %>
    </form>
    </td>
    <td>
    <form action="prevProd.action" method="get">
    <input type="hidden" name="pagenum" value="<%=(Integer)request.getAttribute("colPage")-1 %>"/>
    <%if((Integer)request.getAttribute("colPage") > 1) {%>
    <input type="submit" value="prev">
    <%} else{ %>
    <input type="submit" value="prev" disabled="true">
    <%} %>
    </form>
    </td>  
    </tr>
    
    <tr>
    <td>
    <table>
    <!-- customer -->
    <s:iterator value="#request.rowlist">
    <tr><s:property value="name" escape="false"/><br></tr>
    </s:iterator>
    <tr>
    <form action="nextCus.action" method="get">
    <input type="hidden" name="pagenum" value="<%=(Integer)request.getAttribute("rowPage")+1 %>"/>
    <%if((Integer)request.getAttribute("rowPage") < (Integer)request.getAttribute("maxRowPage")) {%>
    <input type="submit" value="next">
    <%} else{ %>
    <input type="submit" value="next" disabled="true">
    <%} %>
    </form>
    </tr>
    <tr>
    <form action="prevCus.action" method="get">
    <input type="hidden" name="pagenum" value="<%=(Integer)request.getAttribute("rowPage")-1 %>"/>
    <%if((Integer)request.getAttribute("rowPage") > 1) {%>
    <input type="submit" value="prev">
    <%} else{ %>
    <input type="submit" value="prev" disabled="true">
    <%} %>
    </form>
    </tr>
    </table>
    </td> 
    
    <td>
    <table border="1">
    <!-- big table -->
    <%int i,j; 
    int rownum=((List)request.getAttribute("rowlist")).size();
    int colnum=((List)request.getAttribute("collist")).size();
    %>
    <tr>
    <td width="200">&nbsp;</td>
    <td width="200">&nbsp;</td>
    <%for(j=0;j<colnum;j++) { %>
    <td width="200"><%=((ProductListElement)(((List)request.getAttribute("collist")).get(j))).getName() %></td>
    <%} %>
    </tr>
    <tr>
    <td width="200">&nbsp;</td>
    <td width="200">&nbsp;</td>
    <%for(j=0;j<colnum;j++) { %>
    <td width="200"><%=((ProductListElement)(((List)request.getAttribute("collist")).get(j))).getProfit() %></td>
    <%} %>
    </tr>
    <%for(i=0;i<rownum;i++) { %>
    <tr>
    <td width="200"><%=((CustomerListElement)(((List)request.getAttribute("rowlist")).get(i))).getName() %></td>
    <td width="200"><%=((CustomerListElement)(((List)request.getAttribute("rowlist")).get(i))).getCost() %></td>
    <%for(j=0;j<colnum;j++) { %>
    <td width="200"><%=((List)request.getAttribute("bigResult")).get(colnum*i+j) %></td>
    <%} %>
    </tr>
    <%} %>
    </table>
    </td>
    </tr>
    </table> 
    
  <%} } %>
  </body>
</html>
