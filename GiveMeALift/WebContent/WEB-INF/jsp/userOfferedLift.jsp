<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
	<head>
	<title>I miei passaggi</title>

	<meta charset="utf-8">
	
	<link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/ourAdditions.css">
	<link rel="stylesheet" href="css/pagination.css" type="text/css" />
	<link rel="stylesheet" href="css/pictogram-button.css" type="text/css" />

	<link rel="icon" href="images/favicon.ico">
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/touchTouch.css">   
	<link rel="stylesheet" href="css/form.css">   
	
	<script src="js/jquery.js"></script>
	<script src="js/jquery-migrate-1.1.1.js"></script>
	<script src="js/superfish.js"></script>
	<script src="js/jquery.equalheights.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script src="js/jquery.ui.totop.js"></script>
	<script src="js/touchTouch.jquery.js"></script>
	<script>
	$(document).ready(function () {
	    $().UItoTop({
	        easingType: 'easeOutQuart'
	    });
	});
	$(function () {
	    $('.prod a.gal').touchTouch();
	});
	</script>
</head>
<body class="page1" onload="initialize()">
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
	<div class="clear"></div>
  	<c:choose>
  		<c:when test="${noRes==true}">
  			<div class="grid_12">
  				<div class="clear" ></div>
  				<h4>Finora non ci sono passaggi da te offerti</h4>
  			</div>
  			<div class="clear"></div>
  		</c:when>
  		<c:otherwise>
  		<div class="clear"></div>
	    <div class="prod">
  			<c:forEach items="${pageHolder.pageList}" var="lift" >
		      <div class="grid_12">
		        <div class="box">
		          <div class="maxheight">
		          <table style="width: 900px" >
		          	<tr>		          	
		          		<td><div class="emphatizeWhen">${lift.departureDate} - ${lift.departureTime}</div></td>
		          		<td style="text-align: right;">
				        	<div class="emphatizePrice">${lift.cost} &#8364; a persona</div>
				        </td>
		          	</tr>
		          	<tr>
			          	<td>
					        <div class="emphatizeLift">${lift.getPickUpPoint().city} - ${lift.getDropOffPoint().city}</div>
				       		<c:choose>
				       			<c:when test="${lift.possibleDetour==true}">
				       				   Deviazioni possibili
				       			</c:when>
				       			<c:otherwise>
				       				Nessuna deviazione
				       			</c:otherwise>
				       		</c:choose>
				        </td>
				        <td style="text-align: right;">
				        	<div class="emphatizeSeat"><input id="seats" class="number" type="number" name="seats" maxlength="4" size="3" step="1" value="${lift.nSeat}" > posti disponibili</div>
				        </td>
			        </tr>
			        <tr style="text-align: right;">
			        	<td colspan="2" >
			        		<a href="DeleteOfferedLift?lift=${lift.getId()}" class="button red"> <span class="trash"></span> Elimina </a>
			        		<a href="Step1UpdateLiftOfferALift?lift=${lift.getId()}" class="button green"> <span class="edit"></span> Modifica </a>
			        		<a href="HandleShowLiftDetail?lift=${lift.getId()}" class="button cyan"> <span class="preview"></span> Visualizza Annuncio</a>
				        </td>	        
			        </tr>
		          </table>
		          	</div>
		        </div>
		      </div>
		     </c:forEach>
  			</div>
  			<div id="tnt_pagination">
				<c:choose>
					<c:when test="${page == 1}">
						<button class="disabled_tnt_link" id="page_1" disabled="disabled" > Indietro </button>
					</c:when>
					<c:otherwise>
						<button class=page id="page_1" > Indietro </button>
					</c:otherwise>
				</c:choose>
				<c:forEach begin="1" step="1" var="i" end="${pages}">
					<c:choose>
						<c:when test="${i == page}">
							<button id="page_${i}" class="active_tnt_link ">${i} </button>
						</c:when>
						<c:otherwise>
							<button class=page id="page_${i}" >${i}</button>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${page == pages}">
						<button id="page_${page}" disabled="disabled" class="disabled_tnt_link"> Avanti </button>
					</c:when>
					<c:otherwise>
						<button class=page id="page_${page}" > Avanti </button>
					</c:otherwise>
				</c:choose>
				</div>
			  		</c:otherwise>
			  	</c:choose>
			</div>
</div>
<%@include file="footer.jsp" %>
