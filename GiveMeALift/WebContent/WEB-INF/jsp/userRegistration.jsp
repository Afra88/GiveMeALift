<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="stylesheet" href="css/mainstyle.css" type="text/css" />
<script type="text/javascript" src="js/checkSigningUp.js"></script>
<title>Registrati</title>
</head>
<body>
<div class="container">
  <div class="header">
</div>
  <div class="contentNoSidebar">
<form method="post" id="form0">
<h3 align="center">Inserisci i tuoi dati</h3><br />
<div id="mandatoryField">* Campo obbligatorio </div><br />
*Nome: <br /> <input id="name" name="name" type="text" onblur="checkName(this);"/><span id="errorName"></span><br /><br />
*Cognome: <br /> <input id="surname" name="surname" type="text" onblur="checkSurName(this);"/><span id="errorSurName"></span><br /><br />
*Indirizzo email: <br /><input id="email" name="email" type="text" size="40" onblur="checkEmail(this);"/><span id="errorEmail"></span><br /><br />
*Password:  <br /> <input id="psw" type="password" name="psw" onblur="checkPsw(this);" /><span id="errorPsw"></span><br /><br />
*Conferma password:  <br /><input id="pswConfirm" type="password" onblur="checkEqual();" /><span id="notEqual"></span><br /><br />
<input id="send" type="button" value="invia" onclick="setAction();" /><br /><br />
</form>
 </div>
  <div class="footer">
    <p></p>
    </div>
  </div> 
</body>
</html>
