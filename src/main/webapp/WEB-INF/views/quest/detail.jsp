<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="quest-detail"></div>

<script type="text/javascript">
function getId() {
	return $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1);
}

function accept(questerId) {
	if(confirm("수락하시겠습니까?")) {
		ajax.put("/api/quests/" + getId() + "/questers/" + questerId , {}, function(result){
			if(result) {
				alert("수락했습니다");				
			} else {
				alert("퀘스터로 수락할 수 없습니다.");
			}
		});
	}
}

function detail(id) {
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
        link: function (value) { return "/quest/" + value; }
    });
	
	ajax.get("/api/quests/"+id, {}, function(quest){
		$("div.quest-detail").loadTemplate("/quest/node/detail", quest);
	});
}

function next(id) {
	ajax.get("/api/quests/" + id, {'state':'discuss'}, function(result) {
		console.log(result);
	});
}

$(document).ready(function(){
	var id = getId();
	detail(id);
});
</script>