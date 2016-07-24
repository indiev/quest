<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="quest-detail"></div>

<script type="text/javascript">
function detail(id) {
	ajax.get("/api/quests/"+id, {}, function(quest){
		$("div.quest-detail").loadTemplate("/quest/node/progress", quest);
	});
}

$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	detail(id);
});
</script>