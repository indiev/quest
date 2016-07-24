<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<script type="text/javascript">
function login(form) {
	data = ajax.serializeObject($(form));
	ajax.login($(form).attr("action"), data, function(data) {
		data = JSON.parse(data);
		if(data.success) {
			$("#modal-sm").modal('toggle');
			ajax.get("/api/users/me", {}, function(data) { 
				user = data;
				$("header").loadTemplate("/header", user);
			});
		}
		else alert("아이디와 비밀번호를 다시 확인해주세요.");
	});
	return false;
}
</script>

<form role="form" name="login-form" action="/api/login" method="POST" onsubmit="return login(this);">
	<div class="container-pluid">
		<div class="form-group input-group">
			<span class="input-group-addon">ID</span>
			<label for="login_userid" class="sr-only">로그인 아이디</label>
			<input type="text" id="login_userid" name="name" class="form-control" placeholder="아이디" required="required">
		</div>
		<div class="form-group input-group">
			<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
			<label for="login_password" class="sr-only">로그인 비밀번호</label>
			<input type="password" id="login_password" name="password" class="form-control" placeholder="비밀번호">
		</div>
		<input type="submit" id="login_submit" name="submit" class="btn btn-default btn-block" value="로그인" required="required">
	</div>
</form>
