<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=iso-8859-1" %>
<html>
    <head>
    <title>Risultati della ricerca</title>
   
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/ourAdditions.css">
    <link rel="stylesheet" href="css/greenTable.css">
    <link rel="stylesheet" href="css/pagination.css" type="text/css" />
    
    <link rel="icon" href="images/favicon.ico">
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/form.css">
	<link rel="stylesheet" href="js/GooglePlaceAutocomplete/placeAutocomplite.css">
	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" type="text/css"  href="js/calendar/tcal.css">
	
	<!-- <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
    <link rel="stylesheet" href="js/GooglePlaceAutocomplete/placeAutocomplete.css"> -->
    
    <!--  	Google Place Autocomplete -->
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
 	<script src="js/GooglePlaceAutocomplete/placeAutocomplete.js" type="text/javascript"></script>
	<!--     end -->
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<!--   	<script src="js/cssJs/jquery.js"></script> -->
	<script src="js/cssJs/jquery-migrate-1.1.1.js"></script>
	<script src="js/cssJs/superfish.js"></script>
	<script src="js/cssJs/jquery.equalheights.js"></script>
	<script src="js/cssJs/jquery.easing.1.3.js"></script>
	<script src="js/cssJs/jquery.ui.totop.js"></script>
    
    <script type="text/javascript" src="js/hoverMapValue.js"></script>
    <script type="text/javascript" src="js/checkingTime.js"></script>
	<script src="js/calendar/tcal.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/manageSearchLift.js" ></script>
	  	
 	<script src="js/jQueryElement/rangeSlider.js" type="text/javascript"></script>
 	<script src="js/jQueryElement/radio.js" type="text/javascript"></script>
	
	<!-- <link rel="stylesheet" type="text/css" href="js/GoogleMap/map.css">
	<link rel="stylesheet" href="css/mainstyle.css" type="text/css" /> -->

	<!-- <link rel="stylesheet" href="css/listResult.css" type="text/css" />
	<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script> -->   
   
	<script>
	$(document).ready(function () {
	    $().UItoTop({
	        easingType: 'easeOutQuart'
	    });
	});
	</script>  
    </head>
    <body onload="initialize()">
	<%@include file="chooseMenu.jsp"%>
	<div class="content">
  	<div class="container_12">
     <div class="grid_12">
        <p class="locationField" id="mapValues" style="text-align: center" >
			<input class="autocomplete" type="text" id=mapFrom name=mapFrom value="${from}" /> 
			<input id=reverse value="" title="Inverti partenza e arrivo" type="button" />
			<input class="autocomplete" type="text" id=mapTo name=mapTo value="${to}"  />
		</p> 
	</div>
    <c:choose>
	<c:when test="${noResult==1}" >
	<div class="grid_12">
		<div  class="center"><h4>Non ci sono risultati per questa ricerca</h4></div>
	</div>
	 <p class="grid_7" ></p>
	</c:when>
	<c:otherwise>    
    <div class="grid_7">
        <c:forEach items="${pageHolder.pageList}" var="lift" >
        	<div class="item">
        	<input type="hidden" id="liftID" value="${lift.getId()}" />
        	 <div class="rec">
	        	<c:choose>
	        		<c:when test="${user.profilePhoto!=null}">
	        			<img height="120px" src="${user.profilePhoto}" class="img_inner fleft " /> <% //TODO %>
	         		</c:when>
	        		<c:otherwise>
	        			<img height="120px" src="images/default_user.jpg" class="img_inner fleft " />
	        		</c:otherwise>
	        	</c:choose> 
        	<div class="extra_wrapper">
	        	<div class="emphatizeWhen">${lift.departureDate} - ${lift.departureTime}</div>
		        <div><div class="emphatizeLift">${lift.getPickUpPoint().city} - ${lift.getDropOffPoint().city}  </div>
	       		<%-- <c:choose>
	       			<c:when test="${lift.detours.size()>0}">
	       				  ${lift.detours.size()} tappe intermedie
	       			</c:when>
	       			<c:otherwise>
	       				Nessuna tappa intermedia
	       			</c:otherwise>
	       		</c:choose>
	       		</div> --%>
	        	<div class="emphatizePrice">${lift.cost} &#8364; a persona</div>
	        	<div class="emphatizeSeat"> ${lift.nSeat} posti disponibili</div>
	        	</div>
        	</div>
        	<form method="get" action="HandleShowLiftDetail" id="detailsForm_${lift.getId()}">
	        	<input name="lift" type="hidden" value="${lift.getId()}" >
        	</form>
        	</div>
        	</div>
		</c:forEach>
		 	<div id="tnt_pagination">
	<!-- <span class="disabled_tnt_pagination">Prev</span> -->
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
    </div>
	</c:otherwise>
	</c:choose>
	
      
      
      <div class="grid_4 prefix_1">
	<form method="get" action="ResultSearch" id=advancedSearchForm >
      <div class="greenTable" >
                <table >
                    <tr>
                        <td>
                            Data
                        </td>
                    </tr>
                    <tr>
                        <td >
                           <div id=date class=center><script type="text/javascript" src="js/currentDate.js"></script></div>
                        </td>
                    </tr>
                </table>
            </div>
       <div class="greenTable" >
                <table >
                    <tr>
                        <td>
                            Ora
                        </td>
                    </tr>
                    <tr>
                        <td >
                           <div class=contentSlidebar>
							<p><label for="range">Range orario:</label>
						  	<input type="text" id="range" name=range></p>
							<p class=center><p id="slider-range"></p>
						</div>
                        </td>
                    </tr>
                </table>
            </div>  
              <div class="greenTable" >
                <table >
                    <tr>
                        <td>
                            Prezzo
                        </td>
                    </tr>
                    <tr>
                        <td >
                          <div>
							<p id="radio" class="center">
						    <input type="radio" id="radio1" name="radio" value="1" ><label for="radio1">Basso</label>
						    <input type="radio" id="radio2" name="radio" value="2" ><label for="radio2">Medio</label>
						    <input type="radio" id="radio3" name="radio" value="3" ><label for="radio3">Alto</label>
						  	</p>
						</div>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="greenTable" >
                <table >
                    <tr>
                        <td>
                           	Perfeziona Ricerca
                        </td>
                    </tr>
                    <tr>
                        <td>
                           <input type="hidden" id=next_page name=page value="1" />
							<input type="hidden" id=next_from name=mapFrom value="${from}" />
							<input type="hidden" id=next_to name=mapTo value="${to}" />
							<p class="center"><input type="submit"  value="Cerca" class="button" /></p>
                        </td>
                    </tr>
                </table>
            </div>
    </form> 
    </div>
</div>
</div>
<%@include file="footer.jsp"%>


