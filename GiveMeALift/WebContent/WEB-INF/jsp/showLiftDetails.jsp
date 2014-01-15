<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	</div>
	 <p class="locationField" id="mapValues" style="text-align: center" >
		<span class="label">Partenza: </span>
		<input class="autocomplete" onFocus="geolocate()" type="text" id=mapFrom name=mapFrom value="${from}" /> 
		<input class="button" id=reverse value="" tabindex="5" title="Inverti partenza e arrivo" type="button" />
		<span class="label"> Arrivo: </span>
		<input class="autocomplete" onFocus="geolocate()" type="text" id=mapTo name=mapTo value="${to}" />
		<input type="submit" value="Cerca" class="button" />
	</p>
	TORNA INDIETRO ..
	
	<table id="liftDetails" class="table">
		<thead>
			<tr>
				<td colspan="2">
					${lift.pickUpPoint.city}
					-
					<c:forEach items="${lift.detours}" var="i" >
						${i.dropOffPoint.city}
					</c:forEach>
					${lift.dropOffPoint.city}
				</td>
			</tr>
		</thead>
			<tr>
				<td>
					Partenza
				</td>
				<td>
					${lift.pickUpPoint.region},
					${lift.pickUpPoint.city},
					${lift.pickUpPoint.province},
					${lift.pickUpPoint.street}
				</td>
			</tr>
			<tr>
				<td>
					Arrivo
				</td>
				<td>
					${lift.dropOffPoint.region},
					${lift.dropOffPoint.city},
					${lift.dropOffPoint.province},
					${lift.dropOffPoint.street}
				</td>
			</tr>
			<tr>
				<td>
					Data
				</td>
				<td>
					${lift.departureDate}
				</td>
			</tr>
			<tr>
				<td>
					Ora
				</td>
				<td>
					${lift.departureTime}
				</td>
			</tr>
			<tr>
				<td>
					Flessibilità oraria
				</td>
				<td>
					${lift.liftPreferences.scheduleFlexibility}
				</td>
			</tr>
			<tr>
				<td>
					Deviazioni
				</td>
				<td>
					TODO
				</td>
			</tr>
			<tr>
				<td>
					Dimensione del bagaglio
				</td>
				<td>
					<c:choose>
						<c:when test="${lift.liftPreferences.luggageSize}==1">
							Piccolo
						</c:when>
						<c:when test="${lift.liftPreferences.luggageSize}==2">
							Medio
						</c:when>
						<c:when test="${lift.liftPreferences.luggageSize}==3">
							Grande
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>
					Auto
				</td>
				<td>
					${lift.userOffering.driverInfo.car.brand}
					 ${lift.userOffering.driverInfo.car.model}
				</td>
			</tr>
			<tr>
				<td>
					Viaggio Rosa
				</td>
				<td>
					<c:choose>
						<c:when test="${lift.liftPreferences.pinkTrip}==true">
							Si
						</c:when>
						<c:otherwise>
							No
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>
					Tipo di strada
				</td>
				<td>
					${lift.liftPreferences.roadType}
				</td>
			</tr>
	</table>
	
	<table class="table" >
			<tr>
				<td>
					Prezzo
				</td>
				<td>
					${lift.cost}  &#8364; a persona
				</td>
				<td>
					Posti disponibili
				</td>
				<td>
					${lift.nSeat}
				</td>
				<td>
					Prenota il viaggio
				</td>
				<td>
					TODO
				</td>
			</tr>
	</table>
	
	<table class="table">
		<thead>
			<tr>
				<td>
					Conducente
				</td>
			</tr>
		</thead>
			<tr>
				<td>
					<%-- <img src="${lift.user.profilePhoto}" /> --%>
				</td>
			</tr>
			<tr>
				<td>
					<%-- ${lift.user.getYears()} --%>
				</td>
			</tr>
			<tr>
				<td>
					<!-- user activity  -->
				</td>
			</tr>
	
	
	</table>
</div>
</body>
</html>