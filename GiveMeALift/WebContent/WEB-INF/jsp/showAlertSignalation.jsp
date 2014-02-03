<%@include file="header.jsp"%>

<META http-equiv="refresh" content="5; URL=SubmitFeedback">

<div class="container_12">
	<div class="grid_12">

	<c:choose>
			<c:when test="${alerted==true}">
				<h3 class="center" > Segnalazione inviata! </h3>
			</c:when>
			<c:otherwise>
				<h3 class="center" > Non è possibile inviare la segnalazione in questo momento. </h3>		
			</c:otherwise>
	</c:choose>
	
	<h4 class="center" >Redirect in 5 secondi</h4>
	</div>
</div>

<%@include file="footer.jsp"%>