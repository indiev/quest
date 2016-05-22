<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class ="mainWrap center-block">
	<div><h3>포트폴리오 정보</h3><hr></div>
	<div class="portfolio-content row"></div>
	
	
	
</div>

<script type="text/javascript">
$(document).ready(function() {
	ajax.get("/api/portfolio/list/user", {}, function(list) {
		$.get("/portfolio/node/list", function(portfolioNode){
			for(i in list) {
				var portfolio = list[i];
				$portfolioNodeClone = $(portfolioNode).clone();
				
				$portfolioNodeClone.attr("id",portfolio.id);
				$portfolioNodeClone.find(".name").html(portfolio.name);
				$portfolioNodeClone.find(".target").html(portfolio.target);
				$portfolioNodeClone.find(".startDate").html($.datepicker.formatDate('yy년 mm월 dd일', new Date(portfolio.startDate)));
				$portfolioNodeClone.find(".endDate").html($.datepicker.formatDate('yy년 mm월 dd일', new Date(portfolio.endDate)));
				
				
				$("div.portfolio-content").append($portfolioNodeClone);				
			}
		});
	});
});

</script>