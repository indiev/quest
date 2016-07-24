<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="portfolio-detail"></div>

<script type="text/javascript">

function detail(id) {
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
		link: function (value) { return "/portfolio/" + value; }
    });
	
	ajax.get("/api/portfolios/"+id, {}, function(portfolio){
		$("div.portfolio-detail").loadTemplate("/portfolio/node/detail", portfolio);
	});
			
}
$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	detail(id);
});
</script>