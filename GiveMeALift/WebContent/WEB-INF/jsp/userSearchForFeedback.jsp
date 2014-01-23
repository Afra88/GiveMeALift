<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>	 
    <head>
    
	<title>Lascia un feedback</title>
    
    <meta charset="utf-8">
    
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/ourAdditions.css">
    <link rel="stylesheet" href="css/pictogram-button.css">
    
    <link rel="icon" href="images/favicon.ico">
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/form.css">
	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">   
   
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
	    <c:choose>
    		<c:when test="${user!=null}">
    			<%@include file="signedUpForm.jsp"%>
    		</c:when>
    		<c:otherwise>
		    	<%@include file="logInForm.jsp"%>
    		</c:otherwise>
    	</c:choose>
       <h1><a href="/GiveMeALift"><img src="images/logo.png" alt=""></a> </h1>   
		<c:choose>
          <c:when test="${user!=null}">
          	<%@include file="userMenu.jsp" %>
          </c:when>
          <c:otherwise>
         	 <%@include file="classicMenu.jsp" %>
          </c:otherwise>
       	</c:choose>    
  	</div>
  </div>
</header>
<div class="container_12">
	<div class="grid_12">	
	<c:choose>
		<c:when test="${error!=null}">
			<div class="clear" ></div>
			<h4 class="center" >${error}</h4>
		</c:when>
	</c:choose>
	<div class="clear" ></div>
	<br>
	<h4>Lascia un feedback a un altro utente</h4>
	<h5>Ritrova un utente con il quale hai viaggiato indicando il suo numero di cellulare </h5>
		<form action="UserInsertFeedback" method="post" id="form">
			<input type="text" value="3XX XXX XXXX" name="telephone" id="telephone" >
			<input type="submit" class="button cyan" value="Trova">
		</form>
</div>
</div>


<%@include file="footer.jsp"%>