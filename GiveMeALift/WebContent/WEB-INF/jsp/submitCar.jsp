<%@include file="header.jsp"%>



<div class="container_12">
	<div class="grid_12">
	<c:choose>
		<c:when test="${modified==true}">
			<META http-equiv="refresh" content="3; URL=ShowUserCar">
			<h3 class="center" >I dati sono stati aggiornati con successo! </h3>
			<h4 class="center" >Redirect in 3 secondi</h4>
		</c:when>
		<c:otherwise>
			<META http-equiv="refresh" content="3; URL=ShowUserProfile">
			<h3 class="center" >Al momento non è possibile aggiornare i dati, riprova in seguito. </h3>
			<h4 class="center" >Redirect in 3 secondi</h4>
		</c:otherwise>
		
	</c:choose>
	</div>
</div>

<%@include file="footer.jsp"%>