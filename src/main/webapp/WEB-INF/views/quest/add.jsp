<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<script type="text/javascript">
var classQuest = function() {
	this.areas = new Array();
	this.works = new Array();
	this.skills = new Array();
	this.requirements = new Array();
}

var quest = new classQuest();

$(function() {
	selectInputList("area", {}, "분야");
	selectInputList("subArea", {}, "세부분야");
	selectInputList("work", {}, "업무");
	selectInputList("subWork", {}, "세부업무");
	selectInputList("Skill", {}, "스킬");
	
	ajax.get("/api/area", {}, function(list) {
		selectInputList("area", list, "분야");
	});
	
	ajax.get("/api/work", {}, function(list) {
		selectInputList("work", list, "업무");
	});
	
	ajax.get("/api/skill", {}, function(list) {
		selectInputList("skill", list, "스킬");
	});
	
	$("select[name='area']").change(function() {
		if(this.value != "") ajax.get("/api/area/ref/parent/" + this.value, {}, function(list) {
			selectInputList('subArea', list, "세부분야");
		});
	});
	
	$("select[name='work']").change(function() {
		if(this.value != "") ajax.get("/api/work/ref/parent/" + this.value, {}, function(list) {
			selectInputList('subWork', list, "세부업무");
		});
	});
	
	$("select[name='subArea']").change(function(){
		var text = $(this).find(":selected").html()
		var value = this.value;
		if(value != "") $("ul.area").append(function() {
			$node = htmlBadge(text, value);
			$node.find("span.glyphicon-remove").click(function(){
				alert("제외");
			});
			quest.areas.push({"id":value});
			return $("<li>").append($node);
		});
	});
	
	$("select[name='subWork']").change(function() {
		var text = $(this).find(":selected").html()
		var value = this.value;
		if(value != "") $("ul.work").append(function(){
			$node = htmlBadge(text, value);
			$node.find("span.glyphicon-remove").click(function(){
				alert("제외");
			});
			quest.works.push({"id":value});
			return $("<li>").append($node);
		});
	});
	
	$("select[name='skill']").change(function() {
		var text = $(this).find(":selected").html()
		var value = this.value;
		if(value != "") $("ul.skill").append(function(){
			$node = htmlBadge(text, value);
			$node.find("span.glyphicon-remove").click(function(){
				alert("제외");
			});
			quest.skills.push({"id":value});
			return $("<li>").append($node);
		});
	});
});

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

function addRequirement() {
	var $name = $("input[name='requirementName']");
	var $description = $("input[name='requirementDescription']");
	var name = $name.val();
	var description = $description.val();
	$name.val('');
	$description.val('');
	
	$row = $("<tr>");
	$row.append($("<td>").html(name).addClass("col-xs-3"));
	$row.append($("<td>").html(description).addClass("col-xs-9"));
	$("table.requirement-list > tbody").append($row);
	
	quest.requirements.push({"name":name, "description":description});
}

function request(form) {
	/* data = $(form).serializeArray();
	console.log(data);
	console.log(JSON.stringify(data));
	data = ajax.serializeObject($(form));
	console.log(data);
	data.areas = quest.areas;
	data.works = quest.works;
	data.skills = quest.skills;
	data.requirements = quest.requirements;
	console.log(data); */
	/* data = {areas:[{id:7},{id:8}],works:[{id:7},{id:8}],skills:[{id:4},{id:5}],
			requirements:[{name:"1",description:"1"},{name:"2",description:"2"}]}; */
	/* quest.requirements.push({"name":1, "description":1});
	quest.requirements.push({"name":2, "description":2});
	quest.areas.push({"id":7});
	quest.areas.push({"id":8});
	quest.works.push({"id":7});
	quest.works.push({"id":8});
	quest.skills.push({"id":4});
	quest.skills.push({"id":5}); */
	ajax.submit(form, quest, function(result) {
		if(result!= null) alert("퀘스트를 올렸습니다.");
		else alert(data.mssege);
	});
	return false;
}
</script>

<form role="form" action="/api/quest" method="POST" onsubmit="return request(this);">
	<div class="container-pluid">
		<div class="form-group">
			<label for="name" class="sr-only">퀘스트명</label>
			<input type="text" name="name" id="name" class="form-control input-lg" placeholder="퀘스트명">
		</div>
		<div class="form-group row">
			<div class="col-xs-6">
				<label for="area" class="sr-only">분야</label>
				<select name="area" id="area" class="form-control"></select>
			</div>
			<label for="subArea" class="sr-only">세부분야</label>
			<div class="col-xs-6"><select name="subArea" id="subArea" class="form-control"></select></div>
		</div>
		<div class="form-group">
			<ul class="list-inline form-control-static area"></ul>
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
				<input type="text" name="requirementName" id="requirement" class="form-control" placeholder="요구사항 이름">
			</div>
			<div class="col-xs-6">
				<label for="requirement" class="sr-only">요구사항 내용</label>
				<input type="text" name="requirementDescription" id="requirement" class="form-control" placeholder="요구사항 내용">
			</div>
			<div class="col-xs-2">
				<button type="button" id="" name="" class="btn btn-default" onclick="addRequirement();">추가</button>
			</div>
		</div>
		<div class="form-group">
			<table class="table table-bordered requirement-list"><tbody></tbody></table>
			<ul class="list-inline requirement-list"></ul>
		</div>
		<div class="form-group">
			<label for="description" class="sr-only">내용</label>
			<input type="text" name="description" id="description" class="form-control" placeholder="내용">
		</div>
		<input type="submit" id="" name="" class="btn btn-default" value="요청">
	</div>
</form>