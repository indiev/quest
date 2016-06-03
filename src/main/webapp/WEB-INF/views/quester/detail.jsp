<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div><h3>퀘스터 기본 정보</h3></div>
<div class="quester-detail"></div>
<hr>
	<div><h3>참가한 전체 퀘스트 정보 관련 통계</h3></div>
	<div>평균 종합 만족도:4.6/5</div>
	<div>수행한 퀘스트 수:3</div>
	<div>퀘스트에서 습득한 보상 금액:1,500,000 원</div>	
	<div>수행한 분야 순위 차트</div>
	<div>수행한 업무 순위 차트</div>
	<div>수행한 스킬 순위 차트</div>
<hr>
	<div><h3>완료한 퀘스트 리스트</h3></div>
<div class="quest-content"></div>

<script type="text/javascript">

function detail(id) {
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
		link: function (value) { return "/quester/" + value; }
    });
	
	ajax.get("/api/quester/"+id, {}, function(quester){
		$("div.quester-detail").loadTemplate("/quester/node/detail", quester);
		ajax.get("/api/quest/all/search", {}, function(list){
			$.addTemplateFormatter({
				date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
				link: function (value) { return "/quest/" + value; }
		    });
			
			$("div.quest-content").loadTemplate("/quest/node/list", list);
			
			console.log(list);
			// 순위 차트 관련
			var perform_areas = new Array();
			var perform_works = new Array();
			var perform_skills = new Array();
			for ( i in list) {
				for( j in list[i].areas) {
					perform_areas.push(list[i].areas[j])
				}
				for( j in list[i].works) {
					perform_works.push(list[i].works[j])
				}
				
				for( j in list[i].skills) {
					perform_skills.push(list[i].skills[j])
				}
			}
			
			console.log(perform_areas);
			console.log(perform_works);
			console.log(perform_skills);
			
		});
	});
			
}
$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	detail(id);
});
</script>