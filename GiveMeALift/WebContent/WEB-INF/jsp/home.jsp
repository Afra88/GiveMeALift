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
<title>Give me a lift!</title>
</head>
<body>
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


<form method="get" action="ResultSearch">
<p id="mapValues">
	<span class="label">Partenza: </span>
		<input type="text" id=mapFrom name=mapFrom /> 
	<span class="label"> Arrivo: </span>
		<input type="text" id=mapTo name=mapTo />
	<span id=date><script type="text/javascript" src="js/currentDate.js"></script></span>
	<input class="button"  id="FromAToB" type="button" value="Percorso" />
	<input type="submit" value="Cerca" class="button" />
</p>
</form>
<p id="map"></p>
</div>
</body>
</html>