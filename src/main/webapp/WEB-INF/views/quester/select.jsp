<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<script type="text/javascript">
var user = null;

$(document).ready(function() {
	
	/* 유저  현재 메인 퀘스터 확인 */
	$(".mainQuesterInfo").load("/quester/getMainQuester");
	
	
	/* 퀘스터  리스트  */
	ajax.get("/api/quester/list/user", {}, function(list){
		for(i in list) {
			$quester = $("<a>").attr("role", "menuItem").attr("id", list[i].id).html(list[i].name);
			$quester.click(function() { selectMainQuester(this.id); });
			$list = $("<li>").attr("role", "presentation").append($quester);
			$("#questerList").find(".dropdown-menu").append($list);
		}
	});
});



function selectMainQuester(selectedId) {
	ajax.get("/api/user/get", {}, function(user) {
		ajax.put("/api/user/"+user.id+"/mainQuester/"+selectedId, {}, function(result){
			alert("메인 퀘스터가 변경되었습니다.");
			$(".mainQuesterInfo").load("/quester/getMainQuester");
		});
	});
}
</script>

현재 메인퀘스터.
<div class="mainQuesterInfo"></div>


메인퀘스터 선택.


<div id=currentMainQuesterName>
</div>

<div class="dropdown" id=questerList>
  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
    Dropdown
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
  </ul>
</div>