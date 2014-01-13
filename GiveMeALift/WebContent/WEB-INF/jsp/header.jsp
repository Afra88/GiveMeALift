<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<link rel="stylesheet" href="css/mainstyle.css" type="text/css" />
	<script type="text/javascript" src="js/setMapValue.js"></script>
	<script type="text/javascript" src="js/checkingTime.js"></script>
	<script src="js/calendar/tcal.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css"  href="js/calendar/tcal.css">
	<link rel="stylesheet" type="text/css" href="js/GoogleMap/map.css">
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true&language=it"></script>
	<script type="text/javascript" src="js/GoogleMap/map.js"></script>

	<!-- Google Place Autocomplete -->
 	<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
 	<link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
    <link rel="stylesheet" href="js/GooglePlaceAutocomplete/placeAutocomplete.css">
 	<script src="js/GooglePlaceAutocomplete/placeAutocomplete.js" type="text/javascript"></script>
    <!-- end -->


<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>

<!-- geonames
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<link rel="stylesheet" type="text/css" href="js/geonames/geonames.css">
<script type="text/javascript" src="js/geonames/geonames.js"></script>
 end -->

<script type="text/javascript" src="js/managePossibleDetour.js"></script>
<script type="text/javascript" src="js/checkInsertedFields.js"></script>





<title>Give me a lift!</title>
</head>
<body onload="initialize()">
<div class="container">
	<div class="header">
	    <form method="post" action="LoginServlet">
			<table class="login">
				<tr>
					<td>Email:</td>
					<td><input type="text" size="40" name="email" id="email" /></td>
				</tr>
				<tr>
					<td>Password:</td>
				    <td><input type="password" size="40" name="psw" id="psw" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Accedi" />
						oppure
						<a href="userRegistration">Registrati</a>
					</td>
				</tr>
			</table>
		</form>
		<form method="get" action="OfferALift">
			<input type="submit" value="Offri un passaggio!" class="button" />
		</form>
	</div>

