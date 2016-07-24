<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
지급 테스트
<br>
4번 퀘스트 - 보상: 1,000,000
<br>
리퀘스터 1
<br>
퀘스터 1,2

<select name="reward" id="rewardId" class="form-control" ></select>


<script type="text/javascript">
$(document).ready(function() {
	ajax.get("/api/rewards", {}, function(list) {
		selectInputList('reward', list, '보상');
	})
	
	$("select[name='reward']").change(function(){give(this.value);});
});

function give(rewardId) {
	ajax.put("/api/points/give/"+rewardId, {} ,function(result){
		if(result!= "") {
			alert("해당 보상으로 포인트를 지급 하였습니다.");
		}
		else alert("포인트를 지급할 수 없습니다.");
	});
	return false;
}

function selectInputList(name, list, defaultText) {
	var select = $("select[name='" + name + "']");
	select.empty();
	select.append($("<option>").html(defaultText).val(null));
	$.each(list, function(i, node) {
		option = $("<option>");
		option.html("보상Id"+node.id+" /상태:"+node.state.name+"/ 포인트:"+node.hwan+node.name).val(node.id).attr("id", this.value);
		select.append(option);
	});
}
</script>