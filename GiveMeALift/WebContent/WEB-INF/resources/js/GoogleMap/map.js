//var directionDisplay;
//var directionsService = new google.maps.DirectionsService();
//var map;
//
//function initialize() {
//  
//    calcRoute();
//	var latlng = new google.maps.LatLng(41.8941,12.513428);
//	directionsDisplay = new google.maps.DirectionsRenderer();
//    var myOptions = { 
//		zoom:5,
//		center: latlng,
//      	mapTypeId: google.maps.MapTypeId.ROADMAP
//    }
//    
//	map = new google.maps.Map(document.getElementById("map"), myOptions);
//    
//	directionsDisplay.setMap(map);
//	
//	document.getElementById("FromAToB").onclick = function() {
//		calcRoute();
//	}
//	
//}
//  
//  function calcRoute() {
//    var partenza = document.getElementById("mapFrom").value;
//    var arrivo = document.getElementById("mapTo").value;
//    if(arrivo!=0 && arrivo!=null && partenza!=0 && partenza!=null){
//    var request = {
//        origin:partenza, 
//        destination:arrivo,
//        travelMode: google.maps.DirectionsTravelMode.DRIVING
//    };
//    directionsService.route(request, function(response, status) {
//      if (status == google.maps.DirectionsStatus.OK) {
//        directionsDisplay.setDirections(response);
//      }
//    });
//    }
//  }
//window.onload = initialize;

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