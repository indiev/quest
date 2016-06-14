<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="quest-detail"></div>
<script type="text/javascript">
var origin = new Array();
function detail(id) {
	ajax.get("/api/quest/" + id, {}, function(quest){
		$("div.quest-detail").loadTemplate("/quest/node/discuss", quest);
		ajax.get("/api/contract/agree/" + quest.contract.id, {}, function(result){
			$("div.quest-detail").find("button").attr("disabled", result);
		});
	});
}

function agree(value) {
	ajax.put("/api/contract/agree/" + value, {}, function(result){
		console.log(result);
		alert("협의");
		$("div.quest-detail").find("button").attr("disabled", true);
	});
}

$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	detail(id);
});

$(document).on("focus", "[contenteditable='true']", function(){
	$this = $(this);
	if(typeof origin[$this.attr("data-model")] === "undefined") origin[$this.attr("data-model")] = new Array();
	if(typeof origin[$this.attr("data-model")][$this.attr("data-column")] === "undefined") origin[$this.attr("data-model")][$this.attr("data-column")] = new Array();
	if(typeof origin[$this.attr("data-model")][$this.attr("data-column")][$this.attr("value")] === "undefined") origin[$this.attr("data-model")][$this.attr("data-column")][$this.attr("value")] = $this.html();
})

$(document).on("focusout", "[contenteditable='true']", function(){
	$this = $(this);
	var model = $this.attr("data-model");
	var column = $this.attr("data-column");
	var id = $this.attr("value");
	var originValue = origin[model][column][id];
	var value = $this.html();
	if(originValue != value) {
		if(confirm("수정 요청을 하시겠습니까?")) {
			data = {
				"name":value,
				"model":model,
				"attribute":column,
				"refId":id,
				"origin":originValue,
				"type":"U"
			};
			ajax.post("/api/updater", data, function(result) {
				$this.html(originValue);
				$this.css("color", "red");
				alert("수정을 요청했습니다.");
			});
		} else {
			$this.html(originValue);
		}
	}
});
</script>