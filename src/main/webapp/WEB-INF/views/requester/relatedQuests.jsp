<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<h3>준비중인 퀘스트 :<span class="readyRequest-length">0</span>개 </h3>
<div class="container readyRequest-content"></div>

<h3>진행중인 퀘스트 :<span class="progressRequest-length">0</span>개</h3>
<div class="container progressRequest-content"></div>

<h3>완료된 퀘스트 리스트 :<span class="completeRequest-length">0</span>개</h3>
<div class="container completeRequest-content"></div>

<script type="text/javascript">

function releatedQuests(id) {
	/* 모집전  */	
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
		link: function (value) { return "/quest/" + value; }
    });
	ajax.get("/api/quests/requesters/"+ id, {'state':'ready'}, function(list){
		$("span.readyRequest-length").html(list.length);
		$("div.readyRequest-content").loadTemplate("/quest/node/list", list);
	});
	
	/* 진행중 */
	ajax.get("/api/quests/requesters/"+ id, {'state':'discuss,progress'}, function(list){
		$("span.progressRequest-length").html(list.length);
		$("div.progressRequest-content").loadTemplate("/quest/node/list", list);
	});
	
	/* 완료중 */
	ajax.get("/api/quests/requesters/"+ id, {'state':'complete'}, function(list){
		$("span.completeRequest-length").html(list.length);
		$("div.completeRequest-content").loadTemplate("/quest/node/list", list);
	});
}

$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	if(id == "" && user != null) id = user.requester.id;
	releatedQuests(id);
});
</script>