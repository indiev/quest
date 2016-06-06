<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class ="mainWrap center-block">
	<div><h3>유저 정보</h3></div>
	<hr>
	<div class="user-detail"></div>
	<hr>
</div>

<script type="text/javascript">
$(document).ready(function(){
	console.log(user);
	$("div.user-detail").loadTemplate("/user/node/detail",user);
});


</script>