<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<style type="text/css">
span.badge.area { background-color: #5bc0de; }
span.badge.work { background-color: #f0ad4e; }
span.badge.skill { background-color: #d9534f; }
.btn-default:hover,
.btn-default:focus,
.btn-default:active,
.btn-default.active {
	color: #333;
	background-color: #fff;
	border-color: #ccc;
}
</style>
<form role="form" action="/api/quest" method="POST" onsubmit="return request(this);">
	<div class="container-pluid">
		<div class="form-group">
			<label for="name" class="sr-only">퀘스트명</label>	
			<input type="text" name="name" id="name" class="form-control input-lg" placeholder="퀘스트명" title="퀘스트명" required>
		</div>
		<div class="form-group">
			<div class="row">
				<div class="col-md-12">
					<div class="input-group">
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-globe"></span>
						</span>
						<label for="area" class="sr-only">상위분야</label>
						<select name="area" id="area" class="form-control"></select>
						<span class="input-group-btn" style="width:0px;"></span>
						<label for="subArea" class="sr-only">세부분야</label>
						<select name="subArea" id="subArea" class="form-control"></select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<button type="button" class="btn btn-default form-control area" onclick="return false;"></button>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="row">
				<div class="col-md-12">
					<div class="input-group">
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-briefcase"></span>
						</span>
						<label for="work" class="sr-only">상위업무</label>
						<select name="work" id="work" class="form-control"></select>
						<span class="input-group-btn" style="width:0px;"></span>
						<label for="subArea" class="sr-only">세부업무</label>
						<select name="subWork" id="subWork" class="form-control"></select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<button type="button" class="btn btn-default form-control work" onclick="return false;"></button>
				</div>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-md-4">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-wrench"></span>
					</span>
					<label for="skill" class="sr-only">스킬</label>
					<select name="skill" id="skill" class="form-control"></select>
				</div>
			</div>
			<div class="col-md-8">
				<button type="button" class="btn btn-default form-control skill" onclick="return false;"></button>
			</div>
		</div>
		<div class="form-group">
			<!-- <label for="recruitmentEndDate">지원마감일</label><input type="datetime-local" name="recruitmentEndDate" id="recruitmentEndDate"> -->
		</div>
		<div class="form-group row">
			<div class="col-md-4">
				<div class="input-group">
					<label for="duration" class="sr-only">기간</label>
					<input type="number" name="duration" id="duration" class="form-control" placeholder="기간" min="0" step="1" required>
					<span class="input-group-addon">일</span>
				</div>
			</div>
			<div class="col-md-4">
				<div class="input-group">
					<label for="reward"class="sr-only">보상</label>
					<input type="number" name="reward" id="reward" class="form-control" placeholder="보상" min="0" step="1" required>
					<span class="input-group-addon">환</span>
				</div>
			</div>
			<div class="col-md-4">
				<label for="reward"class="sr-only">마감일</label>
				<input type="date" name="recruitmentEndDate" id="recruitmentEndDate" class="form-control" placeholder="마감일" required>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-md-4">
				<div class="input-group">
					<label for="duration" class="sr-only">리퀘스터 위약금</label>
					<input type="number" name="conract.requesterPenalty" id="requesterPenalty" class="form-control" placeholder="기간" min="0" step="1" required>
					<span class="input-group-addon">%</span>
				</div>
			</div>
			<div class="col-md-4">
				<div class="input-group">
					<label for="reward"class="sr-only">퀘스터 위약금</label>
					<input type="number" name="questerPenalty" id="questerPenalty" class="form-control" placeholder="보상" min="0" step="1" required>
					<span class="input-group-addon">%</span>
				</div>
			</div>
			<div class="col-md-4">
				<div class="input-group">
					<label for="qualification" class="sr-only">자격</label>
					<input type="text" name="qualification" id="qualification" class="form-control" placeholder="자격" required>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="row">
				<div class="col-md-12">
					<div class="input-group">
						<label for="requirementName" class="sr-only">요구사항 제목</label>
						<input type="text" name="requirementName" id="requirementName" class="form-control" placeholder="요구사항 제목">
						<span class="input-group-btn" style="width:0px;"></span>
						<label for="requirementDescription" class="sr-only">요구사항 내용</label>
						<input type="text" name="requirementDescription" id="requirementDescription" class="form-control" placeholder="요구사항 내용">
						<div class="input-group-btn">
							<button type="button" id="" name="" class="btn btn-default" onclick="addRequirement();">추가</button>	
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table table-bordered requirement-list" style="margin-bottom: 0"><tbody></tbody></table>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="row">
				<div class="col-md-12">
					<div class="input-group">
						<label for="provisionName" class="sr-only">계약조항 제목</label>
						<input type="text" name="provisionName" id="provisionName" class="form-control" placeholder="계약조항 제목">
						<span class="input-group-btn" style="width:0px;"></span>
						<label for="provisionDescriptoin" class="sr-only">계약조항 내용</label>
						<input type="text" name="provisionDescription" id="provisionDescription" class="form-control" placeholder="계약조항 내용">
						<div class="input-group-btn">
							<button type="button" id="" name="" class="btn btn-default" onclick="addProvision();">추가</button>	
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table table-bordered provision-list" style="margin-bottom: 0"><tbody></tbody></table>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="sr-only">내용</label>
			<input type="text" name="description" id="description" class="form-control" placeholder="내용">
		</div>
		<input type="submit" id="" name="" class="btn btn-default pull-right" value="요청">
	</div>
</form>

<script type="text/javascript">
var classQuest = function() {
	this.areas = new Array();
	this.works = new Array();
	this.skills = new Array();
	this.requirements = new Array();
	this.rewards = new Array();
	this.contract = new Object();
	this.contract.provisions = new Array(); 
}

var quest = new classQuest();

$(function() {
	selectInputList("area", {}, "상위분야");
	selectInputList("subArea", {}, "세부분야");
	selectInputList("work", {}, "상위업무");
	selectInputList("subWork", {}, "세부업무");
	selectInputList("Skill", {}, "스킬");
	
	ajax.get("/api/area", {}, function(list) { selectInputList("area", list, "상위분야"); });
	ajax.get("/api/work", {}, function(list) { selectInputList("work", list, "상위업무"); });
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
		$("button." + category).append(function() {
			$node = htmlBadge(text, value, category);
			$node.find("span.glyphicon-remove").click(function(){ 
				list.splice(inArrayObject(value, list), 1);
				$(this).parent().remove();
			});
			return $node;
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

function addProvision() {
	var $name = $("input[name='provisionName']");
	var $description = $("input[name='provisionDescription']");
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
		$.each(quest.contract.provisions, function(i, provision) {
			if(this.name == name && this.description == description) {
				quest.contract.provisions.splice(i, 1);
				$elem.parent().parent().remove();
				return false;
			}
		});
	});
	$row.append($("<td>").html($button).addClass("col-xs-1"));
	$("table.provision-list > tbody").append($row);
	
	quest.contract.provisions.push({"name":name, "description":description});
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