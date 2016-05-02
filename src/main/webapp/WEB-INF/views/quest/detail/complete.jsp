<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="quest-detail">
	완료 관련 통계 정보 입력<br>
	수행 기간/예상기간<br>
	달성도<br>
	보상 지급<br>
	평가 내용<br>
	업무, 분야, 사용 기술<br>
</div>

<script type="text/javascript">
function detail(id) {
	ajax.get("/api/quest/"+id, {}, function(quest){
	});
}

$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	detail(id);
});
</script>