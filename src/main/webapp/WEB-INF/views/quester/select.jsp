<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="quester-content row"></div>

<script type="text/javascript">
$(document).ready(function() {
	
	
	/* 퀘스터  리스트  */
	ajax.get("/api/quester/list/user", {}, function(list){
		$.get("/quester/node/list", function(questerNode){
			for(i in list) {
				var quester = list[i]; 
				$questerNodeClone = $(questerNode).clone();
				
				$questerNodeClone.attr("id",quester.id);
				$questerNodeClone.find(".name").html(quester.name);
				
				if(quester.areas.length > 0) {
					$questerNodeClone.find(".areas").empty();
					for(i in quester.areas ) {
						$area = $("<li>").html(quester.areas[i].name);
						$questerNodeClone.find(".areas").append($area);
					}
				}
				
				if(quester.works.length > 0) {
					$questerNodeClone.find(".works").empty();
					for(i in quester.works ) {
						$work = $("<li>").html(quester.works[i].name);
						$questerNodeClone.find(".works").append($work);
					}
				}
				
				if(quester.skills.length > 0) {
					$questerNodeClone.find(".skills").empty();
					for(i in quester.skills ) {
						$skill = $("<li>").html(quester.skills[i].name);
						$questerNodeClone.find(".skills").append($skill);
					}
				}
				
				$questerNodeClone.click(function(){
					selectMainQuester(this.id);
				});
				$("div.quester-content").append($questerNodeClone);
			
			}
		});
	});
});

function selectMainQuester(selectedId) {
	ajax.get("/api/user/get", {}, function(user) {
		ajax.put("/api/user/"+user.id+"/mainQuester/"+selectedId, {}, function(result){
			$("#selectQuesterDialog").modal('hide');
			getMainQuesterDetail();
			alert("메인 퀘스터가 변경되었습니다.");
		});
	});
}



</script>

