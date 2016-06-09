<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="mainWrap center-block">
	<div class="jumbotron">
		<div class="jumbotron-contents text-center">
		  	<h2><span class="project-search-length">0</span>개의 프로젝트가 있습니다</h2>
	 		<form class="form-inline" name="questSearchForm" role="form" action="/api/quest/all/search/" method="GET" onsubmit="return list();">
		 		<div class="input-group form-search">
		 			<label for="searchKeyword" class="sr-only">검색</label>
		 			<input type="text" name="searchKeyword" id="searchKeyword" class="form-control input-sm search-query" placeholder="검색" />
		 			<span class="input-group-btn">
		 				<button class="btn btn-default btn-sm" type="button" onclick="list()"><span class="glyphicon-search glyphicon" aria-hidden="true"></span></button>
		 				<button type="button" class="btn btn-default btn-sm" name="requestButton" data-toggle="modal" data-target="#requestDialog"><span class="glyphicon-plus glyphicon" aria-hidden="true"></span></button>
		 			</span>
		 		</div>
	 		</form>
	 	</div>
	</div>
	<div class="quest-content row"></div>
</div>
 
<div class="modal fade" id="requestDialog" role="dialog" aria-labelledby="requestHeader" aria-hidden="true" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title">퀘스트 등록</h4>
			</div>
			<div class="modal-body"></div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
function apply(value) {
	ajax.put("/api/quest/apply", {"id":value}, function(result){
		if(result) alert("지원하였습니다");
		else alert("지원할 수 없습니다.")
	});
}
function del(value) {
	ajax.del("/api/quest/"+value, {}, function(result){
		if(result) alert("퀘스트를 삭제했습니다.");
		else alert("퀘스트를 삭제할 수 없습니다.");
	});
}

function list() {
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
        link: function (value) { return "/quest/" + value; },
    });
	
	$form = $("form[name='questSearchForm']");
	searchKeyword = $form.find("[name='searchKeyword']").val();
	ajax.get($form.attr("action") + searchKeyword, {}, function(list){
		$("span.project-search-length").html(list.length);
		$("div.quest-content").loadTemplate("/quest/node/list", list);
	});
	return false;
}

$(document).ready(function(){
	list();
	$("button[name='requestButton']").click(function(){
		$("#requestDialog").find("div.modal-body").load("/quest/add");
	});
});
</script>