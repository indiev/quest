<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
 퀘스트 보고 이슈 승인 테스트

<select name="issue" id="issuedId" class="form-control" ></select>


<script type="text/javascript">
$(document).ready(function() {
	ajax.get("/api/issue/", {}, function(list) {
		selectInputList('issue', list, '이슈');
	})
	
	$("select[name='issue']").change(function(){approveReport(this.value);});
});

function approveReport(issueId) {
	ajax.put("/api/issue/approveReport/"+issueId, {} ,function(result){
		console.log(result);
	});
	return false;
}


function selectInputList(name, list, defaultText) {
	var select = $("select[name='" + name + "']");
	select.empty();
	select.append($("<option>").html(defaultText).val(null));
	$.each(list, function(i, node) {
		option = $("<option>");
		option.html("이슈Id"+node.id+" /타입:"+node.type.name+"/ 요구사항:"+stringToList(node.requirements)).val(node.id).attr("id", this.value);
		select.append(option);
	});
}

function stringToList(list) {
	var result = "";
	for ( var index in list) {
		console.log(list[index]);
		result += list[index].name+","; 
	}
	return result;
}
</script>