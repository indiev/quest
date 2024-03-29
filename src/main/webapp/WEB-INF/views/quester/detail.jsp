<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="quester-basicInfo"></div>

<div class="quester-relatedQuestsStats"></div>

<div class="quester-relatedQuests"></div>


<script type="text/javascript">
function detail(id) {
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
    });
	
	$("div.quester-basicInfo").load("/quester/basicInfo/"+id);
	$("div.quester-relatedQuestsStats").load("/quester/relatedQuestsStats/"+id);
	$("div.quester-relatedQuests").load("/quester/relatedQuests/"+id);
}

$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	if(id == "" && user != null) id = user.quester.id;
	detail(id);
});

</script>