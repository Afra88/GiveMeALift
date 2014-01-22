    $(document).ready(function(){
    	$($(".page")).click(function(){
    		$("#next_page").val($(this).attr("id").split("_")[1]);
    		$("#form").submit();
    	});
    });