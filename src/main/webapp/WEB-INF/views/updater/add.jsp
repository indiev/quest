<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="updater-content">
	<form role="form" class="form-horizontal" action="/api/updaters" method="post" onsubmit="return add(this);">
		<input type="hidden" name="model" data-value="model">
		<input type="hidden" name="attribute" data-value="attribute">
		<input type="hidden" name="refId" data-value="refId">
		<input type="hidden" name="name" data-value="name">
		<input type="hidden" name="type" data-value="type">
		<div class="form-group">
			<label for="origin" class="col-sm-2 control-label">본래 값</label>
			<div class="col-sm-10">
				<p class="form-control-static" id="origin-static" data-content="name"></p>
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">변경 값</label>
			<div class="col-sm-10">
				<input type="text" name="alternation" id="alternation" class="form-control" placeholder="변경 값" data-value="name">
			</div>
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
</div>

<script type="text/javascript">
function add(form) {
	var origin = $(form).find("[name='name']").val();
	var value = $(form).find("[name='alternation']").val();
	var type = $(form).find("[name='type']").val();
	if(origin == value) alert("본래 값과 변경하려는 값이 같습니다.");
	else {
		ajax.submit(form, {}, function(result) {
			if(result != "") {
				switch(type){
					case "C": alert("추가 요청을 했습니다."); break;
					case "U": alert("수정 요청을 했습니다."); break;
					case "D": alert("삭제 요청을 했습니다."); break;
					default: alert("요청했습니다.");
				}
			} else alert("요청에 실패 했습니다.");
		});
	}
	return false;
}
</script>
