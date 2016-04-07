<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<script type="text/javascript">
function selectInputList(name, list) {
	var select = $("select[name='" + name + "']");
	select.html('');
	select.append($("<option>").html('선택').val(null));
	for(i in list) {
		option = $("<option>");
		option.html(list[i].name).val(list[i].id);
		select.append(option);
	}
}
$(function(){
	selectInputList('area', {});
	selectInputList('kind', {});
	
	ajax.get("/api/area/list", {}, function(list) {
		selectInputList('area', list);
	});
	
	$("select[name='area']").change(function(){
		if(this.value != '') ajax.get("/api/kind/area/" + this.value, {}, function(list) {
			selectInputList('kind', list);
		});
	});
});
function request(form) {
	ajax.submit(form, function(data) {
		console.log(data);
		if(data != null) {
			alert("퀘스트를 올렸습니다.");
			location.href = '/quest/mainlist';
		}
		else {
			alert(data.mssege);
		}
	});
	return false;
}
</script>

<form role="form" action="/api/quest" method="POST" onsubmit="return request(this);">
	<div class="container-pluid">
		<div class="form-group">
			<label for="name" class="sr-only">퀘스트명</label>
			<input type="text" name="name" id="name" class="form-control" placeholder="퀘스트명">
		</div>
		<div class="form-group row">
			<label for="area" class="sr-only">분야</label>
			<div class="col-xs-6"><select name="area" id="area" class="form-control"></select></div>
			<label for="kind" class="sr-only">업무</label>
			<div class="col-xs-6"><select name="kind" id="kind" class="form-control"></select></div>
		</div>
		<div class="form-group">
			<!-- <label for="recruitmentEndDate">지원마감일</label><input type="datetime-local" name="recruitmentEndDate" id="recruitmentEndDate"> -->
		</div>
		<div class="form-group">
			<label for="duration" class="sr-only">기간</label>
			<input type="number" name="duration" id="duration" class="form-control" placeholder="기간">
		</div>
		<div class="form-group">
			<label for="reward"class="sr-only">보상</label>
			<input type="text" name="reward" id="reward" class="form-control" placeholder="보상">
		</div>
		<div class="form-group">
			<label for="qualification" class="sr-only">자격</label>
			<input type="text" name="qualification" id="qualification" class="form-control" placeholder="자격">
		</div>
		<div class="form-group">
			<label for="description" class="sr-only">내용</label>
			<input type="text" name="description" id="description" class="form-control" placeholder="내용">
		</div>
		<input type="submit" id="" name="" class="btn btn-default" value="요청">
	</div>
</form>