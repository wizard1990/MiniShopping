<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<style type="text/css">

body {background-color: white}
h1 {color:#CC00FF; 
font-family: 'Palatino Linotype', 'Book Antiqua', Palatino, serif; 
background-color: #CCCCFF}
h2 {color:#FF00FF; 
font-family: 'Comic Sans MS', cursive, sans-serif; 
font-style: italic;
background-color: #FFCCFF}
h3 {color:#FF9900; 
font-family: 'Trebuchet MS', Helvetica, sans-serif; 
font-style: italic;
font-weight: bold;
font-variant: small-caps;
background-color: #FFFFCC}
p.no2 {background-color: #99CCFF;
border: medium double #000000;
padding: 20px;}

</style>

</head>

<body>

<h1>this is h1</h1>
<h2>this is h2</h2>
<h3>this is h3</h3>
<p>this paragraph</p>
<p class="no2">this has inner margin</p>

</body>
</html>
