$(document).ready( function() {
 
 $("#add_detour").click( function() {
    $("ol").append("<li><input type=\"text\" id=detour name=detour /></li>");
 });
 
  $("#remove_detour").click( function() {
    $("li:last").remove();
 });
 
});