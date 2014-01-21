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