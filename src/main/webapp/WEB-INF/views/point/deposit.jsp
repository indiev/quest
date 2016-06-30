<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
테스트

4번 퀘스트 - 보상: 1,000,000
리퀘스터 1
퀘스터 1,2

<select name="reward" id="rewardId" class="form-control" ></select>


<script type="text/javascript">
$(document).ready(function() {
	ajax.get("/api/reward", {}, function(list) {
		selectInputList('reward', list, '보상');
	})
	
	$("select[name='reward']").change(function(){deposit(this.value);});
});

function deposit(rewardId) {
	ajax.put("/api/point/deposit/"+rewardId, {} ,function(result){
		if(result!= null) {
			console.log(result);
			alert("해당 보상으로 포인트를 예치 하였습니다.");
		}
		else alert(data.mssege);
	});
	return false;
}

function selectInputList(name, list, defaultText) {
	var select = $("select[name='" + name + "']");
	select.empty();
	select.append($("<option>").html(defaultText).val(null));
	$.each(list, function(i, node) {
		option = $("<option>");
		option.html("보상id"+node.id+"금액:"+node.hwan+node.name).val(node.id).attr("id", this.value);
		select.append(option);
	});
}
</script>