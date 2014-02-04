<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Aggiungi auto</title>
		
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
	<body>
		<%@include file="chooseMenu.jsp"%>
	
	<div class="container_12">
	<div class="grid_12">
	<div class="greenTable">
	<form method="get" action="AddCarDetails">
		
			<table id="personalAuto">
			<tr>
				<td colspan="2"> La mia auto </td>
			</tr>
			<tr>
				<td rowspan="2">
					<p><h4>Manca la tua auto! Aggiungine una e riceverai pi&ugrave; contatti!</h4></p>
				</td>
			</tr>
			<tr>
				<td>
					<div class="btns">
						<p align="left">
							<input class="button orange" value="Aggiungi un'auto" type="submit"/>
						</p>
					</div>	
					
				</td>
			</tr>
		</table>
	</form>
</div>
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
    