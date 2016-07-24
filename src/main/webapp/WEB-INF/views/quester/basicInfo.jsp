<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="quester-basiscInfo"></div>

<script type="text/javascript">
function basicInfo(id) {
	ajax.get("/api/questers/"+id, {}, function(quester){
		$("div.quester-basiscInfo").loadTemplate("/quester/node/basicInfo", quester);
	});
}

$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	if(id == "" && user != null) id = user.quester.id;
	basicInfo(id);
});
</script>