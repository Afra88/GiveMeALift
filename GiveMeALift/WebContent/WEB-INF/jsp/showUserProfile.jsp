<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Il mio profilo</title>
		
		<link rel="stylesheet" href="css/login.css">
	    <link rel="stylesheet" href="css/ourAdditions.css">
	    <link rel="stylesheet" href="css/greenTable.css">
	    <link rel="stylesheet" href="css/pagination.css" type="text/css" />
	    <link rel="stylesheet" href="css/blueTable.css">
	    <link rel="stylesheet" href="css/orangeTable.css">
	    
	    <link rel="icon" href="images/favicon.ico">
		<link rel="shortcut icon" href="images/favicon.ico">
		<link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet" href="css/font-awesome.css">
		<link rel="stylesheet" href="css/form.css">
	</head>
	
<%-- 	<% session.getParameter("user"); %> --%>

	
<body>
<header>
  <div class="container_12">
    <div class="grid_12">
	    <c:choose>
    		<c:when test="${user!=null}">
    			<%@include file="signedUpForm.jsp"%>
    		</c:when>
    		<c:otherwise>
		    	<%@include file="logInForm.jsp"%>
    		</c:otherwise>
    	</c:choose>
          <h1><a href="/GiveMeALift"><img src="images/logo.png" alt=""></a> </h1>   
          <c:choose>
          <c:when test="${user!=null}">
          	<%@include file="userMenu.jsp" %>
          </c:when>
          <c:otherwise>
         	 <%@include file="classicMenu.jsp" %>
          </c:otherwise>
          </c:choose>    
  	</div>
  </div>
</header>
<!--    <div class="content"> -->
    <div class="container_12" id="contentOpacity" >
		<div class="grid_12">
<!-- 			<div class="grid_4_prefix_1"> -->
<!-- 	  			<ul> -->
<!--     				<li>  -->
<!-- 			    		<a href="/userProfile">Informazioni personali </a> -->
<!-- 			    	</li> -->
<!-- 			    	<li> -->
<!-- 			    		<a href="">Foto</a> -->
<!-- 			    	<li> -->
<!-- 			    	<li> -->
<!-- 			    		<a href="">Preferenze</a> -->
<!-- 			    	</li> -->
<!-- 			    	<li> -->
<!-- 			    		<a href="/userCar">Auto</a> -->
<!-- 			    	</li> -->
<!-- 			    </ul> -->
<!-- 			    <ul> -->
<!-- 			    	<li> -->
<!-- 			    		<a href="">Password</a> -->
<!-- 			    	</li> -->
<!-- 			    	<li> -->
<!-- 			    		<a href="">Cancella l'account</a> -->
<!-- 			    	</li> -->
<!-- 			    </ul> -->
<!--     		</div> -->
	 
		<div class="greenTable">
			<table id="personalInfo" class=table>
				<tr>
					<td colspan="2">Informazioni personali</td>
				</tr>
<!-- 				<tr> -->
<!-- 				<td colspan="2"> -->
<!-- 					<h4>La mia foto</h4> -->
<!-- 					Aggiungi una foto ora! Sar&agrave; pi&ugrave; facile riconoscersi! -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
					<td>		Foto profilo	</td>
					<td> 
					<img src="/GiveMeALift/avatars/${user.id}.jpg"   width="150" onerror="this.style.visibility = 'hidden'">
						<form name="myWebForm" action="/GiveMeALift/fileupload" method="post" enctype="multipart/form-data"> 
<!-- 						<form name="myWebForm" method="post" enctype="multipart/form-data">  -->
							<input type="file" name="uploadFile"/><br>
							<input value="Carica" type="submit"/>							
						</form>
					</td>
				</tr>
		<form method="get" action="ModifyUserProfile">
				<tr>
					<td>Sesso</td>
					<td>
					<c:choose>
					 	<c:when test="${user.gender == 'M'}"> Sign. </c:when>
					 	<c:when test="${user.gender == 'F'}"> Sign.ra </c:when>
					 </c:choose>
					</td>
				</tr>
				<tr>
					<td>Nome</td>
					<td> 
						<input id="profileName" name="profileName" type="text" value ="${user.name}" required="required"></input>
					</td>
				</tr>
				<tr>
					<td>Cognome</td>
					<td> 
						<input id="profileSurname" name="profileSurname" type="text" value ="${user.surname}" required="required"></input>
					</td>
				</tr>
				<tr>
					<td>Scegli Nickname</td>
					<td> 
						<select id="nickname" name="nickname">
						<option>${user.name}</option>
						<option>${user.name} ${firstLetter}</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Email</td>
					<td>
						<input id="profileEmail" name="profileEmail" type="text" readonly="readonly" value ="${user.email}" required="required"></input>
					</td>
				</tr>
				<tr>
					<td>Cellulare</td>
					<td> 
						<input id="profileCell" name="profileCell" type="text" placeholder ="3XX XXXX XXX" maxlength="10"></input>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input id="hideCellPhone" name="hideCellPhone" type="checkbox"/>
						<small><i>Non mostrare il mio numero di cellulare su Give Me a Lift</i></small> manca nel DB
					</td>
				</tr>
				<tr>
					<td>Telefono</td>
					<td> 
						<input id="profilePhone" name="profilePhone" type="text" placeholder ="0XX XXXX XXXX" maxlength="11"></input>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input id="hidePhone" name="hidePhone" type="checkbox"/>
						<i>Non mostrare il mio numero di telefono su Give Me a Lift</i>manca nel DB
					</td>
				</tr>
				<tr>
					<td>Anno di nascita</td>
					<td>
						<select id="birthYear" name="birthYear">
						  <c:forEach var="i" step="1" begin="1914" end="1996" >
							<option>${i}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">Il mio Indirizzo</td> 
				</tr>
				<tr>
					<td>Strada</td>
					<td> 
						<input id="address" name="address" type="text" placeholder ="Via, Viale, Piazza, ..."></input>
					</td>
				</tr>
				<tr>
					<td>Citt&agrave;</td> 
					<td>
						<input id="cityAddress" name="cityAddress" type="text"></input>
					</td>
				</tr>
				<tr>
					<td>Stato</td>
					<td> 
						<input id="stateAddress" name="stateAddress" type="text" placeholder ="Italia"></input>
					</td>
				</tr>
				<tr>
					<td rowspan="2"> Perchè viaggiare con me? </td>
					<td><textarea  cols="40" rows="6" maxlength="700" >
						Es: "Studio a Milano, ma sono originario di Bologna e viaggio spesso per
						andare a trovare la mia famiglia."</textarea>
						manca nel DB
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td>
							<input value="Salva" type="submit"/>
					</td>
		</form>      <!-- 		ModifyUserProfile -->
				</tr>
		</table>
		</div>
	</div>		
  </div>
<!--   </div> -->

<footer>
  <div class="container_12">
    <div class="grid_12">
      <div class="copy"> Give me a lift(C) 2045 | <a href="#">Politica Privacy</a> | Design by: <a href="http://www.templatemonster.com/">TemplateMonster.com</a> </div>
    </div>
  </div>
</footer>
</body>
</html>    
    

