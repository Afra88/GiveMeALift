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

<link rel="stylesheet" href="js/starRating/jquery.rating.css">

<script type="text/javascript" src="js/starRating/jquery.form.js"></script>
<script type="text/javascript" src="js/starRating/jquery.js"></script>
<script type="text/javascript" src="js/starRating/jquery.MetaData.js"></script>
<script type="text/javascript" src="js/starRating/jquery.rating.js"></script>
<script type="text/javascript" src="js/starRating/jquery.pack.js"></script>


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
						<td colspan="2">Attivit�</td>
					</tr>
					<tr>
						<td>Ultimo accesso</td>
							<c:if test="${receiver.getUserActivity().getLastOnline()!=null}">
	 							<td>${receiver.getUserActivity().getLastOnline()}</td> 
							</c:if>
					</tr>
					<tr>
						<td>Utente dal</td>
							<c:if test="${receiver.getUserActivity().getMemberSince()!=null}">
								<td>${receiver.getUserActivity().getMemberSince()}</td>
							</c:if>
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
								<c:when test="${receiver.getCar().getCarPhoto()!=null}">
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
						<td>
						<c:choose>
							<c:when test="${receiver.getCar().getConfort()==1}">
							base <img height="20px" src="images/carConfort/base.png" />
							</c:when>
							<c:when test="${receiver.getCar().getConfort()==2}">
							normale <img height="20px" src="images/carConfort/normale.png" />
							</c:when>
							<c:when test="${receiver.getCar().getConfort()==3}">
							confortevole <img height="20px" src="images/carConfort/confortevole.png" />
							</c:when>
							<c:when test="${receiver.getCar().getConfort()==4}">
							lusso <img height="20px" src="images/carConfort/lusso.png" />
							</c:when>
						</c:choose>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="grid_8">
			<div class="blueTable">
				<input id="receiverId" name="receiverId" value = "${receiver.getId()}" type="hidden">
				<table>
					<tr>
						<td colspan="2">
							${receiver.computeNickName()} - <c:if test="${receiver.getGender()=='M'}"> Uomo</c:if> <c:if test="${receiver.getGender()=='F'}"> Donna</c:if>
						</td>
					</tr>
					<tr>
						<td>
							<c:choose>
								<c:when test="${receiver.profilePhoto!=null}">
									<img src=" avatars/${receiver.getId()}.jpg" /> <h5>(${receiver.computeAge()} anni) di ${receiver.getAddress().getCity()}</h5> 
										<div><br><br>Le mie preferenze
								<c:choose>
									<c:when test="${receiver.getPersonalPreference().getChatnessLevel()!=null}">
										<c:if test="${receiver.getPersonalPreference().getChatnessLevel()==1}">
											<img src="images/profile/chatness1.png">
										</c:if>
										<c:if test="${receiver.getPersonalPreference().getChatnessLevel()==2}">
											<img src="images/profile/chatness2.png">
										</c:if>
										<c:if test="${receiver.getPersonalPreference().getChatnessLevel()==3}">
											<img src="images/profile/chatness3.png">
										</c:if>
									</c:when>
								
									<c:when test="${receiver.getPersonalPreference().getMusic()!=null}">
									<c:if test="${receiver.getPersonalPreference().getMusic()==true}">
										<img src="images/profile/music.png">
									</c:if>
									<c:if test="${receiver.getPersonalPreference().getMusic()==false}">
										<img src="images/profile/NoMusic.png">
									</c:if>
								</c:when>
								<c:when test="${receiver.getPersonalPreference().getSmoking()!=null}">
									<c:if test="${receiver.getPersonalPreference().getSmoking()==true}">
										<img src="images/profile/smoking.png">
									</c:if>
									<c:if test="${receiver.getPersonalPreference().getSmoking()==false}">
										<img src="images/profile/NoSmoking.png">
									</c:if>
								</c:when>
								<c:when test="${receiver.getPersonalPreference().getPetsOnBoard()!=null}">
									<c:if test="${receiver.getPersonalPreference().getPetsOnBoard()==true}">
										<img src="images/profile/pets.png">
									</c:if>
									<c:if test="${receiver.getPersonalPreference().getPetsOnBoard()==false}">
										<img src="images/profile/NoPets.png">
									</c:if>
								</c:when>
							</c:choose>
							</div>
								</c:when>
								<c:otherwise>
									<img height="120px" src="images/default_user.jpg" /> <h5>(${receiver.computeAge()} anni) di ${receiver.getAddress().getCity()}</h5> 
									<div><br><br>Le mie preferenze
								<c:choose>
									<c:when test="${receiver.getPersonalPreference().getChatnessLevel()!=null}">
										<c:if test="${receiver.getPersonalPreference().getChatnessLevel()==1}">
											<img src="images/profile/chatness1.png">
										</c:if>
										<c:if test="${receiver.getPersonalPreference().getChatnessLevel()==2}">
											<img src="images/profile/chatness2.png">
										</c:if>
										<c:if test="${receiver.getPersonalPreference().getChatnessLevel()==3}">
											<img src="images/profile/chatness3.png">
										</c:if>
									</c:when>
								
									<c:when test="${receiver.getPersonalPreference().getMusic()!=null}">
									<c:if test="${receiver.getPersonalPreference().getMusic()==true}">
										<img src="images/profile/music.png">
									</c:if>
									<c:if test="${receiver.getPersonalPreference().getMusic()==false}">
										<img src="images/profile/NoMusic.png">
									</c:if>
								</c:when>
								<c:when test="${receiver.getPersonalPreference().getSmoking()!=null}">
									<c:if test="${receiver.getPersonalPreference().getSmoking()==true}">
										<img src="images/profile/smoking.png">
									</c:if>
									<c:if test="${receiver.getPersonalPreference().getSmoking()==false}">
										<img src="images/profile/NoSmoking.png">
									</c:if>
								</c:when>
								<c:when test="${receiver.getPersonalPreference().getPetsOnBoard()!=null}">
									<c:if test="${receiver.getPersonalPreference().getPetsOnBoard()==true}">
										<img src="images/profile/pets.png">
									</c:if>
									<c:if test="${receiver.getPersonalPreference().getPetsOnBoard()==false}">
										<img src="images/profile/NoPets.png">
									</c:if>
								</c:when>
							</c:choose>
							</div>
								</c:otherwise>
							</c:choose>							
						</td>
						
						<td>Valutazione media: 
						
							${avg}
							<c:if test="{$avg==0}">
<!-- 							<td> -->
								<img height="120px" src="images/feedback/0t.png" />
<!-- 							</td> -->
							</c:if>
							
							<c:if test="{$avg>0 && $avg<=0.5 }">
<!-- 							<td> -->
								<img height="120px" src="images/feedback/0_5t.png" />
<!-- 							</td> -->
							</c:if>
							
							<c:if test="{$avg>0.5 && $avg<=1}">
<!-- 							<td> -->
								<img height="120px" src="images/feedback/1t.png" />
<!-- 							</td> -->
							</c:if>
							
							<c:if test="{$avg>1 && $avg<=1.5}">
<!-- 							<td> -->
								<img height="120px" src="images/feedback/1_5t.png" />
<!-- 							</td> -->
							</c:if>
							
							<c:if test="{$avg>1.5 && $avg<=2}">
<!-- 							<td> -->
								<img height="120px" src="images/feedback/2t.png" />
<!-- 							</td> -->
							</c:if>
							
							<c:if test="{$avg>2 && $avg<=2.5}">
<!-- 							<td> -->
								<img height="120px" src="images/feedback/2_5t.png" />
<!-- 							</td> -->
							</c:if>
							
							<c:if test="{$avg>2.5 && $avg<=3}">
<!-- 							<td> -->
								<img height="120px" src="images/feedback/3t.png" />
<!-- 							</td> -->
							</c:if>
							
							<c:if test="{$avg>3 && $avg<=3.5}">
<!-- 							<td> -->
								<img height="120px" src="images/feedback/3_5t.png" />
<!-- 							</td> -->
							</c:if>
							
							<c:if test="{$avg>3.5 && $avg<=4}">
<!-- 							<td> -->
								<img height="120px" src="images/feedback/4t.png" />
<!-- 							</td> -->
							</c:if>
							
							<c:if test="{$avg>4 && $avg<=4.5}">
<!-- 							<td> -->
								<img height="120px" src="images/feedback/4_5t.png" />
<!-- 							</td> -->
							</c:if>
							
							<c:if test="{$avg>4.5 && $avg<=5}">
<!-- 							<td> -->
								<img height="120px" src="images/feedback/5t.png" />
<!-- 							</td> -->
							</c:if>
					</tr>
					<tr>
						<td>Perch&egrave; viaggiare con me?</td>
						<td> " ${receiver.getDescription()} "</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td colspan="2" -->
<!-- 							style="text-align: center; vertical-align: middle;"> -->
<!-- 							<h4>Attivit�</h4> -->
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
			
			
				</table>
			</div>
		</div>

<div>
		<c:choose>
			<c:when test="${released == false}">

				<form action="UserInsertFeedback" method="post" id="form">
					<div class="grid_8" style="margin: 0 auto; ">
						<div class="greenTable">
							<table>
								<tr>
									<td colspan="2">Lascia un feedback!</td>
								</tr>
								<tr>
									
								
									<td>Lascia un commento! Es. Passeggero/Conducente
										piacevole e prudente,... <br><br>
										<textarea style="margin: 0 auto;"name="feedComment"cols="30" rows="10" maxlength="500"></textarea>
									
									
									<select>
											<option value="1">Dai 1 stella :(</option>
											<option value="2">Dai 2 stelle :|</option>
											<option value="3">Dai 3 stelle :)</option>
											<option value="4">Dai 4 stelle ;)</option>
											<option value="5">Dai 5 stelle ;D</option>
									</select>
									
<!-- 									<img  src="images/feedback/0t.png"> -->
<!-- 									<img  src="images/feedback/1t.png"> -->
<!-- 									<img  src="images/feedback/2t.png"> -->
<!-- 									<img  src="images/feedback/3t.png"> -->
<!-- 									<img  src="images/feedback/4t.png"> -->
<!-- 									<img  src="images/feedback/5t.png"> -->
									</td>
									
								</tr>
								<tr>
									<td>
									<div align="center">
										<input type="submit" class="button cyan" value="Vota">
									</div>
									
									</td> 
									
									
								</tr>
							</table>
						</div>
					</div>
				</form>
			</c:when>
		</c:choose>

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
