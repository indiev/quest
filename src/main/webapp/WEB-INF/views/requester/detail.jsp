<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div><h3>리퀘스터 기본 정보</h3></div>
<div class="requester-detail"></div>
<hr>
<div><h3>발주한 전체 퀘스트 정보 관련 통계</h3></div>
<div class="requester-questTotalStat-detail"></div>	
<hr>
<div><h3>발주한 퀘스트 리스트</h3></div>
<div class="request-content"></div>

<script type="text/javascript">


function detail(id) {
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
		link: function (value) { return "/quester/" + value; }
    });
	
	ajax.get("/api/requester/"+id, {}, function(requester){
		$("div.requester-detail").loadTemplate("/requester/node/detail", requester);
		console.log(requester);
		ajax.get("/api/quest/all/search", {}, function(list){
			$.addTemplateFormatter({
				date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
				link: function (value) { return "/quest/" + value; }
		    });
			
			$("div.request-content").loadTemplate("/quest/node/list", list);
			var stat = new classQuestTotalStat(list);
			console.log(stat);
			
			
			
			$("div.requester-questTotalStat-detail").loadTemplate("/requester/node/questTotalStat", stat);
			
		});
	});
			
}

function classQuestTotalStat(list) {
	
	
	function classElement() {
		this.perform_areas = new Object();
		this.perform_works = new Object();
		this.perform_skills = new Object();
	}
	
	function init(categories, performs) {
		$.each(categories, function(i, category) {
			if(typeof performs[category.name] == "undefined") {
				performs[category.name] = 0;
			}
			performs[category.name]++;
		});
	}
	
	var stat = new classElement();
	$.each(list, function(i, quest) {
		init(quest.areas, stat.perform_areas);
		init(quest.works, stat.perform_works);
		init(quest.skills, stat.perform_skills);
	});
	
	
	var areas = [];
	$.each(Object.keys(stat['perform_areas']), function(i, element){
		console.log(element +":" + stat['perform_areas'][element]);
		areas.push('k');
	});
	console.log(areas);
}
	

$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1);
	if(id == "" && user != null) id = user.quester.id;
	detail(id);
	
});

</script>