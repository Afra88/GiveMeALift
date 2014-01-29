<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Lascia un feedback per il tuo compagno di viaggio!</title>

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
<link rel="stylesheet" href="css/starRating.css">

<link rel="icon" href="images/favicon.ico">
<link rel="shortcut icon" href="images/favicon.ico">

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
	<div class="container_12">

		<div class="grid_4">
			<div class="orangeTable">
				<table id="activityTab">
					<tr>
						<td colspan="2">Attività</td>
					</tr>
					<tr>
						<td>Ultimo accesso</td>
<%-- 						<td>${receiver.getLastOnline()}</td> --%>
					</tr>
					<tr>
						<td>Utente dal</td>
<%-- 						<td>${receiver.getMemberSince()}</td> --%>
					</tr>
				</table>
			</div>
			<div class="orangeTable">
				<table id="carTab">
					<tr>
						<td colspan="2">La mia auto</td>
					</tr>
					<tr>
						<td>Foto</td>
						<td>
						<c:choose>
								<c:when test="${receiver.getCar().getCarPhot()!=null}">
									<img height="120px" src=" avatars/${receiver.getId()}_car.jpg" />
								</c:when>
								<c:otherwise>
									<img height="120px" src="images/default_car.jpg" />
								</c:otherwise>
							</c:choose>
						
						</td>
		
					</tr>
					<tr>
						<td>Marca</td>
						<td>${receiver.getCar().getBrand()}</td>
					</tr>
					<tr>
						<td>Modello</td>
						<td>${receiver.getCar().getModel()}</td>
					</tr>
					<tr>
						<td>Colore</td>
						<td>${receiver.getCar().getColor()}</td>
					</tr>
					<tr>
						<td>Confort</td>
						<td>${receiver.getCar().getConfort()}</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="grid_8">
			<div class="blueTable">
				<input id="receiverId" name="receiverId" value = "${receiver.getId()}" type="hidden">
				<table>
					<tr>
						<td colspan="2">${receiver.computeNickName()} (${receiver.computeAge()} anni)</td>
					</tr>
					<tr>
						<td>Valutazione media</td>
						<td> ${receiver.giveAvgRating()} <td>
						<c:if test="${receiver.giveAvgRating()}==1">
						immagine 1 stella
						</c:if>
						
						<c:if test="${receiver.giveAvgRating()}==2">
						immagine 2 stella
						</c:if>
						
						<c:if test="${receiver.giveAvgRating()}==3">
						immagine 3 stella
						</c:if>
						
						<c:if test="${receiver.giveAvgRating()}==4">
						immagine 4 stella
						</c:if>
						
						<c:if test="${receiver.giveAvgRating()}==5">
						immagine 5 stella
						</c:if>
					</tr>
					<tr>
						<td>
							<c:choose>
								<c:when test="${receiver.profilePhoto!=null}">
									<img src=" avatars/${receiver.getId()}.jpg" />
								</c:when>
								<c:otherwise>
									<img height="120px" src="images/default_user.jpg" />
								</c:otherwise>
							</c:choose>							
						</td>
						<td>
							<div>
								<c:choose>
									<c:when test="${receiver.getPersonalPreference().getSmoking()==true}">
										<img src="images/profile/smoking.png">
									</c:when>
									<c:when
										test="${receiver.getPersonalPreference().getSmoking()==false}">
										<img src="images/profile/NoSmoking.png">
									</c:when>
								</c:choose>
								<c:choose>
									<c:when
										test="${receiver.personalPreference.chatnessLevel==1}">
										<img src="images/profile/chatness1.png">
									</c:when>
									<c:when
										test="${receiver.personalPreference.chatnessLevel==2}">
										<img src="images/profile/chatness2.png">
									</c:when>
									<c:when
										test="${receiver.personalPreference.chatnessLevel==3}">
										<img src="images/profile/chatness3.png">
									</c:when>
								</c:choose>
							</div> <c:choose>
								<c:when test="${receiver.personalPreference.music==true}">
									<img src="images/profile/music.png">
								</c:when>
								<c:when test="${receiver.personalPreference.music==false}">
									<img src="images/profile/NoMusic.png">
								</c:when>
							</c:choose>
							<div>
								<c:choose>
									<c:when
										test="${receiver.personalPreference.petsOnBoard==true}">
										<img src="images/profile/pets.png">
									</c:when>
									<c:when
										test="${receiver.personalPreference.petsOnBoard==false}">
										<img src="images/profile/NoPets.png">
									</c:when>
								</c:choose>
<%-- 								<c:choose> --%>
<%-- 									<c:when --%>
<%-- 										test="${receiver.personalPreference.childrensOnBoard==true}"> --%>
<!-- 										<img src="images/profile/children.png"> -->
<%-- 									</c:when> --%>
<%-- 									<c:when --%>
<%-- 										test="${receiver.personalPreference.childrensOnBoard==false}"> --%>
<!-- 										<img src="images/profile/NoChildren.png"> -->
<%-- 									</c:when> --%>
<%-- 								</c:choose> --%>
							</div>

						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td colspan="2" -->
<!-- 							style="text-align: center; vertical-align: middle;"> -->
<!-- 							<h4>Attività</h4> -->
<!-- 						</td> -->
<!-- 					</tr> -->
					<%-- <tr>
				<td>
					Passaggi Offerti
				</td>
				<td>
					${userOffering.userActivity.ridesOffered}
				</td>
			</tr> --%>
					<c:if test="${memberSince!=null}">
						<tr>
							<td>
								<h5>Utente dal</h5>
							</td>
							<td>${memberSince}</td>
						</tr>
					</c:if>
					<c:if test="${lastOnline!=null}">
						<tr>
							<td>
								<h5>Online il</h5>
							</td>
							<td>${lastOnline}</td>
						</tr>
					</c:if>
					<%-- <tr>
				<td>
					Percentuale di risposta
				</td>
				<td>
					${userOffering.userActivity.answersPercentage}
				</td>
			</tr> --%>
			
			<tr>
			<td colspan="2">" ${receiver.getDescription()} "
			</td>
			</tr>
				</table>
			</div>
		</div>
		
	</div>
<div align = "center">

<c:choose>

<c:when test="${released == true}">
	<form action="UserInsertFeedback" method="post" id="form">
			<input id="feedback" name="feedback" type="text" placeholder="passeggero/conducente"  size="15" maxlength="10" > 
			
			<div class="starRate">
				<div> Valutazione corrente: 3 stelle<b></b></div>
					<ul>
					<li><a href="#"><span> Dai 1 stella :( </span></a></li>
					<li><a href="#"><span> Dai 2 stelle :| </span></a></li>
					<li><a href="#"><span> Dai 3 stelle :) </span><b></b></a></li>
					<li><a href="#"><span> Dai 4 stella ;) </span></a></li>
					<li><a href="#"><span> Dai 5 stelle ;D </span></a></li>
					</ul>
				</div>
				
				<input type="submit" class="button cyan" value="Trova">
			
			</form>
			</c:when>
			<c:otherwise>
			buh
			</c:otherwise>
	</c:choose>
</div>		
		
		<div class="starRate">
<div>Currently rated: 3 stars<b></b></div>
<ul>
<li><a href="#"><span>Give it 5 stars</span></a></li>
<li><a href="#"><span>Give it 4 stars</span></a></li>
<li><a href="#"><span>Give it 3 stars</span><b></b></a></li>
<li><a href="#"><span>Give it 2 stars</span></a></li>
<li><a href="#"><span>Give it 1 star</span></a></li>
</ul>
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

