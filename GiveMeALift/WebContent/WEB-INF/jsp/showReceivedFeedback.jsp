<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feedback ricevuti</title>

<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/ourAdditions.css">
<link rel="stylesheet" href="css/greenTable.css">
<link rel="stylesheet" href="css/pagination.css" type="text/css" />
<link rel="stylesheet" href="css/blueTable.css">
<link rel="stylesheet" href="css/orangeTable.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" href="css/form.css">
<link rel="stylesheet" href="css/pictogram-button.css">

<link rel="icon" href="images/favicon.ico">
<link rel="shortcut icon" href="images/favicon.ico">

</head>
<body>
	<%@include file="chooseMenu.jsp"%>
	<div class="content">
		<div class="container_12">
			<div class="grid_4">
				<div class="greenTable" style="margin-left: 0; margin-right: 0">
					<table id="avgRating">
						<tr>
							<td colspan="2">Valutazione media</td>
						</tr>
						<tr>
							<td>${avg}</td>
							<c:if test="${avg==0}">
								<td><img height="50px" src="images/feedback/0t.png" /></td>
							</c:if>

							<c:if
								test="${avg>0 && $avg<=0.5 }">
								<td><img height="50px" src="images/feedback/0_5t.png" />
								</td>
							</c:if>

							<c:if
								test="${avg>0.5 && $avg<=1}">
								<td><img height="50px" src="images/feedback/1t.png" /></td>
							</c:if>

							<c:if
								test="${avg>1 && $avg<=1.5}">
								<td><img height="50px" src="images/feedback/1_5t.png" />
								</td>
							</c:if>

							<c:if
								test="${avg>1.5 && $avg<=2}">
								<td><img height="50px" src="images/feedback/2t.png" /></td>
							</c:if>

							<c:if
								test="${avg>2 && $avg<=2.5}">
								<td><img height="50px" src="images/feedback/2_5t.png" />
								</td>
							</c:if>

							<c:if
								test="${avg>2.5 && $avg<=3}">
								<td><img height="50px" src="images/feedback/3t.png" /></td>
							</c:if>

							<c:if
								test="${avg>3 && $avg<=3.5}">
								<td><img height="50px" src="images/feedback/3_5t.png" />
								</td>
							</c:if>

							<c:if
								test="${avg>3.5 && $avg<=4}">
								<td><img height="50px" src="images/feedback/4t.png" /></td>
							</c:if>

<%-- 							<c:if --%>
<%-- 								test="${avg>4 && $avg<=4.5}"> --%>
<!-- 								<td><img height="50px" src="images/feedback/4_5t.png" /> -->
<!-- 								</td> -->
<%-- 							</c:if> --%>

<%-- 							<c:if --%>
<%-- 								test="${avg>4.5 && $avg<=5}"> --%>
<!-- 								<td><img height="50px" src="images/feedback/5t.png" /></td> -->
<%-- 							</c:if> --%>
						</tr>
					</table>
				</div>
			</div>

			<div class="grid_6">
				<c:choose>
					<c:when test="${noFeed==true}">
<!-- 						<META http-equiv="refresh" content="5; URL=UserInsertFeedback"> -->
						<h3 class="center">Non hai ancora alcun feedback!</h3>
<!-- 						<h4 class="center">Redirect in 5 secondi</h4> -->
					</c:when>
					<c:otherwise>
					<div class="grid_6">
					<div class="greenTable">
					<table id="myFeedback">
						<tr>
							<td colspan="3">I miei feedback</td>
						</tr>
						<tr>
							<td style="text-align: center;"> <h4>Utente</h4> </td>
							<td style="text-align: center;"> <h4>Valutazione ricevuta</h4> </td>
							<td style="text-align: center;"> <h4>Commento</h4> </td>
						</tr>
						
						<c:forEach var="i"  begin="0" end="${list.size()-1}" step="1" >
						<tr>
							<td> 
<%-- 							 ${senders.get(i).computeNickname()} - <c:if test="${senders.get(i).getGender()=='M'}"> Uomo</c:if> <c:if test="${senders.get(i)=='F'}"> Donna</c:if> --%>
							<br>
<%-- 							<c:choose> --%>
<%-- 								<c:when test="${senders.get(i).profilePhoto!=null}"> --%>
<%-- 									<img src=" avatars/${senders.get(i).getId()}.jpg" /> <h5>(${senders.get(i).computeAge()} anni) di ${senders.get(i).getAddress().getCity()}</h5>  --%>
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
<%-- 									<img src=" avatars/${senders.get(i).getId()}.jpg" /> <h5>(${senders.get(i).computeAge()} anni) di ${senders.get(i).getAddress().getCity()}</h5>  --%>
<%-- 								</c:otherwise> --%>
<%-- 								<img height="120px" src="images/default_user.jpg" /> <h5>(${senders.get(i).computeAge()} anni) di ${senders.get(i).getAddress().getCity()}</h5>   --%>
<%-- 							</c:choose> --%>
							
							
							<td style="text-align: center;">
								Rating:
								<c:if test="${list.get(i).getRating()!=null}">
									 ${list.get(i).getRating()}	
								<c:if test="${list.get(i).getRating()==0}">
	<!-- 							<td> --> - Pessimo :(
									<br><img height="50px" src="images/feedback/0t.png" />
	<!-- 							</td> -->
								</c:if>
								
								<c:if test="${list.get(i).getRating()==1}">
	<!-- 							<td> --> - Passabile :|
									<br><img height="50px" src="images/feedback/1t.png" />
	<!-- 							</td> -->
								</c:if>
								
								<c:if test="${list.get(i).getRating()==2}">
	<!-- 							<td> --> - Buono :)
									<br><img height="50px" src="images/feedback/2t.png" />
	<!-- 							</td> -->
								</c:if>
								
								<c:if test="${list.get(i).getRating()==3}">
	<!-- 							<td> --> - Ottimo ;)
									<br><img height="50px" src="images/feedback/3t.png" />
	<!-- 							</td> -->
								</c:if>
								
								<c:if test="${list.get(i).getRating()==4}">
	<!-- 							<td> --> - Grande! ;D
									<br><img height="50px" src="images/feedback/4t.png" />
	<!-- 							</td> -->
								</c:if>	
							</c:if>
							</td>
							<td>
								<c:choose>
									<c:when test="${list.get(i).getText()!=null}">
											${list.get(i).getText()}
									</c:when>
									<c:otherwise> Nessun commento lasciato </c:otherwise>
								</c:choose>
							</td>
						</tr>
						</c:forEach>
					</table>
					</div>
				</div>
			</c:otherwise>
			</c:choose>
			</div>
			</div>
		
	</div>
					


	<footer>
		<div class="container_12">
			<div class="grid_12">
				<div class="copy">
					Give me a lift(C) 2045 | <a href="#">Politica Privacy</a> | Design
					by: <a href="http://www.templatemonster.com/">TemplateMonster.com</a>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>
