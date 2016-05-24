<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>


<div class ="mainWrap center-block">
<form role="form" action="/api/portfolio" method="POST" onsubmit="return request(this);">
	<div class="container-pluid">
		
		<div class="form-gorup">
			<label for="type">0.유형(type)</label>
			<select name="typeId" id="typeId" class="form-control" ></select>
		</div>
	
		<div class="form-gorup">
			<label for="name">1.타이틀</label> 
			<input type="text" name="name" id="name" class="form-control" placeholder="타이틀">
		</div>
		
		<div class="form-gorup">
			<label for="target">2.목적(target)</label> 
			<input type="text" name="target" id="target" class="form-control" placeholder="개인프로젝트, 00공모전">
		</div>
		
		<div class="form-gorup">
			<label for="startDate">3.기간</label> 
			<div>
			시작일:<input type="date" id="startDate" name="startDate">
			~
			종료일:<input type="date" id="endDate" name="endDate">
			</div>
		</div>
		
		<div class="form-gorup">
			<label for="name">4.설명(Description)</label> 
			<textarea name="description" id="description" class="form-control" placeholder="학교 졸업 프로젝트로 개발하게된  일정관리 웹사이트 였습니다." rows="5"></textarea>
		</div>
		
		<div class="form-group">
			<label>5.분야</label> 
			<div class="row">
				<div class="col-md-5"><select name="area" id="area" class="form-control" ></select></div>
				<div class="col-md-5"><select name="subArea" id="subArea" class="form-control" ></select></div>
				<div class="col-md-2"><button type="button" id="addAreaBtn" class="btn btn-success">분야 추가</button></div>
			</div>
		</div>
		
		<ul class="list-inline form-control-static area">
			<li><span class="badge">분야 > 세부분야 <span class="glyphicon glyphicon-remove"></span></span></li>
			<li><span class="badge">세부분야 <span class="glyphicon glyphicon-remove"></span></span></li>
		</ul>
		
		<div class="form-group">
			<label>6.업무</label> 
			<div class="row">
				<div class="col-md-5"><select name="work" id="work" class="form-control" ></select></div>
				<div class="col-md-5"><select name="subWork" id="subWork" class="form-control" ></select></div>
				<div class="col-md-2"><button type="button" id="addWorkBtn" class="btn btn-success">업무 추가</button></div>
			</div>
		</div>
		
		<ul class="list-inline form-control-static work">
			<li><span class="badge">업무 > 세부업무<span class="glyphicon glyphicon-remove"></span></span></li>
			<li><span class="badge">세부업무 <span class="glyphicon glyphicon-remove"></span></span></li>
		</ul>
		
		<div class="form-group">
			<label>7.스킬</label> 
			<div class="row">
				<div class="col-md-5"><select name="skill" id="skill" class="form-control" ></select></div>
				<div class="col-md-2"><button type="button" id="addSkillBtn" class="btn btn-success">스킬 추가</button></div>
			</div>
		</div>
		
		<ul class="list-inline form-control-static skill">
			<li><span class="badge">스킬 <span class="glyphicon glyphicon-remove"></span></span></li>
		</ul>
		 
		<input type="submit" class="btn btn-default" value="추가">

	</div>
</form>
</div>


<script type="text/javascript">

var classPortfolio= function() {
	this.areas = new Array();
	this.works = new Array();
	this.skills = new Array();
}

var portfolio = new classPortfolio();

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
	ajax.submit(form, portfolio, function(result) {
		if(result!= null) alert("새로운 포트폴리오 생성하였습니다.");
		else alert(data.mssege);
	});
	return false;
}


$(document).ready(function() {
	
	ajax.get("/api/code/list/model/Portfolio",{},function(list){
		selectInputList("typeId", list, "유형")
	});
	
	ajax.get("/api/area",{},function(list){
		selectInputList("area", list, "분야");
	});
	
	ajax.get("/api/work",{},function(list){
		selectInputList("work", list, "업무");
	});
	
	ajax.get("/api/skill",{},function(list){
		selectInputList("skill", list, "스킬");
	});
	
	
	$("select[name='subArea']").attr("readonly",true);
	
	$("select[name='subWork']").attr("readonly",true);
	
	$("select[name='area']").change(function(){
		if(this.value != "") ajax.get("/api/area/parentId/" + this.value, {}, function(list) {
			selectInputList('subArea', list, "세부분야 없음");
			$("select[name='subArea']").attr("readonly", false);
		});
		else {
			selectInputList('subArea', {}, "세부분야 없음");
			$("select[name='subArea']").attr("readonly", true);
		}
			
	});
	
	$("select[name='work']").change(function(){
		if(this.value != "") ajax.get("/api/work/parentId/" + this.value, {}, function(list) {
			selectInputList('subWork', list, "세부업무 없음");
			$("select[name='subWork']").attr("readonly", false);
		});
		else {
			selectInputList('subWork', {}, "세부분야 없음");
			$("select[name='subWork']").attr("readonly", true);
		}
			
	});
	
	
	$("button#addAreaBtn").click(function(){
		var area = new Object();
		area.id = $("select[name='subArea']").val();
		
		for(i in portfolio.areas) {
			if(portfolio.areas[i].id == area.id) {
				alert("중복");
				return;
			}
		}
		
		portfolio.areas.push(area);
		var text = $("select[name='subArea']").find(":selected").html();
		
		$("ul.area").append(function() {
			$node = htmlBadge(text, area.id);
			return $("<li>").append($node);
		});
	});
	
	$("button#addWorkBtn").click(function(){
		var work = new Object();
		work.id = $("select[name='subWork']").val();
		
		for(i in portfolio.works) {
			if(portfolio.works[i].id == work.id) {
				alert("중복");
				return;
			}
		}
		
		portfolio.works.push(work);
		var text = $("select[name='subWork']").find(":selected").html();
		
		$("ul.work").append(function() {
			$node = htmlBadge(text, work.id);
			return $("<li>").append($node);
		});
	});
	
	$("button#addSkillBtn").click(function(){
		var skill = new Object();
		skill.id = $("select[name='skill']").val();
		
		for(i in portfolio.skills) {
			if(portfolio.skills[i].id == skill.id) {
				alert("중복");
				return;
			}
		}
		
		portfolio.skills.push(skill);
		var text = $("select[name='skill']").find(":selected").html();
		
		$("ul.skill").append(function() {
			$node = htmlBadge(text, skill.id);
			return $("<li>").append($node);
		});
	});
	
});

</script>


