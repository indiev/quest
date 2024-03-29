<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="container-fluid">
	<div class="row">
		<div class="navbar navbar-default col-md-2 text-center" role="navigation" style="position:fixed; height:100%; padding-top:15px;">
			<h4><span class="project-search-length">0</span>개의 프로젝트가 있습니다</h4>
	 		<form class="form-inline" name="questSearchForm" role="form" action="/api/quests/" method="GET" onsubmit="return list();">
		 		<div class="input-group form-search">
		 			<label for="searchKeyword" class="sr-only">검색</label>
		 			<input type="text" name="searchKeyword" id="searchKeyword" class="form-control input-sm search-query" placeholder="검색" />
		 			<span class="input-group-btn">
		 				<button class="btn btn-default btn-sm" type="button" onclick="list()"><span class="glyphicon-search glyphicon" aria-hidden="true"></span></button>
		 				<!-- <a href="/quest/add" class="load"><span class="btn btn-default btn-sm"><span class="glyphicon-plus glyphicon" aria-hidden="true"></span></span></a> -->
		 			</span>
		 		</div>
		 		<div>가격바</div>
		 		<div>프로젝트 상태 체크</div>
	 		</form>
		</div>
		<div class="col-md-6 col-md-offset-3">
			<div class="quest-content"></div>
		</div>
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
	ajax.get($form.attr("action"), {"name":searchKeyword}, function(list){
		$("span.project-search-length").html(list.length);
		$("div.quest-content").loadTemplate("/quest/node/list", list);
	});
	return false;
}

$(document).ready(function(){
	list();
});
</script>