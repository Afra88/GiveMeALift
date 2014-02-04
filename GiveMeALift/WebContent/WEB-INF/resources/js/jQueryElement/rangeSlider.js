 $(function() {
	 if($("#timeFrom").text()!='' && $("#timeTo").text()!=''){
		 $( "#slider-range" ).slider({
		      range: true,
		      min: 0,
		      max: 23,
		      values: [ $("#timeFrom").text() , $("#timeTo").text() ],
		      slide: function( event, ui ) {
		        $( "#range" ).val( "Ore " + ui.values[ 0 ] + " - " + ui.values[ 1 ] );
		      }
		    }); 
	 }
	 else{
		 $( "#slider-range" ).slider({
		      range: true,
		      min: 0,
		      max: 23,
		      values: [ 0, 23 ],
		      slide: function( event, ui ) {
		        $( "#range" ).val( "Ore " + ui.values[ 0 ] + " - " + ui.values[ 1 ] );
		      }
		    }); 		 
	 }
    $( "#range" ).val( "Ore " + $( "#slider-range" ).slider( "values", 0 ) +
      " - " + $( "#slider-range" ).slider( "values", 1 ) );
  });