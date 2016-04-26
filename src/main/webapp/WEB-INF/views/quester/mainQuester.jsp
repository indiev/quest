<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class ="mainWrap center-block">
	<div><h3>퀘스터 정보</h3></div>
	<hr>
	<div class="quester-detail"></div>
	<div><button type="button" name="changeMainQuesterBtn" class="btn btn-success" data-toggle="modal" data-target="#selectQuesterDialog">변경</button></div>
	<hr>
	<div><h3>해당 퀘스터가 진행한 퀘스트 정보</h3></div>
	
</div>

<div class="modal fade" id="selectQuesterDialog" role="dialog" aria-labelledby="selectQuesterHeader" aria-hidden="true" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body"></div>
		</div>
	</div>
</div>


<script type="text/javascript">
$(document).ready(function() {
	getMainQuesterDetail();
	$("#selectQuesterDialog").find("div.modal-body").load("/quester/select");
	
});

function getMainQuesterDetail() {
	ajax.get("/api/user/get", {}, function(user) {
		ajax.get("/api/quester/"+user.mainQuester.id,{},function(quester){
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