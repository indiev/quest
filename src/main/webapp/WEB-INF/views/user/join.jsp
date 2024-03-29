<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<script type="text/javascript">
$(function() {
	$('#name').change(function() {
		if($('#name').val() !='')
			checkDuplication($('#name').val(), 'name');
		else
			$('.name_checkMessageBox').empty();
	});
	
	$('#email').change(function() {
		if($('#email').val() !='')
			checkDuplication($('#email').val(), 'email');
		else
			$('.email_checkMessageBox').empty();
	});
	
	/*
	$('#name').keyup(function(){
		console.log('keyup');
	});
	*/
});

function checkDuplication(value, attribute) {
	obj = new Object();
	obj[attribute] = value;
	
	ajax.get("/api/users", obj, function(data) {
		console.log(data);
		if(data.length == 0)
			setMessageBox(false, attribute, '사용가능합니다.');
		else
			setMessageBox(true, attribute, '중복된값으로 사용이 불가능합니다.');
	});
}

function setMessageBox(isDuplication, attribute, messeage) {
	$msgBox = $('.'+attribute+'_checkMessageBox');
	$msgBox.empty();
	
	if(isDuplication)
		$msgBox.append($("<div class='alert alert-danger'>").html(messeage));
	else
		$msgBox.append($("<div class='alert alert-success'>").html(messeage));
}

function join(form) {
	ajax.submit(form, {}, function(data) {
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

<form role="form" action="/api/users" method="POST" onsubmit="return join(this);">
	<div class="container-pluid">
		<div class="form-group input-group">
			<span class="input-group-addon">ID</span>
			<label for="name" class="sr-only">아이디</label>
			<input type="text" id="name" name="name" class="form-control" placeholder="아이디" required="required">
		</div>
		<div class="name_checkMessageBox">
		</div>
		<div class="form-group input-group">
			<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
			<label for="password" class="sr-only">비밀번호</label>
			<input type="password" id="password" name="password" class="form-control" placeholder="비밀번호" required="required">
		</div>
		<div class="form-group input-group">
			<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
			<label for="passwordCheck" class="sr-only">비밀번호 확인</label>
			<input type="password" id="passwordCheck" name="passwordCheck" class="form-control" placeholder="비밀번호 확인" required="required">
		</div>
		<div class="form-group input-group">
			<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
			<label for="realname" class="sr-only">이름</label>
			<input type="text" id="realname" name="realname" class="form-control" placeholder="이름" required="required">
		</div>
		<div class="form-group input-group">
			<span class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></span>
			<label for="phone" class="sr-only">휴대폰 번호</label>
			<input type="tel" id="phone" name="phone" class="form-control" placeholder="휴대폰 번호" required="required">
		</div>
		<div class="form-group input-group">
			<span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
			<label for="email" class="sr-only">이메일</label>
			<input type="email" id="email" name="email" class="form-control" placeholder="이메일" required="required">
		</div>
		<div class="email_checkMessageBox">
		</div>
		<input type="submit" id="join_submit" name="join_submit" class="btn btn-default center-block" value="가입">
	</div>
</form>