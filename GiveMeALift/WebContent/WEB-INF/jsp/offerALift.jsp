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


<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>


<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>


<link rel="stylesheet" type="text/css" href="js/geonames/geonames.css">
<script type="text/javascript" src="js/geonames/geonames.js"></script>

<script type="text/javascript" src="js/managePossibleDetour.js"></script>
<script type="text/javascript" src="js/checkInsertedFields.js"></script>




<div class="sidebar1">

	<a href="/GiveMeALift/">Homepage</a>

</div>

<div class="content">



	<form method="get" action="InsertALift">
		
<!-- 	<div class="ui-widget" style="margin-top: 2em; font-family: Arial"> -->
<!-- 		Result: -->
<!-- 		<div id="log" style="height: 200px; width: 300px; overflow: auto;" -->
<!-- 			class="ui-widget-content"></div> -->
<!-- 	</div> -->
	

	
		<table id="fillOffer" class="table">
		
					<tr><td><h2>Itinerario</h2></td></tr>
			<tr>
				<td>
				<span class="label"> Luogo di Partenza: </span> 							
<!-- 				<input id="city">						 -->
				<input type="text" id=mapFrom name=mapFrom />
				
				<span class="label"> Luogo di arrivo: </span>				 
				<input	type="text" id=mapTo name=mapTo />
				<br>			
		Powered by <a href="http://geonames.org">geonames.org</a>	
				</td>
			</tr>
			<tr>
				<td><span class="label"> Tappa intermedia (se prevista):
				</span>				
					<ol>
<!-- 						<li><input type="text" id=detour name=detour /></li> -->
					</ol></td>
			</tr>
			<tr>
				<td>
					<input class="button" type="button"  id="add_detour" value="Aggiungi tappa intermedia"  />
					<input class="button" type="button"  id="remove_detour" value="Rimuovi tappa intermedia "/>
				</td>
			</tr>
		
		
			<tr>
				<td><h2>Data e ora</h2></td>
			<tr>
			<tr>
				<td colspan="2"><span class="label"> Data partenza: </span> 
					<span id=date><script type="text/javascript" src="js/currentDate.js"></script></span> 
					<span class="label"> Ora partenza: </span>
					<select id="goingTimeH" name="goingTimeH" >
					  <option>0<option>1<option>2<option>3<option>4<option>5<option>6<option>7<option>8<option>9 <option>10<option>11<option>12<option>13
					  <option>14<option>15<option>16<option>17<option>18<option>19<option>20<option>21<option>22<option>23 
					</select> 														  						
					<select id="goingTimeM" name="goingTimeM">
					  <option>00<option>15<option>30<option>45						
					</select> 	 
				</td>
			</tr>
			<tr>
				<td><span class="label"> Data ritorno (se previsto): </span> 			
					<span id=date1><script type="text/javascript" src="js/currentDate.js">
					</script></span>
					<span class="label"> Ora ritorno: </span>					
						<select id="returnTimeH" name="returnTimeH">
						  <option>0<option>1<option>2<option>3<option>4<option>5<option>6<option>7<option>8<option>9
						  <option>10<option>11<option>12<option>13<option>14<option>15<option>16<option>17
						  <option>18<option>19<option>20<option>21<option>22<option>23 
						</select> 														  						
						<select id="returnTimeM" name="returnTimeM">
						  <option>00<option>15<option>30<option>45					
						</select> 								
<!-- 					<span id=date1><script type="text/javascript" src="js/currentDate.js"></script></span> -->
				</td>
			</tr>
			<tr>
				<td>
					<input class="button" type="button"  id="FromAToB"  value="Percorso" />
					<input type="submit" value="Conferma" class="button" />
				</td>
			</tr>
		</table>
	</form>
		
		
	<p id="map"></p>
</div>


<%@include file="footer.jsp"%>
<!-- 	</body> -->
<!-- </html> -->