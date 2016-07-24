<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="requester-relatedQuestsStats-detail"></div>	

<script type="text/javascript">
function relatedQuestsStats(id) {
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
    });
	
	ajax.get("/api/quests/requester/" + id, {'state':'discuss,progress'}, function(list){
		$("div.requester-relatedQuestsStats-detail").loadTemplate("/requester/node/relatedQuestsStats", {});
	});
}

$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	if(id == "" && user != null) id = user.requester.id;
	relatedQuestsStats(id);
});
</script>