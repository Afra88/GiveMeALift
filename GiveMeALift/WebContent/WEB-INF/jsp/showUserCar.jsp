<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>La mia auto</title>

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
	<%@include file="chooseMenu.jsp"%>
	<div class="content">
		<div class="container_12">
			<div class="grid_6">
				<div class="greenTable">


					<table id="personalAuto">
						<tr>
							<td colspan="2">La mia auto</td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td>Anno</td> -->
<!-- 							<td><input id="yearCar" name="yearCar" type="text" -->
<%-- 								value="${year}"></input></td> --%>
<!-- 						</tr> -->
						<tr>
							<td>Marca</td>
							<td><input id="brandCar" name="brandCar" type="text"
								value="${user.getCar().getBrand()}"></input></td>
						</tr>
						<tr>
							<td>Modello</td>
							<td><input id="modelCar" name="modelCar" type="text"
								value="${user.getCar().getModel()}"></input></td>
						</tr>
						<tr>
							<td>Colore</td>
							<td><input id="colorCar" name="colorCar" type="text"
								value="${user.getCar().getColor()}"></input></td>
						</tr>
						<tr>
							<td>Confort</td>
							<td><c:choose>
									<c:when test="${user.getCar().getConfort() == 1}">
										<input id="confortCar" name="confortCar" type="text"
											value="base"></input>
										<img height="20px" src="images/carConfort/base.png" />
									</c:when>
									<c:when test="${user.getCar().getConfort() == 2}">
										<input id="confortCar" name="confortCar" type="text"
											value="normale"></input>
										<img height="20px" src="images/carConfort/normale.png" />
									</c:when>
									<c:when test="${user.getCar().getConfort() == 3}">
										<input id="confortCar" name="confortCar" type="text"
											value="confortevole"></input>
										<img height="20px" src="images/carConfort/confortevole.png" />
									</c:when>
									<c:when test="${user.getCar().getConfort() == 4}">
										<input id="confortCar" name="confortCar" type="text"
											value="lusso"></input>
										<img height="20px" src="images/carConfort/lusso.png" />
									</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td>Foto</td>
							<td>
							
							<c:choose>
							<c:when test="${user.getCar().getCarPhoto()!=null}">
								<img src="${user.getCar().getCarPhoto()}" height="120px" onerror="this.style.visibility = 'hidden'"> 
<!-- 								<input name="files[0]" type="file" /> -->
							</c:when>
							<c:otherwise>
								<img height="120px" src="avatars/default_car.jpg" />
								<input name="files[0]" type="file" />
							</c:otherwise>
						</c:choose>
							
<%-- 													<img id="photoCar" name="photoCar" src="${user.getDriverInfo().getCar().getPhoto()}"></img> --%>
<%-- 								<img src="/GiveMeALift/avatars/${user.id}_car.jpg" width="150" --%>
<!-- 								onerror="this.style.visibility = 'hidden'"> -->
								
							</td>
						</tr>
						<tr>
						</tr>
					</table>
				</div>
			</div>


			<!-- 	<div class="grid_4 prefix_1"> troppo piccola? -->
			<div class="grid_6">
				<div class="orangeTable" style="margin-top: 20px;">

					<!-- 	<form method="get" action="ModifyUserCar"> -->
					<form:form modelAttribute="uploadForm" method="post" action="SubmitCar" enctype="multipart/form-data">

						<table id="modifyMyCar">
							<tr>
								<td colspan="2">Modifica Auto</td>
							</tr>
							<tr>
								<td>Anno</td>
								<td><select name="car-years" id="car-years"></select></td>
							</tr>
							<tr>
								<td>Marca</td>
								<td><select name="car-makes" id="car-makes"></select></td>
							</tr>
							<tr>
								<td>Modello</td>
								<td><select name="car-models" id="car-models"></select></td>
							</tr>
							<tr>
								<td>Colore</td>
								<td><select id="colorAuto" name="colorAuto">
										<c:choose>
											<c:when test="${user.getCar().getColor()=='arancio'}">
												<option selected="selected">arancio</option>
											</c:when>
											<c:otherwise>
												<option>arancio</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${user.getCar().getColor()=='bianco'}">
												<option selected="selected">bianco</option>
											</c:when>
											<c:otherwise>
												<option>bianco</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${user.getCar().getColor()=='blu'}">
												<option selected="selected">blu</option>
											</c:when>
											<c:otherwise>
												<option>blu</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${user.getCar().getColor()=='celeste'}">
												<option selected="selected">celeste</option>
											</c:when>
											<c:otherwise>
												<option>celeste</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${user.getCar().getColor()=='grigio'}">
												<option selected="selected">grigio</option>
											</c:when>
											<c:otherwise>
												<option>grigio</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${user.getCar().getColor()=='marrone'}">
												<option selected="selected">marrone</option>
											</c:when>
											<c:otherwise>
												<option>marrone</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${user.getCar().getColor()=='bianco'}">
												<option selected="selected">nero</option>
											</c:when>
											<c:otherwise>
												<option>nero</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${user.getCar().getColor()=='oro'}">
												<option selected="selected">oro</option>
											</c:when>
											<c:otherwise>
												<option>oro</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${user.getCar().getColor()=='rosa'}">
												<option selected="selected">rosa</option>
											</c:when>
											<c:otherwise>
												<option>rosa</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${user.getCar().getColor()=='rosso'}">
												<option selected="selected">rosso</option>
											</c:when>
											<c:otherwise>
												<option>rosso</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${user.getCar().getColor()=='verde'}">
												<option selected="selected">verde</option>
											</c:when>
											<c:otherwise>
												<option>verde</option>
											</c:otherwise>
										</c:choose>
								</select></td>
							</tr>
							<tr>
								<td>Confort</td>
								<td><select id="confortAuto" name="confortAuto">
										<!-- 						<select id="confortAuto" name="confortAuto"> -->
										<!-- 							<option>base</option> -->
										<!-- 							<option selected="selected">normale</option> -->
										<!-- 							<option>confortevole</option> -->
										<!-- 							<option>lusso</option> -->
										<!-- 						</select> -->

										<c:choose>
											<c:when test="${user.getCar().getConfort()==1}">
												<option selected="selected">base</option>
											</c:when>
											<c:otherwise>
												<option>base</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${user.getCar().getConfort()==2}">
												<option selected="selected">normale</option>
											</c:when>
											<c:otherwise>
												<option>normale</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${user.getCar().getConfort()==3}">
												<option selected="selected">confortevole</option>
											</c:when>
											<c:otherwise>
												<option>confortevole</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${user.getCar().getConfort()==4}">
												<option selected="selected">lusso</option>
											</c:when>
											<c:otherwise>
												<option>lusso</option>
											</c:otherwise>
										</c:choose>
								</select></td>
							</tr>
							<tr>
								<td>Foto</td>
								<td>
									<!-- 					<form name="myWebForm" action="fileupload" method="post" enctype="multipart/form-data">  -->
									<!-- 						<form name="myWebForm" method="post" enctype="multipart/form-data">  -->
									<!-- 					<input type="file" name="uploadFile"/><br> --> 
									
									<input name="files[0]" type="file" /> 
									
									<!-- 					<input value="Carica" type="submit"/>							 -->
									<!-- 					</form> -->
								</td>
							</tr>
							<tr>
							</tr>
						</table>
						<div class="btns">
							<p class="center">
								<input class="button cyan" type="submit" value="Salva" />
							</p>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<form method=get action="DeleteUserCar">
		<div class="btns">
			<p align="center">
				<input class="button red" value="Elimina Auto" type="submit" />
			</p>
		</div>
	</form>







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