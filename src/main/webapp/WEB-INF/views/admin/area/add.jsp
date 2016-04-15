<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>

<form role="form" action="/api/area" method="POST" onsubmit="return request(this);">
	<div class="container-pluid">
		<div class="form-group">
			<label for="parentId">1.상위 분야 선택</label>
			<select name="parentId" id="superArea" class=form-control></select>
		</div>
		
		<div class="form-group">
			<label for="superArea">2.생성 분야명</label>
			<input type="text" name="name" id="name" class="form-control" placeholder="분야명">
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
	ajax.get("/api/area/list", {}, function(list){
		selectInputList("parentId", list, "상위 분야 없음");
	});
	
});


function request(form) {
	console.log($(form).serialize());
	ajax.submit(form, {}, function(data) {
		if(data != null) {
			alert("분야가 추가되었습니다.");
			//location.href = '/admin/area';
		}
		else {
			alert(data.mssege);
		}
	});
	return false;
}

</script>
