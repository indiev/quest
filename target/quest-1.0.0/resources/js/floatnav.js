/**
 * 
 */

$(function(){
	$("#navToggle").mouseover(function() { 
		if($("#navIcon").attr('class') != "close") $("#navIcon").attr('class', 'plus');
	});
	$("#navToggle").mouseout(function() {
		if($("#navIcon").attr('class') != "close") $("#navIcon").attr('class', 'default');
	});
	$("#navToggle").click(function() {
		if($("#navIcon").attr('class') == "close") {
			$("#navIcon").attr('class', 'plus');
			$("#navPanel").attr('class', 'hover');
			$("#navContent").attr('class', 'remove');
		} else {
			$("#navIcon").attr('class', 'close');
			$("#navPanel").attr('class', 'open');
			$("#navContent").attr('class', 'open');
		}
	});
});