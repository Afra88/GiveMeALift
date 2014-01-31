<%@include file="header.jsp"%>

<META http-equiv="refresh" content="3; URL=ShowUserProfile">

<div class="container_12">
	<div class="grid_12">

	<c:choose>
			<c:when test="${modified==true}">
				<h3 class="center" > I dati sono stati modificati con successo! </h3>
			</c:when>
			<c:otherwise>
				<h3 class="center" > Non è possibile modificare i dati in questo momento. </h3>		
			</c:otherwise>
	</c:choose>
	
	<h4 class="center" >Redirect in 3 secondi</h4>
	</div>
</div>

<%@include file="footer.jsp"%>