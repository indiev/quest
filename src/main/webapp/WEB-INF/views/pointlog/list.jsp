<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div>
	<h3>포인트 거래 내역 정보</h3><hr>
</div>
<div class="pointLog-content"></div>
	
<script type="text/javascript">
$(document).ready(function() {
	list();
});

function list() {
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일 ", new Date(value)); },
		link: function (value) { return "/reward/" + value; },
    	point: function (value) {return value + " point"}
	});
	
	ajax.get("/api/pointLogs/user/", {}, function(list){
		$("div.pointLog-content").loadTemplate("/pointLog/node/list", list);
	});
	return false;
}

</script>