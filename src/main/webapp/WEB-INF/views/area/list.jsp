<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="area-content list-group"></div>

<script type="text/javascript">

$(document).ready(function() {
	/* area 로드. ajax.get()......*/
	ajax.get("/api/area/list", {}, function(list){
		$.get("/area/node/list", function(areaNode){
			for(i in list) {
				var $areaNodeClone = $(areaNode).clone();
				
				$areaNodeClone.find(".name").html(list[i].name);
				$areaNodeClone.attr("name", list[i].name);
				$areaNodeClone.on("mouseover",function(){this.style.backgroundColor ="#eee"; });
				$areaNodeClone.on("mouseout",function(){this.style.backgroundColor ="#fff"; });
				$areaNodeClone.click(function() {
					console.log('ss');
				});
				$("div.area-content").append($areaNodeClone);
			
			}
		});
		
		
	});
});
</script>

