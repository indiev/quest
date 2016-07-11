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
		if(result!= "") {
			alert("해당 보고 이슈를 승인 하였습니다.");
		}
		else alert("해당 보고 이슈를 승인 할 수 없습니다.");
	});
	return false;
}


function selectInputList(name, list, defaultText) {
	var select = $("select[name='" + name + "']");
	select.empty();
	select.append($("<option>").html(defaultText).val(null));
	$.each(list, function(i, node) {
		option = $("<option>");
		option.html("이슈Id"+node.id+" /상태:"+node.type.name+"/ 퀘스트 id:"+node.quest.id).val(node.id).attr("id", this.value);
		select.append(option);
	});
}
</script>