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
		this.perform_areas = new Array();
		this.perform_works = new Array();
		this.perform_skills = new Array();
	}

	function checkDuplication(list, sublist) {
		for( i in list) {
			var isDuplication = false;
			
			for(j in sublist) {
				if(this.sublist[j].name == list[i].name) {
					this.sublist[k].count++;
					isDuplication = true;
					break;
				}	
			}
			if(isDuplication == false) {
				newElement = new classElement(list[i].areas[j].name, 1);
				this.sublist.push(newElement);
			}
		}
	}
	
	function init(categories) {
		var sublist = new Array();
		for(i in categories) {
			var category = categories[i].name;
			if(typeof sublist[category] == "undefined") {
				sublist[category] = 0;
			}
			sublist[category]++;
		}
		return sublist;
	}
	
	function babo(categories, performs) {
		$.each(categories, function(i, category) {
			if(typeof performs[category.name] == "undefined") {
				performs[category.name] = 0;
			}
			performs[category.name]++;
		});
	}
	
	var stat = new classElement();
	$.each(list, function(i, quest) {
		babo(quest.areas, stat.perform_areas);
		babo(quest.works, stat.perform_works);
		babo(quest.skills, stat.perform_skills);
	});
	return stat;
}
	

$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1);
	if(id == "" && user != null) id = user.quester.id;
	detail(id);
});

</script>