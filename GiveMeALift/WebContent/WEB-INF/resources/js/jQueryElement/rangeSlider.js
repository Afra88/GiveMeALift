 $(function() {
    $( "#slider-range" ).slider({
      range: true,
      min: 0,
      max: 23,
      values: [ 8, 18 ],
      slide: function( event, ui ) {
        $( "#range" ).val( "Ore " + ui.values[ 0 ] + " - " + ui.values[ 1 ] );
      }
    });
    $( "#range" ).val( "Ore " + $( "#slider-range" ).slider( "values", 0 ) +
      " - " + $( "#slider-range" ).slider( "values", 1 ) );
  });