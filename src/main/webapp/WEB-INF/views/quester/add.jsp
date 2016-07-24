<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<form role="form" action="/api/questers" method="POST" onsubmit="return request(this);">
	<div class="container-pluid">
		<div class="form-gorup">
			<label for="name">닉네임</label> 
			<input type="text" name="name" id="name" class="form-control" placeholder="닉네임">
		</div>
		
		<div class="form-group">
			<label>분야</label> 
			<div class="row">
				<div class="col-md-5"><select name="area" id="area" class="form-control" ></select></div>
				<div class="col-md-5"><select name="subArea" id="subArea" class="form-control" ></select></div>
			</div>
		</div>
		<div class="form-group">
			<ul class="list-inline form-control-static area"></ul>
		</div>
		<div class="form-group">
			<label>업무</label> 
			<div class="row">
				<div class="col-md-5"><select name="work" id="work" class="form-control" ></select></div>
				<div class="col-md-5"><select name="subWork" id="subWork" class="form-control" ></select></div>
			</div>
		</div>
		<div class="form-group">
			<ul class="list-inline form-control-static work"></ul>
		</div>
		<div class="form-group">
			<label>스킬</label> 
			<div class="row">
				<div class="col-md-5"><select name="skill" id="skill" class="form-control" ></select></div>
			</div>
		</div>
		<div class="form-group">
			<ul class="list-inline form-control-static skill"></ul>
		 </div>
		 
		<input type="submit" class="btn btn-default" value="추가">
		
	</div>
</form>



<script type="text/javascript">

var classQuester = function() {
	this.areas = new Array();
	this.works = new Array();
	this.skills = new Array();
}

var quester = new classQuester();

$(document).ready(function() {
	selectInputList("area", {}, "분야");
	selectInputList("subArea", {}, "세부분야");
	selectInputList("work", {}, "업무");
	selectInputList("subWork", {}, "세부업무");
	selectInputList("Skill", {}, "스킬");
	
	ajax.get("/api/areas/parents",{},function(list){ selectInputList("area", list, "분야"); });
	ajax.get("/api/work/parents",{},function(list){ selectInputList("work", list, "업무"); });
	ajax.get("/api/skill",{},function(list){ selectInputList("skill", list, "스킬"); });
	
	$("select[name='area']").change(function(){
		if(this.value != "") ajax.get("/api/area/parents/" + this.value, {}, function(list) {
			selectInputList('subArea', list, "세부분야" );
		});
	});
	
	$("select[name='work']").change(function(){
		if(this.value != "") ajax.get("/api/work/parents/" + this.value, {}, function(list) {
			selectInputList('subWork', list, "세부업무");
		});
	});
	
	$("select[name='subArea']").change(function(){ addSelectValue(this, "area", quester.areas); });
	$("select[name='subWork']").change(function() { addSelectValue(this, "work", quester.works); });
	$("select[name='skill']").change(function() { addSelectValue(this, "skill", quester.skills); });
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
	for(i in list) {
		$option = $("<option>");
		$option.html(list[i].name).val(list[i].id)
		$option.attr("parentId", list[i].parentId);
		select.append($option);
	}
}

function htmlBadge(text, value) {
	$removeIcon = $("<span>").addClass("glyphicon glyphicon-remove");
	return $("<span>").addClass("badge").append(text + "&nbsp;").append($removeIcon);
}

function request(form) {
	ajax.submit(form, quester, function(result) {
		if(result!= null) alert("새로운 퀘스터를 생성하였습니다.");
		else alert(data.mssege);
	});
	return false;
}
</script>


