<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div><h3>퀘스터 기본 정보</h3></div>
<div class="quester-detail"></div>
<hr>
<div><h3>참가한 전체 퀘스트 정보 관련 통계</h3></div>
<div class="container quester-questTotalStat-detail"></div>	
<hr>
<div><h3>준비중인 퀘스트 리스트</h3></div>
<div class="container readyRequest-content"></div>

<div><h3>진행중인 퀘스트 리스트</h3></div>
<div class="container progressRequest-content"></div>

<div><h3>완료된 퀘스트 리스트</h3></div>
<div class="container completeRequest-content"></div>

<script type="text/javascript">


function detail(id) {
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
		link: function (value) { return "/quester/" + value; }
    });
	
	ajax.get("/api/quester/"+id, {}, function(quester){
		$("div.quester-detail").loadTemplate("/quester/node/detail", quester);
		/* 모집전  */		
		ajax.get("/api/quest/quester/state/ready", {}, function(list){
			$.addTemplateFormatter({
				date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
				link: function (value) { return "/quest/" + value; }
		    });
			$("div.readyRequest-content").loadTemplate("/quest/node/list", list);
		});
		
		/* 진행중 */
		ajax.get("/api/quest/quester/stateGroup/ongoing", {}, function(list){
			$.addTemplateFormatter({
				date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
				link: function (value) { return "/quest/" + value; }
		    });
			console.log(list);
			$("div.progressRequest-content").loadTemplate("/quest/node/list", list);
		});
		
		/* 완료중 */
		ajax.get("/api/quest/quester/state/complete", {}, function(list){
			$.addTemplateFormatter({
				date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
				link: function (value) { return "/quest/" + value; }
		    });
			$("div.completeRequest-content").loadTemplate("/quest/node/list", list);
		});
		
		
		ajax.get("/api/quester/user", {}, function(list){
			var stat = new classQuestTotalStat(list);
			$("div.quester-questTotalStat-detail").loadTemplate("/quester/node/questTotalStat", {});
		});
			
	});
			
}

function classQuestTotalStat(list) {
	
	function classElement(name, count) {
		this.name = name;
		this.count= count;
	}
	// 순위 차트 관련
	this.areas = new Array();
	this.works = new Array();
	this.skills = new Array();

	this.perform_areas = new Array();
	this.perform_works = new Array();
	this.perform_skills = new Array();
	
	for ( i in list) {
		for( j in list[i].areas) {
			var isDuplication = false;
			
			for(k in this.perform_areas) {
				if(this.perform_areas[k][0].name == list[i].areas[j].name) {
					this.perform_areas[k].push(list[i].areas[j]);
					isDuplication = true;
					break;
				}	
			}
			if(isDuplication == false) {
				newArray = new Array();
				newArray.push(list[i].areas[j]);
				this.perform_areas.push(newArray);
			}
		}
		
	
		for( j in list[i].works) {
			var isDuplication = false;
			
			for(k in this.perform_works) {
				if(this.perform_works[k][0].name == list[i].works[j].name) {
					this.perform_works[k].push(list[i].works[j]);
					isDuplication = true;
					break;
				}	
			}
			if(isDuplication == false) {
				newArray = new Array();
				newArray.push(list[i].works[j]);
				this.perform_works.push(newArray);
			}
		}
		
		for( j in list[i].skills) {
			var isDuplication = false;
			
			for(k in this.perform_skills) {
				if(this.perform_skills[k][0].name == list[i].skills[j].name) {
					this.perform_skills[k].push(list[i].skills[j]);
					isDuplication = true;
					break;
				}	
			}
			if(isDuplication == false) {
				newArray = new Array();
				newArray.push(list[i].skills[j]);
				this.perform_skills.push(newArray);
			}
		}
	}
	
	//**************************
	for(i in this.perform_areas) {
		var element = new classElement(this.perform_areas[i][0].name, this.perform_areas[i].length);
		this.areas.push(element);
	}
	
	for(i in this.perform_works) {
		var element = new classElement(this.perform_works[i][0].name, this.perform_works[i].length);
		this.works.push(element);
	}
	
	for(i in this.perform_skills) {
		var element = new classElement(this.perform_skills[i][0].name, this.perform_skills[i].length);
		this.skills.push(element);
	}
	
}
	

$(document).ready(function(){
	var id = $(location).attr("href").slice($(location).attr("href").lastIndexOf("/")+1)
	if(id == "" && user != null) id = user.quester.id;
	detail(id);
});

</script>