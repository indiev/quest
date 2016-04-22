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

function detail(id) {
	ajax.get("/api/quest/"+id, {}, function(quest){
		$.get("/quest/node/detail", function(detailNode){
			$detailNodeClone = $(detailNode).clone();
			if(jQuery.type(quest.classification) != "undefined" && quest.classification.length > 0) {
				for(i in quest.classification) {
					$detailNodeClone.find(".area").append(quest.classification[i].kind.area.name);
					$detailNodeClone.find(".kind").append(quest.classification[i].kind.name);
				}
			}
			$detailNodeClone.find(".realname").html(quest.requester.name);
			$detailNodeClone.find(".name").html(quest.name);
			$detailNodeClone.find(".createdDate").html($.datepicker.formatDate('yy년 mm월 dd일', new Date(quest.createdDate)));
			$detailNodeClone.find(".reward").html(quest.reward);
			$detailNodeClone.find(".qualification").html(quest.qualification);
			$detailNodeClone.find(".duration").html(quest.duration);
			$detailNodeClone.find(".description").html(quest.description);
			if(quest.applicants.length > 0) {
				$detailNodeClone.find(".applicant").empty();
				for(i in quest.applicants) {
					$applicant = $("<li>").html(quest.applicants[i].name);
					$applicant.click(function(){ accept(quest.id, quest.applicants[i].id); });
					$detailNodeClone.find(".applicant").append($applicant);
				}
			}
			if(quest.questers.length > 0) {
				$detailNodeClone.find(".questers").empty();
				for(i in quest.questers) {
					$quester = $("<li>").html(quest.questers[i].name);
					$detailNodeClone.find(".questers").append($quester);
				}
			}
			$detailNodeClone.find("button").click(function() {
				ajax.get("/api/quest/" + quest.id + "/state/discuss", {}, function(result) {
					console.log(result);
				});
			})
			$("div.quest-detail").append($detailNodeClone);
		});
	});
}
$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	detail(id);
});
</script>