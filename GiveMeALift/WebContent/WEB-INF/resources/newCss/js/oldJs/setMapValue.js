function setMap(where) {
	var idCity="City"+where;
	var idStreet="Street"+where;
	var id="map"+where;
	var p=document.getElementById(idCity).value;
	var s=document.getElementById(idStreet).value;
	if(s=="defaultStreet" && p!="defaultCity"){
		document.getElementById(id).value="Italia - "+p;
	}
	else if(s!="defaultStreet" && p!="defaultCity"){
		document.getElementById(id).value="Italia - "+p+" - "+s;
	}
}
