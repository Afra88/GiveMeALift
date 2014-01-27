<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>	 
    <head>
    
	<title>Offri un passaggio</title>
    
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/ourAdditions.css">
    <link rel="stylesheet" href="css/greenTable.css">
    <link rel="stylesheet" href="css/pagination.css" type="text/css" />
    <link rel="stylesheet" href="css/blueTable.css">
    <link rel="stylesheet" href="css/orangeTable.css">
    <link rel="stylesheet" href="css/pictogram-button.css">
    
    <link rel="icon" href="images/favicon.ico">
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/form.css">
	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" type="text/css"  href="js/calendar/tcal.css">
	
	<link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
    <link rel="stylesheet" href="js/GooglePlaceAutocomplete/placeAutocomplete.css">
   
   
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
    
	 <!--  	Google Place Autocomplete -->
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places&language=it&region=IT"></script>
 	<script src="js/GooglePlaceAutocomplete/placeAutocomplete.js" type="text/javascript"></script>
	<!--     end -->
	
	<script type="text/javascript" src="js/setMapValue.js"></script>
    <script type="text/javascript" src="js/hoverMapValue.js"></script>
	<script src="js/calendar/tcal.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/managePossibleDetour.js"></script>
	<script type="text/javascript" src="js/checkInsertedFields.js"></script>
	<script type="text/javascript" src="js/GoogleMapNew/map.js" ></script>
   
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
	<form method="get" action="Step2UpdateLiftInsertALift">
	<input type="hidden" value="${lift.id}" name="lift" >
	<input type="hidden" value="${liftReturn.id}" name="liftReturn" >
	<p class="greenTable">
		<table id="fillOffer">
			<tr><td colspan="2">Itinerario</td></tr>
			<tr id="mapTr">
			<td colspan="2">
				<p><input class="button" type="button"  id="FromAToB"  value="Percorso" /></p>
				<div id="map_canvas" style="float:left;width:100%;height:400px"></div>
			</td>
			</tr>
			<tr>
				<td>
				Luogo di Partenza:				
				<c:choose>
					<c:when test="${lift!=null}" >
						<input class="autocomplete" type="text" id=mapFrom name=mapFrom value="${lift.pickUpPoint.city}" /> 
					</c:when>
					<c:otherwise>
						<input class="autocomplete" type="text" id=mapFrom name=mapFrom value="Partenza" /> 
					</c:otherwise>
				</c:choose>
				</td>
				<td>
				Luogo di Arrivo:
				<c:choose>
					<c:when test="${lift!=null}" >
						<input class="autocomplete" type="text" id=mapTo name=mapTo value="${lift.dropOffPoint.city}"  />
					</c:when>
					<c:otherwise>
						<input class="autocomplete" type="text" id=mapTo name=mapTo value="Arrivo"  /> 
					</c:otherwise>
				</c:choose>		 
				</td>
			</tr>
			<tr>
				<td colspan="2"><p class="label"> Aggiungi Tappe intermedie:
				</p>				
					<ol>
						<c:choose>
							<c:when test="${lift!=null && path!=null && path.size()>0}" >
								<c:forEach var="i" begin="1" end="${path.size()-1}" step="1" >
									<li><input id="detour${i}" class="autocomplete" type="text" name="detour${i}" value="${path.get(i)}" ></li>
								</c:forEach>
							</c:when>
						</c:choose>	
					</ol>
				</td>
			</tr>
			<tr>
				<td>
					<input class="button" type="button"  id="add_detour" value="Aggiungi"  />
				</td>
				<td>
					<input class="button" type="button"  id="remove_detour" value="Rimuovi"/>
				</td>
			</tr>
		
		
			<tr>
				<td colspan="2"><h2>Data e ora</h2></td>
			<tr>
			<tr>
				<td><span class="label"> Data partenza: </span> 
					<c:choose>
						<c:when test="${lift!=null}">
							<span id=date><input type="text" name="goingDate" class="tcal" value="${goingDate}" ></span>
						</c:when>
						<c:otherwise>
<!-- 						<script type="text/javascript" src="js/currentDate.js"></script> -->
							<span id=date><input type="text" name="goingDate" class="tcal" value="" ></span>
						</c:otherwise>
					</c:choose>
				</td>
				<td>
				<c:choose>
					<c:when test="${lift!=null}">
						<span class="label"> Ora partenza: </span>
						<select id="goingTimeH" name="goingTimeH" >
						  <c:forEach var="i" step="1" begin="00" end="23" >
						  <c:choose>
						  	<c:when test="${i==goingTimeH}">
						  		<option selected="selected">${i}</option>
						  	</c:when>
						  	<c:otherwise>
								<option>${i}</option>
						  	</c:otherwise>
						  </c:choose>
							</c:forEach>
						</select> 
						:														  						
						<select id="goingTimeM" name="goingTimeM">
						 <c:forEach var="i" step="1" begin="00" end="59" >
						  <c:choose>
							<c:when test="${i==goingTimeM}">
						  		<option selected="selected">${i}</option>
						  	</c:when>
						  	<c:otherwise>
								<option>${i}</option>
						  	</c:otherwise>
						  </c:choose>
						</c:forEach>							
						</select> 	 
					</c:when>
					<c:otherwise>
						<span class="label"> Ora partenza: </span>
						<select id="goingTimeH" name="goingTimeH" >
						  <c:forEach var="i" step="1" begin="00" end="23" >
							<option>${i}</option>
							</c:forEach>
						</select> 														  						
						<select id="goingTimeM" name="goingTimeM">
						 <c:forEach var="i" step="1" begin="00" end="59" >
							<option>${i}</option>
						</c:forEach>							
						</select> 	 
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<c:if test="${lift!=null && lift.getReturnLift()!=null}">
			<tr>
				<td><span class="label"> Data ritorno (se previsto): </span> 
					<c:choose>
						<c:when test="${lift!=null}">
							<span id=date><input type="text" name="date" class="tcal" value="${returnDate}" ></span>
						</c:when>
						<c:otherwise>
<!-- 						<script type="text/javascript" src="js/currentDate.js"></script> -->
							<span id=date><input type="text" name="date" class="tcal" value="" ></span>
						</c:otherwise>
					</c:choose>
				</td>
			<td>
				<c:choose>
					<c:when test="${lift!=null}">
						<span class="label"> Ora ritorno (se previsto): </span>
						<select id="returnTimeH" name="returnTimeH" >
						  <c:forEach var="i" step="1" begin="00" end="23" >
						  <c:choose>
						  	<c:when test="${i==returnTimeH}">
						  		<option selected="selected">${i}</option>
						  	</c:when>
						  	<c:otherwise>
								<option>${i}</option>
						  	</c:otherwise>
						  </c:choose>
							</c:forEach>
						</select> 														  						
						<select id="returnTimeM" name="returnTimeM">
						 <c:forEach var="i" step="1" begin="00" end="59" >
						  <c:choose>
							<c:when test="${i==returnTimeM}">
						  		<option selected="selected">${i}</option>
						  	</c:when>
						  	<c:otherwise>
								<option>${i}</option>
						  	</c:otherwise>
						  </c:choose>
						</c:forEach>							
						</select> 	 
					</c:when>
					<c:otherwise>
						<span class="label"> Ora ritorno: </span>
						<select id="returnTimeH" name="returnTimeH" >
						  <c:forEach var="i" step="1" begin="00" end="23" >
							<option>${i}</option>
							</c:forEach>
						</select> 														  						
						<select id="returnTimeM" name="returnTimeM">
						 <c:forEach var="i" step="1" begin="00" end="59" >
							<option>${i}</option>
						</c:forEach>							
						</select> 	 
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			</c:if>
			<tr>
				<td colspan="2">
					<input type="submit" value="Conferma" class="button" />
				</td>
			</tr>
		</table>
	</form>
</div>
</div>


<%@include file="footer.jsp"%>
<!-- 	</body> -->
<!-- </html> -->