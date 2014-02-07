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
	$(document).ready(function(){
    	$("tr").click(function(){
    		var a=$(this).attr("id");
    		alert(a);
    		var b=new String("messages_"+a);
    		document.getElementById(String(b)).submit();
    	});
    });
	/* $(document).ready(function () {
	    $().UItoTop({
	        easingType: 'easeOutQuart'
	    });
	}); */
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
  		<c:when test="${conversations.size()==0}">
  				<div class="clear" ></div>
  				<h4>Nessun Messaggio</h4>
  			<div class="clear"></div>
  		</c:when>
  		<c:otherwise>
  		<div class="clear"></div>
         	<table class="simpleTable" >
  			<c:forEach items="${conversations}" var="conversation" >
         			<form action="ShowConversationMessages" method="post" id="messages_${conversation.id}" >
						<input type="hidden" name="c" value="${conversation.id}" />
       				</form>
	          		<tr id="${conversation.id}">	
	          			<td>
	          				<c:choose>
								<c:when test="${conversation.computeOtherUser(user.id).profilePhoto!=null}">
			          				<img height="70px" width="70px" alt="${conversation.computeOtherUser(user.id).computeNickName()}" src="${conversation.computeOtherUser(user.id).profilePhoto}">
								</c:when>
								<c:when test="${conversation.computeOtherUser(user.id).profilePhoto==null}">
			          				<img height="70px" width="70px" src="avatars/default_user.jpg" />
								</c:when>
	          				</c:choose>
          					<span class="text">${conversation.computeOtherUser(user.id).computeNickName()}</span>
	          			</td>
	          			<td>
	          				<h4>${conversation.caption}</h4>
	          			</td>
	          			<td>
	          				<h4>${conversation.computeLastMessageDate()} - ${conversation.computeLastMessageTime()}</h4>
	          			</td>
	          			<td class="archive">
	          				<form action="ArchiveConversation" method="post">
								<input type="hidden" name="c" value="${conversation.id}" />
		          				<button type="submit" class="button magenta"><span class="lock"></span>Archivia</button>
	          				</form>
	          			</td>
	          		</tr>
		     </c:forEach>
          	</table>
  			<div id="tnt_pagination">
				<c:choose>
					<c:when test="${page == 1}">
						<button class="disabled_tnt_link" id="page_1" disabled="disabled" > Indietro </button>
					</c:when>
					<c:otherwise>
						<button class=page id="page_1" > Indietro </button>
					</c:otherwise>
				</c:choose>
				<c:forEach begin="1" step="1" var="i" end="${pages}">
					<c:choose>
						<c:when test="${i == page}">
							<button id="page_${i}" class="active_tnt_link ">${i} </button>
						</c:when>
						<c:otherwise>
							<button class=page id="page_${i}" >${i}</button>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${page == pages}">
						<button id="page_${page}" disabled="disabled" class="disabled_tnt_link"> Avanti </button>
					</c:when>
					<c:otherwise>
						<button class=page id="page_${page}" > Avanti </button>
					</c:otherwise>
				</c:choose>
				</div>
			  		</c:otherwise>
			  	</c:choose>
	  	</div>
  	</div>
</div>

<%@include file="footer.jsp"%>