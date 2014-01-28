<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>

<title>Lascia un feedback</title>

<meta charset="utf-8">



<link rel="stylesheet" href="css/greenTable.css">
<link rel="stylesheet" href="css/blueTable.css">
<link rel="stylesheet" href="css/orangeTable.css">

<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/ourAdditions.css">
<link rel="stylesheet" href="css/pictogram-button.css">

<link rel="icon" href="images/favicon.ico">
<link rel="shortcut icon" href="images/favicon.ico">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" href="css/form.css">


<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<!-- <script src="js/cssJs/jquery.js"></script> -->
<script src="js/cssJs/jquery-migrate-1.1.1.js"></script>
<script src="js/cssJs/superfish.js"></script>
<script src="js/cssJs/jquery.equalheights.js"></script>
<script src="js/cssJs/jquery.easing.1.3.js"></script>
<script src="js/cssJs/jquery.ui.totop.js"></script>
<script type="text/javascript"
	src="js/jQueryElement/jquery.format.1.05.js"></script>

<script>
	$(document).ready(function () {
	    $().UItoTop({
	        easingType: 'easeOutQuart'
	    });
	});
	    </script>
</head>
<body onload="initialize()">
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
				<h1>
					<a href="/GiveMeALift"><img src="images/logo.png" alt=""></a>
				</h1>
				<c:choose>
					<c:when test="${user!=null}">
						<%@include file="userMenu.jsp"%>
					</c:when>
					<c:otherwise>
						<%@include file="classicMenu.jsp"%>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</header>



	<div class="container_12">
	<br><br>
		<div class="grid_4">
			<div class="blueTable">
				<table class="table">
					<tr>
						<td> <a href="UserSearchForFeedback">Lascia un feedback</a></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="grid_4">
			<div class="blueTable">
			<table>
				<tr>
					<td> <a href="ReceivedFeedback">Feedback ricevuti</a></td>
				</tr>
			</table>
			</div>
			</div>
		
			<div class="grid_4">
			<div class="blueTable">
			<table>
				<tr>
					<td><a href="ReleasedFeedback">Feedback lasciati</a></td>
				</tr>
			</table>
		</div>
		</div>
<!-- 	</div> -->
	


	<div class="grid_12" align="center">
			
			<c:choose>
				<c:when test="${error!=null}">
					<div class="clear"></div>
					<h4 class="center">${error}</h4>
				</c:when>
			</c:choose>
			<div class="clear"></div>
			<br>
			
			
			
			<h3 class=>Lascia un feedback ad un altro utente!</h3>
			<h4>Trova un utente con il quale hai viaggiato indicando il suo
				numero di cellulare:</h4>
			<form action="UserInsertFeedback" method="post" id="form">
				<input id="telephone" name="telephone" type="text" placeholder="3XX XXX XXXX"  maxlength="10" > 
				<input type="submit" class="button cyan" value="Trova">
			</form>
		</div>
	</div>



	<%@include file="footer.jsp"%>