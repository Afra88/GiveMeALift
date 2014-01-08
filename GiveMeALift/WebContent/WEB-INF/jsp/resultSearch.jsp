<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" %>
<html>
    <head>
		<link rel="stylesheet" href="css/mainstyle.css" type="text/css" />
        <title>Result of the search</title>
    </head>
    <body>
    <div class="container">
	<div class="header">
	    <form method="post" action="LoginServlet">
		<table class="login">
			<tr>
				<td>Email:</td>
				<td><input type="text" size="40" name="email" id="email" /></td>
			</tr>
			<tr>
				<td>Password:</td>
			    <td><input type="password" size="40" name="psw" id="psw" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="Accedi" />
					oppure
					<a href="userRegistration">Registrati</a>
				</td>
			</tr>
		</table>
	</form>
	</div>
	<table id="resultTable" class="table">
        <c:forEach items="${result}" var="lift" >
        <tr>
			<td>${lift.cost}</td>
			<td>${lift.nSeat}</td>
			<td>${lift.possibleDetour}</td>
		</tr>
		</c:forEach>
	</table>
    </body>
</div>
</html>
