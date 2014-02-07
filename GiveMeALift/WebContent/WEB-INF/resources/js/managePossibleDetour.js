//
// var contId=0;
//
//$(document).ready( function() {
// $("#add_detour").click( function() {
//	if (contId<5) {		
//		$("ol").append("<li><input  class=\"autocomplete\"  type=\"text\" id=detour"+contId+" name=detour"+contId+" /></li>");
//		contId++;		
//	} 
// });
// 
//  $("#remove_detour").click( function() {
//	if (contId>0) {
//		$("li:last").remove();
//		contId--;		
//	}
// });
// 
//});

var contId=0;
var nDetour=5;
var added=0;
var deleted=0;

$(document).ready( function() {
	$("#add_detour").click( function() {
		 if ((added-deleted)<5) {		
			$("ol").append("<li><input class=\"autocomplete\"  type=\"text\" id=detour"+contId+" name=detour />" +
					"<input type=\"hidden\"  name=detour value=\",,,\" />" +
					"<button type=\"button\" class=\"button red\" id="+contId+" ><span class=trash ></span></button>" +
					"</li>");
			var options = {
					types: ['(cities)'],
					componentRestrictions: {country: 'it'}
			};
			var from=document.getElementById("detour"+contId);
			autocomplete = new google.maps.places.Autocomplete(from,options);
			added++;
			contId++;
		}
	});

	$( "ol" ).on( "click", ":button", function() {
	    $(this).parent().remove();
	    deleted++;
	});
});