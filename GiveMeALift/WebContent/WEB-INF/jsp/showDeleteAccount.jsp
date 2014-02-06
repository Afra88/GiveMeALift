<%@include file="header.jsp"%>

<META http-equiv="refresh" content="5; URL=/GiveMeALift/">

<div class="container_12">
	<div class="grid_12">
	<c:choose>
		<c:when test="${error==true}">
			<h3 class="center" >In questo momento non è possibile effettuare l'eliminazione, riprova in seguito. </h3>
		</c:when>
		<c:otherwise>
			<h3 class="center" >L'eliminazione è avvenuta con successo. </h3>		
		</c:otherwise>
	</c:choose>
	
	<h4 class="center" >Redirect in 5 secondi</h4>
	</div>
</div>

<%@include file="footer.jsp"%>