<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class ="mainWrap center-block">
	<div>
		<h3>포트폴리오 정보</h3><hr>
		<select name="PortfolioType">
		</select>
	</div>
	<div class="portfolio-content"></div>
	
</div>

<script type="text/javascript">
$(document).ready(function() {
	getPortfolios("전체");
	
	ajax.get("/api/code/model/portfolio",{},function(list){
		$select = $("select");
		$select.empty();
		$select.append($("<option>").html("전체"))
		for(i in list) {
			$option = $("<option>");
			$option.html(list[i].name);
			$option.val(list[i].id)
			$select.append($option);
		}
	});
	
	$("select").change(function(){
		getPortfolios(this.value);
	});
	
});

function getPortfolios(type){
	
	if(type =="전체") 
	{
		ajax.get("/api/portfolio/user", {}, function(list) {
			$.addTemplateFormatter({
				date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
		        link: function (value) { return "/portfolio/" + value; }
		    });
			$("div.portfolio-content").loadTemplate("/portfolio/node/list", list, {bindingOptions:{"ignoreNull":true}});
		});
	}
	else 
	{
		ajax.get("/api/portfolio/typeId/"+type, {}, function(list) {
			$.addTemplateFormatter({
				date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
		        link: function (value) { return "/quest/" + value; }
		    });
			$("div.portfolio-content").loadTemplate("/portfolio/node/list", list, {bindingOptions:{"ignoreNull":true}});
		});
	
	}
		
}


</script>