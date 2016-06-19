<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="requester-basiscInfo"></div>

<script type="text/javascript">
function basicInfo(id) {
	ajax.get("/api/requester/"+id, {}, function(requester){
		$("div.requester-basiscInfo").loadTemplate("/requester/node/basicInfo", requester);
	});
}

$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	if(id == "" && user != null) id = user.requester.id;
	basicInfo(id);
});
</script>