<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<!-- <div class="jumbotron" style="position:fixed; height:100%;">
	<div class="jumbotron-contents text-center">
	  	<h2><span class="project-search-length">0</span>개의 프로젝트가 있습니다</h2>
 		<form class="form-inline" name="questSearchForm" role="form" action="/api/quests/" method="GET" onsubmit="return list();">
	 		<div class="input-group form-search">
	 			<label for="searchKeyword" class="sr-only">검색</label>
	 			<input type="text" name="searchKeyword" id="searchKeyword" class="form-control input-sm search-query" placeholder="검색" />
	 			<span class="input-group-btn">
	 				<button class="btn btn-default btn-sm" type="button" onclick="list()"><span class="glyphicon-search glyphicon" aria-hidden="true"></span></button>
	 				<button type="button" class="btn btn-default btn-sm" name="requestButton" data-toggle="modal" data-target="#modal"><span class="glyphicon-plus glyphicon" aria-hidden="true"></span></button>
	 			</span>
	 		</div>
 		</form>
 	</div>
</div> -->
<div class="row">
	<div class="col-md-3">
	<div class="navbar navbar-default col-md-2 text-center" role="navigation" style="position:fixed; height:100%">
		<h4><span class="project-search-length">0</span>개의 프로젝트가 있습니다</h4>
 		<form class="form-inline" name="questSearchForm" role="form" action="/api/quests/" method="GET" onsubmit="return list();">
	 		<div class="input-group form-search">
	 			<label for="searchKeyword" class="sr-only">검색</label>
	 			<input type="text" name="searchKeyword" id="searchKeyword" class="form-control input-sm search-query" placeholder="검색" />
	 			<span class="input-group-btn">
	 				<button class="btn btn-default btn-sm" type="button" onclick="list()"><span class="glyphicon-search glyphicon" aria-hidden="true"></span></button>
	 				<button type="button" class="btn btn-default btn-sm" name="requestButton" data-toggle="modal" data-target="#modal"><span class="glyphicon-plus glyphicon" aria-hidden="true"></span></button>
	 			</span>
	 		</div>
 		</form>
		</div>
	</div>
	<div class="col-md-6">
		<div class="quest-content"></div>
	</div>
	<div class="col-md-3">
	</div>
</div>
<script type="text/javascript">
var apiUrl = "/api/quests/";
function apply(id) {
	ajax.put(apiUrl + id + "/applicants/" + user.quester.id, {}, function(result) {
		if(result) alert("지원하였습니다");
		else alert("지원할 수 없습니다.")
	});
}
function del(id) {
	ajax.del(apiUrl + id, {}, function(result){
		if(result) alert("퀘스트를 삭제했습니다.");
		else alert("퀘스트를 삭제할 수 없습니다.");
	});
}

function list() {
	$.addTemplateFormatter({
		date: function(value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
        link: function(value) { return "/quest/" + value; },
        remain: function(value) {  return $.datepicker.formatDate("m월 d일 남음", new Date(new Date() - new Date(value))); }
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
		$modal = $("body div.modal");
		$modal.find("div.modal-body").load("/quest/add");
	});
});
</script>