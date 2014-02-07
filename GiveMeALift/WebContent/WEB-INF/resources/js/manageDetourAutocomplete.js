
 
  $("#0").click( function() {
	  if(contId>0){		  
			for (var i = 1; i < contId; i++) {
				$("#li"+i).attr("id","li"+(i-1));
				$("#detour"+i).attr("id","detour"+(i-1));
				$("#detour"+i).attr("name","detour"+(i-1));
				$("#"+i).attr("id",(i-1));
			}
		contId--;
		  $("#detour0").val("");
		  $("#li0").attr("id","li"+contId);
		  $("#detour0").attr("id","detour"+contId);
		  $("#detour0").attr("name","detour"+contId);
		  $("#0").attr("id",contId);
		  $("#li"+contId).hide();
	  }
	  else{
		  $("#li0").val(" ");
		  $("#li0").hide();
		  contId--;
	  }
 });
  $("#1").click( function() {
	  if(contId>1){		  
		  for (var i = 2; i < contId; i++) {
				$("#li"+i).attr("id","li"+(i-1));
				$("#detour"+i).attr("id","detour"+(i-1));
				$("#detour"+i).attr("name","detour"+(i-1));
				$("#"+i).attr("id",(i-1));
			}
		  contId--;
		  	$("#detour1").val("");
			$("#li1").attr("id","li"+contId);
			$("#detour1").attr("id","detour"+contId);
			$("#detour1").attr("name","detour"+contId);
			$("#1").attr("id",contId);
			$("#li"+contId).hide();
	  }
	  else{
		  $("#li1").val(" ");
		  $("#li1").hide();
		  contId--;
	  }
	 });
  $("#2").click( function() {
	  if(contId>2){		  
		  for (var i = 3; i < contId; i++) {
				$("#li"+i).attr("id","li"+(i-1));
				$("#detour"+i).attr("id","detour"+(i-1));
				$("#detour"+i).attr("name","detour"+(i-1));
				$("#"+i).attr("id",(i-1));
			}
		  contId--;
		  	$("#detour2").val("");
			$("#li2").attr("id","li"+contId);
			$("#detour2").attr("id","detour"+contId);
			$("#detour2").attr("name","detour"+contId);
			$("#2").attr("id",contId);
			$("#li"+contId).hide();
	  }
	  else{
		  $("#li2").val(" ");
		  $("#li2").hide();
		  contId--;
	  }
	 });
  $("#3").click( function() {
	  if(contId>3){		  
		  for (var i = 4; i < contId; i++) {
				$("#li"+i).attr("id","li"+(i-1));
				$("#detour"+i).attr("id","detour"+(i-1));
				$("#detour"+i).attr("name","detour"+(i-1));
				$("#"+i).attr("id",(i-1));
			}
		  contId--;
		  	$("#detour3").val("");
			$("#li3").attr("id","li"+contId);
			$("#detour3").attr("id","detour"+contId);
			$("#detour3").attr("name","detour"+contId);
			$("#3").attr("id",contId);
			$("#li"+contId).hide();
	  }
	  else{
		  $("#li3").val(" ");
		  $("#li3").hide();
		  contId--;
	  }
	 });
  $("#4").click( function() {
	  if(contId>4){		  
	  	$("#detour4").val("");
		$("#li4").hide();
		contId--;
	  }
	 });
 
});