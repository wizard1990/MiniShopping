<%@ page language="java" import="java.util.*, DBModel.CustomerListElement, DBModel.StateListElement, DBModel.ProductListElement" pageEncoding="ISO-8859-1"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="/ico/favicon.ico">

    <title>Dashboard</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/navbar-fixed-top.css" rel="stylesheet">

    <style type="text/css">
    table.fixed
	{
		table-layout:fixed; 
		width: 1100px;
	}

	table.fixed td 
	{
		width: 80px;
		overflow: hidden; 
		text-overflow: ellipsis;
		word-wrap:break-word;
		display: inline-block;
	    white-space: nowrap;
	}

	.solid 
	{
		border-style:solid;
		border-top-style:solid;
		border-width: 1px;
	}
    </style>
  </head>

  <body>

    <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="mainPage.jsp">Minishopping</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">DASHBOARD</a></li>
            <li><a href="http://db.ucsd.edu/CSE135S14/handouts/project.pdf">About</a></li>
            <li><a href="https://github.com/wizard1990/">resource</a></li>
            <li class="developers">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Members <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">Yan Zhang</a></li>
                <li><a href="#">Yingyan Hua</a></li>
                <li><a href="#">Ding Sun</a></li>
              </ul>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="mainPage.jsp">main page</a></li>
            <li class="active"><a href="logout.jsp">log out</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h1>DASHBOARD</h1>
  
	  <%if(session.getAttribute("username") == null || session.getAttribute("userrole").equals("customer")) {%>
	  <h2>You are not owner and you will be redirected to main page now.</h2>
	  <%response.setHeader("refresh", "2; URL=mainPage.jsp");} else{ %>
	  
	  <%if (request.getAttribute("rowPage") == null || ((Integer)request.getAttribute("rowPage") == 1 && (Integer)request.getAttribute("colPage") == 1 )) {%>
	 	<form action="Filter.action" method="get">
	    <table>
	    
		<tr>
		<td>
		row type:
		<select name="rowType"  class="form-control">
			<%if (session.getAttribute("sRowtype") != null && session.getAttribute("sRowtype").equals("customer")) {%>
			<option value="customer" selected="selected">customer</option>
			<%} else{%>
			<option value="customer">customer</option>
			<%} %>
			
			<%if (session.getAttribute("sRowtype") != null && session.getAttribute("sRowtype").equals("state")) {%>
			<option value="state" selected="selected">state</option>
			<%} else{%>
			<option value="state">state</option>
			<%} %>	
	
	    </select>
	    </td>
	    </tr>
	    
		<tr>
	    <td>
		category:
		<select name="cid"  class="form-control">
			<option value="">All categories</option>
			<s:iterator value="#request.categories">
				<%if(session.getAttribute("sCate") != null) {%>
				<s:if test="id==#session.sCate">
				<option value=<s:property value="id"/> selected="selected"><s:property value="name"/></option>
				</s:if>
				<s:else>
		    	<option value=<s:property value="id"/>><s:property value="name"/></option>
		    	</s:else>
		    	<%} else { %>
		    	<option value=<s:property value="id"/>><s:property value="name"/></option>
		    	<%} %>
		    </s:iterator>
	    </select>
		</td> 
		<td>
		&nbsp;
		</td>

		<td>
		state:
		<select name="state"  class="form-control">
			<option value="">All states</option>
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Alabama")) {%>
			<option value="Alabama" selected="selected">Alabama</option>
			<%} else{%>
			<option value="Alabama">Alabama</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Alaska")) {%>
			<option value="Alaska" selected="selected">Alaska</option>
			<%} else{%>
			<option value="Alaska">Alaska</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Arizona")) {%>
			<option value="Arizona" selected="selected">Arizona</option>
			<%} else{%>
			<option value="Arizona">Arizona</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Arkansas")) {%>
			<option value="Arkansas" selected="selected">Arkansas</option>
			<%} else{%>
			<option value="Arkansas">Arkansas</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("California")) {%>
			<option value="California" selected="selected">California</option>
			<%} else{%>
			<option value="California">California</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Colorado")) {%>
			<option value="Colorado" selected="selected">Colorado</option>
			<%} else{%>
			<option value="Colorado">Colorado</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Connecticut")) {%>
			<option value="Connecticut" selected="selected">Connecticut</option>
			<%} else{%>
			<option value="Connecticut">Connecticut</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Delaware")) {%>
			<option value="Delaware" selected="selected">Delaware</option>
			<%} else{%>
			<option value="Delaware">Delaware</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Florida")) {%>
			<option value="Florida" selected="selected">Florida</option>
			<%} else{%>
			<option value="Florida">Florida</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Georgia")) {%>
			<option value="Georgia" selected="selected">Georgia</option>
			<%} else{%>
			<option value="Georgia">Georgia</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Hawaii")) {%>
			<option value="Hawaii" selected="selected">Hawaii</option>
			<%} else{%>
			<option value="Hawaii">Hawaii</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Idaho")) {%>
			<option value="Idaho" selected="selected">Idaho</option>
			<%} else{%>
			<option value="Idaho">Idaho</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Illinois")) {%>
			<option value="Illinois" selected="selected">Illinois</option>
			<%} else{%>
			<option value="Illinois">Illinois</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Indiana")) {%>
			<option value="Indiana" selected="selected">Indiana</option>
			<%} else{%>
			<option value="Indiana">Indiana</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Iowa")) {%>
			<option value="Iowa" selected="selected">Iowa</option>
			<%} else{%>
			<option value="Iowa">Iowa</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Kansas")) {%>
			<option value="Kansas" selected="selected">Kansas</option>
			<%} else{%>
			<option value="Kansas">Kansas</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Kentucky")) {%>
			<option value="Kentucky" selected="selected">Kentucky</option>
			<%} else{%>
			<option value="Kentucky">Kentucky</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Louisiana")) {%>
			<option value="Louisiana" selected="selected">Louisiana</option>
			<%} else{%>
			<option value="Louisiana">Louisiana</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Maine")) {%>
			<option value="Maine" selected="selected">Maine</option>
			<%} else{%>
			<option value="Maine">Maine</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Maryland")) {%>
			<option value="Maryland" selected="selected">Maryland</option>
			<%} else{%>
			<option value="Maryland">Maryland</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Massachusetts")) {%>
			<option value="Massachusetts" selected="selected">Massachusetts</option>
			<%} else{%>
			<option value="Massachusetts">Massachusetts</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Michigan")) {%>
			<option value="Michigan" selected="selected">Michigan</option>
			<%} else{%>
			<option value="Michigan">Michigan</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Minnesota")) {%>
			<option value="Minnesota" selected="selected">Minnesota</option>
			<%} else{%>
			<option value="Minnesota">Minnesota</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Mississippi")) {%>
			<option value="Mississippi" selected="selected">Mississippi</option>
			<%} else{%>
			<option value="Mississippi">Mississippi</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Missouri")) {%>
			<option value="Missouri" selected="selected">Missouri</option>
			<%} else{%>
			<option value="Missouri">Missouri</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Montana")) {%>
			<option value="Montana" selected="selected">Montana</option>
			<%} else{%>
			<option value="Montana">Montana</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Nebraska")) {%>
			<option value="Nebraska" selected="selected">Nebraska</option>
			<%} else{%>
			<option value="Nebraska">Nebraska</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Nevada")) {%>
			<option value="Nevada" selected="selected">Nevada</option>
			<%} else{%>
			<option value="Nevada">Nevada</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("New Hampshire")) {%>
			<option value="New Hampshire" selected="selected">New Hampshire</option>
			<%} else{%>
			<option value="New Hampshire">New Hampshire</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("New Jersey")) {%>
			<option value="New Jersey" selected="selected">New Jersey</option>
			<%} else{%>
			<option value="New Jersey">New Jersey</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("New Mexico")) {%>
			<option value="New Mexico" selected="selected">New Mexico</option>
			<%} else{%>
			<option value="New Mexico">New Mexico</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("New York")) {%>
			<option value="New York" selected="selected">New York</option>
			<%} else{%>
			<option value="New York">New York</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("North Carolina")) {%>
			<option value="North Carolina" selected="selected">North Carolina</option>
			<%} else{%>
			<option value="North Carolina">North Carolina</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("North Dakota")) {%>
			<option value="North Dakota" selected="selected">North Dakota</option>
			<%} else{%>
			<option value="North Dakota">North Dakota</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Ohio")) {%>
			<option value="Ohio" selected="selected">Ohio</option>
			<%} else{%>
			<option value="Ohio">Ohio</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Oklahoma")) {%>
			<option value="Oklahoma" selected="selected">Oklahoma</option>
			<%} else{%>
			<option value="Oklahoma">Oklahoma</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Oregon")) {%>
			<option value="Oregon" selected="selected">Oregon</option>
			<%} else{%>
			<option value="Oregon">Oregon</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Pennsylvania")) {%>
			<option value="Pennsylvania" selected="selected">Pennsylvania</option>
			<%} else{%>
			<option value="Pennsylvania">Pennsylvania</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Rhode Island")) {%>
			<option value="Rhode Island" selected="selected">Rhode Island</option>
			<%} else{%>
			<option value="Rhode Island">Rhode Island</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("South Carolina")) {%>
			<option value="South Carolina" selected="selected">South Carolina</option>
			<%} else{%>
			<option value="South Carolina">South Carolina</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("South Dakota")) {%>
			<option value="South Dakota" selected="selected">South Dakota</option>
			<%} else{%>
			<option value="South Dakota">South Dakota</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Tennessee")) {%>
			<option value="Tennessee" selected="selected">Tennessee</option>
			<%} else{%>
			<option value="Tennessee">Tennessee</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Texas")) {%>
			<option value="Texas" selected="selected">Texas</option>
			<%} else{%>
			<option value="Texas">Texas</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Utah")) {%>
			<option value="Utah" selected="selected">Utah</option>
			<%} else{%>
			<option value="Utah">Utah</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Vermont")) {%>
			<option value="Vermont" selected="selected">Vermont</option>
			<%} else{%>
			<option value="Vermont">Vermont</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Virginia")) {%>
			<option value="Virginia" selected="selected">Virginia</option>
			<%} else{%>
			<option value="Virginia">Virginia</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Washington")) {%>
			<option value="Washington" selected="selected">Washington</option>
			<%} else{%>
			<option value="Washington">Washington</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("West Virginia")) {%>
			<option value="West Virginia" selected="selected">West Virginia</option>
			<%} else{%>
			<option value="West Virginia">West Virginia</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Wisconsin")) {%>
			<option value="Wisconsin" selected="selected">Wisconsin</option>
			<%} else{%>
			<option value="Wisconsin">Wisconsin</option>
			<%} %>
	
			<%if (session.getAttribute("sState") != null && session.getAttribute("sState").equals("Wyoming")) {%>
			<option value="Wyoming" selected="selected">Wyoming</option>
			<%} else{%>
			<option value="Wyoming">Wyoming</option>
			<%} %>
		</select>
		</td>
		</tr>
		
		<tr>
		<td>
		age:
		<select name="age"  class="form-control">
			<option value="">All ages</option>
		
			<%if (session.getAttribute("sAge") != null && session.getAttribute("sAge").equals("12")) {%>
			<option value="12" selected="selected">12~18</option>
			<%} else{%>
			<option value="12">12~18</option>
			<%} %>
			
			<%if (session.getAttribute("sAge") != null && session.getAttribute("sAge").equals("18")) {%>
			<option value="18" selected="selected">18~45</option>
			<%} else{%>
			<option value="18">18~45</option>
			<%} %>		
			
			<%if (session.getAttribute("sAge") != null && session.getAttribute("sAge").equals("45")) {%>
			<option value="45" selected="selected">45~65</option>
			<%} else{%>
			<option value="45">45~65</option>
			<%} %>
			
			<%if (session.getAttribute("sAge") != null && session.getAttribute("sAge").equals("65")) {%>
			<option value="65" selected="selected">above 65</option>
			<%} else{%>
			<option value="65">above 65</option>
			<%} %>
	    </select>
		</td>
		</tr>
		
<%-- 		<!-- ---------------------------------------------- -->
		<%session.setAttribute("sAge", "65"); 
		session.setAttribute("sState", "Washington"); 
		session.setAttribute("sCate", "C1");
		session.setAttribute("sRowtype", "state");
		%>
		<!-- ---------------------------------------------- --> --%>
		<tr>
		<td>
		&nbsp;
		</td>
		</tr>
		<tr>
		<td>
		<input class="btn btn-lg btn-primary" type="submit" value="Run Query">
		</td>
		</tr>
	    </table>
	    </form>
	    <%} %>
	    
	    <br>
	    <br>
	
	    <% if(request.getAttribute("colPage") != null) { %>
	    
	    <!-- big table -->
	    <table class="fixed">
	    <%int i,j; 
	    int rownum=((List)request.getAttribute("rowlist")).size();
	    int colnum=((List)request.getAttribute("collist")).size();
	    %>
	    <tr>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	    <%for(j=0;j<colnum;j++) { %>
	    <td style="font-weight:bold;"><%=((ProductListElement)(((List)request.getAttribute("collist")).get(j))).getName() %></td>
	    <%} %>
	    </tr>
	    <tr>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	    <%for(j=0;j<colnum;j++) { %>
	    <td style="font-weight:bold;">($<%=((ProductListElement)(((List)request.getAttribute("collist")).get(j))).getProfit() %>)</td>
	    <%} %>
	    <%if(colnum<10) {%>
	    <td><input type="button" value="next10" disabled="true"></td>
	    <%} else{ %>
	    <td><form action="NextPage.action" method="get">
	    <input type="hidden" name="colPage" value="<%=(Integer)request.getAttribute("colPage")+1 %>"/>
	    <input type="hidden" name="rowPage" value="<%=(Integer)request.getAttribute("rowPage") %>"/>	    
	    <input type="submit" value="next10">
	    </form></td>
	    <%} %>
	    </tr> 
	    <%for(i=0;i<rownum;i++) { %>
	    <tr>
	    <%if(session.getAttribute("sRowtype").equals("customer")) {%>
	    <td style="font-weight:bold;"><%=((CustomerListElement)(((List)request.getAttribute("rowlist")).get(i))).getName() %></td>
	    <td style="font-weight:bold;">($<%=((CustomerListElement)(((List)request.getAttribute("rowlist")).get(i))).getCost() %>)</td>
	    <%} else{ %>
	    <td style="font-weight:bold;"><%=((StateListElement)(((List)request.getAttribute("rowlist")).get(i))).getName() %></td>
	    <td style="font-weight:bold;">($<%=((StateListElement)(((List)request.getAttribute("rowlist")).get(i))).getCost() %>)</td>	    
	    <%} %>
	    <%for(j=0;j<colnum;j++) { %>
	    <td class="solid"><%=((List)request.getAttribute("bigResult")).get(colnum*i+j) %></td>
	    <%} %>
	    </tr>
	    <%} %>
	    </table>
	    
	    
	    <%if(rownum<20) {%>
	    <input type="button" value="next20" disabled="true">
	    <%} else{%>
	    <form action="NextPage.action" method="get" style="left:90px;">
	    <input type="hidden" name="colPage" value="<%=(Integer)request.getAttribute("colPage") %>"/>
	    <input type="hidden" name="rowPage" value="<%=(Integer)request.getAttribute("rowPage")+1 %>"/>
	    <input type="submit" value="next20">
	    </form>
	    <%} %>
	
	  <%} } %>
      </div>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
