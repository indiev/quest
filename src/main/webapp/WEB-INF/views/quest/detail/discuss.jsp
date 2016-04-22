<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="quest-detail">
	<div>협의</div>
	<div>기간</div>
	<div>금액?</div>
	<div>위약금 관련</div>
	<div>
		수행해야 할 일(테이블로)
		<ul class="requirements"></ul>
	</div>
	<div>협의 관련 게시판 또는 채팅 ajax로 가져오기. progress에서도 활용</div>
	<button type="button" class="btn btn-default">협의완료</button>
</div>

<script type="text/javascript">
function detail(id) {
	ajax.get("/api/quest/"+id, {}, function(quest){
		for(i in quest.requirements) {
			$list = $("<li>").html(quest.requirements[i].name + " / " + quest.requirements[i].description);
			$("div.quest-detail").find("ul.requirements").append($list);			
		}
	});
}

$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	detail(id);
});
</script>