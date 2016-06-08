<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>

<form role="form" action="/api/portfolio" method="POST" onsubmit="return request(this);">
	<div class="container-pluid">
		
		<div class="form-group typeId">
			<label id="typeId_label" for="typeId">type</label>
			<select name="typeId" id="typeId" class="form-control" ></select>
		</div>
		<div class="form-group">
			<label id="name_label" for="name">name</label> 
			<input type="text" name="name" id="name" class="form-control" placeholder="name">
		</div>
		<div class="form-group place">
			<label id="place_label" for="place">place</label> 
			<input type="text" name="place" id="place" class="form-control" placeholder="place">
		</div>
		<div class="form-group level">
			<label id="level_label" for="level">level</label>
			<input type="text" name="level" id="level" class="form-control" placeholder="level">
		</div>
		<div class="form-group result result">
			<label id="result_label" for="result">result</label>
			<input type="text" name="result" id="result" class="form-control" placeholder="result">
		</div>
		<div class="form-group">
			<label for="startDate">date</label> 
			<div>
			시작일:<input type="date" id="startDate" name="startDate">
			~
			종료일:<input type="date" id="endDate" name="endDate">
			</div>
		</div>
		<div class="form-group description">
			<label id="description_label" for="description">description</label> 
			<textarea name="description" id="description" class="form-control" placeholder="description" rows="5"></textarea>
		</div>
		<div class="form-group subPortfolio">
			<label id="subPortfolio_label" for="subPortfolio">subPortfolio</label>
			<div class="row">
				<div class="col-md-5"><select name="subPortfolioType" id="subPortfolioType" class="form-control" ></select></div>
				<div class="col-md-5"><select name="subPortfolio" id="subPortfolio" class="form-control" ></select></div>
			</div>
		</div>
		<div class="form-group">
			<ul class="list-inline form-control-static subPortfolio"></ul>
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

var classPortfolio= function() {
	this.areas = new Array();
	this.works = new Array();
	this.skills = new Array();
	this.subPortfolios = new Array();
}

var portfolio = new classPortfolio();

$(function() {
	selectInputList("typeId", {}, "유형");
	selectInputList("subPortfolioType", {}, "관련포트폴리오 유형");
	selectInputList("subPortfolio", {}, "관련포트폴리오");
	selectInputList("area", {}, "분야");
	selectInputList("subArea", {}, "세부분야");
	selectInputList("work", {}, "업무");
	selectInputList("subWork", {}, "세부업무");
	selectInputList("Skill", {}, "스킬");
	
	ajax.get("/api/code/Portfolio/type",{},function(list){ 
		selectInputList("typeId", list, "유형");
		selectInputList("subPortfolioType", list, "유형");
	});
	ajax.get("/api/area",{},function(list){ selectInputList("area", list, "분야"); });
	ajax.get("/api/work",{},function(list){ selectInputList("work", list, "업무"); });
	ajax.get("/api/skill",{},function(list){ selectInputList("skill", list, "스킬"); });
	
	$("select[name='typeId']").change(function(){
		var text = $("select[name='typeId'] option:selected").text();
		var value = this.value;
		init();
		$("select[name='typeId']").val(value);
		
		if(text == "경력") {
			$("label#name_label").html("근무부서");
			$("label#place_label").html("회사이름");
			$("label#level_label").html("직책");
			$("label#description_label").html("주요 성과");
			$(".result").hide();
		}else if(text == "프로젝트") {
			$("label#name_label").html("프로젝트이름");
			$("label#level_label").html("공모전/개인프로젝트 크기");
			$("label#description_label").html("주요 수행 내용");
			$(".place").hide();
			$(".level").hide();
			$(".result").hide();
			$(".subPortfolio").hide();
		}else if(text == "학력") {
			$("label#name_label").html("전공/교육과정명");
			$("label#place_label").html("학교명");
			$("label#level_label").html("학교분류");
			$("label#result_label").html("주요 교과목 학점 및 성과");
			$("label#description_label").html("주요 교과목 공부 내용");
		}else if(text == "자격증") {
			$("label#name_label").html("자격증 이름");
			$("label#place_label").html("발급기관");
			$("label#level_label").html("자격증 분류");
			$(".result").hide();
			$(".description").hide();
			$(".subPortfolio").hide();
		}else if(text == "대회") {
			$("label#name_label").html("대회 이름");
			$("label#place_label").html("주최기관");
			$("label#level_label").html("대회 크기");
			$("label#result_label").html("수상 결과");
			$("label#description_label").html("대회 설명");
		}
	})
	
	$("select[name='subPortfolioType']").change(function(){
		if(this.value != "") ajax.get("/api/portfolio/typeId/" + this.value, {}, function(list) {
			selectInputList('subPortfolio', list, "관련 포트폴리오");
		});
	});
	
	$("select[name='area']").change(function(){
		if(this.value != "") ajax.get("/api/area/parentId/" + this.value, {}, function(list) {
			selectInputList('subArea', list, "세부분야");
		});
	});
	
	$("select[name='work']").change(function(){
		if(this.value != "") ajax.get("/api/work/parentId/" + this.value, {}, function(list) {
			selectInputList('subWork', list, "세부업무");
		});
	});
	
	$("select[name='subPortfolio']").change(function() { addSelectValue(this, "subPortfolio", portfolio.subPortfolios); });
	$("select[name='subArea']").change(function(){ addSelectValue(this, "area", portfolio.areas); });
	$("select[name='subWork']").change(function() { addSelectValue(this, "work", portfolio.works); });
	$("select[name='skill']").change(function() { addSelectValue(this, "skill", portfolio.skills); });
	
});

function init() {
	$("form").each(function() {  
        this.reset();  
     });
	
	$(".place").show();
	$(".level").show();
	$(".result").show();
	$(".date").show();
	$(".description").show();
	$(".subPortfolio").show();
}

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
		if(!(name == "typeId" || name == "subPortfolioType"))
			option.html(node.name).val(node.id);
		else
			option.html(node.value).val(node.id);
		select.append(option);
	});
}

function htmlBadge(text, value) {
	$removeIcon = $("<span>").addClass("glyphicon glyphicon-remove");
	return $("<span>").addClass("badge").append(text + "&nbsp;").append($removeIcon);
}

function request(form) {
	ajax.submit(form, portfolio, function(result) {
		if(result!= null) alert("새로운 포트폴리오 생성하였습니다.");
		else alert(data.mssege);
	});
	return false;
}



</script>


