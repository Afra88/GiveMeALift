<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="stylesheet" href="css/mainstyle.css" type="text/css" />
<title>Give me a lift!</title>
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
<h1>...</h1>
</div>
</body>
</html>