<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>	 
    <head>
    
	<title>I miei messaggi</title>
    
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/ourAdditions.css">
    <link rel="stylesheet" href="css/pagination.css" type="text/css" />
    <link rel="stylesheet" href="css/simpleTable.css">
    <link rel="stylesheet" href="css/pictogram-button.css">
    <link rel="stylesheet" href="css/bubbleLeft.css">
    <link rel="stylesheet" href="css/bubbleRight.css">
    
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
	  
	<script>
	$(document).ready(function () {
	    $().UItoTop({
	        easingType: 'easeOutQuart'
	    });
	});
	    </script>	
</head>
<body onload="initialize()" >
<%@include file="chooseMenu.jsp"%>
<div class="content">
	<div class="container_12">
		<div class="grid_12">
		<div class="clear"></div>
		<c:choose>
		<c:when test="${out==null}">
			<h4>Scrivi un messaggio al conducente.</h4>
		     <div>
				<form action="SendMessage" method="post" > <%//TODO se è null Conversation perchè è una nuova  %>
					<span class="bubbleRight">
		 				<span class="message">
	 						<textarea rows="4" cols="92" maxlength="255" autofocus="autofocus" name="text" ></textarea>
 							<button type="submit" class="button cyan" >Invia</button>
						</span>
					</span> 
 					<c:choose>
						<c:when test="${user.profilePhoto!=null}">
		         				<img height="70px" alt="${user.computeNickName()}" src="${user.profilePhoto}">
						</c:when>
						<c:when test="${user.profilePhoto==null}">
		         				<img height="70px" src="avatars/default_user.jpg" />
						</c:when>
       				</c:choose>
				</form>
			</div>
			<form action="BookALift" method="post" >
				<h4>Oppure prenota direttamente un posto in questo passaggio.
					<input type="hidden" value="${seat}" name="seat" />
					<input type="hidden" value="${lift}" name="lift" />
					<button class="button cyan">Prenota </button> 
				</h4>
			</form>
		</c:when>
		<c:otherwise>
			<h4>${out}</h4>
		</c:otherwise>
		</c:choose>
	  	</div>
  	</div>
</div>

<%@include file="footer.jsp"%>