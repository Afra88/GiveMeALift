<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Aggiungi dettagli auto</title>
		
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
	</head>
	
	<body>
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
		
	<div class="container_12" id="contentOpacity" >
		<div class="grid_12">
		
		
		
		
			<form method="get" action="SubmitCar">
			<div class="greenTable">
				<table id="addMyCar" class="table">
				<tr>
					<td colspan="2"> La mia auto </td>
				</tr>
				<tr>
					<td>Marca</td>
					<td> 
<!-- 						<select id="brandAuto" name="brandAuto" url="" required="required"></select> -->
<!-- 						<option selected="selected" value="">Scegli</option> -->
						<select id="brandAuto" name="brandAuto" >
							<option value="Fiat">Fiat</option>
							<option value="Ford">Ford</option>
							<option value="Mercedes">Mercedes</option>
						</select>

					
					</td>
				</tr>
				<tr>
					<td>Modello</td>
					<td>
<!-- 						<select id="modelAuto" name="modelAuto" url="" required="required"></select> -->
<!-- 						<option selected="selected" value="">Scegli</option> -->
						<select id="modelAuto" name="modelAuto" >
							<option value="500">500</option>
							<option value="Fiesta">Fiesta</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Confort</td>
					<td>
						<select id="confortAuto" name="confortAuto">
							<option>base</option>
							<option selected="selected">normale</option>
							<option>confortevole</option>
							<option>lusso</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Colore</td>
					<td>
						<select id="colorAuto" name="colorAuto">
							<option value="FFAD5B">arancio</option>
							<option value="FFFFFF">bianco</option>
							<option value="0000FF">blu</option>
							<option value="BBBBFD">celeste</option>				
							<option value="C0C0C0">grigio chiaro</option>
							<option value="A0A0A0">grigio metallizzato</option>
							<option value="996633">marrone</option>
							<option value="000000" selected="selected">nero</option>
							<option value="988800">oro</option>
							<option value="FF04FF">rosa</option>
							<option value="FF0000">rosso</option>
							<option value="00FF00">verde</option>
						</select>
					</td>
				</tr>
				<tr>
				<td>Foto</td>
<!-- 				<td> -->
<%-- 					<img id="photoCar" name="photoCar" src="${user.getDriverInfo().getCar().getPhoto()}"></img> --%>
<!-- 				</td> -->
			</tr>
				</table>
			</div>
				<p align = "center"><input value="Conferma" type="submit"/></p>
			</form>
			
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