<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<script type="text/javascript">
$(document).ready(function() {
	
	/* area 로드. ajax.get()......*/
	areaList = [];
	areaList.push({name:'IT', id:1});
	areaList.push({name:'디자인', id:2});
	
	
	/* area 로드 이후. */
	for(i in areaList) {
		$area = $("<a>").attr("role","menuItem").attr("id",areaList[i].id).html(areaList[i].name);
		$area.click(function() {searchSkillByArea(this.id); });
		$list = $("<li>").attr("role", "presentation").append($area);
		$("#areaList").find(".dropdown-menu").append($list);
	}
	
});


function searchSkillByArea(id) {
	
	/* skills 로드. ajax.get()...... */
	var skillList = [];
	var skillListByAreaId = [];
	skillList.push({name:'spring', id:1, areaId:1});
	skillList.push({name:'php', id:2, areaId:1});
	skillList.push({name:'캘리그라피', id:3, areaId:2})
	for(i in skillList) {
		if(id == skillList[i].areaId)
		skillListByAreaId.push(skillList[i]);
	}
	
	/* skills 로드 이후. */
	$caret = $("<span>").attr("class","caret");
	$('#areaMenu').html($('#areaList').find("#"+id).html()).append($caret);
	
	for ( i in skillListByAreaId) {
		console.log(skillListByAreaId[i]);
	}
	
}

function addQuester(form) {
	ajax.submit(form, function(data) {
		console.log(data);
		if(data != null) {
			alert("새로운 퀘스터를 생성하였습니다.");
			location.href = '/';
		}
		else {
			alert(data.mssege);
		}
	});
	return false;
}



</script>



<form role="form" action="/api/quester" method="POST" onsubmit="return addQuester(this);">		
	
	<div class="form-group">
		<label for="name">닉네임</label>
		<input type="text" id="name" name="name" class="form-control" placeholder="닉네임">
	</div>
	
	<div class="form-group">
		<label for="area">관심분야</label>
		<div class="dropdown" id=areaList>
		  <button class="btn btn-default dropdown-toggle" type="button" id="areaMenu" data-toggle="dropdown" aria-expanded="true">
		    분야
		    <span class="caret"></span>
		  </button>
		  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
		  </ul>
		</div>
		
	</div>
	
	<div class="form-group">
		<label for="name">스킬</label>
		<input type="text" id="name" name="area" class="form-control" placeholder="관심분야">
	</div>
	
	<input type="submit" id="addQuester_submit" name="addQuester_submit" class="btn btn-default center-block" value="새 퀘스터 생성">
</form>

