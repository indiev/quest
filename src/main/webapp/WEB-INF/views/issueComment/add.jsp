<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<form action="/api/issueComments" method="post" name="issueCommentForm" onsubmit="return add(this);">
	<input type="hidden" name="issueId" id="issueId" data-value="id">
	<div class="input-group">
		<textarea name="name" rows="3" class="form-control" style="resize:none;" placeholder="내용"></textarea>
		<span class="input-group-addon btn btn-primary">Send</span>
	</div>
</form>

<script type="text/javascript">
function add(form) {
	ajax.submit(form, {}, function(result) {
		if(result != "") alert("Issue를 추가했습니다.");
		else alert("추가할 수 없습니다.");
	});
	return false;
}
</script>
