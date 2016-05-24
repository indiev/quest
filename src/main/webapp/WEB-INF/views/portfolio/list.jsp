<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class ="mainWrap center-block">
	<div><h3>포트폴리오 정보</h3><hr></div>
	<div class="portfolio-content"></div>
	
	
</div>

<script type="text/javascript">
$(document).ready(function() {
	$("div.portfolio-content").empty();
	ajax.get("/api/portfolio/user", {}, function(list) {
		$.get("/portfolio/node/list", function(portfolioNode){

			for(i in list) {
				$portfolioNodeClone = $(portfolioNode).clone();
				
				$portfolioNodeClone.attr("id",list[i].id);
				$name = $("<a>").attr("href","/portfolio/" + list[i].id).html(list[i].name);
				$portfolioNodeClone.find(".name").html($name);
				$portfolioNodeClone.find(".type").html(list[i].type.name);
				$portfolioNodeClone.find(".target").html(list[i].target);
				$portfolioNodeClone.find(".startDate").html($.datepicker.formatDate('yy년 mm월 dd일', new Date(list[i].startDate)));
				$portfolioNodeClone.find(".endDate").html($.datepicker.formatDate('yy년 mm월 dd일', new Date(list[i].endDate)));
				
				
				$("div.portfolio-content").append($portfolioNodeClone);				
			}
		});
	});
});

</script>