<%@include file="header.jsp"%>

<META http-equiv="refresh" content="2; URL=UserSearchForFeedback">

<div class="container_12">
	<div class="grid_12">
		<c:choose>
			<c:when test="${found==false}">
				<h3 class="center" > Il numero di telefono indicato non è valido! 
				<br> Riprova </h3>
				<h4 class="center" > Redirect fra 2 secondi</h4>
			</c:when>
		</c:choose>
	</div>
</div>

<%@include file="footer.jsp"%>


