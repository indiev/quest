<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class ="mainWrap center-block">
	<div><h3>퀘스터 정보</h3></div>
	<hr>
	<div class="quester-detail"></div>
	<hr>
	<div><h3>해당 퀘스터가 진행한 퀘스트 정보</h3></div>
	
</div>

<script type="text/javascript">
$(document).ready(function() {
	getMainQuesterDetail();
	
});

function getMainQuesterDetail() {
	ajax.get("/api/user/get", {}, function(user) {
		ajax.get("/api/quester/"+user.id,{},function(quester){
			$.get("/quester/node/detail",function(detailNode) {
				$("div.quester-detail").empty();
				$detailNodeClone = $(detailNode).clone();
				
				$detailNodeClone.find(".name").html(quester.name);
				if(quester.areas.length > 0) {
					$detailNodeClone.find(".areas").empty();
					for(i in quester.areas ) {
						$area = $("<li>").html(quester.areas[i].name);
						$detailNodeClone.find(".areas").append($area);
					}
					
				}
				
				if(quester.works.length > 0) {
					$detailNodeClone.find(".works").empty();
					for(i in quester.works ) {
						$work = $("<li>").html(quester.works[i].name);
						$detailNodeClone.find(".works").append($work);
					}
				}
				
				if(quester.skills.length > 0) {
					$detailNodeClone.find(".skills").empty();
					for(i in quester.skills ) {
						$skill = $("<li>").html(quester.skills[i].name);
						$detailNodeClone.find(".skills").append($skill);
					}
				}
				
				$("div.quester-detail").append($detailNodeClone);
			
			});
		});
	});
}

</script>