
 var contId=0;

$(document).ready( function() {
 $("#add_detour").click( function() {
	if (contId<5) {		
		$("ol").append("<li><input  class=\"autocomplete\"  type=\"text\" id=detour"+contId+" name=detour"+contId+" /></li>");
		contId++;		
	} 
 });
 
  $("#remove_detour").click( function() {
	if (contId>0) {
		$("li:last").remove();
		contId--;		
	}
 });
 
});