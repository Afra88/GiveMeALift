<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>	 
    <head>
    
	<title>Registrati!</title>
    
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
   
    <link rel="stylesheet" href="css/ourAdditions.css">
    
    <link rel="icon" href="images/favicon.ico">
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/form.css">    
    
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
    
	<script type="text/javascript" src="js/checkSigningUp.js"></script>
	  	
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

<div class="container_12">
	<div class="grid_12">
	<form method="post" id="form0" >
		<h3>Inserisci i tuoi dati</h3><br />
		<div id="null"></div>
		*Nome: <br /> <input id="name" name="name" type="text" onblur="checkName(this);"/><span id="errorName"></span><br /><br />
		*Cognome: <br /> <input id="surname" name="surname" type="text" onblur="checkSurName(this);"/><span id="errorSurName"></span><br /><br />
		*Indirizzo email: <br /><input id="emailAdd" name="email" type="text" size="40" onblur="checkEmail(this);" /><span id="errorEmail"></span><br /><br />
		*Password:  <br /> <input id="ps" type="password" name="psw" onblur="checkPsw(this);" /><span id="errorPsw"></span><br /><br />
		*Conferma password:  <br /><input id="pswConfirm" type="password" onblur="checkEqual();" /><span id="notEqual"></span><br /><br />
		*Genere: <br /><select name="gender" >
						<option selected="selected" value="M"  >M</option>
						<option value="F">F </option>
						</select><br /><br />
		*Anno di nascita: <br /><select name="year" >
								<c:forEach var="i" begin="1914" step="1" end="1996">
									<option value="${i}" >${i} </option>
								</c:forEach>
								</select><br /><br />
		
		<input id="send" type="button" value="invia" onclick="setAction();" /><br /><br />
	</form>
	</div>
</div>
<%@include file="footer.jsp"%> 

