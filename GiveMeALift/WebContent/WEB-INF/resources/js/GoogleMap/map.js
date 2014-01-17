var directionDisplay;
var directionsService = new google.maps.DirectionsService();
var map;

function initialize() {
  
    calcRoute();
//    var chicago = new google.maps.LatLng(42.650122, 12.513428);
//    var mapOptions = {
//      zoom: 6,
//      center: chicago
//    }
    directionsDisplay = new google.maps.DirectionsRenderer();
    
	var myOptions = { 
		zoom:5,
		center: new google.maps.LatLng(-33.8688, 151.2195),
      	mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    
	map = new google.maps.Map(document.getElementById("map"), myOptions);
    
	directionsDisplay.setMap(map);
	
	document.getElementById("FromAToB").onclick = function() {
		calcRoute();
	}
	
}
  
  function calcRoute() {
    var partenza = document.getElementById("mapFrom").valueOf();
    var arrivo = document.getElementById("mapTo").valueOf();
    if(arrivo!=0 && arrivo!=null && partenza!=0 && partenza!=null){
    var request = {
        origin:partenza, 
        destination:arrivo,
        travelMode: google.maps.DirectionsTravelMode.DRIVING
    };
    directionsService.route(request, function(response, status) {
      if (status == google.maps.DirectionsStatus.OK) {
        directionsDisplay.setDirections(response);
      }
    });
    }
  }
window.onload = initialize;

var map;
function initialize() {
  var mapOptions = {
    zoom: 8,
    center: new google.maps.LatLng(-34.397, 150.644)
  };
  map = new google.maps.Map(document.getElementById('map'),
      mapOptions);
}

google.maps.event.addDomListener(window, 'load', initialize);