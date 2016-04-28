<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class ="mainWrap center-block">
	<div><h3>유저 정보</h3></div>
	<hr>
	<div class="user-detail"></div>
	<hr>
</div>

<script type="text/javascript">
$(document).ready(function(){
	getUserDetail();
});


function getUserDetail() {
	ajax.get("/api/user/get", {}, function(user) {
		$.get("/user/node/detail",function(detailNode){
			$("div.user-detail").empty();
			$detailNodeClone = $(detailNode).clone();
			
			$detailNodeClone.find(".name").html(user.name);
			$detailNodeClone.find(".realname").html(user.realname);
			$detailNodeClone.find(".email").html(user.email);
			$detailNodeClone.find(".phone").html(user.phone);
			$detailNodeClone.find(".createdDate").html(new Date(user.createdDate));
			
			$("div.user-detail").append($detailNodeClone);
			
			
		});
	});
}

</script>