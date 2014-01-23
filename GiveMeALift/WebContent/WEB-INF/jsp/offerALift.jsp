<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ page session="false" %> --%>
<!-- <html> -->
<!--     <head> -->
<!-- 		  <link rel="stylesheet" href="css/mainstyle.css" type="text/css" />  -->
<!--         <title>Offri un viaggio!</title> -->
<!--     </head> -->

<!-- 	<body> -->
<!-- 	<div class = "container">  -->
<!-- 		<div class="header" align=right> -->
<!-- 			<ul id=menu>  -->
<!-- 				<li> Iscriviti!</li> -->
<!-- 				<li> Login </li> -->
<!-- 				<li> Come funziona? <li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
<!-- 	</div> -->

<%@include file="header.jsp"%>
<div class="container_12">
	<div class="grid_12">
		
<!-- 	<div class="ui-widget" style="margin-top: 2em; font-family: Arial"> -->
<!-- 		Result: -->
<!-- 		<div id="log" style="height: 200px; width: 300px; overflow: auto;" -->
<!-- 			class="ui-widget-content"></div> -->
<!-- 	</div> -->
	
	<c:choose>
		<c:when test="${error!=null}">
			<div class="clear" ></div>
			<h4 class="center" >${error}</h4>
		</c:when>
	</c:choose>

	<form method="get" action="InsertALift">
	<p class="greenTable">
		<table id="fillOffer">
					<tr><td colspan="2">Itinerario</td></tr>
			<tr id="mapTr">
			<td colspan="2">
			<p><input class="button" type="button"  id="FromAToB"  value="Percorso" /></p>
			<div id="map_canvas" style="float:left;width:100%;height:400px"></div>
<!-- 				<div style="float:right;width:100px;height:400px;overflow:auto"> -->
<!-- 			  	<button id="undo" style="display:block;margin:auto" onclick="undo()">Undo -->
<!-- 			 	 </button> -->
<!-- 			 	<div id="directions_panel" style="width:200px"></div> -->
<!-- 			</div> -->
			</td>
			</tr>
			<tr>
			<tr>
				<td>
				Luogo di Partenza:				
				<input class="autocomplete" type="text" id=mapFrom name=mapFrom value="Partenza" /> 
				</td>
				<td>
				Luogo di arrivo:				 
				<input class="autocomplete" type="text" id=mapTo name=mapTo value="Arrivo"  />
				</td>
			</tr>
			<tr>
				<td colspan="2"><span class="label"> Aggiungi Tappe intermedie:
				</span>				
					<ol>
<!-- 						<li><input id="detour0" class="autocomplete" type="text" name="detour0"></input></li> -->
<!-- 						<li><input type="text" id=detour name=detour /></li> -->
							<li></li>
					</ol></td>
			</tr>
			<tr>
				<td>
					<input class="button" type="button"  id="add_detour" value="Aggiungi"  />
				</td>
				<td>
					<input class="button" type="button"  id="remove_detour" value="Rimuovi"/>
				</td>
			</tr>
		
		
			<tr>
				<td colspan="2"><h2>Data e ora</h2></td>
			<tr>
			<tr>
				<td><span class="label"> Data partenza: </span> 
					<span id=date><script type="text/javascript" src="js/currentDate.js"></script></span>
				</td>
				<td>
					<span class="label"> Ora partenza: </span>
					<select id="goingTimeH" name="goingTimeH" >
					  <c:forEach var="i" step="1" begin="00" end="23" >
						<option>${i}</option>
						</c:forEach>
					</select> 														  						
					<select id="goingTimeM" name="goingTimeM">
					 <c:forEach var="i" step="1" begin="00" end="59" >
						<option>${i}</option>
					</c:forEach>							
					</select> 	 
				</td>
			</tr>
			<tr>
				<td><span class="label"> Data ritorno (se previsto): </span> 			
					<span id=date1><script type="text/javascript" src="js/currentDate.js">
					</script></span>
				</td>
				<td>
					<span class="label"> Ora ritorno: </span>					
						<select id="returnTimeH" name="returnTimeH">
						 <c:forEach var="i" step="1" begin="00" end="23" >
							<option>${i}</option>
						</c:forEach> 
						</select> 														  						
						<select id="returnTimeM" name="returnTimeM">
						<c:forEach var="i" step="1" begin="00" end="59" >
						<option>${i}</option>
						</c:forEach>
						</select> 								
<!-- 					<span id=date1><script type="text/javascript" src="js/currentDate.js"></script></span> -->
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Conferma" class="button" />
				</td>
			</tr>
		</table>
	</form>
</div>
</div>


<%@include file="footer.jsp"%>
<!-- 	</body> -->
<!-- </html> -->