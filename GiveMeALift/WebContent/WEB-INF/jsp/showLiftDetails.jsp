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
					${lift.pickUpPoint.city}
					-
					<c:forEach items="${lift.detours}" var="i" >
						${i.dropOffPoint.city}
					</c:forEach>
					${lift.dropOffPoint.city}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					Partenza
				</td>
				<td>
					${lift.pickUpPoint.region},
					${lift.pickUpPoint.city},
					${lift.pickUpPoint.province},
					${lift.pickUpPoint.street}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					Arrivo
				</td>
				<td>
					${lift.dropOffPoint.region},
					${lift.dropOffPoint.city},
					${lift.dropOffPoint.province},
					${lift.dropOffPoint.street}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					Data
				</td>
				<td>
					${lift.departureDate}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					Ora
				</td>
				<td>
					${lift.departureTime}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					Flessibilità oraria
				</td>
				<td>
					${lift.liftPreferences.scheduleFlexibility}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					Deviazioni
				</td>
				<td>
					TODO
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					Dimensione del bagaglio
				</td>
				<td>
					<c:choose>
						<c:when test="${lift.liftPreferences.luggageSize}==1">
							Piccolo
						</c:when>
						<c:when test="${lift.liftPreferences.luggageSize}==2">
							Medio
						</c:when>
						<c:when test="${lift.liftPreferences.luggageSize}==3">
							Grande
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					Auto
				</td>
				<td>
					${lift.userOffering.driverInfo.car.brand}
					 ${lift.userOffering.driverInfo.car.model}
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					Viaggio Rosa
				</td>
				<td>
					<c:choose>
						<c:when test="${lift.liftPreferences.pinkTrip}==true">
							Si
						</c:when>
						<c:otherwise>
							No
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="labelTable">
					Tipo di strada
				</td>
				<td>
					${lift.liftPreferences.roadType}
				</td>
			</tr>
	</table>
	</div>
	</div>
	<div class="grid_4 prefix_1">
	<div class="orangeTable" >
	<table>
			<tr>
				<td>
					Prezzo
				</td>
			</tr>
			<tr>
				<td>
					${lift.cost}  &#8364; a persona
				</td>
			</tr>
	</table>
	</div>
	 <div class="orangeTable" >
		<table>
		<tr>
			<td>
					Posti disponibili
				</td>
		</tr>
		<tr>
				<td>
					${lift.nSeat}
				</td>
		</tr>
	</table>
	</div>
	<div class="orangeTable" >
		<table >
		<tr>
			<td>
				Prenota il viaggio
				</td>
			</tr>
			<tr>
				<td>
					TODO
				</td>
		</tr>
	</table>
	</div>

<div class="blueTable" >
	<table>
			<tr>
				<td>
					Conducente
				</td>
			</tr>
			<tr>
				<td>
					<%-- <img src="${lift.user.profilePhoto}" /> --%>
				</td>
			</tr>
			<tr>
				<td>
					<%-- ${lift.user.getYears()} --%>
				</td>
			</tr>
			<tr>
				<td>
					<!-- user activity  -->
				</td>
			</tr>
	</table>
</div>
</div>
</div>
</div>
<%@include file="footer.jsp"%>