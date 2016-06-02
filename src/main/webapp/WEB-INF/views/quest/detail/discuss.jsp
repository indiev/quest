<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="quest-detail"></div>

<script type="text/javascript">
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
</script>