<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%@ page session="false" %>
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
    
    <link rel="icon" href="images/favicon.ico">
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/form.css">
	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" type="text/css"  href="js/calendar/tcal.css">
	
	<link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
    <link rel="stylesheet" href="js/GooglePlaceAutocomplete/placeAutocomplete.css">
    
<!--      	Google Place Autocomplete
   	 <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
    <script src="https://maps.googleapis.com/maps/api/js?&sensor=false&libraries=places&language=it&region=IT"></script>
    //maps.googleapis.com/maps/api/js?sensor=false&libraries=places&client=gme-comuto
 	<script src="js/GooglePlaceAutocomplete/placeAutocomplete.js" type="text/javascript"></script>
	end -->
    
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
	
    <script type="text/javascript" src="js/hoverMapValue.js"></script>
    <script type="text/javascript" src="js/checkingTime.js"></script>
	<script src="js/calendar/tcal.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/manageSearchLift.js" ></script>
	<script type="text/javascript" src="js/managePossibleDetour.js"></script>
	<script type="text/javascript" src="js/checkInsertedFields.js"></script>
	<script type="text/javascript" src="js/GoogleMapNew/map.js" ></script>
	<script type="text/javascript" src="js/dynamicPriceSum.js"></script>
	<script type="text/javascript" src="js/checkSigningUp.js"></script>
	  	
 	<script src="js/jQueryElement/rangeSlider.js" type="text/javascript"></script>
 	<script src="js/jQueryElement/radio.js" type="text/javascript"></script>
	
	<!-- serve per l'input di soli numeri (va messo dopo)  -->
	<script type="text/javascript"> $(".number").format({precision: 0,autofix:true});</script>
	<script type="text/javascript"> $(".toSum").format({precision: 0,autofix:true});</script>
	
	 	<script type="text/javascript"> 	 	
 	var elements = document.getElementsByName("path") 	// name="path"	
	for ( var i = 1; i < elements.length-1; i++) {
		 //id="inputs"
		$("#inputs").append(                             
 			"<input name=\"detour"+(i-1)+"\" value=\"" + elements[i].innerHTML + "\">"
 		);
	} 
 	
 	 //id="inputs"
	$("#inputs").append("<input name=\"mapFrom\" value=\"" +elements[0].innerHTML + "\">");
	$("#inputs").append("<input name=\"mapTo\" value=\"" + elements[elements.length-1].innerHTML + "\">");

  	
	var elements2 = document.getElementsByName("inputs") 	// name="inputs"	

	var dateG = elements2[0].innerHTML;	
	var dateR = elements2[1].innerHTML;
	
 	//id="inputs"
	if (dateR.localeCompare("NULL")) {
		$("#inputs").append("<input name=\"date\" value=\""+dateG+"\">" );  //DATA A,DATA R				
	}else {
		$("#inputs").append("<input name=\"date\" value=\"" + dateG+","+dateR+ "\">" );  //DATA A,DATA R		
	}	
	$("#inputs").append("<input name=\"goingTimeH\" value=\"" + elements2[2].innerHTML + "\">" );  // H ANDATA
 	$("#inputs").append("<input name=\"goingTimeM\" value=\"" + elements2[3].innerHTML + "\">" );  // M ANDATA
 	
 	//CONTROLLI TUTTI SULLA DATA DI RITORNO
 	if (!dateR.localeCompare("NULL")) { 
	 	$("#inputs").append("<input name=\"returnTimeH\" value=\"" + elements2[4].innerHTML + "\">" );  // H RITORNO
	 	$("#inputs").append("<input name=\"returnTimeM\" value=\"" + elements2[5].innerHTML + "\">" );  // M RITORNO 		
 	} 		 	
	</script>
   
	<script>
	$(document).ready(function () {
	    $().UItoTop({
	        easingType: 'easeOutQuart'
	    });
	    $("#submit").click(function() {  
	        /* var mapFrom=$('#mapFrom').val();
	        alert(mapFrom); */
	        /* var mapTo=$('#mapTo').val();
	        var detour0=$('#detour0').val();
	        var detour1=$('#detour1').val();
	        var detour2=$('#detour2').val();
	        var detour3=$('#detour3').val();
	        var detour4=$('#detour4').val();
	        var date=$('#date').val();
	        var goingTimeH=$('#goingTimeH').val();
	        var goingTimeM=$('#goingTimeM').val();
	        var returnTimeH=$('#returnTimeH').val();
	        var returnTimeM=$('#returnTimeM').val(); */
	       /*  $.get('confirmALift',{mapFrom:mapFrom},{mapTo:mapTo},
	        		{detour0:detour0},{detour1:detour1},
	        		{detour3:detour3},{detour2:detour2},
	        		{detour4:detour4},
	        		{date:date},
	        		{goingTimeH:goingTimeH},
	        		{goingTimeM:goingTimeM},
	        		{returnTimeM:returnTimeM},
	        		{returnTimeH:returnTimeH},
	        		 function(responseText) { 
	        	 $('#contentOpacity').css({ 'opacity' : 0.2 });
	              //$('#welcometext').text(responseText); 
	             $('#messageConfirm').text(responseText);
		         $('#dialog').dialog( "open" );
	            });
	        }); */
	});
 	$( "#dialog" ).dialog({
		autoOpen: false,
		show: {
		effect: "blind",
		duration: 1000
		},
		hide: {
			effect: "puff",
			duration: 1000
		}
	});
 	});
	</script>
	
	<script>
	/* $(function() { */
	/* $(document).ready(function() {        */                 
//     });
/* 	$( "#opener" ).click(function() {
		$('#contentOpacity').css({ 'opacity' : 0.2 });
	$( "#dialog" ).dialog( "open" );
	});
	}); */
	</script>
	
</head>
<body onload="initialize()" >
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
            <li class="current"><a href="/GiveMeALift">Home</a></li>
            <li><a href="OfferALift">Offri un passaggio!</a></li>
            <!-- <li><a href="blog.html">Blog</a></li>
            <li><a href="recipes.html">Recipes</a></li>
            <li><a href="contacts.html">Contacts</a></li> -->
          </ul>
        </nav>
        <div class="clear"></div>
      </div>
      <div class="clear"></div>
    </div>
    
  </div>
</header>

<div class="container_12" id="contentOpacity" >
	<div class="grid_12">      
	  	<div id="dialog" title="Publicazione Offerta">
			<p id="messageConfirm"> </p>
		</div>
	<!-- hidden div per visualizzare nel dom i valori di path -->
		<c:choose>
		<c:when test="${path.size()>0}">
			<c:forEach var="i"  begin="0" end="${path.size()-1}" > 
				<input name="path" type="hidden" value="${path.get(i)}" />
			</c:forEach>
		</c:when>
		</c:choose>
	
	<!-- hidden div per visualizzare nel dom i valori di inputs -->
	<c:forEach var="i"  begin="0" end="${inputs.size()-1}" > 
		<input name="inputs"  type="hidden" ${inputs.get(i)} />
	</c:forEach>
	
	
	<!-- hidden div inserire tag input con name e value caricati con jquery -->	
	<input type="hidden" id="inputs" />
	
	<div class="greenTable">
		<table id="plusDetails" class=table>
			<tr>
				<td colspan="2">
					Dettagli Aggiuntivi
				</td>
			</tr>
			<tr>
				<td colspan="2">Quota a passeggero</td>
			</tr>
			<c:forEach var="i"  begin="0" end="${path.size()-2}" > 
			<tr>
				<td>
					${path.get(i)} <img src="images/freccia1.gif" height="10px"/> ${path.get(i+1)}
				</td>
				<td> 
					<input type="number" id="price" name="price" class="toSum" maxlength="3" size="3">&#8364;
				</td>
			</tr>
			</c:forEach>			
<!-- 				<td> -->
<%-- 					<h3>${path.get(0)} <img src="images/freccia1.gif" height="10px"/> ${inputs.get(1)} </h3> --%>
<!-- 				<td>  -->
<!-- 						<input type="text" id="price" name="price"> -->
<!-- 					<i>euro</i> -->
<!-- 				</td> -->
			<tr>
<%-- 			${path.get(0)} <img src="images/freccia1.gif" height="10px"/> ${path.get(path.size()-1)} --%>
			
				<td><font color="orange"><b>TOTALE</b></font></td>
				<td><div id="sum">0 &#8364;</div></td>
			</tr>
			<tr>
				<td>
					Numero posti disponibili
				</td>
				<td>
					<input id="seats" class="number" type="number" name="seats" maxlength="4" size="3">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<h2>Descrizione del passaggio</h2>
				</td>
			</tr>
			<tr>
				<td>
					<textarea  cols="40" rows="6" maxlength="700" >
					Fornisci informazioni aggiuntive sul tuo viaggio. 
					Cerca di invogliare più passeggeri a contattarti!
					</textarea>
				</td>
			
				<td>
					<p><small><i>* Nel rispetto della tua privacy, non indicare mai i tuoi recapiti nella descrizione del viaggio.</i></small></p>
				</td>
			</tr>
			<tr>
				<td><h2> Bagaglio massimo consentito: </h2></td>
				<td>
					<select id="luggage" name="luggage">
	 				 	<option value="medium">Medio</option>
	  					<option value="small">Piccolo</option>
	  					<option value="large">Grande</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><h2> Partirò: </h2></td>
				<td>
					<select id="delay" name="delay">
	  					<option value="strict">puntuale</option>
	 				 	<option value="15min">+/- 15 minuti</option>
	  					<option value="30min">+/- 30 minuti</option>
	  					<option value="1h">+/- un'ora</option>
	  					<option value="2h">+/- due ore</option>
					</select>
				</td>
			</tr>
			<tr>
			<td><h2> Disponibile a deviazioni: </h2></td>
				<td>
					<select id="deviation" name="deviation">
	 				 	<option value="nothing">Nessuna deviazione, mi dispiace... :|</option>
	  					<option value="15min">15 minuti al massimo</option>
	  					<option value="30min">30 minuti al massimo</option>
	  					<option value="any">Qualsiasi deviazione. No Problem! :D</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><h2> Tratta percorsa </h2></td>
				<td>
					<h2><input type="number" id="timesForThisRoute" name="timesForThisRoute" class="number" maxlength="6" size="6" value="0" > volte</h2>
				</td>
			</tr>
			<tr>
				<td><h2> Preferisco viaggiare su: </h2></td>
				<td>
					<select id="roadType" name="roadType">
						<option value="freeway">Autostrada</option>
						<option value="country">Strada di paese</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><h2> Viaggio rosa: </h2></td>
				<td>
					<select id="pinkTrip" name="pinkTrip"> 
	  					<option value="bothPass">viaggio con uomini e donne</option>
	  					<option value="onlyWomen">viaggio solo con donne</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2"> 
					<input id="checkLicence" type="checkbox" name="drivingLicence" value="drivingLicence"/> 
					<i>Dichiaro di essere in possesso di patente di guida valida ed assicurazione RCA </i>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><h2>Riepilogo Dati</h2></td>
			</tr>
			<tr>
				<td colspan="2" >
					<b><i>${path.get(0)} <img src="images/freccia1.gif" height="10px"/> ${path.get(path.size()-1)}</b></i>
					<br>
					<font color="blue">Andata:</font> ${inputs.get(0)} - ore: ${inputs.get(2)}:${inputs.get(3)}
					<br>
					<font color="blue">Ritorno:</font> ${inputs.get(1)} - ore: ${inputs.get(4)}:${inputs.get(5)}
				</td>	
<%-- 	 		<c:forEach var="i" items="${inputs}">  --%>
<!-- 	 		<tr>  -->
<%-- 	 			<td><h3>${i}</h3></td> --%>
<!-- 	 		<tr>  -->
<%-- 	 		</c:forEach> --%>

<!-- 				<td rowspan="3"> -->
<!-- 					<p id="map"></p> -->
<!-- 				</td> -->

			</tr>
			<tr>
				<td>
					<input type="button" id="goBack" value="Indietro" onClick="history.back()"/>
				</td>
				<td>
					<input type="button" value="Pubblica" id="submit"  />
				</td>
			</tr>
		</table>
		</div>
	<!-- </form> -->
<!-- 	<p id="map"></p> -->
</div>
</div>

<footer>
  <div class="container_12">
    <div class="grid_12">
      <div class="copy"> Give me a lift(C) 2045 | <a href="#">Politica Privacy</a> | Design by: <a href="http://www.templatemonster.com/">TemplateMonster.com</a> </div>
    </div>
  </div>
</footer>
</body>
</html>