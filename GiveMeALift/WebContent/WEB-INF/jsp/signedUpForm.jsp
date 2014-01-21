<%@page import="it.unical.mat.domain.User"%>
<%@ page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <% User user=(User)session.getAttribute("user"); %> --%>
	<table class="login">
		<tr>
		<c:choose>
			<c:when test="${user.gender=='M'}">
				<td colspan="2"><label>Benvenuto ${user.name}</label></td>
			</c:when>
			<c:when test="${user.gender=='F'}">
				<td colspan="2"><label>Benvenuta ${user.name}</label></td>
			</c:when>
			<c:otherwise>
				<td colspan="2"><label>Benvenuto ${user.name} </label></td>
 			</c:otherwise> 
		</c:choose>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a href="LogOut" class="button black">  Esci </a>
			</td>
		</tr>
	</table>