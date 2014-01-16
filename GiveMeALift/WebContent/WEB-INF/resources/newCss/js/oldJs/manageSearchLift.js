    $(document).ready(function(){
    	$($(".page")).click(function(){
    		$("#next_page").val($(this).attr("id").split("_")[1]);
    		$("#next_from").val($("#mapFrom").val());
    		$("#next_to").val($("#mapTo").val());
    		$("#searchForm").submit();
    	});
    	$($("#reverse")).click(function(){
    		var text=$("#mapFrom").val();
    		$("#mapFrom").val($("#mapTo").val());
    		$("#mapTo").val(text);
    	});
    	$($("#searchForm")).submit(function(){
    		$("#next_from").val($("#mapFrom").val());
    		$("#next_to").val($("#mapTo").val());
    	});
    	$($(".item")).click(function(){
    		var a=$(this).children(":hidden").attr("value");
    		var b=new String("detailsForm_"+a);
    		document.getElementById(String(b)).submit();
//    		alert(x.elements[0].value);
    	});
    });