<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=iso-8859-1" %>
<html>
    <head>
    <script type="text/javascript" src="js/checkingTime.js"></script>
	<script src="js/calendar/tcal.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css"  href="js/calendar/tcal.css">
	<link rel="stylesheet" type="text/css" href="js/GoogleMap/map.css">
	<link rel="stylesheet" href="css/mainstyle.css" type="text/css" />
	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
  	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  	<link rel="stylesheet" href="/resources/demos/style.css">
 	<script src="js/jQueryElement/rangeSlider.js" type="text/javascript"></script>
 		
 	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
  	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
 	<link rel="stylesheet" href="/resources/demos/style.css">
 	<script src="js/jQueryElement/radio.js" type="text/javascript"></script>
 	
 	<!-- Google Place Autocomplete -->
 	<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <!-- <meta charset="utf-8"> -->
 	<link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
    <link rel="stylesheet" href="js/GooglePlaceAutocomplete/placeAutocomplete.css">
 	<script src="js/GooglePlaceAutocomplete/placeAutocomplete.js" type="text/javascript"></script>
    <!-- end -->
	
	<link rel="stylesheet" href="css/listResult.css" type="text/css" />
	<!-- <script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script> -->
    
    <link rel="stylesheet" href="css/pagination.css" type="text/css" />
    <script type="text/javascript">
    $(document).ready(function(){
    	$($(".page")).click(function(){
    		$("#next_page").val($(this).attr("id").split("_")[1]);
    		$("#next_from").val($("#mapFrom").val());
    		$("#next_to").val($("#mapTo").val());
    		$("#searchForm").submit();
    	});
    	$($("#reverse")).click(function(){
    		var text=$("#mapFrom").val();
    		$("#mapFrom").val($("#mapTo").val());
    		$("#mapTo").val(text);
    	});
    	$($("#searchForm")).submit(function(){
    		$("#next_from").val($("#mapFrom").val());
    		$("#next_to").val($("#mapTo").val());
    	});
    });
    
    </script>
    
   	<title>Risultati della ricerca</title>
    
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
	<div class="sidebar1">
	<form method="get" action="AdvancedSearchOptions" id=searchForm >
     	<h4>Data</h4>
		<p id=date class=center><script type="text/javascript" src="js/currentDate.js"></script></p>
		<h4>Ora</h4>
		<div class=contentSlidebar>
			<p><label for="range">Price range:</label>
		  	<input type="text" id="range" name=range></p>
			<p id="slider-range"></p>
		</div>
		<h4>Costo</h4>
		<div>
			<p id="radio" class="center">
		    <input type="radio" id="radio1" name="radio" value="basso" ><label for="radio1">Basso</label>
		    <input type="radio" id="radio2" name="radio" value="medio" checked="checked"><label for="radio2">Medio</label>
		    <input type="radio" id="radio3" name="radio" value="alto" ><label for="radio3">Alto</label>
		  </p>
		</div>
		<h4>Perfeziona Ricerca</h4>
		<input type="hidden" id=next_page name=page value="1" />
		<input type="hidden" id=next_from name=from value="${from}" />
		<input type="hidden" id=next_to name=to value="${to}" />
		<p class="center"><input type="submit"  value="Cerca" class="button" /></p>
    </form>
    </div>
    <div class="content">
    <p class="locationField" id="mapValues" style="text-align: center" >
		<span class="label">Partenza: </span>
		<input class="autocomplete" onFocus="geolocate()" type="text" id=mapFrom name=mapFrom value="${from}" /> 
		<input class="button" id=reverse value="" tabindex="5" title="Inverti partenza e arrivo" type="button" />
		<span class="label"> Arrivo: </span>
		<input class="autocomplete" onFocus="geolocate()" type="text" id=mapTo name=mapTo value="${to}" />
<!-- 		<input type="submit" value="Cerca" class="button" /> -->
	</p>
	<div id="resultTable" class="scrollable vertical"> 
      <p class="items">      
        <c:forEach items="${pageHolder.pageList}" var="lift" >
        <div class="item">
        	<p class=imgContainer ><img src="http://farm1.static.flickr.com/3650/3323058611_d35c894fab_m.jpg" /></p>
        	<ul>
	        	<li><p><span class="emphatizeLift">${lift.getPickUpPoint().city} - ${lift.getDropOffPoint().city}  </span>
	        		<c:choose>
	        			<c:when test="${lift.possibleDetour}==true">
	        				   Deviazioni possibili
	        			</c:when>
	        			<c:otherwise>
	        				Nessuna deviazione
	        			</c:otherwise>
	        		</c:choose>
	        	</p></li>
	        	<li><p><span class="emphatizePrice">Prezzo ${lift.cost} &#8364; </span>a testa</p></li>
	        	<li><p class="emphatizeSeat"> ${lift.nSeat} Posti disponibili</p></li>
        	</ul>
		</div>
		</c:forEach>
        </p>
	</div>
	<div id="tnt_pagination">
	<!-- <span class="disabled_tnt_pagination">Prev</span> -->
	<c:choose>
		<c:when test="${page == 1}">
			<button class=page id="page_1" disabled="disabled" > << </button>
		</c:when>
		<c:otherwise>
			<button class=page id="page_1" > << </button>
		</c:otherwise>
	</c:choose>
	<c:forEach begin="1" step="1" var="i" end="${pages}">
		<c:choose>
			<c:when test="${i == page}">
				<button class=page id="page_${i}" class="active_tnt_link">${i} </button>
			</c:when>
			<c:otherwise>
				<button class=page id="page_${i}" >${i}</button>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${page == pages}">
			<button class=page id="page_${page}" disabled="disabled" > >> </button>
		</c:when>
		<c:otherwise>
			<button class=page id="page_${page}" > >> </button>
		</c:otherwise>
	</c:choose>
	
	<!-- <a class=page href="#forwaed">Next</a> -->
	</div>
 <!-- end .content --></div>
  <div class="footer">
    <p></p>
    <!-- end .footer --></div>
  <!-- end .container --></div> 
</body>
</html>
