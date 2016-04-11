<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>

<form role="form" action="/api/work" method="POST" onsubmit="return request(this);">
	<div class="container-pluid">
		<div class="form-group">
			<label for="parentId">1.상위 업무 선택</label>
			<select name="parentId" id="superWork" class=form-control></select>
		</div>
		
		<div class="form-group">
			<label for="name">2.생성 업무명</label>
			<input type="text" name="name" id="name" class="form-control" placeholder="업무명">
		</div>
		
		<input type="submit" class="btn btn-default" value="추가">
		
	</div>
</form>

<script type="text/javascript">
function selectInputList(name, list, defaultText) {
	var select = $("select[name='" + name + "']");
	select.empty();
	select.append($("<option>").html(defaultText).val(null));
	for(i in list) {
		option = $("<option>");
		option.html(list[i].name).val(list[i].id);
		select.append(option);
	}
}

$(document).ready(function() {
	ajax.get("/api/work/list", {}, function(list){
		selectInputList("parentId", list, "상위 업무 없음");
	});
	
});


function request(form) {
	console.log($(form).serialize());
	ajax.submit(form, function(data) {
		if(data != null) {
			alert("업무가 추가되었습니다.");
			//location.href = '/admin/work/add';
		}
		else {
			alert(data.mssege);
		}
	});
	return false;
}

</script>
