<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feedback lasciati</title>
</head>
<body>

</body>
</html>

	<div class="content">
  	<div class="container_12">
     <div class="grid_12">
     <div class="grid_7">
<!-- 	<div class="greenTable" > -->
<!-- 		<table id="liftDetails"> -->
<!-- 			<tr> -->
<!-- 				<td colspan="2"> -->
<%-- 					<c:forEach items="${route}" var="i" > --%>
<%-- 						${i}  --%>
<%-- 						<c:if test="${i!=lift.get(0).dropOffPoint.city}"> --%>
<!-- 							- -->
<%-- 						</c:if> --%>
<%-- 					</c:forEach> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td class="labelTable"> -->
<!-- 					<h5>Partenza</h5> -->
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					<c:choose> --%>
<%-- 						<c:when test="${lift.get(0).pickUpPoint.region!=null}"> --%>
<%-- 							${lift.get(0).pickUpPoint.region}					 --%>
<%-- 						</c:when> --%>
<%-- 					</c:choose> --%>
<%-- 					<c:choose> --%>
<%-- 						<c:when test="${lift.get(0).pickUpPoint.city!=null}"> --%>
<%-- 							${lift.get(0).pickUpPoint.city}				 --%>
<%-- 						</c:when> --%>
<%-- 					</c:choose> --%>
<%-- 					<c:choose> --%>
<%-- 						<c:when test="${lift.get(0).pickUpPoint.province!=null}"> --%>
<%-- 							${lift.get(0).pickUpPoint.province}				 --%>
<%-- 						</c:when> --%>
<%-- 					</c:choose> --%>
<%-- 					<c:choose> --%>
<%-- 						<c:when test="${lift.get(0).pickUpPoint.street!=null}"> --%>
<%-- 							${lift.get(0).pickUpPoint.street}				 --%>
<%-- 						</c:when> --%>
<%-- 					</c:choose> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td class="labelTable"> -->
<!-- 					<h5>Arrivo</h5> -->
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 				<c:choose> --%>
<%-- 					<c:when test="${lift.get(0).dropOffPoint.region!=null}"> --%>
<%-- 						${lift.get(0).dropOffPoint.region}					 --%>
<%-- 					</c:when> --%>
<%-- 				</c:choose> --%>
<%-- 				<c:choose> --%>
<%-- 					<c:when test="${lift.get(0).dropOffPoint.city!=null}"> --%>
<%-- 						${lift.get(0).dropOffPoint.city}				 --%>
<%-- 					</c:when> --%>
<%-- 				</c:choose> --%>
<%-- 				<c:choose> --%>
<%-- 					<c:when test="${lift.get(0).dropOffPoint.province!=null}"> --%>
<%-- 						${lift.get(0).dropOffPoint.province}				 --%>
<%-- 					</c:when> --%>
<%-- 				</c:choose> --%>
<%-- 				<c:choose> --%>
<%-- 					<c:when test="${lift.get(0).dropOffPoint.street!=null}"> --%>
<%-- 						${lift.get(0).dropOffPoint.street}				 --%>
<%-- 					</c:when> --%>
<%-- 				</c:choose> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td class="labelTable"> -->
<!-- 					<h5>Data</h5> -->
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					${lift.get(1)} --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td class="labelTable"> -->
<!-- 					<h5>Ora</h5> -->
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					${lift.get(2)}:${lift.get(3)} --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td class="labelTable"> -->
<!-- 					<h5>Flessibilità oraria</h5> -->
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					${lift.get(4)} --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td class="labelTable"> -->
<!-- 					<h5>Deviazioni</h5> -->
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					${lift.get(5)} --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td class="labelTable"> -->
<!-- 					<h5>Viaggio Rosa</h5> -->
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					${lift.get(6)} --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td class="labelTable"> -->
<!-- 					<h5>Tipo di strada</h5> -->
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					${lift.get(7)} --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td class="labelTable"> -->
<!-- 					<h5>Dimensione del bagaglio</h5> -->
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					${lift.get(8)} --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td class="labelTable"> -->
<!-- 					<h5>Auto</h5> -->
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					${$userOffering.driverInfo.car.brand} --%>
<%-- 					 ${$userOffering.driverInfo.car.model} --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 	</table> -->
<!-- 	</div> -->
<!-- 	</div> -->
	<div class="grid_4 prefix_1">
	<div class="orangeTable" >
	<table>
			<tr>
				<td colspan="2">
					Dettagli del passaggio
				</td>
			</tr>
			<tr>
				<td>
					<h5>Prezzo</h5>
				</td>
				<td>
					<b>${lift.get(0).cost}  &#8364; a persona</b>
				</td>
			</tr>
			<tr>
				<td>
					<h5>Posti disponibili</h5>
				</td>
				<td>
					<b>${lift.get(0).nSeat}</b>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="2" >
					<a href="ContactUser?seat=${lift.get(0).nSeat}&lift=${lift.get(0).id}" class="button magenta"> <span class="phone"></span> Contatta il conducente </a>
				</td>
		</tr>
	</table>
	</div>

<div class="blueTable" >
	<table>
			<tr>
				<td colspan="2">
					Conducente
				</td>
			</tr>
			<tr>
				<td>
					<c:choose>
						<c:when test="${userOffering.profilePhoto!=null}">
							<img src="${userOffering.profilePhoto}" />
						</c:when>
						<c:otherwise>
		        			<img height="120px" src="avatars/default_user.jpg" />
		        		</c:otherwise>
					</c:choose>
				</td>
				<td>
					<p><b>${userNickName}</b></p>
					<p>${userAge} anni</p>
					<div>
					<c:choose>
						<c:when test="${userOffering.personalPreference.smoking==true}">
							<img src="images/profile/smoking.png" >
						</c:when>
						<c:when test="${userOffering.personalPreference.smoking==false}">
							<img src="images/profile/NoSmoking.png" >
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${userOffering.personalPreference.chatnessLevel==1}">
							<img src="images/profile/chatness1.png" >
						</c:when>
						<c:when test="${userOffering.personalPreference.chatnessLevel==2}">
							<img src="images/profile/chatness2.png" >
						</c:when>
						<c:when test="${userOffering.personalPreference.chatnessLevel==3}">
							<img src="images/profile/chatness3.png" >
						</c:when>
					</c:choose>
					</div>
					<c:choose>
						<c:when test="${userOffering.personalPreference.music==true}">
							<img src="images/profile/music.png" >
						</c:when>
						<c:when test="${userOffering.personalPreference.music==false}">
							<img src="images/profile/NoMusic.png" >
						</c:when>
					</c:choose>
					<div>
					<c:choose>
						<c:when test="${userOffering.personalPreference.petsOnBoard==true}">
							<img src="images/profile/pets.png" >
						</c:when>
						<c:when test="${userOffering.personalPreference.petsOnBoard==false}">
							<img src="images/profile/NoPets.png" >
						</c:when>
						</c:choose>
					<c:choose>
						<c:when test="${userOffering.personalPreference.childrensOnBoard==true}">
							<img src="images/profile/children.png" >
						</c:when>
						<c:when test="${userOffering.personalPreference.childrensOnBoard==false}">
							<img src="images/profile/NoChildren.png" >
						</c:when>
					</c:choose>
					</div>
					
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center; vertical-align: middle; " >
					<h4>Attività</h4>
				</td>
			</tr>
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
					<td>
						${memberSince}
					</td>
				</tr>
			</c:if>
			<c:if test="${lastOnline!=null}">
				<tr>
					<td>
						<h5>Online il </h5>
					</td>
					<td>
						${lastOnline}
					</td>
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
</div>
</div>
<%@include file="footer.jsp"%>