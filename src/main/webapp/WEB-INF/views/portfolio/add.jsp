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
<form role="form" action="/api/portfolios" method="POST" onsubmit="return request(this);">
	<div class="container-pluid">
		<div class="form-group typeId">
			<label id="typeId_label" for="typeId" class="sr-only">type</label>
			<select name="typeId" id="typeId" class="form-control" ></select>
		</div>
		<div class="form-group name">
			<label id="name_label" for="name" class="sr-only">name</label> 
			<input type="text" name="name" id="name" class="form-control" placeholder="name">
		</div>
		<div class="form-group area">
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
		<div class="form-group work">
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
		<div class="form-group row skill">
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
		
		<div class="form-group row date">
			<label for="startDate" class="sr-only">date</label> 
			<div>
			시작일:<input type="date" id="startDate" name="startDate">
			~
			종료일:<input type="date" id="endDate" name="endDate">
			</div>
		</div>
		<div class="form-group place">
			<label id="place_label" for="place" class="sr-only">place</label> 
			<input type="text" name="place" id="place" class="form-control" placeholder="place">
		</div>
		<div class="form-group level">
			<label id="level_label" for="level" class="sr-only">level</label>
			<input type="text" name="level" id="level" class="form-control" placeholder="level">
		</div>
		<div class="form-group result">
			<label id="result_label" for="result" class="sr-only">result</label>
			<input type="text" name="result" id="result" class="form-control" placeholder="result">
		</div>
		
		<div class="form-group description">
			<label id="description_label" for="description" class="sr-only">description</label> 
			<textarea name="description" id="description" class="form-control" placeholder="description" rows="5"></textarea>
		</div>
		
		<div class="form-group subPortfolio">
			<div class="row">
				<div class="col-md-12">
					<div class="input-group">
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-briefcase"></span>
						</span>
						<label for="subPortfolioType" class="sr-only">서브 포트폴리오 타입</label>
						<select name="subPortfolioType" id="subPortfolioType" class="form-control"></select>
						<span class="input-group-btn" style="width:0px;"></span>
						<label for="subPortfolio" class="sr-only">서브 포트폴리오</label>
						<select name="subPortfolio" id="subPortfolio" class="form-control"></select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<button type="button" class="btn btn-default form-control subPortfolio" onclick="return false;"></button>
				</div>
			</div>
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
	
	ajax.get("/api/codes/",{'model':'PortFolio','attribute':'type'},function(list){ 
		selectInputList("typeId", list, "유형");
		selectInputList("subPortfolioType", list, "유형");
	});
	ajax.get("/api/areas/parents",{},function(list){ selectInputList("area", list, "분야"); });
	ajax.get("/api/works/parents",{},function(list){ selectInputList("work", list, "업무"); });
	ajax.get("/api/skills",{},function(list){ selectInputList("skill", list, "스킬"); });
	
	hideElem();
	
	$("select[name='typeId']").change(function(){
		var typeValue = $('select#typeId option:selected').attr("id");
		var value = this.value;
		init();
		$("select[name='typeId']").val(value);
		
		if(typeValue == "carrer") {
			$("input#name").attr("placeholder", "근무부서");
			$("input#place").attr("placeholder", "회사이름");
			$("input#level").attr("placeholder", "직책");
			$("textarea#description").attr("placeholder", "주요 성과");
			$(".result").hide();
		}else if(typeValue == "project") {
			$("input#name").attr("placeholder", "프로젝트이름");
			$("input#level").attr("placeholder", "공모전/개인프로젝트 크기");
			$("textarea#description").attr("placeholder", "주요 수행 내용");
			$(".place").hide();
			$(".level").hide();
			$(".result").hide();
			$(".form-group.subPortfolio").hide();
		}else if(typeValue == "education") {
			$("input#name").attr("placeholder", "전공/교육과정명");
			$("input#place").attr("placeholder", "학교명");
			$("input#level").attr("placeholder", "학교분류");
			$("input#result").attr("placeholder", "주요 교과목 학점 및 성과");
			$("textarea#description").attr("placeholder", "주요 교과목 공부 내용");
		}else if(typeValue == "certificate") {
			$("input#name").attr("placeholder", "자격증 이름");
			$("input#place").attr("placeholder", "발급기관");
			$("input#level").attr("placeholder", "자격증 분류");
			$(".result").hide();
			$(".description").hide();
			$(".form-group.subPortfolio").hide();
		}else if(typeValue == "contest") {
			$("input#name").attr("placeholder", "대회 이름");
			$("input#place").attr("placeholder", "주최기관");
			$("input#level").attr("placeholder", "대회 크기");
			$("input#result").attr("placeholder", "수상 결과");
			$("textarea#description").attr("placeholder", "대회 설명");
		}else {
			hideElem();
		}
	})
	
	$("select[name='subPortfolioType']").change(function(){
		if(this.value != "") ajax.get("/api/portfolios/users/me", {'type':this.value}, function(list) {
			selectInputList('subPortfolio', list, "관련 포트폴리오");
		});
	});
	
	$("select[name='area']").change(function(){
		if(this.value != "") ajax.get("/api/areas/parents/" + this.value, {}, function(list) {
			selectInputList('subArea', list, "세부분야");
		});
	});
	
	$("select[name='work']").change(function(){
		if(this.value != "") ajax.get("/api/work/parents/" + this.value, {}, function(list) {
			selectInputList('subWork', list, "세부업무");
		});
	});
	
	$("select[name='subPortfolio']").change(function() { addSelectValue(this, "subPortfolio", portfolio.subPortfolios); });
	$("select[name='subArea']").change(function(){ addSelectValue(this, "area", portfolio.areas); });
	$("select[name='subWork']").change(function() { addSelectValue(this, "work", portfolio.works); });
	$("select[name='skill']").change(function() { addSelectValue(this, "skill", portfolio.skills); });
	
});

function hideElem() {
	$(".form-group").each(function(i,elem) { if(i!=0) $(elem).hide(); });
	$("input[type='submit']").hide();
}

function showElem() {
	$(".form-group").each(function(i,elem) { if(i!=0) $(elem).show(); });
	$("input[type='submit']").show();
}

function init() {
	$("form").each(function() {  
        this.reset();  
     });
	showElem();
}

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
		if(!(name == "typeId" || name == "subPortfolioType"))
			option.html(node.name).val(node.id);
		else
			option.html(node.name).val(node.id).attr("id", this.value);
		select.append(option);
	});
}

function htmlBadge(text, value, category) {
	$removeIcon = $("<span>").addClass("glyphicon glyphicon-remove").css("cursor", "pointer");
	return $("<span>").addClass("badge " + category).append(text + "&nbsp;").append($removeIcon);
}

function request(form) {
	ajax.submit(form, portfolio, function(result) {
		if(result!= null) alert("새로운 포트폴리오 생성하였습니다.");
		else alert(data.mssege);
	});
	return false;
}



</script>


