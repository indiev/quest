<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="portfolio-detail"></div>

<script type="text/javascript">

function detail(id) {
	ajax.get("/api/portfolio/"+id, {}, function(portfolio){
		$.get("/portfolio/node/detail", function(detailNode){
			$detailNodeClone = $(detailNode).clone();
			/*
			if(jQuery.type(quest.classification) != "undefined" && quest.classification.length > 0) {
				for(i in quest.classification) {
					$detailNodeClone.find(".area").append(quest.classification[i].kind.area.name);
					$detailNodeClone.find(".kind").append(quest.classification[i].kind.name);
				}
			}
			*/
			$detailNodeClone.find(".type").html(portfolio.type.name);
			$detailNodeClone.find(".name").html(portfolio.name);
			$("div.portfolio-detail").append($detailNodeClone);
		});
	});
}
$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	detail(id);
});
</script>