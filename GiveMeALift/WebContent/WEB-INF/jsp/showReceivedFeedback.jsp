<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feedback ricevuti</title>

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

<script type="text/javascript"
	src="http://www.carqueryapi.com/js/jquery.min.js"></script>
<script type="text/javascript"
	src="http://www.carqueryapi.com/js/carquery.0.3.3.js"></script>
<script type="text/javascript" src="js/carBrandAndModel.js"></script>

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
				<h1>
					<a href="/GiveMeALift"><img src="images/logo.png" alt=""></a>
				</h1>
				<c:choose>
					<c:when test="${user!=null}">
						<%@include file="userMenu.jsp"%>
					</c:when>
					<c:otherwise>
						<%@include file="classicMenu.jsp"%>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</header>
	<div class="content">
		<div class="container_12">
	
		<div class="grid_6">
				<div class="greenTable">
					<table id="personalAuto" class=table>
						<tr>
							<td colspan="2">Valutazione media</td>
						</tr>
						<tr>
							<td>{$user.giveAvgRating()}</td>
							<c:if test="{$user.giveAvgRating()==1}">
							<td>
								immagine 1 stella
							</td>
							
							</c:if>
							<c:if test="{$user.giveAvgRating()==2}">
							<td>
								immagine 2 stella
							</td>
							</c:if>
							<c:if test="{$user.giveAvgRating()==3}">
							<td>
								immagine 3 stella
							</td>
							</c:if>
							<c:if test="{$user.giveAvgRating()==4}">
							<td>
								immagine 4 stella
							</td>
							</c:if>
							<c:if test="{$user.giveAvgRating()==5}">
							<td>
								immagine 5 stella
							</td>
							</c:if>
						</tr>
					</table>

				</div>
			</div>
			
			<div class="grid_6">
				<div class="greenTable">
					<table id="personalAuto" class=table>
						<tr>
							<td colspan="2">I miei feedback</td>
						</tr>
						<c:choose>
							<c:forEach items="${senders}" var="i" >
							<tr>						
								<td> ${i.computeNickName()}</td>	
<!-- 								<td> ${ratings</td>	 -->
								
							</tr>
							</c:forEach>
					</c:choose>			
					</table>

				</div>
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
