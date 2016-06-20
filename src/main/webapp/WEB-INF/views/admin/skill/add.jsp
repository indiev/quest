<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<form role="form" action="/api/skill" method="POST" onsubmit="return request(this);">
	<div class="container-pluid">
		<div class="form-group">
			<label for="superArea">1.생성 스킬명</label>
			<input type="text" name="name" id="name" class="form-control" placeholder="스킬명">
		</div>
		<input type="submit" class="btn btn-default" value="추가">
	</div>
</form>

<script type="text/javascript">
$(document).ready(function() {
	
});

function request(form) {
	console.log($(form).serialize());
	ajax.submit(form, {}, function(data) {
		if(data != null) alert("스킬이 추가되었습니다.");
		else alert(data.mssege);
	});
	return false;
}
</script>