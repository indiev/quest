<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="quest-detail"></div>

<script type="text/javascript">
function accept(questId, questerId) {
	if(confirm("수락하시겠습니까?")) {
		ajax.post("/api/quest/accept", {"questId":questId, "questerId":questerId}, function(result){
			if(result) {
				alert("수락했습니다");				
			} else {
				alert("퀘스터로 수락할 수 없습니다.");
			}
		});
	}
}

function detailNode(id) {
	ajax.get("/api/quest/"+id, {}, function(quest){
		$.get("/quest/node/detail", function(detailNode){
			if(jQuery.type(quest.classification) != "undefined" && quest.classification.length > 0) {
				for(i in quest.classification) {
					detailNode.find(".area").append(quest.classification[i].kind.area.name);
					detailNode.find(".kind").append(quest.classification[i].kind.name);
				}
			}
			detailNode.find(".realname").html(quest.requester.name);
			detailNode.find(".name").html(quest.name);
			detailNode.find(".createdDate").html($.datepicker.formatDate('yy년 mm월 dd일', new Date(quest.createdDate)));
			detailNode.find(".reward").html(quest.reward);
			detailNode.find(".qualification").html(quest.qualification);
			detailNode.find(".duration").html(quest.duration);
			detailNode.find(".description").html(quest.description);
			for(i in quest.applicants) {
				$applicant = $("<span>").html(quest.applicants[i].name);
				$applicant.click(function(){ accept(quest.id, quest.applicants[i].id); });
				detailNode.find(".applicant").append($applicant);
			}
			for(i in quest.questers) {
				$quester = $("<span>").html(quest.questers[i].name);
				detailNode.find(".questers").append($quester);
			}
			$("quest-detail").html(detailNode);
		});
		//$html.append($("<div>").html());
		//패널티 위약금
		//requirements
		//지원자수/모집자수
		$("#main").append($html);
	});
}
$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	console.log(id);
	/*
	ajax.get("/api/quest/"+id, {}, function(quest){
		$.get("/quest/node/detail", function(detailNode){
			if(jQuery.type(quest.classification[0]) != "undefined") {
				detailNode.find(".area").html(quest.classification[0].kind.area.name);
				detailNode.find(".kind").html(quest.classification[0].kind.name);
			}
			detailNode.find(".realname").html(quest.requester.user.realname);
			detailNode.find(".name").html(quest.name);
			detailNode.find(".createdDate").html($.datepicker.formatDate('yy년 mm월 dd일', new Date(quest.createdDate)));
			detailNode.find(".reward").html(quest.reward);
			detailNode.find(".qualification").html(quest.qualification);
			detailNode.find(".duration").html(quest.duration);
			detailNode.find(".description").html(quest.description);
			for(i in quest.applicants) {
				$applicant = $("<span>").html(quest.applicants[i].user.realname);
				$applicant.click(function(){ accept(quest.id, quest.applicants[i].id); });
				detailNode.find(".applicant").append($applicant);
			}
			for(i in quest.questers) {
				$quester = $("<span>").html(quest.questers[i].user.realname);
				detailNode.find(".questers").append($quester);
			}
			$("quest-detail").html(detailNode);
		});
		//$html.append($("<div>").html());
		//패널티 위약금
		//requirements
		//지원자수/모집자수
		$("#main").append($html);
	});
	*/
});
</script>