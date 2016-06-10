<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div>
	<h3>포트폴리오 정보</h3><hr>
	<div class="jumbotron">
		<div class="jumbotron-contents text-center">
		  	<h2><span class="portfolio-search-length">0</span>개의 포트폴리오가 있습니다</h2>
	 		<form class="form-inline" name="portfolioSearchForm" role="form" action="/api/portfolio/user/search/" method="GET" onsubmit="return list();">
		 		<div class="input-group form-search">
		 			<label for="searchKeyword" class="sr-only">검색</label>
		 			<input type="text" name="searchKeyword" id="searchKeyword" class="form-control input-sm search-query" placeholder="검색" />
		 			<span class="input-group-btn">
		 				<button class="btn btn-default btn-sm" type="button" onclick="list()"><span class="glyphicon-search glyphicon" aria-hidden="true"></span></button>
		 				<button type="button" class="btn btn-default btn-sm" name="addButton" data-toggle="modal" data-target="#addDialog"><span class="glyphicon-plus glyphicon" aria-hidden="true"></span></button>
		 			</span>
		 		</div>
	 		</form>
	 	</div>
	</div>
	
	
	<select name="PortfolioType">
	</select>
</div>
<div class="portfolio-content"></div>

<div class="modal fade" id="addDialog" role="dialog" aria-labelledby="addHeader" aria-hidden="true" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title">새 포트폴리오 등록</h4>
			</div>
			<div class="modal-body"></div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>
	

<script type="text/javascript">
$(document).ready(function() {
	list();
	getPortfolios("전체");
	
	ajax.get("/api/code/portfolio/type",{},function(list){
		$select = $("select");
		$select.empty();
		$select.append($("<option>").html("전체"));
		for(i in list) {
			$option = $("<option>");
			$option.html(list[i].value);
			$option.val(list[i].id)
			$select.append($option);
		}
	});
	
	$("select").change(function(){
		getPortfolios(this.value);
	});
	
	$("button[name='addButton']").click(function(){
		$("#addDialog").find("div.modal-body").load("/portfolio/add");
	});
	
});

function del(value) {
	ajax.del("/api/portfolio/"+value, {}, function(result){
		if(result) alert("해당 포트폴리오를 삭제했습니다.");
		else alert("해당 포트폴리오를 삭제할 수 없습니다.");
	});
}

function list() {
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
        link: function (value) { return "/quest/" + value; },
    });
	
	$form = $("form[name='portfolioSearchForm']");
	searchKeyword = $form.find("[name='searchKeyword']").val();
	ajax.get($form.attr("action") + searchKeyword, {}, function(list){
		console.log(list);
		$("span.portfolio-search-length").html(list.length);
		$("div.portfolio-content").loadTemplate("/portfolio/node/list", list);
	});
	return false;
}

function getPortfolios(type){
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
        link: function (value) { return "/portfolio/" + value; }
    });
	var link = "/api/portfolio/user/typeId/"+type;
	if(type =="전체") link = "/api/portfolio/user";
	ajax.get(link, {}, function(list) {
		$("div.portfolio-content").loadTemplate("/portfolio/node/list", list);
	});
		
}

</script>