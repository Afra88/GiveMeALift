<%@include file="header.jsp"%>
<div class="container_12">
	<div class="grid_2" >
		<br><br><a href="ShowStatisticsLiftSeat" class="button cyan" >Posti prenotati</a>
		<br><br><a href="ShowStatisticsLift?year=2014" class="button cyan" >Passaggi offerti</a>
	</div>
	<div class="grid_8">
	<form action="ShowStatisticsLift" method="get">
		<br><br><p style="vertical-align: middle; text-align: center;"> Anno
		<select name=year >
			<c:forEach var="i" begin="2010"  end="2014" step="1" >
				<option value="${i}">${i}</option>
			</c:forEach> 
		</select>
		Mese
		<select name=month >
			<option value="" selected="selected" ></option>
			<option value="0" >Gennaio</option>
			<option value="1" >Febbraio</option>
			<option value="2" >Marzo</option>
			<option value="3" >Aprile</option>
			<option value="4" >Maggio</option>
			<option value="5" >Giugno</option>
			<option value="6" >Luglio</option>
			<option value="7" >Agosto</option>
			<option value="8" >Settembre</option>
			<option value="9" >Ottobre</option>
			<option value="10" >Novembre</option>
			<option value="11" >Dicembre</option>
		</select>
		<button type="submit" class="button cyan" >Visualizza  
			</button>
		</p><br>
	</form>
	<img class="chart" src="${url}" alt="Chart" />
	</div>
</div>

<%@include file="footer.jsp"%>