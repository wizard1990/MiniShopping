<%@ page language="java" import="java.util.*, DBModel.CustomerListElement, DBModel.ProductListElement" pageEncoding="ISO-8859-1"%>

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
            <li class="active"><a href="#">MAINPAGE</a></li>
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
        <h1>MiniShopping System</h1>
        <%
        if(session.getAttribute("username") != null) { %>
          <h2>Hello, <%=session.getAttribute("username")%></h2> 
          <% if(session.getAttribute("userrole").equals("owner")) {%>
          <form action="ListCate.action" method="get">
          <input class="btn btn-lg btn-primary" type="submit" value="category"/>
          </form>
          <br>
          <form action="ListProd.action" method="get">
          <input class="btn btn-lg btn-primary" type="submit" value="product"/>
          </form>
          <br>
          <form action="Dashboard.action" method="get">
          <input class="btn btn-lg btn-primary" type="submit" value="dashboard"/>
          </form>
          <% } else {%> 
          <form action="BrowseProd.action" method="get">
          <input class="btn btn-lg btn-primary" type="submit" value="see product"/>
          </form>
          <%} %>
          <br>
          <% } else { %>
          <h2>Hello, guest.</h2>
          <button class="btn btn-lg btn-primary" type="button" onclick="window.location='signup.jsp'">sign up</button><br>
          <br>
          <button class="btn btn-lg btn-primary" type="button" onclick="window.location='login.jsp'">login</button><br>
         <%} %>

        <%if(session.getAttribute("username") != null && session.getAttribute("userrole").equals("customer")) {%>
        <div class="cartpg">
        <form action="ListCart.action" method="get">
        <input class="btn btn-lg btn-primary" type="submit" value="shopping_cart"/>
        </form>
        </div>
        <%} %>
      </div>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
