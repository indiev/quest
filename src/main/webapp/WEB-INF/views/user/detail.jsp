<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="user-detail"></div>

<script type="text/javascript">

function detail(id) {
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
    });
	
	ajax.get("/api/user/"+id, {}, function(user){
		$("div.user-detail").loadTemplate("/user/node/detail", user);
	});
			
}
$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	if(id=="" && user != null) id = user.id;
	detail(id);
});
</script>