<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- <html>
<head>
	<link rel="stylesheet" href="css/mainstyle.css" type="text/css" />
	<script type="text/javascript" src="js/setMapValue.js"></script>
	<script type="text/javascript" src="js/checkingTime.js"></script>
	<script src="js/calendar/tcal.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css"  href="js/calendar/tcal.css">
	<link rel="stylesheet" type="text/css" href="js/GoogleMap/map.css">
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true&language=it"></script>
	<script type="text/javascript" src="js/GoogleMap/map.js"></script>

	Google Place Autocomplete
 	<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
 	<link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
    <link rel="stylesheet" href="js/GooglePlaceAutocomplete/placeAutocomplete.css">
 	<script src="js/GooglePlaceAutocomplete/placeAutocomplete.js" type="text/javascript"></script>
    end

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
	</div>

<p>
	<form method="get" action="OfferALift">
		<input type="submit" value="Offri un passaggio!" class="button" />
	</form>
</p>
<p>
<form method="get" action="ResultSearch">
	<p class="locationField" id="mapValues">
	<span class="label">Partenza: </span>
		<input class="autocomplete" type="text" id=mapFrom name=mapFrom /> 
	<span class="label"> Arrivo: </span>
		<input class="autocomplete" type="text" id=mapTo name=mapTo />
	<span id=date><script type="text/javascript" src="js/currentDate.js"></script></span>
	<input class="button"  id="FromAToB" type="button" value="Percorso" />
	<input type="submit" value="Cerca" class="button" />
	</p>
</form>
</p>
<p id="map"></p>
</div>
</body>
</html> -->

<!DOCTYPE html>
<html lang="it">
	<head>
	<title>Give me a Lift!</title>
	
	<meta charset="utf-8">
	<link rel="icon" href="images/favicon.ico">
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/slider.css">
	<link rel="stylesheet" href="css/elastislide.css">
	<link rel="stylesheet" href="css/form.css">
<!-- 	<script src="js/cssJs/jquery.js"></script> -->
	<script src="js/cssJs/jquery-migrate-1.1.1.js"></script>
	<script src="js/cssJs/superfish.js"></script>
	<script src="js/cssJs/jquery.equalheights.js"></script>
	<script src="js/cssJs/jquery.easing.1.3.js"></script>
	<script src="js/cssJs/modernizr.custom.63321.js"></script>
	<script src="js/cssJs/jquerypp.custom.js"></script>
	<script src="js/cssJs/jquery.ui.totop.js"></script>
	<script src="js/cssJs/jquery.carouFredSel-6.1.0-packed.js"></script>
	<script src="js/cssJs/jquery.elastislide.js"></script>
	<script src="js/cssJs/jquery.catslider.js"></script>
	<script src="js/cssJs/jquery.touchSwipe.min.js"></script>
	<script>
	$(window).load(function () {
	    $('#mi-slider').catslider();
	    $('#carousel').elastislide({
	        orientation: 'vertical'
	    });
	});
	$(window).load(function () {
	    $('#carousel1').carouFredSel({
	        auto: false,
	        prev: '.prev1',
	        next: '.next1',
	        width: 220,
	        items: {
	            visible: {
	                min: 1,
	                max: 1
	            },
	            height: 'auto',
	            width: 220,
	        },
	        responsive: true,
	        scroll: 1,
	        mousewheel: false,
	        swipe: {
	            onMouse: false,
	            onTouch: false
	        }
	    });
	});
	$(document).ready(function () {
	    $().UItoTop({
	        easingType: 'easeOutQuart'
	    });
	});
	</script>
<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<link rel="stylesheet" media="screen" href="css/ie.css">
<![endif]-->

	<link rel="stylesheet" href="css/login.css">
	<script type="text/javascript" src="js/setMapValue.js"></script>
	<script type="text/javascript" src="js/checkingTime.js"></script>
	<script src="js/calendar/tcal.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css"  href="js/calendar/tcal.css">
<!-- 	<link rel="stylesheet" type="text/css" href="js/GoogleMap/map.css">
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true&language=it"></script>
	<script type="text/javascript" src="js/GoogleMap/map.js"></script>
 -->
	<!-- Google Place Autocomplete -->
<!--  	<meta name="viewport" content="initial-scale=1.0, user-scalable=no"> -->
<!--     <meta charset="utf-8"> -->
<!--  	<link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500"> -->
<!--     <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script> -->
<!--     <link rel="stylesheet" href="js/GooglePlaceAutocomplete/placeAutocomplete.css"> -->
<!--  	<script src="js/GooglePlaceAutocomplete/placeAutocomplete.js" type="text/javascript"></script> -->
    <!--  end -->
    <script type="text/javascript" src="js/hoverMapValue.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    
</head>
<body class="page1" onload="initialize()">
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
          <h1><a href=""><img src="images/logo.png" alt=""></a> </h1>  
      <div class="menu_block">
        <nav>
          <ul class="sf-menu">
            <li class="current"><a href="">Home</a></li>
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
<div class="content">
  <div class="container_12">
	<div class="grid_2">
  		<form method="get" action="ResultSearch" id="searchForm" class="form" >
<!-- 			<p class="locationField" id="mapValues"> -->
			<div class="clear"></div>
		  	<!-- <div class="grid_3 prefix_1">   -->
				<label class="name">
					<input class="autocomplete" type="text" id=mapFrom name=mapFrom value="Partenza" /> 
				</label>
			<!-- </div> -->
			<div class="clear"></div>
			<!-- <div class="grid_6 prefix_1">   -->
			<label class="name">
				<input class="autocomplete" type="text" id=mapTo name=mapTo value="Arrivo"  />
			</label>
			<!-- </div> -->
			<div class="clear"></div>
			<label class="name" id="date">
				<script type="text/javascript" src="js/currentDate.js"></script>
			</label>
			<div class="clear"></div>
			<div class="btns"><input type="submit" value="Cerca"/></div>
            <div class="clear"></div>
<!--             </p> -->
		</form>
	</div>
    <div class="grid_8 prefix_2">  
      <div id="mi-slider" class="mi-slider">
<!--         <ul> -->
<!--           <li><a href="#"><img src="images/travel/mapcar.jpg" alt=""></a></li> -->
<!--           <li><a href="#"><img src="images/travel/collage.jpg" alt=""></a></li> -->
<!--           <li><a href="#"><img src="images/travel/car.jpg" alt=""></a></li> -->
<!--         </ul> -->
<!--         <ul> -->
<!--           <li><a href="#"><img src="images/cities/rome.jpg" alt=""></a></li> -->
<!--           <li><a href="#"><img src="images/cities/turin.jpg" alt=""></a></li> -->
<!--           <li><a href="#"><img src="images/cities/florence.jpg" alt=""></a></li> -->
<!--         </ul> -->
        <ul>
          <li><a href="#"><img src="images/friends/friends.jpg" alt=""></a></li>
          <li><a href="#"><img src="images/friends/friends1.jpg" alt=""></a></li>
          <li><a href="#"><img src="images/friends/friends2.jpg" alt=""></a></li>
        </ul>
<!--         <ul> -->
<!--           <li><a href="#"><img src="images/dier1.jpg" alt=""></a></li> -->
<!--           <li><a href="#"><img src="images/dier2.jpg" alt=""></a></li> -->
<!--           <li><a href="#"><img src="images/dier3.jpg" alt=""></a></li> -->
<!--         </ul> -->
        <nav> <a href="#">Dividi le spese!</a> <a href="#">Viaggia!</a> <a href="#">Conosci nuove persone!</a> 
<!--         <a href="#">dairy</a>  -->
        </nav>
      </div>
    </div>
    <div class="clear"></div>
    <div class="grid_8">
      <h3>Welcome to Organic Company</h3>
      <img src="images/page1_img1.jpg" alt="" class="img_inner fleft">
      <div class="extra_wrapper">
        <p>Want to learn more about this freebie designed by TemplateMonster.com? Follow the link</p>
        <p>Find more themes of this kind at the category of premium Agriculture Website Templates.</p>
      </div>
      <div class="clear"></div>
      Aliquam nibh ante, egestas id dictum a, commodo luctus libero. Praesent faucibus malesuada faucibus. Donecyl laoreet metus id laoreet malesuada. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam consectetu orci sed nulla facilisis consequat. Curabitur vel lorem sit amet nulla ullamcorper fermentum.
      <div class="grid_4 alpha">
        <div class="banner maxheight">
          <h3><a href="#">100% Eco &amp; <br>
            Organic</a></h3>
        </div>
        <h3>Our Mission</h3>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In mollis erat mattis neque facilisis, sit amet ultriciesyt erat rutrum. Cras facilisis, nulla vel viverra auctor, leo ame magna sodales felis, quis malesuada.</p>
        <ul class="list">
          <li><a href="#">Duis massa elit, auctor non pellentesque vel.</a></li>
          <li><a href="#">Nullam eget dignissim nisi, aliquam feugiat nibh. </a></li>
          <li><a href="#">Ut nisi nibh, sagittis ut semper elementum.</a></li>
          <li><a href="#">Pellentesque a odiohasellus vitae libero vel.</a></li>
          <li><a href="#">Justo pretium dignissimteger semper in estgue.</a></li>
          <li><a href="#"> In laoreet lacus eros, vel pulvinar urna ultut.</a></li>
        </ul>
      </div>
      <div class="grid_4 omega">
        <div class="banner b1 maxheight">
          <h3><a href="#">Control of <br>
            the Quality </a></h3>
        </div>
        <h3>What We Offer</h3>
        <p>Retrolom ipsum dolor sit met, consectetur ipiscing elit. In mollis erat mattis neque facilisis, sit amet ultriciesert erat rutrum. Bras facilisis, nulla vel viverra aucto ame magna sodales felis, quis nibh odio. </p>
        <ul class="list">
          <li><a href="#">Setrolis assa elit, auctor non reto lo. </a></li>
          <li><a href="#">Master lam eget dignissim nliam feugiat nibherty. </a></li>
          <li><a href="#">Do nisi nibh ut semper ntumertolillente derto.</a></li>
          <li><a href="#">Sertloque a odiohasellus vitae libero velertlo fer.</a></li>
          <li><a href="#">Monrilo sto pretium dignissimerwtoli moloer. </a></li>
          <li><a href="#">Ontnteger semper in estgue destro.</a></li>
        </ul>
      </div>
      <div class="clear"></div>
    </div>
    <div class="grid_3 prefix_1">
      <ul id="carousel" class="elastislide-list">
        <li><a href="#"><img src="images/page1_img2.jpg" alt=""></a></li>
        <li><a href="#"><img src="images/page1_img3.jpg" alt=""></a></li>
        <li><a href="#"><img src="images/page1_img4.jpg" alt=""></a></li>
        <li><a href="#"><img src="images/page1_img2.jpg" alt=""></a></li>
        <li><a href="#"><img src="images/page1_img3.jpg" alt=""></a></li>
        <li><a href="#"><img src="images/page1_img4.jpg" alt=""></a></li>
        <li><a href="#"><img src="images/page1_img2.jpg" alt=""></a></li>
        <li><a href="#"><img src="images/page1_img3.jpg" alt=""></a></li>
        <li><a href="#"><img src="images/page1_img4.jpg" alt=""></a></li>
      </ul>
      <div class="car_div">
        <div class="title">What People Say</div>
        <ul id="carousel1">
          <li>“Lorem ipsum dolor sit amet, tetur adipiscing elit. In ollis erat matti neque facilisis, sit amet .”</li>
          <li>"Retrolom ipsum dolor sit met, consectetur ipiscing elit. In mollis erat mattis neque."</li>
          <li>"Aliquam nibh ante, egestas id dictum a, commodo luctus libero. Praesent faucibus."</li>
        </ul>
        <a href="#" class="prev1"></a><a href="#" class="next1"></a> </div>
    </div>
  </div>
</div>
<div class="bottom_block">
  <div class="container_12">
    <div class="grid_10 prefix_1">
      <div class="column"><a href="#">Useful Info</a><br>
        <a href="#">Our Staff</a></div>
      <div class="column"><a href="#">News &amp; Events </a><br>
        <a href="#">Our Guarantee</a></div>
      <div class="column"><a href="#">FAQs </a><br>
        <a href="#">Contacts</a></div>
      <div class="clear"></div>
      <div class="socials"> <a href="#"><i class=" icon-facebook"></i></a> <a href="#"><i  class="icon-twitter"></i></a> <a href="#"><i class=" icon-google-plus "></i></a> <a href="#"><i class=" icon-pinterest"></i></a> </div>
    </div>
  </div>
</div>
<footer>
  <div class="container_12">
    <div class="grid_12">
      <div class="copy"> OrganicFood (C) 2045 | <a href="#">Privacy Policy</a> | Design by: <a href="http://www.templatemonster.com/">TemplateMonster.com</a> </div>
    </div>
  </div>
</footer>
</body>
</html>
