<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="requester-basicInfo"></div>

<div class="requester-relatedQuestsStats"></div>

<div class="requester-relatedQuests"></div>


<script type="text/javascript">
function detail(id) {
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
    });
	
	$("div.requester-basicInfo").load("/requester/basicInfo/"+id);
	$("div.requester-relatedQuestsStats").load("/requester/relatedQuestsStats/"+id);
	$("div.requester-relatedQuests").load("/requester/relatedQuests/"+id);
}

$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	if(id == "" && user != null) id = user.quester.id;
	detail(id);
});

</script>