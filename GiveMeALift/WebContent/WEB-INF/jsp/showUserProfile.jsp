<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Il mio profilo</title>

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
	<!--    <div class="content"> -->
	<!--     <div class="container_12" id="contentOpacity" > -->
	<!-- 		<div class="grid_12"> -->

			<c:choose>
			<c:when test="${modified==true}">
			<META http-equiv="refresh" content="3; URL=ShowUserProfile">
				<h3 class="center" > I dati sono stati modificati con successo! </h3>
				<h4 class="center" > Redirect in 3 secondi</h4>
			</c:when>
			</c:choose>


	<form:form modelAttribute="uploadForm" method="post"
		action="ModifyUserProfile" enctype="multipart/form-data">
		<div class="container_12" id="contentOpacity">
			<div class="grid_12">
				<div class="greenTable">
					<table id="personalPhoto" class=table>

						<tr>
							<td colspan="2">La mia foto</td>
						</tr>
						<tr>
							<!-- 				<td colspan="2"> -->
							<!-- 					<h4>La mia foto</h4> -->
							<!-- 					Aggiungi una foto ora! Sar&agrave; pi&ugrave; facile riconoscersi! -->
							<!-- 					</td> -->
							<!-- 				</tr> -->
							<!-- 				<tr> -->
							<td rowspan="2">
								<h4 align="center">
									Aggiungi/Cambia una foto ora!<br> Sar&agrave; pi&ugrave;
									facile riconoscersi!
								</h4>
							</td>
							<td><img src="/GiveMeALift/avatars/${user.id}.jpg"
								width="150" onerror="this.style.visibility = 'hidden'"> <input
								name="files[0]" type="file" /> <%-- 							<form name="myWebForm" action="/GiveMeALift/fileupload" method="post" enctype="multipart/form-data">  --%>
								<%-- 							<form name="myWebForm" method="post" enctype="multipart/form-data">  --%>
								<!-- 								<input type="file" name="uploadFile"/><br> --> <!-- 								<input value="Carica" type="submit"/>							 -->
								<%-- 							</form> --%></td>
						</tr>
					</table>
				</div>
				<%-- 				<form method="get" action="ModifyUserProfile"> --%>
				<div class="greenTable">
					<table id="personalIinfo" class="table">
						<tr>
							<td colspan="2">Informazioni personali</td>
						</tr>
						<tr>
							<td class="labelTable">Sesso</td>
							<td><c:choose>
									<c:when test="${user.gender == 'M'}"> Sign. </c:when>
									<c:when test="${user.gender == 'F'}"> Sign.ra </c:when>
								</c:choose></td>
						</tr>
						<tr>
							<td>Nome</td>
							<td><input id="profileName" name="profileName" type="text"
								value="${user.name}" required="required"></input></td>
						</tr>
						<tr>
							<td>Cognome</td>
							<td><input id="profileSurname" name="profileSurname"
								type="text" value="${user.surname}" required="required"></input>
							</td>
						</tr>
						<%-- <tr>
							<td>Scegli Nickname</td>
							<td><select id="nickname" name="nickname">
									<option>${user.name}</option>
									<option selected="selected">${user.computeNickName()}</option>
							</select></td>
						</tr> --%>
						<tr>
							<td>Email</td>
							<td><input id="profileEmail" name="profileEmail" type="text"
								readonly="readonly" value="${user.email}" required="required"></input>
							</td>
						</tr>
						<tr>
							<td>Cellulare</td>
							<td><input id="profileCell" name="profileCell" type="text"
								placeholder="3XX XXXX XXX" value="${user.getMobilePhone()}"
								maxlength="10"></input></td>
						</tr>
						<!-- <tr>
							<td colspan="2"><input id="hideCellPhone"
								name="hideCellPhone" type="checkbox" /> <small><i>Non
										mostrare il mio numero di cellulare su Give Me a Lift</i></small> manca
								nel DB</td>
						</tr> -->
						<tr>
							<td>Telefono</td>
							<td><input id="profilePhone" name="profilePhone" type="text"
								placeholder="0XX XXXX XXXX" value="${user.getPhone()}"
								maxlength="11"></input></td>
						</tr>
						<tr>
							<td colspan="2"><input id="hidePhone" name="hidePhone"
								type="checkbox" /> <i>Non mostrare i miei numeri di telefono
									su Give Me a Lift</i></td>
						</tr>
						<tr>
							<td>Anno di nascita</td>
							<td> ${user.birthYear}
								<%-- <input id="birthYear" name="birthYear" type="number"
								readonly="readonly" value="${user.birthYear}"
								required="required" size="4"></input>  --%><!-- 						<select id="birthYear" name="birthYear"> -->
								<%-- 						  <c:forEach var="i" step="1" begin="1914" end="1996" > --%>
								<%-- 							<option>${i}</option> --%> <%-- 							</c:forEach> --%>
								<!-- 						</select> --></td>
						</tr>
					</table>
				</div>
				<div class="greenTable">
					<table id="personalAddress" class=table>
						<tr>
							<td colspan="2">Il mio Indirizzo</td>
						</tr>
						<tr>
							<td>Strada</td>
							<td><input id="address" name="address" type="text"
								placeholder="Via, Viale, Piazza, ..."
								value="${user.getAddress().getStreet()}"></input></td>
						</tr>
						<tr>
							<td>Citt&agrave;</td>
							<td><input id="cityAddress" name="cityAddress" type="text"
								value="${user.getAddress().getCity()}"></input></td>
						</tr>
						<tr>
							<td>Stato</td>
							<td><input id="stateAddress" name="stateAddress" type="text"
								placeholder="Italia" value="${user.getAddress().getState()}"></input>
							</td>
						</tr>
						<!-- 				<tr> -->
						<!-- 					<td rowspan="2"> Perchè viaggiare con me? </td> -->
						<!-- 					<td><textarea  cols="40" rows="6" maxlength="700" > -->
						<!-- 						Es: "Studio a Milano, ma sono originario di Bologna e viaggio spesso per -->
						<!-- 						andare a trovare la mia famiglia."</textarea> -->
						<!-- 						manca nel DB -->
						<!-- 					</td> -->
						<!-- 				</tr> -->
						<!-- 				<tr> -->
						<!-- 				</tr> -->
						<!-- 				<tr> -->
						<!-- 					<td> -->
						<!-- <!-- 							<input value="Salva" type="submit"/> -->

						<!-- 							<p class="center"><input type="submit"  value="Salva" class="button" /></p> -->
						<!-- 					</td> -->
						<!-- <!-- 		</form>      		ModifyUserProfile -->
						<!-- 				</tr> -->
					</table>
				</div>
			</div>

<%-- 			<div class="grid_6">
				<div class="orangeTable">
					<table id="personalPref" class=table>
						<tr>
							<td colspan="2">Le mie preferenze</td>
						</tr>
						<tr>
							<td>Conversazione</td>
							<td>						
								<img src="images/profile/chatness1.png">
								<c:choose>
									<c:when test="${user.getPersonalPreference().getChatnessLevel() == 1}"> 
										<input type="radio" name="chatness" value="1" checked="checked">
									</c:when>
									<c:otherwise>
										<input type="radio" name="chatness" value="1">
									</c:otherwise>
								</c:choose>
								
								<img src="images/profile/chatness2.png">
								<c:choose>
									<c:when test="${user.getPersonalPreference().getChatnessLevel() == 2}"> 
										<input type="radio" name="chatness" value="2" checked="checked">
									</c:when>
									<c:otherwise>
										<input type="radio" name="chatness" value="2">
									</c:otherwise>
								</c:choose>
								
								<img src="images/profile/chatness3.png">
								<c:choose>
									<c:when test="${user.getPersonalPreference().getChatnessLevel() == 3}"> 
										<input type="radio" name="chatness" value="3" checked="checked">
									</c:when>
									<c:otherwise>
										<input type="radio" name="chatness" value="3">
									</c:otherwise>
								</c:choose>							
							</td>
						</tr>
						<tr>
							<td>Musica</td>
							<td>
								<img src="images/profile/NoMusic.png">
								<c:choose>
									<c:when test="${user.getPersonalPreference().getMusic() == false}"> 
										<input type="radio" name="music" value="noMus" checked="checked">
									</c:when>
									<c:otherwise>
										<input type="radio" name="music" value="noMus">
									</c:otherwise>
								</c:choose>	
								
								<img src="images/profile/music.png">
								<c:choose>
									<c:when test="${user.getPersonalPreference().getMusic() == true}"> 
										<input type="radio" name="music" value="yesMus" checked="checked">
									</c:when>
									<c:otherwise>
										<input type="radio" name="music" value="yesMus" >
									</c:otherwise>
								</c:choose>	
							</td>
						</tr>
						<tr>
							<td>Fumo</td>
							<td>
								<img src="images/profile/NoSmoking.png">
								<c:choose>
									<c:when test="${user.getPersonalPreference().getSmoking() == false}"> 
										<input type="radio" name="smoking" value="noSmok" checked="checked">
									</c:when>
									<c:otherwise>
										<input type="radio" name="smoking" value="noSmok">
									</c:otherwise>
								</c:choose>
							
								<img src="images/profile/smoking.png">
								<c:choose>
									<c:when test="${user.getPersonalPreference().getSmoking() == true}"> 
										<input type="radio" name="smoking" value="yesSmok" checked="checked">
									</c:when>
									<c:otherwise>
										<input type="radio" name="smoking" value="yesSmok">
									</c:otherwise>
								</c:choose>
								
							</td>
						</tr>
						<tr>
							<td>Animali a bordo</td>
							<td>
								<img src="images/profile/NoPets.png">
								<c:choose>
									<c:when test="${user.getPersonalPreference().getPetsOnBoard() == false}"> 
										<input type="radio" name="pets" value="noPets" checked="checked">
									</c:when>
									<c:otherwise>
										<input type="radio" name="pets" value="noPets">
									</c:otherwise>
								</c:choose>
								
									
								<img src="images/profile/pets.png">
								<c:choose>
									<c:when test="${user.getPersonalPreference().getPetsOnBoard() == true}"> 
										<input type="radio" name="pets" value="yesPets" checked="checked">
									</c:when>
									<c:otherwise>
										<input type="radio" name="pets" value="yesPets">
									</c:otherwise>
								</c:choose>								
							</td>
						</tr>
					</table>
				</div>
			</div> --%>

			<div class="grid_6">
				<div class="greenTable">
					<table id="personalOther" class=table>
						<tr>
							<td>Perchè viaggiare con me?</td>
						</tr>
						<tr>
							<td colspan="2">
								<p align="center">
									<textarea cols="51" rows="9" maxlength="1000">
						Es: "Studio a Milano, ma sono originario di Bologna e viaggio spesso per
						andare a trovare la mia famiglia."</textarea>
								</p> manca nel DB
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="btns">
			<p align="center">
				<input class="button orange" type="submit" value="Salva" />
			</p>
		</div>
	</form:form>

	<!--   </div> -->

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

