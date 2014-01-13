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
<script type="text/javascript" src="js/dynamicPriceSum.js"></script>
<script type="text/javascript" src="js/jQueryElement/jquery.format.1.05.js"></script>


<div class="sidebar1">
	<a href="/GiveMeALift/">Homepage</a>
</div>

<div class="content">
	<form method="get" action="SubmitALift">
		<table id="plusDetails" class=table>
			<tr>
				<td colspan="3" align="center"><h2>Quota a passeggero</h2></td>
			</tr>
			<c:forEach var="i"  begin="0" end="${path.size()-2}" > 
			<tr>
				<td>
					<h3>  ${path.get(i)} <img src="images/freccia1.gif" height="10px"/> ${path.get(i+1)}</h3>
				<td> 
						<input type="number" id="price" name="price" class="number" maxlength="3" size="3">
					<i>euro</i>
				</td>
			</tr>
			</c:forEach>			
<!-- 				<td> -->
<%-- 					<h3>${path.get(0)} <img src="images/freccia1.gif" height="10px"/> ${inputs.get(1)} </h3> --%>
<!-- 				<td>  -->
<!-- 						<input type="text" id="price" name="price"> -->
<!-- 					<i>euro</i> -->
<!-- 				</td> -->
			<tr>
<%-- 			${path.get(0)} <img src="images/freccia1.gif" height="10px"/> ${path.get(path.size()-1)} --%>
			
				<td><h3><font color="orange"><b>TOTALE</b></font></h3>
				<td><h2 id="sum">0 Euro</h2></td>
			</tr>
			<tr>
				<td>
					<h2> Numero posti disponibili </h2>
				</td>
				<td>
					<input id="seats" class="numeric" type="number" name="seats" maxlength="4" size="3">
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<h2> Dettagli del viaggio</h2>
				</td>
			</tr>
			<tr>
				<td>
					<textarea  cols="40" rows="6" maxlength="700" >
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
					<select id="luggage">
	 				 	<option value="medium">Medio</option>
	  					<option value="small">Piccolo</option>
	  					<option value="large">Grande</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><h2> Partitò: </h2></td>
				<td>
					<select id="delay">
	  					<option value="strict">puntuale</option>
	 				 	<option value="15min">+/- 15 minuti</option>
	  					<option value="30min">+/- 30 minuti</option>
	  					<option value="1h">+/- un'ora</option>
	  					<option value="2h">+/- due ore</option>
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
				<td><h2> Viaggio rosa: </h2></td>
				<td>
					<select id="pinkTrip">
	  					<option value="bothPass">viaggio con uomini e donne</option>
	  					<option value="bothPass">viaggio solo con donne</option>
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
				<td colspan="2" align="center"><h2>Riepilogo Dati</h2></td>
			</tr>
			<tr>
				<td rowspan="2"><h3><b><i>${path.get(0)} <img src="images/freccia1.gif" height="10px"/> ${path.get(path.size()-1)}</b></i></h3>
				<br>
				<h3><font color="blue">Andata:</font> ${inputs.get(0)} - ore: ${inputs.get(2)}:${inputs.get(3)} </h3>
				<br>
				<h3><font color="blue">Ritorno:</font> ${inputs.get(1)} - ore: ${inputs.get(4)}:${inputs.get(5)}</h3>
				</td>	
<%-- 	 		<c:forEach var="i" items="${inputs}">  --%>
<!-- 	 		<tr>  -->
<%-- 	 			<td><h3>${i}</h3></td> --%>
<!-- 	 		<tr>  -->
<%-- 	 		</c:forEach> --%>

<!-- 				<td rowspan="3"> -->
<!-- 					<p id="map"></p> -->
<!-- 				</td> -->

			</tr>
			<tr>
				<td colspan="3" align="right">
					<input class="button" type="button" id="goBack" value="Indietro" onClick="history.back()"/>
					<input class="button" type="submit" value="Pubblica annuncio"/>
				</td>
			</tr>
		</table>
	</form>
<!-- 	<p id="map"></p> -->
</div>


<!-- va messo dopo  -->
<script type="text/javascript"> $(".number").format({precision: 0,autofix:true});</script>

<%@include file="footer.jsp"%>