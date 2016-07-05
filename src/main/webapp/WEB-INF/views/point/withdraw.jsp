<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
예치취소 테스트

<select name="reward" id="rewardId" class="form-control" ></select>


<script type="text/javascript">
$(document).ready(function() {
	ajax.get("/api/reward", {}, function(list) {
		console.log(list);
		selectInputList('reward', list, '보상');
	})
	
	$("select[name='reward']").change(function(){withdraw(this.value);});
});

function withdraw(rewardId) {
	ajax.put("/api/point/withdraw/"+rewardId, {} ,function(result){
		if(result!= "") {
			alert("해당 보상으로 포인트를 예치취소 하였습니다.");
		}
		else alert("포인트를 예치취소를 할 수 없습니다.");
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