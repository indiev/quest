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
	
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
    });
});

$(document).on("mouseenter", ".updater", function(){
	$this = $(this);
	var data = saveOrigin(this);
	$this.append($("div.quest-detail").find("template").html());
	$this.find("span.list").click(function() {
		ajax.get("/api/updater/"+data.model+"/"+data.attribute+"/"+data.refId, {}, function(list) {
			$("#modal").find(".modal-body").load("/updater/list", function() {
				$template = $("#modal").find("tbody");
				$template.loadTemplate($template.children(), list, {bindingOptions:{"ignoreNull":false}});
				$("#modal").modal("show");
			});
		});
	});
	$this.find("span.edit").click(function() {
		data.type = "U";
		$("#modal").find(".modal-body").loadTemplate("/updater/add", data);
		$("#modal").modal("show");
	});
	$this.find("span.remove").click(function() {
		if(confirm("삭제 요청을 하시겠습니까?")) {
			data.type = "D";
			ajax.post("/api/updater", data, function() {
				alert("삭제 요청을 했습니다.");
			});
		}
	});
}).on("mouseleave", ".updater", function(){
	$(this).find(".editButtons").remove();
});

function saveOrigin(elem) {
	$elem = $(elem);
	var model = $elem.attr("data-model");
	var attribute = $elem.attr("data-attribute");
	var refId = $elem.attr("value");
	if(typeof origin[model] === "undefined") origin[model] = new Array();
	if(typeof origin[model][attribute] === "undefined") origin[model][attribute] = new Array();
	if(typeof origin[model][attribute][refId] === "undefined") origin[model][attribute][refId] = $this.html();
	
	return {"model":model, "attribute":attribute, "refId":refId, "name":origin[model][attribute][refId]};
}
</script>