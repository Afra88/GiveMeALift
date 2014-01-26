<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html;" %>
<html>	 
    <head>
    
	<title>Offri un passaggio</title>
    
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/ourAdditions.css">
    <link rel="stylesheet" href="css/greenTable.css">
    <link rel="stylesheet" href="css/pagination.css">
    <link rel="stylesheet" href="css/blueTable.css">
    <link rel="stylesheet" href="css/orangeTable.css">
    <link rel="stylesheet" href="css/pictogram-button.css">
    
    <link rel="icon" href="images/favicon.ico">
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/form.css">
	
<!-- 	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css"> -->
	<link rel="stylesheet" type="text/css"  href="js/calendar/tcal.css">
	
<!-- 	<link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500"> -->
    <link rel="stylesheet" href="js/GooglePlaceAutocomplete/placeAutocomplete.css">
    
<!--      	Google Place Autocomplete
   	 <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
    <script src="https://maps.googleapis.com/maps/api/js?&sensor=false&libraries=places&language=it&region=IT"></script>
    //maps.googleapis.com/maps/api/js?sensor=false&libraries=places&client=gme-comuto
 	<script src="js/GooglePlaceAutocomplete/placeAutocomplete.js" type="text/javascript"></script>
	end -->
    
    <script src="js/cssJs/jquery.js"></script>
   	<script src="js/cssJs/jquery-migrate-1.1.1.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<!-- 	<script src="js/cssJs/superfish.js"></script>
	<script src="js/cssJs/jquery.equalheights.js"></script>
	<script src="js/cssJs/jquery.easing.1.3.js"></script>
	<script src="js/cssJs/modernizr.custom.63321.js"></script>
	<script src="js/cssJs/jquerypp.custom.js"></script>
	<script src="js/cssJs/jquery.ui.totop.js"></script>
	<script src="js/cssJs/jquery.elastislide.js"></script>
	<script src="js/cssJs/jquery.catslider.js"></script>
	    -->

	 <!--  	Google Place Autocomplete -->
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
	<script type="text/javascript" src="js/GoogleMapNew/map.js" ></script> 
	<!--     end -->
	
    <script type="text/javascript" src="js/hoverMapValue.js"></script>
	<script src="js/calendar/tcal.js" type="text/javascript"></script>
<!-- 	<script type="text/javascript" src="js/manageDetourAutocomplete.js"></script> -->
	<script type="text/javascript" src="js/managePossibleDetour.js"></script>
   
	<script>
	$(document).ready(function () {
		$("#checkReturn").change(function(){
// 	 	    alert(this.val());
			if($("#checkReturn").val()=="false"){
	   		    $("#checkReturn").val("true");
// 	 			alert($("#checkReturn").val());
			}
			else if($("#checkReturn").val()=="true"){
	  		    $("#checkReturn").val("false");
// 	  		  alert($("#checkReturn").val());
			}
	  		 	$("#returnDate").toggle();
		      });
		 /*    $().UItoTop({
		        easingType: 'easeOutQuart'
		    }); */
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
<div class="content">
<div class="container_12">
	<div class="grid_12">
		
<!-- 	<div class="ui-widget" style="margin-top: 2em; font-family: Arial"> -->
<!-- 		Result: -->
<!-- 		<div id="log" style="height: 200px; width: 300px; overflow: auto;" -->
<!-- 			class="ui-widget-content"></div> -->
<!-- 	</div> -->
	
	<c:choose>
		<c:when test="${error!=null}">
			<div class="clear" ></div>
			<h4 class="center" >${error}</h4>
		</c:when>
	</c:choose>

	<form method="get" action="InsertALift">
	<p class="greenTable">
		<table id="fillOffer">
					<tr><td colspan="2">Itinerario</td></tr>
			<tr id="mapTr">
			<td colspan="2">
			<p><input class="button" type="button"  id="FromAToB"  value="Percorso" /></p>
			<div id="map_canvas" style="float:left;width:100%;height:400px"></div>
<!-- 				<div style="float:right;width:100px;height:400px;overflow:auto"> -->
<!-- 			  	<button id="undo" style="display:block;margin:auto" onclick="undo()">Undo -->
<!-- 			 	 </button> -->
<!-- 			 	<div id="directions_panel" style="width:200px"></div> -->
<!-- 			</div> -->
			</td>
			</tr>
			<tr>
			<tr>
				<td>
				<label for="mapFrom">Luogo di Partenza</label>				
				<input class="autocomplete" type="text" id=mapFrom name=mapFrom /> 
				</td>
				<td>
				<label for="mapTo" >Luogo di arrivo</label>
				<input class="autocomplete" type="text" id=mapTo name=mapTo />
				</td>
			</tr>
			<tr>
				<td colspan="2"><h5>Tappe intermedie</h5>				
					<ol>
						<li id="li0" hidden="true" >
							<input id="detour0" class="autocomplete" type="text" name="detour0"></input>
							<button class="button red" id="0" ><span class="trash" ></span></button>
						</li>
						<li id="li1" hidden="true" >
							<input id="detour1" class="autocomplete" type="text" name="detour1"></input>
							<button class="button red" id="1" ><span class="trash" ></span></button>
						</li>
						<li id="li2" hidden="true" >
							<input id="detour2" class="autocomplete" type="text" name="detour2"></input>
							<button class="button red" id="2" ><span class="trash" ></span></button>
						</li>
						<li id="li3" hidden="true" >
							<input id="detour3" class="autocomplete" type="text" name="detour3"></input>
							<button class="button red" id="3" ><span class="trash" ></span></button>
						</li>
						<li id="li4" hidden="true" >
							<input id="detour4" class="autocomplete" type="text" name="detour4"></input>
							<button class="button red" id="4" ><span class="trash" ></span></button>
						</li>
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
				<td colspan="2"><h2>Data e ora di Andata</h2></td>
			<tr>
			<tr>
				<td>
					<span class="label"> Data partenza </span> 
					<input type="text" class="tcal" name="goingDate" />
				</td>
				<td>
					<span class="label"> Ora partenza </span>
					<select id="goingTimeH" name="goingTimeH" >
					  <c:forEach var="i" step="1" begin="0" end="23" >
					 	<c:choose>
					  	<c:when test="${i<=9}">
					  		<option>0${i}</option>
					  	</c:when>
					  	<c:otherwise>
						<option>${i}</option>
					  	</c:otherwise>
					 	</c:choose>
						</c:forEach>
					</select> 	
					:													  						
					<select id="goingTimeM" name="goingTimeM">
					 <c:forEach var="i" step="1" begin="0" end="59" >
						<c:choose>
					  	<c:when test="${i<=9}">
					  		<option>0${i}</option>
					  	</c:when>
					  	<c:otherwise>
						<option>${i}</option>
					  	</c:otherwise>
					 	</c:choose>
					</c:forEach>							
					</select> 	 
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="checkbox" name="checkReturn" id="checkReturn" value="false" > 
					<span>Ritorno Previsto</span>
				</td>
			</tr>
			<tr id="returnDate" hidden="true" >
				<td><span class="label"> Data ritorno </span> 			
					<input type="text" class="tcal" name="returnDate" />
				</td>
				<td>
					<span class="label"> Ora ritorno </span>					
						<select id="returnTimeH" name="returnTimeH">
						 <c:forEach var="i" step="1" begin="00" end="23" >
							<c:choose>
						  	<c:when test="${i<=9}">
						  		<option>0${i}</option>
						  	</c:when>
						  	<c:otherwise>
							<option>${i}</option>
						  	</c:otherwise>
						 	</c:choose>
						</c:forEach> 
						</select> 
						:										  						
						<select id="returnTimeM" name="returnTimeM">
						<c:forEach var="i" step="1" begin="00" end="59" >
							<c:choose>
						  	<c:when test="${i<=9}">
						  		<option>0${i}</option>
						  	</c:when>
						  	<c:otherwise>
							<option>${i}</option>
						  	</c:otherwise>
						 	</c:choose>
						</c:forEach>
						</select> 								
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Conferma" class="button" />
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
</div>

<%@include file="footer.jsp"%>
<!-- 	</body> -->
<!-- </html> -->