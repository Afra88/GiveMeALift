<%@include file="header.jsp"%>
<div class="container_12">
	<div class="grid_12">
	<c:choose>
		<c:when test="${error==true}">
			<h3 class="center" >In questo momento non è possibile eliminare l'auto, riprova in seguito. </h3>
		</c:when>
		<c:otherwise>
			<h3 class="center" >L'eliminazione dell'auto è avvenuta con successo. </h3>		
		</c:otherwise>
	</c:choose>
	</div>
</div>

<%@include file="footer.jsp"%>