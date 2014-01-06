function checkTime(time) {
	if(time.match("[0-9]")==null ||parseInt(time)<0 || parseInt(time)>23){
//		document.write("Ora non valida");
		document.getElementById("errorTime").innerHTML="Deve essere un numero compreso tra 0 e 23";
		document.getElementById("errorTime").style.color="red";
	}
	else{
//		document.write("ok")
		document.getElementById("errorTime").innerHTML="";
	}
	//controllo data inserita maggipre di quella attuale 
	//e select per ora???
}