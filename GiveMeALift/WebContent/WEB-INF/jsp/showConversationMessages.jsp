<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>	 
    <head>
    
	<title>I miei messaggi</title>
    
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/ourAdditions.css">
    <link rel="stylesheet" href="css/pagination.css" type="text/css" />
    <link rel="stylesheet" href="css/simpleTable.css">
    <link rel="stylesheet" href="css/pictogram-button.css">
    <link rel="stylesheet" href="css/bubbleLeft.css">
    <link rel="stylesheet" href="css/bubbleRight.css">
    
    <link rel="icon" href="images/favicon.ico">
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/form.css">
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
   	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<!-- <script src="js/cssJs/jquery.js"></script> -->
	<script src="js/cssJs/jquery-migrate-1.1.1.js"></script>
	<script src="js/cssJs/superfish.js"></script>
	<script src="js/cssJs/jquery.equalheights.js"></script>
	<script src="js/cssJs/jquery.easing.1.3.js"></script>
	<script src="js/cssJs/jquery.ui.totop.js"></script>
	<script type="text/javascript" src="js/jQueryElement/jquery.format.1.05.js"></script>
	  
	<script>
	$(document).ready(function () {
	    $().UItoTop({
	        easingType: 'easeOutQuart'
	    });
	});
	    </script>	
</head>
<body onload="initialize()" >
<%@include file="chooseMenu.jsp"%>
<div class="content">
	<div class="container_12">
		<div class="grid_12">
			<a href="ShowConversations" class="button orange"><span class="email"></span>Conversazioni attive</a>
			<a href="ShowConversationsArchiviated"  class="button magenta"><span class="lock"></span>Conversazioni archiviate</a>
		</div>

	<div class="grid_12">
  	<c:choose>
  		<c:when test="${messages.size()==0}">
  				<div class="clear" ></div>
  				<h4>Nessun Messaggio</h4>
  			<div class="clear"></div>
  		</c:when>
  		<c:otherwise>
  		<div class="clear"></div>
  			<c:forEach items="${messages}" var="message" >
  				<c:choose>
  					<c:when test="${message.sender.id==user.id }">
						<div>
		  					<span class="bubbleRight">
			  					<span class="sender">${message.sender.computeNickName()} scrive:</span>
			  					<span class="message">"${message.text}"</span>
			  					<span class="when">${message.dateSending} - ${message.timeSending}</span>
			  				</span> 
			  				<c:choose>
								<c:when test="${message.sender.profilePhoto!=null}">
			          				<img height="70px" alt="${message.sender.computeNickName()}" src="${message.sender.profilePhoto}">
								</c:when>
								<c:when test="${message.sender.profilePhoto==null}">
			          				<img height="70px" src="avatars/default_user.jpg" />
								</c:when>
		          			</c:choose>
						</div>
  					</c:when>
  					<c:otherwise>
						<div>
	  					<c:choose>
							<c:when test="${message.receiver.profilePhoto!=null}">
		          				<img height="70px" alt="${message.receiver.computeNickName()}" src="${message.receiver.profilePhoto}">
							</c:when>
							<c:when test="${message.receiver.profilePhoto==null}">
		          				<img height="70px" src="avatars/default_user.jpg" />
							</c:when>
	          			</c:choose>
		  				<span class="bubbleLeft">
		  					<span class="sender">${message.receiver.computeNickName()} scrive:</span>
		  					<span class="message">"${message.text}"</span>
		  					<span class="when">${message.dateSending} - ${message.timeSending}</span>
		  				</span>
		  				</div>  			
  					</c:otherwise>
  				</c:choose>
		     </c:forEach>
		     <div>
				<form action="SendMessage" method="post" >
						<span class="bubbleRight">
			 				<span class="message">
		 						<textarea rows="4" cols="92" maxlength="255" autofocus="autofocus" name="text" ></textarea>
	 							<input type="hidden" value="${conversation}" name="c" />
	 							<button type="submit" class="button cyan" >Invia</button>
							</span>
 						</span> 
 				<c:choose>
					<c:when test="${message.sender.profilePhoto!=null}">
	         				<img height="70px" alt="${message.sender.computeNickName()}" src="${message.sender.profilePhoto}">
					</c:when>
					<c:when test="${message.sender.profilePhoto==null}">
	         				<img height="70px" src="avatars/default_user.jpg" />
					</c:when>
       			</c:choose>
				</form>
			</div>
			<form action="ArchiveConversation" method="post">
				<input type="hidden" name="c" value="${conversation}" />
 				<button type="submit" class="button magenta"><span class="lock"></span>Archivia</button>
			</form>
		</c:otherwise>
	</c:choose>
	  	</div>
  	</div>
</div>

<%@include file="footer.jsp"%>