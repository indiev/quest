<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div data-content="name"></div>
<div data-content="user.realname"></div>
<div data-content="description"></div>
<div data-list="requirements">
	<span data-content="name"></span>/
	<span data-content="description"></span>
</div>
<input type="hidden" name="issueId" data-value="id">
<div class="issueCommentForm"></div>
<div class="issueCommentList" data-exist="comments">
</div>

<script type="text/javascript">
$(document).ready(function() {
	var issueId = $("input[name='issueId']").val();
	$("div.issueCommentForm").loadTemplate("/issueComment/add", {"id":issueId});
	ajax.get("/api/issueComment/ref/issue/" + issueId, {}, function(list) {
		console.log(list);
		$("div.issueCommentList").loadTemplate("/issueComment/node/list", list);
	});
});
</script>