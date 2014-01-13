<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%--     pageEncoding="ISO-8859-1"%> --%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!-- <title>Inserisci un viaggio</title> -->
<!-- </head> -->
<!-- <body> -->

<!-- </body> -->
<!-- </html> -->

<%@include file="header.jsp"%>

<div class="sidebar1">
	<a href="/GiveMeALift/">Homepage</a>
</div>

<div class="content">
	<form method="get" action="SubmitALift">
		<table id="plusDetails" class=table>
			<tr>
				<td colspan="3" align="center"><h2>Quota a passeggero</h2></td>
			</tr>
			<tr>
				<td>
					<h3>${inputs.get(0)} <img src="images/freccia1.gif" height="10px"/> ${inputs.get(1)} </h3>
				<td> 
						<input type="text" id="price" name="price">
					<i>euro</i>
				</td>
				
			</tr>
			<tr>
				<td>
					<h2> Numero posti disponibili </h2>
				</td>
				<td>
					<input type="text" name="seats">
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<h2> Dettagli del viaggio</h2>
				</td>
			</tr>
			<tr>
				<td>
					<textarea  cols="30" maxlength="700" >
					Fornisci informazioni aggiuntive sul tuo viaggio. 
					Cerca di invogliare più passeggeri a contattarti!
					</textarea>
				</td>
			
				<td colspan="2">
					<p><small><i>* Nel rispetto della tua privacy, non indicare mai i tuoi recapiti nella descrizione del viaggio.</i></small></p>
				</td>
			</tr>
			<tr>
				<td><h2> Bagaglio massimo consentito: </h2></td>
				<td>
					<select>
	 				 	<option value="medium">Medio</option>
	  					<option value="small">Piccolo</option>
	  					<option value="large">Grande</option>
					</select>
				</td>
			</tr>
			<tr>
			<td><h2> Disponibile a deviazioni: </h2></td>
				<td>
					<select>
	  					<option value="15min">15 minuti al massimo</option>
	 				 	<option value="nothing">Nessuna deviazione, mi dispiace... :|</option>
	  					<option value="30min">30 minuti al massimo</option>
	  					<option value="any">Qualsiasi deviazione. No Problem! :D</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<input id="checkLicence" type="checkbox" name="drivingLicence" value="drivingLicence"/> 
				</i>Dichiaro di essere in possesso di patente di guida valida ed assicurazione RCA </i>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="right">
					<input class="button" type="button" id="goBack" value="Indietro"/>
					<input class="button" type="submit" value="Pubblica annuncio"/>
				</td>
			</tr>
	<%--  		<c:forEach var="i" items="${inputs}">  --%>
	<!--  		<tr>  -->
	<%--  			<td><h3>${i}</h3></td> --%>
	<!--  		<tr>  -->
	<%--  		</c:forEach> --%>
		</table>
	</form>
	<p id="map"></p>
</div>

<%@include file="footer.jsp"%>