<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="quest-detail"></div>

<!-- <div class="modal fade" id="updatorDialog" role="dialog" aria-labelledby="updateHeader" aria-hidden="true" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title">퀘스트 등록</h4>
			</div>
			<div class="modal-body"></div>
			<div class="modal-footer"><input type="submit" id="" name="" class="btn btn-default" value="요청"></div>
		</div>
	</div>
</div> -->
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

$(document).on("click", "[contenteditable='true']", function(){
	//alert($(this).html());
})

$(document).on("focus", "[contenteditable='true']", function(){
	$this = $(this);
	if(typeof origin[$this.attr("data-model")] === "undefined") origin[$this.attr("data-model")] = new Array();
	if(typeof origin[$this.attr("data-model")][$this.attr("data-column")] === "undefined") origin[$this.attr("data-model")][$this.attr("data-column")] = new Array();
	if(typeof origin[$this.attr("data-model")][$this.attr("data-column")][$this.val()] === "undefined")  origin[$this.attr("data-model")][$this.attr("data-column")][$this.val()] = $this.html();
	console.log($this.val());
	
	
	//var origin = $(this).html();
	/* $("#updatorDialog").modal('toggle'); */
	//alert();
})

$(document).on("focusout", "[contenteditable='true']", function(){
	$this = $(this);
	var originValue = origin[$this.attr("data-model")][$this.attr("data-column")][$this.val()];
	console.log(origin);
	console.log(originValue);
	if(originValue != $this.html()) {
		if(confirm("수정 요청을 하시겠습니까?")) {
			data = {
				"name":$this.html(),
				"refModel":$this.attr("data-model"),
				"refColumn":$this.attr("data-column"),
				"refId":$this.val(),
				"origin":origin[$this.attr("data-column")][$this.val()],
				"type":"U"
			};
			ajax.post("/api/updater", data, function(result) {
				origin[$this.attr("data-column")][$this.val()] = $this.html();
				$this.css("color", "red");
			});
		} else {
			$this.html(originValue);
		}
	}
});
</script>