<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<title>Dettagli Passaggio</title>

<!-- <link rel="stylesheet" href="css/mainstyle.css" type="text/css" /> -->

    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/ourAdditions.css">
    <link rel="stylesheet" href="css/greenTable.css">
    <link rel="stylesheet" href="css/orangeTable.css">
    <link rel="stylesheet" href="css/blueTable.css">
    
    <script type="text/javascript" src="js/hoverMapValue.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
    
	<script type="text/javascript" src="js/setMapValue.js"></script>
	<script type="text/javascript" src="js/checkingTime.js"></script>
	<script src="js/calendar/tcal.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css"  href="js/calendar/tcal.css">
	<!-- <link rel="stylesheet" type="text/css" href="js/GoogleMap/map.css">
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true&language=it"></script>
	<script type="text/javascript" src="js/GoogleMap/map.js"></script> -->
	
	<!--  	Google Place Autocomplete -->
<!--  	<meta name="viewport" content="initial-scale=1.0, user-scalable=no"> -->
<!--     <meta charset="utf-8"> -->
<!--  	<link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500"> -->
<!--     <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script> -->
<!--     <link rel="stylesheet" href="js/GooglePlaceAutocomplete/placeAutocomplete.css"> -->
<!--  	<script src="js/GooglePlaceAutocomplete/placeAutocomplete.js" type="text/javascript"></script> -->
<!--     end -->


   <meta charset="utf-8">
	<link rel="icon" href="images/favicon.ico">
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/form.css">
<!-- 	<script src="js/cssJs/jquery.js"></script> -->
	<script src="js/cssJs/jquery-migrate-1.1.1.js"></script>
	<script src="js/cssJs/superfish.js"></script>
	<script src="js/cssJs/jquery.equalheights.js"></script>
	<script src="js/cssJs/jquery.easing.1.3.js"></script>
	<script src="js/cssJs/jquery.ui.totop.js"></script>
	<script>
	$(document).ready(function () {
	    $().UItoTop({
	        easingType: 'easeOutQuart'
	    });
	});
	</script>

</head>
<body>
<header>
  	<div class="container_12">
		    <div class="grid_12">
	    <form method="post" action="LoginServlet">
			<table class="login">
				<tr>
					<td><label>Email:</label></td>
					<td><input type="text" size="40" name="email" id="email" /></td>
				</tr>
				<tr>
					<td><label>Password:</label></td>
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
			      <h1><a href="/GiveMeALift"><img src="images/logo.png" alt=""></a> </h1>
		      <div class="menu_block">
		        <nav>
		          <ul class="sf-menu">
		            <li><a href="/GiveMeALift">Home</a></li>
		            <li><a href="products.html">Products</a></li>
		            <li><a href="blog.html">Blog</a></li>
		            <li class="current"><a href="recipes.html">Recipes</a></li>
		            <li><a href="contacts.html">Contacts</a></li>
		          </ul>
		        </nav>
		        <div class="clear"></div>
		      </div>
		      <div class="clear"></div>
		    </div>
		  </div>

	</header>
	<div class="content">
  	<div class="container_12">
     <div class="grid_12">
        <p class="locationField" id="mapValues" style="text-align: center" >
			<input class="autocomplete" type="text" id=mapFrom name=mapFrom value="${from}" /> 
			<input id=reverse value="" title="Inverti partenza e arrivo" type="button" />
			<input class="autocomplete" type="text" id=mapTo name=mapTo value="${to}"  />
		</p> 
	<form>
		<p class="center">
			<input type="button" value="Indietro" onClick="javascript:history.back()" name="button">
		</p>
	</form>
	</div>
     <div class="grid_7">
	<div class="greenTable" >
	<table id="liftDetails">
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
			<tr>
				<td class="labelTable">
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
				<td class="labelTable">
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
				<td class="labelTable">
					Data
				</td>
				<td>
					${lift.departureDate}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					Ora
				</td>
				<td>
					${lift.departureTime}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					Flessibilità oraria
				</td>
				<td>
					${lift.liftPreferences.scheduleFlexibility}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					Deviazioni
				</td>
				<td>
					TODO
				</td>
			</tr>
			<tr>
				<td class="labelTable">
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
				<td class="labelTable">
					Auto
				</td>
				<td>
					${lift.userOffering.driverInfo.car.brand}
					 ${lift.userOffering.driverInfo.car.model}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
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
				<td class="labelTable">
					Tipo di strada
				</td>
				<td>
					${lift.liftPreferences.roadType}
				</td>
			</tr>
	</table>
	</div>
	</div>
	<div class="grid_4 prefix_1">
	<div class="orangeTable" >
	<table>
			<tr>
				<td>
					Prezzo
				</td>
			</tr>
			<tr>
				<td>
					${lift.cost}  &#8364; a persona
				</td>
			</tr>
	</table>
	</div>
	 <div class="orangeTable" >
		<table>
		<tr>
			<td>
					Posti disponibili
				</td>
		</tr>
		<tr>
				<td>
					${lift.nSeat}
				</td>
		</tr>
	</table>
	</div>
	<div class="orangeTable" >
		<table >
		<tr>
			<td>
				Prenota il viaggio
				</td>
			</tr>
			<tr>
				<td>
					TODO
				</td>
		</tr>
	</table>
	</div>

<div class="blueTable" >
	<table>
			<tr>
				<td>
					Conducente
				</td>
			</tr>
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
</div>
</div>
</div>
</body>
</html>