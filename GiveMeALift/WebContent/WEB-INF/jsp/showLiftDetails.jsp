<%@include file="header.jsp"%>
	<div class="content">
  	<div class="container_12">
     <div class="grid_12">
        <p class="locationField" id="mapValues" style="text-align: center" >
			<input class="autocomplete" type="text" id=mapFrom name=mapFrom value="${from}" /> 
			<input id=reverse value="" title="Inverti partenza e arrivo" type="button" />
			<input class="autocomplete" type="text" id=mapTo name=mapTo value="${to}"  />
		</p> 
	<form>
		<p class="center">
			<input type="button" value="Indietro" onClick="javascript:history.back()" name="button">
		</p>
	</form>
	</div>
     <div class="grid_7">
	<div class="greenTable" >
		<table id="liftDetails">
			<tr>
				<td colspan="2">
					<c:forEach items="${route}" var="i" >
						${i} 
						<c:if test="${i!=lift.get(0).dropOffPoint.city}">
							-
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					<h4>Partenza</h4>
				</td>
				<td>
					<c:choose>
						<c:when test="${lift.get(0).pickUpPoint.region!=null}">
							${lift.get(0).pickUpPoint.region}					
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${lift.get(0).pickUpPoint.city!=null}">
							${lift.get(0).pickUpPoint.city}				
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${lift.get(0).pickUpPoint.province!=null}">
							${lift.get(0).pickUpPoint.province}				
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${lift.get(0).pickUpPoint.street!=null}">
							${lift.get(0).pickUpPoint.street}				
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					<h4>Arrivo</h4>
				</td>
				<td>
				<c:choose>
					<c:when test="${lift.get(0).dropOffPoint.region!=null}">
						${lift.get(0).dropOffPoint.region}					
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${lift.get(0).dropOffPoint.city!=null}">
						${lift.get(0).dropOffPoint.city}				
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${lift.get(0).dropOffPoint.province!=null}">
						${lift.get(0).dropOffPoint.province}				
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${lift.get(0).dropOffPoint.street!=null}">
						${lift.get(0).dropOffPoint.street}				
					</c:when>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					<h4>Data</h4>
				</td>
				<td>
					${lift.get(0).departureDate}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					<h4>Ora</h4>
				</td>
				<td>
					${lift.get(1)}:${lift.get(2)}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					<h4>Flessibilità oraria</h4>
				</td>
				<td>
					${lift.get(3)}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					<h4>Deviazioni</h4>
				</td>
				<td>
					${lift.get(4)}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					<h4>Viaggio Rosa</h4>
				</td>
				<td>
					${lift.get(5)}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					<h4>Tipo di strada</h4>
				</td>
				<td>
					${lift.get(6)}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					<h4>Dimensione del bagaglio</h4>
				</td>
				<td>
					${lift.get(7)}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					<h4>Auto</h4>
				</td>
				<td>
					${$userOffering.driverInfo.car.brand}
					 ${$userOffering.driverInfo.car.model}
				</td>
			</tr>
	</table>
	</div>
	</div>
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
					<h4>Prezzo</h4>
				</td>
				<td>
					<b>${lift.get(0).cost}  &#8364; a persona</b>
				</td>
			</tr>
			<tr>
				<td>
					<h4>Posti disponibili</h4>
				</td>
				<td>
					<b>${lift.get(0).nSeat}</b>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="2" >
					<a href="" class="button magenta"> <span class="phone"></span> Contatta il conducente </a>
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
		        			<img height="120px" src="images/default_user.jpg" />
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
			<tr>
				<td>
					Passaggi Offerti
				</td>
				<td>
					${userOffering.userActivity.ridesOffered}
				</td>
			</tr>
			<tr>
				<td>
					Iscrizione
				</td>
				<td>
					${userOffering.userActivity.memberSince}
				</td>
			</tr>
			<tr>
				<td>
					Ultima volta online
				</td>
				<td>
					${userOffering.userActivity.lastOnline}
				</td>
			</tr>
			<tr>
				<td>
					Percentuale di risposta
				</td>
				<td>
					${userOffering.userActivity.answersPercentage}
				</td>
			</tr>
	</table>
</div>
</div>
</div>
</div>
<%@include file="footer.jsp"%>