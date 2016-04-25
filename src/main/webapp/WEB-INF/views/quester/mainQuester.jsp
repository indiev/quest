<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>

메인퀘스터 정보 화면.<br/>


<div class="name">닉네임:</div>
<div class="areas">분야:</div>
<div class="works">업무:</div>
<div class="skills">스킬:</div>

<script type="text/javascript">
$(document).ready(function() { 

	ajax.get("/api/user/get", {}, function(user) {
		ajax.get("/api/quester/"+user.mainQuester.id,{},function(quester){
			console.log(quester);
			
			$(".name").html($(".name").text() + quester.name);
			
			for(i=0; i<quester.areas.length; i++) {
				console.log(quester.areas[i]);
				$(".areas").html($(".areas").text() + quester.areas[i].name+ ",");
			}
			
			for(i=0; i<quester.works.length; i++) {
				console.log(quester.works[i]);	
				$(".works").html($(".works").text() + quester.works[i].name+ ",");
			}
			
			for(i=0; i<quester.skills.length; i++) {
				$(".skills").html($(".skills").text() + quester.skills[i].name+ ",");
				console.log(quester.skills[i]);	
			}
			
			
		});
		
	});
	
});
</script>