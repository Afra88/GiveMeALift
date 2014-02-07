<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Viaggi di prossima partenza</title>

<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/ourAdditions.css">
<link rel="stylesheet" href="css/greenTable.css">
<link rel="stylesheet" href="css/pagination.css" type="text/css" />
<link rel="stylesheet" href="css/blueTable.css">
<link rel="stylesheet" href="css/orangeTable.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" href="css/form.css">
<link rel="stylesheet" href="css/pictogram-button.css">

<link rel="icon" href="images/favicon.ico">
<link rel="shortcut icon" href="images/favicon.ico">

</head>

<%-- 	<% session.getParameter("user"); %> --%>

<body>
	<%@include file="chooseMenu.jsp"%>
	<!--    <div class="content"> -->
	<!--     <div class="container_12" id="contentOpacity" > -->
	<!-- 		<div class="grid_12"> -->

			<c:choose>
			<c:when test="${modified==true}">
			<META http-equiv="refresh" content="5; URL=ShowUserProfile">
				<h3 class="center" > I dati sono stati modificati con successo! </h3>
				<h4 class="center" > Redirect in 5 secondi</h4>
			</c:when>
			</c:choose>
			

<div class="container_12" id="contentOpacity">
			<div class="grid_8">
				<div class="greenTable">
<c:choose>
<c:when test="${lifts.size()!=0}">
	<c:forEach items="${lifts}" var="l">
	    <div class="prod">
  			
		      <div class="grid_12">
		        <div class="box">
		          <div class="maxheight">
		          <table style="width: 900px" >
		          	<tr>		          	
		          		<td><div class="emphatizeWhen">${l.get(1).getDepartureDate()} - ${lifts(1).getDepartureTime()}</div></td>
		          		<td style="text-align: right;">
				        	<div class="emphatizePrice">${l.cost} &#8364; a persona</div>
				        </td>
		          	</tr>
		          	<tr>
			          	<td>
					        <div class="emphatizeLift"> CITTA'  </div>
				       		
				        </td>
				        <td style="text-align: right;">
				        	<div class="emphatizeSeat"><input id="seats" class="number" type="number" name="seats" maxlength="4" size="3" step="1" value="2" > posti disponibili</div>
				        </td>
			        </tr>
			        <tr style="text-align: right;">
			        	<td colspan="2" >
			        		<a href="DeleteLiftOffered?lift=1" class="button red"> <span class="trash"></span> Elimina </a>
			        		<a href="Step1UpdateLiftOfferALift?lift=1" class="button green"> <span class="edit"></span> Modifica </a>
			        		<a href="HandleShowLiftDetail?lift=1" class="button cyan"> <span class="preview"></span> Visualizza Annuncio</a>
				        </td>	        
			        </tr>
		          </table>
		          	</div>
		        </div>
		      </div>
		      </div>
		      </c:forEach>

</c:when>
</c:choose>
</div>
</div>
</div>


	
	
	

	<footer>
		<div class="container_12">
			<div class="grid_12">
				<div class="copy">
					Give me a lift(C) 2045 | <a href="#">Politica Privacy</a> | Design
					by: <a href="http://www.templatemonster.com/">TemplateMonster.com</a>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>
