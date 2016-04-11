<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="quest-form">
	<form class="form-inline" role="form" action="" method="POST" onsubmit="return search(this);">
		<div class="input-group">
			<label for="searchKeyword" class="sr-only">검색</label>
			<input type="text" name="searchKeyword" id="searchKeyword" class="form-control input-sm" placeholder="검색">
			<span class="input-group-btn">
				<button class="btn btn-default btn-sm" type="button">
					<span class="glyphicon-search glyphicon" aria-hidden="true"></span>
				</button>
			</span>
		</div>
		<button type="button" class="btn btn-default btn-sm" name="requestButton" data-toggle="modal" data-target="#requestDialog">
			<span class="glyphicon-plus glyphicon" aria-hidden="true"></span>
		</button>
	</form>
</div>
<div class="quest-content list-group"></div>

<div class="modal fade" id="requestDialog" role="dialog" aria-labelledby="requestHeader" aria-hidden="true" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body"></div>
		</div>
	</div>
</div>

<style type="text/css">
.quest-list-node { border:1px solid; margin-bottom: 10px;}
</style>

<script type="text/javascript">
function apply(value) {
	ajax.post("/api/quest/apply", {"id":value}, function(result){
		if(result) alert("지원하였습니다");
		else alert("지원할 수 없습니다.")
	});
}

function list() {
	$("div.quest-content").empty();
	ajax.get("/api/quest/list", {}, function(list){
		$.get("/quest/node/list", function(questNode){
			for(i in list) {
				var questNodeClone = $(questNode).clone();
				
				/* if(jQuery.type(list[i].classification[0]) != "undefined") {
					questNodeClone.find(".area").html(list[i].classification[0].kind.area.name);
					questNodeClone.find(".kind").html(list[i].classification[0].kind.name);
				} */
				questNodeClone.find(".realname").html(list[i].requester.user.realname);
				$name = $("<a>").attr("href", "/quest/detail/" + list[i].id).html(list[i].name);
				questNodeClone.find(".name").html($name);
				questNodeClone.find(".duration").html(list[i].duration + "일");
				questNodeClone.find(".reward").html(list[i].reward);
				questNodeClone.find(".qualification").html(list[i].qualification);
				questNodeClone.find(".description").html(list[i].description);
				questNodeClone.find("[name='applyButton']").val(list[i].id);
				questNodeClone.find("[name='applyButton']").click(function(){ apply(this.value); });
				$("div.quest-content").append(questNodeClone);
				//마감일
				//지원자수
				//모집자수?
			}
		});
	});
}

$(document).ready(function(){
	list();
	$("button[name='requestButton']").click(function(){
		$("#requestDialog").find("div.modal-body").load("/quest/request");
	});
});
</script>