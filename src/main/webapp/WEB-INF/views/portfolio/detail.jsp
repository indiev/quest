<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="portfolio-detail"></div>

<script type="text/javascript">

function detail(id) {
	ajax.get("/api/portfolio/"+id, {}, function(portfolio){
		$.get("/portfolio/node/detail", function(detailNode){
			$detailNodeClone = $(detailNode).clone();
		
			$detailNodeClone.find(".type").html(portfolio.type.name);
			$detailNodeClone.find(".name").html(portfolio.name);
			$detailNodeClone.find(".target").html(portfolio.target);
			$detailNodeClone.find(".startDate").html($.datepicker.formatDate('yy년 mm월 dd일', new Date(portfolio.startDate)));
			$detailNodeClone.find(".endDate").html($.datepicker.formatDate('yy년 mm월 dd일', new Date(portfolio.endDate)));
			$detailNodeClone.find(".description").html(portfolio.description);
			$("div.portfolio-detail").append($detailNodeClone);
		});
	});
}
$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	detail(id);
});
</script>