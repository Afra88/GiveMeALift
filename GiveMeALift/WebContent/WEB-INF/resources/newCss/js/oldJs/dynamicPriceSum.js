    $(document).ready(function() {
        var somma;
        var val;
        $(".number").keyup(function(){
        	
//        	s=$(this).val();
//            if (!/^\d*\.?\d*$/.test(s)) $(this).val(s.substr(0,s.length-1));
        	
            somma = 0;
            
            $(".number").each(function(){
	            val = parseInt($(this).val());
	            if(isNaN(val) ) val =0;
                somma += val;
            });
             $("#sum").html(somma+" Euro");
        });
        
    });