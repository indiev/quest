<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<form role="form" action="/api/quester" method="POST" onsubmit="return request(this);">
	<div class="container-pluid">
		<div class="form-gorup">
			<label for="name">1.닉네임</label> 
			<input type="text" name="name" id="name" class="form-control" placeholder="닉네임">
		</div>
		
		<div class="form-group">
			<label>2.분야</label> 
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
			<label>3.업무</label> 
			<div class="row">
				<div class="col-md-5"><select name="work" id="work" class="form-control" ></select></div>
				<div class="col-md-5"><select name="subWork" id="subWork" class="form-control" ></select></div>
				<div class="col-md-2"><button type="button" id="addWorkBtn" class="btn btn-success">업무 추가</button></div>
			</div>
		</div>
		
		<div class="form-group">
			<label>3.스킬</label> 
			<div class="row">
				<div class="col-md-5"><select name="skill" id="skill" class="form-control" ></select></div>
				<div class="col-md-2"><button type="button" id="addWorkBtn" class="btn btn-success">스킬 추가</button></div>
			</div>
		</div>
		 
		<input type="submit" class="btn btn-default" value="추가">
		
		1.닉네임 2.분야3.업무4.기술 5.자기소개

	</div>
</form>



<script type="text/javascript">

var classQuester = function() {
	this.areas = new Array();
	this.works = new Array();
	this.skills = new Array();
}

var quester = new classQuester();

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
		if(result!= null) alert("퀘스트를 올렸습니다.");
		else alert(data.mssege);
	});
	return false;
}


$(document).ready(function() {
	var area = 	new Object();
	area.id=1;
	area.parentId=4
	
	var work = 	new Object();
	work.id=7;
	work.parentId=4
	
	quester.areas.push(area);
	quester.works.push(work);
	
	ajax.get("/api/area/list",{},function(list){
		selectInputList("area", list, "분야");
	});
	
	$("select[name='subArea']").attr("readonly",true);
	
	$("select[name='area']").change(function(){
		if(this.value != "") ajax.get("/api/area/list/parent/" + this.value, {}, function(list) {
			selectInputList('subArea', list, "세부분야 없음");
			$("select[name='subArea']").attr("readonly", false);
		});
		else {
			selectInputList('subArea', {}, "세부분야 없음");
			$("select[name='subArea']").attr("readonly", true);
		}
			
	});
	
	
	$("button#addAreaBtn").click(function(){
		/*
		var area = new Object();
		//console.log($("select[name='area']").val());
		//console.log($("select[name='area'] option:selected").text());
		//console.log($("select[name='subArea']").val());
		//console.log($("select[name='subArea'] option:selected").text());
		area.id = $("select[name='subArea']").val();
		area.parentId = $("select[name='area']").val();
		
		areaIds.add(area);
		areaIds.forEach(function(item){
			console.log('id: '+ item);
		});
		*/
	
		var text = $("select[name='subArea']").find(":selected").html();
		var value = $("select[name='subArea']").val();
		
		$("ul.area").append(function(){
			$node = htmlBadge(text, value);
			return $("<li>").append($node);
		});

	
	});
	
	
	
	
	
	
});


</script>


