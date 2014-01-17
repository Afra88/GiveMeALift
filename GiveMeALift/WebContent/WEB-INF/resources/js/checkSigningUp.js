var bool=new Boolean("false");
var msg="* Campo obbligatorio";
function checkEqual(){
	var p=document.getElementById("psw");
	var p1=document.getElementById("pswConfirm");
	if(p.value!=p1.value){
		bool=true;
		p.style.border="double red";
		p1.style.border="double red";
		document.getElementById("notEqual").style.color="red";
		document.getElementById("notEqual").innerHTML="    Le password non coincidono.";
	}
	else{
		bool=false;
		p.style.border="";
		p1.style.border="";
		document.getElementById("notEqual").innerHTML="";
	}
}

function checkName(s){
	var patt=/^[\A-z]*$/;
	if(patt.test(s.value)==false && s.value!=""){
		bool=true;
		s.style.border="double red";
		document.getElementById("errorName").innerHTML="   Nome non valido.";
		document.getElementById("errorName").style.color="red";
	}
	else{
		bool=false;
		document.getElementById("errorName").innerHTML="";
		s.style.border="";//ripristinare quello di partenza
	}
}

function checkSurName(s){
	var patt=/^[\A-z]*$/;
	if(patt.test(s.value)==false && s.value!=""){
		bool=true;
		s.style.border="double red";
		document.getElementById("errorSurName").innerHTML="   Cognome non valido.";
		document.getElementById("errorSurName").style.color="red";
	}
	else{
		bool=false;
		document.getElementById("errorSurName").innerHTML="";
		s.style.border="";//ripristinare quello di partenza
	}
}

function checkEmail(s){
	var patt=/^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	if(patt.test(s.value)==false && s.value!=""){
		bool=true;
		s.style.border="double red";
		document.getElementById("errorEmail").innerHTML="   Email non valida.";
		document.getElementById("errorEmail").style.color="red";
	}
	else{
		bool=false;
		document.getElementById("errorEmail").innerHTML="";
		s.style.border="";//ripristinare quello di partenza
	}
}

function checkPsw(s){
	var pattPsw=/^[\w]*$/;
	if(pattPsw.test(s.value)==false && s.value!=""){
		bool=true;
		s.style.border="double red";
		document.getElementById("errorPsw").innerHTML="   Nella password è consentito l'uso dei seguenti caratteri: a-z, A-Z, 0-9 e comuni segni di 						punteggiatura.";
		document.getElementById("errorPsw").style.color="red";
	}
	else if(parseInt(s.value.length)<8){
		bool=true;
		s.style.border="double red";
		document.getElementById("errorPsw").innerHTML="   La password deve contenere almeno 8 caratteri.";
		document.getElementById("errorPsw").style.color="red";
	}
	else if(parseInt(s.value.length)>20){
		bool=true;
		s.style.border="double red";
		document.getElementById("errorPsw").innerHTML="   La password deve contenere al massimo 20 caratteri.";
		document.getElementById("errorPsw").style.color="red";
	}
	else{
		bool=false;
		document.getElementById("errorPsw").innerHTML="";
		s.style.border="";//ripristinare quello di partenza
	}
}

function setAction() {
	var n=document.getElementById("name").value;
	var s=document.getElementById("surname").value;
	var e=document.getElementById("email").value;
	var p=document.getElementById("psw").value;
	var p1=document.getElementById("pswConfirm").value;	
	if(n=="" || n==null){
		document.getElementById("name").style.border="double red";
		document.getElementById("null").innerHTML=msg;
		document.getElementById("null").style.color="red";
		bool=true;
	}
	if(s=="" || s==null){
		document.getElementById("surname").style.border="double red";
		document.getElementById("null").innerHTML=msg;
		document.getElementById("null").style.color="red";
		bool=true;
	}
	if(e=="" || e==null){
		document.getElementById("email").style.border="double red";
		document.getElementById("null").innerHTML=msg;
		document.getElementById("null").style.color="red";
		bool=true;
	}
	if(p=="" || p==null){
		document.getElementById("psw").style.border="double red";
		document.getElementById("null").innerHTML=msg;
		document.getElementById("null").style.color="red";
		bool=true;
	}
	if(p1=="" || p1==null){
		document.getElementById("pswConfirm").style.border="double red";
		document.getElementById("null").innerHTML=msg;
		document.getElementById("null").style.color="red";
		bool=true;
	}
	if(bool==false){
		document.getElementById("null").innerHTML="";
		document.getElementById("form0").action="SignUp";
		document.getElementById("form0").submit();
	}
}