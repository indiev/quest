<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<style type="text/css">
span.badge.area { background-color: #5bc0de; }
span.badge.work { background-color: #f0ad4e; }
span.badge.skill { background-color: #d9534f; }
</style>
<form role="form" action="/api/quest" method="POST" onsubmit="return request(this);">
	<div class="container-pluid">
		<div class="form-group">
			<label for="name" class="sr-only">퀘스트명</label>
			<input type="text" name="name" id="name" class="form-control input-lg" placeholder="퀘스트명" title="퀘스트명" required>
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
			<input type="number" name="duration" id="duration" class="form-control" placeholder="기간" required>
		</div>
		<div class="form-group">
			<label for="reward"class="sr-only">보상</label>
			<input type="number" name="reward" id="reward" class="form-control" placeholder="보상" required>
		</div>
		<div class="form-group">
			<label for="qualification" class="sr-only">자격</label>
			<input type="text" name="qualification" id="qualification" class="form-control" placeholder="자격" required>
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

<script type="text/javascript">
var classQuest = function() {
	this.areas = new Array();
	this.works = new Array();
	this.skills = new Array();
	this.requirements = new Array();
	this.rewards = new Array();
}

var quest = new classQuest();

$(function() {
	selectInputList("area", {}, "분야");
	selectInputList("subArea", {}, "세부분야");
	selectInputList("work", {}, "업무");
	selectInputList("subWork", {}, "세부업무");
	selectInputList("Skill", {}, "스킬");
	
	ajax.get("/api/area", {}, function(list) { selectInputList("area", list, "분야"); });
	ajax.get("/api/work", {}, function(list) { selectInputList("work", list, "업무"); });
	ajax.get("/api/skill", {}, function(list) { selectInputList("skill", list, "스킬"); });
	
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
	
	$("select[name='subArea']").change(function(){ addSelectValue(this, "area", quest.areas); });
	$("select[name='subWork']").change(function() { addSelectValue(this, "work", quest.works); });
	$("select[name='skill']").change(function() { addSelectValue(this, "skill", quest.skills); });
});

function inArrayObject(value, list) {
	return list.map(function(e) { return e.id; }).indexOf(value);
}

function addSelectValue(elem, category, list) {
	var text = $(elem).find(":selected").html();
	var value = $(elem).val();
	if(value != "" && inArrayObject(value, list) == -1) {
		list.push({"id":value});
		$("ul." + category).append(function() {
			$node = htmlBadge(text, value, category);
			$node.find("span.glyphicon-remove").click(function(){ 
				list.splice(inArrayObject(value, list), 1);
				$(this).parent().remove();
			});
			return $("<li>").append($node);
		});
	}
}

function selectInputList(name, list, defaultText) {
	var select = $("select[name='" + name + "']");
	select.empty();
	select.append($("<option>").html(defaultText).val(null));
	$.each(list, function(i, node) {
		option = $("<option>");
		option.html(node.name).val(node.id);
		select.append(option);
	});
}

function htmlBadge(text, value, category) {
	$removeIcon = $("<span>").addClass("glyphicon glyphicon-remove").css("cursor", "pointer");
	return $("<span>").addClass("badge " + category).append(text + "&nbsp;").append($removeIcon);
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
	$row.append($("<td>").html(description).addClass("col-xs-8"));
	$button = $("<button>").addClass("btn glyphicon glyphicon-remove").attr("type", "button");
	$button.click(function() {
		$elem = $(this);
		$.each(quest.requirements, function(i, requirement) {
			if(this.name == name && this.description == description) {
				quest.requirements.splice(i, 1);
				$elem.parent().parent().remove();
				return false;
			}
		});
	});
	$row.append($("<td>").html($button).addClass("col-xs-1"));
	$("table.requirement-list > tbody").append($row);
	
	quest.requirements.push({"name":name, "description":description});
}

function request(form) {
	quest.rewards.push({"hwan":$("input[name='reward']").val()});
	ajax.submit(form, quest, function(result) {
		if(result != "") alert("퀘스트를 추가했습니다.");
		else alert("퀘스트를 추가하는 데 실패했습니다.");
	});
	return false;
}
</script>