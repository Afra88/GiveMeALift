  var directionsDisplay;
  var directionsService = new google.maps.DirectionsService();
  var map;
//  var oldDirections = [];
//  var currentDirections = null;

  function initialize() {
	  directionsDisplay = new google.maps.DirectionsRenderer();
//    var myOptions = ({
//      zoom: 5,
//      center: google.maps.LatLng(41.8941,12.513428),
//      mapTypeId: google.maps.MapTypeId.ROADMAP
//    });
    var myOptions = { 
    		zoom:5,
    		center: new google.maps.LatLng(42.650122, 12.513428),
          	mapTypeId: google.maps.MapTypeId.ROADMAP
        }
    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

    directionsDisplay = new google.maps.DirectionsRenderer({
        'map': map,
        'preserveViewport': true,
        'draggable': true
    });
//    directionsDisplay.setPanel(document.getElementById("directions_panel"));

//    google.maps.event.addListener(directionsDisplay, 'directions_changed',
//      function() {
//        if (currentDirections) {
//          oldDirections.push(currentDirections);
//          setUndoDisabled(false);
//        }
//        currentDirections = directionsDisplay.getDirections();
//      });
//
//    setUndoDisabled(true);
    
//    alert(document.getElementById("FromAToB").value);

	document.getElementById("FromAToB").onclick = function() {
		calcRoute();
	};
  }

  function calcRoute() {
	var start= document.getElementById("mapFrom").value;
	var end = document.getElementById("mapTo").value;
    var request = {
        origin:start,
        destination:end,
        travelMode: google.maps.DirectionsTravelMode.DRIVING
    };
    directionsService.route(request, function(response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
          directionsDisplay.setDirections(response);
        }
      });
  }

//  function undo() {
//    currentDirections = null;
//    directionsDisplay.setDirections(oldDirections.pop());
//    if (!oldDirections.length) {
//      setUndoDisabled(true);
//    }
//  }

//  function setUndoDisabled(value) {
//    document.getElementById("undo").disabled = value;
//  }