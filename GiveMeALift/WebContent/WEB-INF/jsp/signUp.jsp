<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>	 
    <head>
    
	<title>Registrati!</title>
    
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
   
    <link rel="stylesheet" href="css/ourAdditions.css">
    <link rel="stylesheet" href="css/pictogram-button.css">
    <link rel="icon" href="images/favicon.ico">
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/form.css">    
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
   	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<!-- <script src="js/cssJs/jquery.js"></script> -->
	<script src="js/cssJs/jquery-migrate-1.1.1.js"></script>
	<script src="js/cssJs/superfish.js"></script>
	<script src="js/cssJs/jquery.equalheights.js"></script>
	<script src="js/cssJs/jquery.easing.1.3.js"></script>
	<script src="js/cssJs/jquery.ui.totop.js"></script>
	<script type="text/javascript" src="js/jQueryElement/jquery.format.1.05.js"></script>
    
	<script type="text/javascript" src="js/checkSigningUp.js"></script>
	  	
	<script>
	$(document).ready(function () {
	    $().UItoTop({
	        easingType: 'easeOutQuart'
	    });
	});
	</script>	
</head>
<body onload="initialize()" >
<header>
  <div class="container_12">
    <div class="grid_12">
	<h1><a href="/GiveMeALift"><img src="images/logo.png" alt=""></a> </h1>   
	<%@include file="classicMenu.jsp" %>
	</div>
	</div>
</header>
<div class="container_12">
	<div class="grid_12">
	<br>
	<c:if test="${error==true}">
		<h4>Email o password errata. Riprova!</h4>
	</c:if>
	<br>
	<form method="post" action="LogIn">
		<div id="null"></div>
		Indirizzo email: <br /><input id="email" name="email" type="text" size="40" /><br /><br />
		Password:  <br /> <input id="ps" type="password" name="psw" /><br /><br />
		<input id="send" type="button" class="button cyan" value="Accedi" /><br /><br />
	</form>
	</div>
</div>
<%@include file="footer.jsp"%> 

