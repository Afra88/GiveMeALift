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
<script type="text/javascript" src="js/managePossibleDetour.js"></script>


<div class="sidebar1">

	<a href="/GiveMeALift/">Homepage</a>

</div>

<div class="content">
	<form method="get" action="OfferALift">
		<table id="fillOffer" class="table">
			<tr>
				<td><h2>Data e ora</h2></td>
			<tr>
			<tr>
				<td><span class="label"> Data partenza: </span> <span id=date><script
							type="text/javascript" src="js/currentDate.js"></script></span> <span
					class="label"> Ora partenza: </span> <!-- 				<span id=date><script type="text/javascript" src="js/currentDate.js"></script></span> -->
				</td>
			</tr>
						<tr>
				<td><span class="label"> Data ritorno (se previsto): </span> <!-- 			<span id=date><script type="text/javascript" src="js/currentDate.js"></script></span> -->
					<span class="label"> Ora partenza: </span> <!-- 			<span id=date><script type="text/javascript" src="js/currentDate.js"></script></span> -->
				</td>
			</tr>
			
			


			<tr>		<td>		<h2>Itinerario</h2>			</td>		</tr>
			<tr>
				<td><span class="label"> Luogo di Partenza: </span> <input
					type="text" id=mapFrom name=mapFrom /></td>
			</tr>
			<tr>
				<td><span class="label"> Luogo di arrivo: </span> <input
					type="text" id=mapTo name=mapTo /></td>
			</tr>

			<tr>
				<td><span class="label"> Tappa intermedia (se prevista):
				</span>
				
			<!-- 	VEDERE COME PRENDERE + ELEMENTI CON LO STESSO ID?? o mettere diversi id .... -->
					<ol>
						<li><input type="text" id=detour name=detour /></li>
					</ol></td>
			</tr>
		</table>
	</form>


	<p align=right>
				<input class="button" type="button"  id="add_detour" value="Aggiungi una tappa intermedia"  />
				<input class="button" type="button"  id="remove_detour" value="Rimuovi una tappa "/>
		<input class="button" id="FromAToB" type="button" value="Percorso" />
		<input type="submit" value="Conferma" class="button" />
	</p>
	<p id="map"></p>
</div>


<%@include file="footer.jsp"%>
<!-- 	</body> -->
<!-- </html> -->