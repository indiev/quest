<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>

<script type="text/javascript">
function join(form) {
	ajax.submit(form, function(data) {
		console.log(data);
		if(data != null) {
			alert("회원가입에 성공하였습니다.");
			location.href = '/';
		}
		else {
			alert(data.mssege);
		}
	});
	return false;
}
</script>

<form role="form" action="/api/user" method="POST" onsubmit="return join(this);">
	<div class="container-pluid">
		<div class="form-group">
			<label for="name" class="sr-only">아이디</label>
			<input type="text" id="name" name="name" class="form-control" placeholder="아이디">
		</div>
		<div class="form-group">
			<label for="password" class="sr-only">패스워드</label>
			<input type="password" id="password" name="password" class="form-control" placeholder="패스워드">
		</div>
		<div class="form-group">
			<label for="passwordCheck" class="sr-only">패스워드 확인</label>
			<input type="password" id="passwordCheck" name="passwordCheck" class="form-control" placeholder="패스워드 확인">
		</div>
		<div class="form-group">
			<label for="realname" class="sr-only">이름</label>
			<input type="text" id="realname" name="realname" class="form-control" placeholder="이름">
		</div>
		<div class="form-group">
			<label for="phone" class="sr-only">휴대폰 번호</label>
			<input type="tel" id="phone" name="phone" class="form-control" placeholder="휴대폰 번호">
		</div>
		<div class="form-group">
			<label for="email" class="sr-only">이메일</label>
			<input type="email" id="email" name="email" class="form-control" placeholder="이메일">
		</div>
		<input type="submit" id="join_submit" name="join_submit" class="btn btn-default center-block" value="가입">
	</div>
</form>