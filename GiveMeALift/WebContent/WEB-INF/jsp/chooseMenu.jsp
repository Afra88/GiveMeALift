<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
  <div class="container_12">
    <div class="grid_12">
	    <c:choose>
    		<c:when test="${user!=null || admin!=null}">
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
          <c:when test="${admin!=null}">
          	<%@include file="adminMenu.jsp" %>
          </c:when>
          <c:otherwise>
         	 <%@include file="classicMenu.jsp" %>
          </c:otherwise>
          </c:choose>    
  </div>
  </div>
</header>