/**
 * 
 */
var keyCtrl = {
	inputKeyPrint:function(){
		$(document).on('keydown', function ( e ) {
			$(document).bind('keydown', 'ctrl+c', f);
			$(document).bind('keydown', 'ctrl+c', function(){
				console.log( "You pressed CTRL + C" );
			});
		    // You may replace `c` with whatever key you want
		    if ( e.ctrlKey && ( String.fromCharCode(e.which) === 'c' || String.fromCharCode(e.which) === 'C' ) ) {
		        console.log( "You pressed CTRL + C" );
		    }
		});
	}
};