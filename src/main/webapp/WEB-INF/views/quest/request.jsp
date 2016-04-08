<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
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

function htmlBadge(text, value) {
	$removeIcon = $("<span>").addClass("glyphicon glyphicon-remove");
	return $("<span>").addClass("badge").append(text + "&nbsp;").append($removeIcon);
}

$(function(){
	selectInputList("area", {}, "분야");
	selectInputList("subArea", {}, "세부분야");
	selectInputList("work", {}, "업무");
	selectInputList("subWork", {}, "세부업무");
	selectInputList("Skill", {}, "스킬");
	
	ajax.get("/api/area/list", {}, function(list) {
		selectInputList("area", list, "분야");
	});
	
	ajax.get("/api/work/list", {}, function(list) {
		selectInputList("work", list, "업무");
	});
	
	ajax.get("/api/skill/list", {}, function(list) {
		selectInputList("skill", list, "스킬");
	});
	
	$("select[name='area']").change(function(){
		if(this.value != "") ajax.get("/api/area/list/parent/" + this.value, {}, function(list) {
			selectInputList('subArea', list, "세부분야");
		});
	});
	
	$("select[name='work']").change(function(){
		if(this.value != "") ajax.get("/api/work/list/parent/" + this.value, {}, function(list) {
			selectInputList('subWork', list, "세부업무");
		});
	});
	
	$("select[name='subArea']").change(function(){
		var text = $(this).find(":selected").html()
		var value = this.value;
		if(value != "") $("ul.area").append(function(){
			$node = htmlBadge(text, value);
			$node.find("span.glyphicon-remove").click(function(){
				alert("제외");
			});
			return $("<li>").append($node);
		});
	});
	
	$("select[name='subWork']").change(function(){
		var text = $(this).find(":selected").html()
		var value = this.value;
		if(value != "") $("ul.work").append(function(){
			$node = htmlBadge(text, value);
			$node.find("span.glyphicon-remove").click(function(){
				alert("제외");
			});
			return $("<li>").append($node);
		});
	});
	
	$("select[name='skill']").change(function(){
		var text = $(this).find(":selected").html()
		var value = this.value;
		if(value != "") $("ul.skill").append(function(){
			$node = htmlBadge(text, value);
			$node.find("span.glyphicon-remove").click(function(){
				alert("제외");
			});
			return $("<li>").append($node);
		});
	});
});
function request(form) {
	ajax.submit(form, function(data) {
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
			<label for="subArea" class="sr-only">세부분야</label>
			<div class="col-xs-6"><select name="subArea" id="subArea" class="form-control"></select></div>
		</div>
		<div class="form-group">
			<ul class="list-inline form-control-static area">
				<li><span class="badge">분야 > 세부분야 <span class="glyphicon glyphicon-remove"></span></span></li>
				<li><span class="badge">세부분야 <span class="glyphicon glyphicon-remove"></span></span></li>
			</ul>
		</div>
		<div class="form-group row">
			<label for="work" class="sr-only">업무</label>
			<div class="col-xs-6"><select name="work" id="work" class="form-control"></select></div>
			<label for="subArea" class="sr-only">세부업무</label>
			<div class="col-xs-6"><select name="subWork" id="subWork" class="form-control"></select></div>
		</div>
		<div class="form-group">
			<ul class="list-inline form-control-static work"></ul>
		</div>
		<div class="form-group row">
			<label for="skill" class="sr-only">스킬</label>
			<div class="col-xs-6"><select name="skill" id="skill" class="form-control"></select></div>
			<label for="subArea" class="sr-only">-</label>
			<div class="col-xs-6"></div>
		</div>
		<div class="form-group">
				<ul class="list-inline form-control-static skill"><li>선택없음</li></ul>
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
		<div class="form-group row">
			<div class="col-xs-4">
				<label for="requirement" class="sr-only">요구사항 이름</label>
				<input type="text" name="requirement" id="requirement" class="form-control" placeholder="요구사항 이름">
			</div>
			<div class="col-xs-6">
				<label for="requirement" class="sr-only">요구사항 내용</label>
				<input type="text" name="requirement" id="requirement" class="form-control" placeholder="요구사항 내용">
			</div>
			<div class="col-xs-2">
				<input type="submit" id="" name="" class="btn btn-default" value="추가">
			</div>
		</div>
		<div class="form-group">
			요구사항에 대해서 추가된 사항 기입
		</div>
		<div class="form-group">
			<label for="description" class="sr-only">내용</label>
			<input type="text" name="description" id="description" class="form-control" placeholder="내용">
		</div>
		<input type="submit" id="" name="" class="btn btn-default" value="요청">
	</div>
</form>