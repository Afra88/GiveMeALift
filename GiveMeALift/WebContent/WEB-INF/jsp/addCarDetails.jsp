<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		<link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet" href="css/font-awesome.css">
		<link rel="stylesheet" href="css/form.css">
		<link rel="stylesheet" href="css/pictogram-button.css">
	    
	    <link rel="icon" href="images/favicon.ico">
		<link rel="shortcut icon" href="images/favicon.ico">
		
		<script type="text/javascript" src="http://www.carqueryapi.com/js/jquery.min.js"></script>
		<script type="text/javascript" src="http://www.carqueryapi.com/js/carquery.0.3.3.js"></script>
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
		
		
		
		
			<form:form modelAttribute="uploadForm" method="post" action="SubmitCar"  enctype="multipart/form-data">
			<div class="greenTable">
				<table id="addMyCar" class="table">
				<tr>
					<td colspan="2"> La mia auto </td>
				</tr>
				<tr>
					<td>Anno</td>
					<td><select name="car-years" id="car-years"></select></td>
				</tr>
				<tr>
					<td>Marca</td>
					<td> 
						<select name="car-makes" id="car-makes"></select> 
					</td>
				</tr>
				<tr>
					<td>Modello</td>
					<td>
						<select name="car-models" id="car-models"></select>
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
							<option>arancio</option>
							<option>bianco</option>
							<option>blu</option>
							<option>celeste</option>				
							<option selected="selected">grigio</option>
							<option>marrone</option>
							<option>nero</option>
							<option>oro</option>
							<option>rosa</option>
							<option>rosso</option>
							<option>verde</option>
						</select>
					</td>
				</tr>
				
				<tr>
				<td>Foto</td>
<!-- 				<td> -->
<%-- 					<img id="photoCar" name="photoCar" src="${user.getDriverInfo().getCar().getPhoto()}"></img> --%>
<!-- 				</td> -->


					<td>
						<c:choose>
							<c:when test="${user.getCar().getPhotoCar()!=null}">
								<img src="/GiveMeALift/avatars/${user.id}_car.jpg" height="120px" onerror="this.style.visibility = 'hidden'"> 
								<input name="files[0]" type="file" />
							</c:when>
							<c:otherwise>
								<img height="120px" src="images/default_car.jpg" />
								<input name="files[0]" type="file" />
							</c:otherwise>
						</c:choose>
					</td>


<!-- 					<td> -->
<%-- 					<img src="/GiveMeALift/avatars/${user.id}_car.jpg"   width="150" onerror="this.style.visibility = 'hidden'"> --%>
<!-- 					<form name="myWebForm" action="/GiveMeALift/fileupload" method="post" enctype="multipart/form-data">  -->
<!-- 							<form name="myWebForm" method="post" enctype="multipart/form-data">  --> 
<!-- 					<input type="file" name="uploadFile"/><br> -->

<%-- 					<form:form method="post" action="Save" modelAttribute="uploadForm" enctype="multipart/form-data"> --%>
<!-- 					<p>Select files to upload. Press Add button to add more file inputs.</p> -->
				
<!--      				<input name="files[0]" type="file" /> -->
<!-- 				    <input type="submit" value="Carica foto" />				     -->
<%-- 					</form:form> --%>
<!-- 				</td> -->
			</tr>
				</table>
			</div>
			
			<div class="btns">
				<p align="center">
					<input class="button orange" value="Conferma" type="submit"/>
				</p>
			</div>
			</form:form>
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