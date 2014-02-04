<%@page import="it.unical.mat.domain.User"%>
<%@ page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<table class="login">
		<tr>
		<c:choose>
			<c:when test="${user!=null}">		
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
			</c:when>
			<c:when test="${admin!=null}">		
				<c:choose>
					<c:when test="${admin.gender=='M'}">
						<td colspan="2"><label>Benvenuto ${admin.name}</label></td>
					</c:when>
					<c:when test="${admin.gender=='F'}">
						<td colspan="2"><label>Benvenuta ${admin.name}</label></td>
					</c:when>
					<c:otherwise>
						<td colspan="2"><label>Benvenuto ${admin.name} </label></td>
		 			</c:otherwise> 
				</c:choose>
			</c:when>
		</c:choose>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a href="LogOut" class="button cyan">  Esci </a>
			</td>
		</tr>
	</table>