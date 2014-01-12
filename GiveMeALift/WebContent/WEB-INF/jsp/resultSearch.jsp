<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" %>
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
    
    <link rel="stylesheet" href="css/pagination.css" type="text/css" />
    
   	<title>Risultati della ricerca</title>
    
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
	<div class="sidebar1">
     	<h4>Data</h4>
		<div id=date class=contentSlidebar><script type="text/javascript" src="js/currentDate.js"></script></div>
		<h4>Ora</h4>
		<div class=contentSlidebar>
			<p><label for="range">Price range:</label>
		  	<input type="text" id="range"></p>
			<div id="slider-range"></div>
		</div>
		<h4>Costo</h4>
		<div>
			<div id="radio">
		    <input type="radio" id="radio1" name="radio"><label for="radio1">Basso</label>
		    <input type="radio" id="radio2" name="radio" checked="checked"><label for="radio2">Medio</label>
		    <input type="radio" id="radio3" name="radio"><label for="radio3">Alto</label>
		  </div>
		</div>
    </div>
    <div class="content">
	<table id="resultTable" class="table">
        
        <thead>
        	<tr>
        		<td>Id</td>
	        	<td>Partenza</td>
	        	<td>Arrivo</td>
				<td>Posti Disponibili</td>
				<td>Costo</td>
			</tr>
		</thead>
        <c:forEach items="${pageHolder.pageList}" var="lift" >
        <tr>
        	<td>${lift.getId()}</td>
        	<td>${lift.getPickUpPoint().city}</td>
        	<td>${lift.getDropOffPoint().city}</td>
			<td>${lift.possibleDetour}</td>
			<td>${lift.cost}</td>
		</tr>
		</c:forEach>
	</table>
<!-- 	<ul class="tsc_pagination"> -->
<%-- 		<c:forEach  begin="1" step="1" var="i" end="${pages}"> --%>
<%-- 			<c:if test="i==1"> --%>
<%-- 			  <li class="single">Pagina 1 di ${pages}</li> --%>
<%-- 			</c:if> --%>
<%-- 			<c:otherwise> --%>
<!-- 			  <li><a href="index-2.html">2</a></li> -->
<%-- 			</c:otherwise> --%>
<!-- 	  <li class="current">1</li> -->
<!-- 	  <li><a href="index-3.html">3</a></li> -->
<!-- 	  <li><a href="index-4.html">4</a></li> -->
<!-- 	  <li><a href="index-5.html">5</a></li> -->
<!-- 	  <li><a href="index-2.html">next</a></li> -->
<%-- 	</c:forEach> --%>
<!-- 	</ul> -->
	<div id="tnt_pagination">
	<span class="disabled_tnt_pagination">Prev</span><a href="#1">1</a><a href="#2">2</a><a href="#3">3</a><span class="active_tnt_link">4</span><a href="#5">5</a><a href="#6">6</a><a href="#7">7</a><a href="#8">8</a><a href="#9">9</a><a href="#10">10</a><a href="#forwaed">Next</a></div>
	
 <!-- end .content --></div>
  <div class="footer">
    <p></p>
    <!-- end .footer --></div>
  <!-- end .container --></div> 
</body>
</html>
