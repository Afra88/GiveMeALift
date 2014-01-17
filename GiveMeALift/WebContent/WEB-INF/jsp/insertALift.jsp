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

<div class="container_12">
	<div class="grid_12">
	<form method="get" action="SubmitALift">
	
	
	<!-- hidden div per visualizzare nel dom i valori di path -->
	<c:choose>
	<c:when test="${path.size()>0}">
		<c:forEach var="i"  begin="0" end="${path.size()-1}" > 
			<div name="path" hidden=true>${path.get(i)}</div>
		</c:forEach>
	</c:when>
	</c:choose>
	
	<!-- hidden div per visualizzare nel dom i valori di inputs -->
	<c:forEach var="i"  begin="0" end="${inputs.size()-1}" > 
		<div name="inputs"  hidden=true>${inputs.get(i)}</div>
	</c:forEach>
	
	
	<!-- hidden div inserire tag input con name e value caricati con jquery -->	
	<div id="inputs" hidden=true></div>


 	<script type="text/javascript"> 	 	
 	var elements = document.getElementsByName("path") 	// name="path"	
	for ( var i = 1; i < elements.length-1; i++) {
		 //id="inputs"
		$("#inputs").append(                             
 			"<input name=\"detour"+(i-1)+"\" value=\"" + elements[i].innerHTML + "\">"
 		);
	} 
 	
 	 //id="inputs"
	$("#inputs").append("<input name=\"mapFrom\" value=\"" +elements[0].innerHTML + "\">");
	$("#inputs").append("<input name=\"mapTo\" value=\"" + elements[elements.length-1].innerHTML + "\">");

  	
	var elements2 = document.getElementsByName("inputs") 	// name="inputs"	

	var dateG = elements2[0].innerHTML;	
	var dateR = elements2[1].innerHTML;
	
 	//id="inputs"
	if (dateR.localeCompare("NULL")) {
		$("#inputs").append("<input name=\"date\" value=\""+dateG+"\">" );  //DATA A,DATA R				
	}else {
		$("#inputs").append("<input name=\"date\" value=\"" + dateG+","+dateR+ "\">" );  //DATA A,DATA R		
	}	
	$("#inputs").append("<input name=\"goingTimeH\" value=\"" + elements2[2].innerHTML + "\">" );  // H ANDATA
 	$("#inputs").append("<input name=\"goingTimeM\" value=\"" + elements2[3].innerHTML + "\">" );  // M ANDATA
 	
 	//CONTROLLI TUTTI SULLA DATA DI RITORNO
 	if (!dateR.localeCompare("NULL")) { 
	 	$("#inputs").append("<input name=\"returnTimeH\" value=\"" + elements2[4].innerHTML + "\">" );  // H RITORNO
	 	$("#inputs").append("<input name=\"returnTimeM\" value=\"" + elements2[5].innerHTML + "\">" );  // M RITORNO 		
 	} 		 	
	</script>
		        
  
	
	<div class="greenTable">
		<table id="plusDetails" class=table>
			<tr>
				<td colspan="2">
					Dettagli Aggiuntivi
				</td>
			</tr>
			<tr>
				<td colspan="2">Quota a passeggero</td>
			</tr>
			<c:forEach var="i"  begin="0" end="${path.size()-2}" > 
			<tr>
				<td>
					${path.get(i)} <img src="images/freccia1.gif" height="10px"/> ${path.get(i+1)}
				</td>
				<td> 
					<input type="number" id="price" name="price" class="toSum" maxlength="3" size="3">&#8364;
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
			
				<td><font color="orange"><b>TOTALE</b></font></td>
				<td><div id="sum">0 &#8364;</div></td>
			</tr>
			<tr>
				<td>
					Numero posti disponibili
				</td>
				<td>
					<input id="seats" class="number" type="number" name="seats" maxlength="4" size="3">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<h2>Descrizione del passaggio</h2>
				</td>
			</tr>
			<tr>
				<td>
					<textarea  cols="40" rows="6" maxlength="700" >
					Fornisci informazioni aggiuntive sul tuo viaggio. 
					Cerca di invogliare più passeggeri a contattarti!
					</textarea>
				</td>
			
				<td>
					<p><small><i>* Nel rispetto della tua privacy, non indicare mai i tuoi recapiti nella descrizione del viaggio.</i></small></p>
				</td>
			</tr>
			<tr>
				<td><h2> Bagaglio massimo consentito: </h2></td>
				<td>
					<select id="luggage" name="luggage">
	 				 	<option value="medium">Medio</option>
	  					<option value="small">Piccolo</option>
	  					<option value="large">Grande</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><h2> Partirò: </h2></td>
				<td>
					<select id="delay" name="delay">
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
					<select id="deviation" name="deviation">
	 				 	<option value="nothing">Nessuna deviazione, mi dispiace... :|</option>
	  					<option value="15min">15 minuti al massimo</option>
	  					<option value="30min">30 minuti al massimo</option>
	  					<option value="any">Qualsiasi deviazione. No Problem! :D</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><h2> Tratta percorsa </h2></td>
				<td>
					<h2><input type="number" id="timesForThisRoute" name="timesForThisRoute" class="number" maxlength="6" size="6" value="0" > volte</h2>
				</td>
			</tr>
			<tr>
				<td><h2> Preferisco viaggiare su: </h2></td>
				<td>
					<select id="roadType" name="roadType">
						<option value="freeway">Autostrada</option>
						<option value="country">Strada di paese</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><h2> Viaggio rosa: </h2></td>
				<td>
					<select id="pinkTrip" name="pinkTrip"> 
	  					<option value="bothPass">viaggio con uomini e donne</option>
	  					<option value="onlyWomen">viaggio solo con donne</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2"> 
					<input id="checkLicence" type="checkbox" name="drivingLicence" value="drivingLicence"/> 
					<i>Dichiaro di essere in possesso di patente di guida valida ed assicurazione RCA </i>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><h2>Riepilogo Dati</h2></td>
			</tr>
			<tr>
				<td colspan="2" >
					<b><i>${path.get(0)} <img src="images/freccia1.gif" height="10px"/> ${path.get(path.size()-1)}</b></i>
					<br>
					<font color="blue">Andata:</font> ${inputs.get(0)} - ore: ${inputs.get(2)}:${inputs.get(3)}
					<br>
					<font color="blue">Ritorno:</font> ${inputs.get(1)} - ore: ${inputs.get(4)}:${inputs.get(5)}
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
				<td>
					<input type="button" id="goBack" value="Indietro" onClick="history.back()"/>
				</td>
				<td>
					<input type="submit" value="Pubblica"  />
				</td>
			</tr>
		</table>
		</div>
	</form>
<!-- 	<p id="map"></p> -->
</div>
</div>

<!-- serve per l'input di soli numeri (va messo dopo)  -->
<script type="text/javascript"> $(".number").format({precision: 0,autofix:true});</script>
<script type="text/javascript"> $(".toSum").format({precision: 0,autofix:true});</script>

<%@include file="footer.jsp"%>