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
	
	<!-- <link rel="stylesheet" type="text/css" href="js/GoogleMap/map.css">
	<link rel="stylesheet" href="css/mainstyle.css" type="text/css" /> -->

	<!-- <link rel="stylesheet" href="css/listResult.css" type="text/css" />
	<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script> -->   
   
	<script>
	$(document).ready(function () {
	    $().UItoTop({
	        easingType: 'easeOutQuart'
	    });
	    $('#submit').click(function(event) {  
	        alert("fmdd");
//	         var mapFrom=$('#mapFrom').text();
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