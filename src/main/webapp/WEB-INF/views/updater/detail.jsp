<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div data-content="name"></div>
<div data-content="user.realname"></div>
<div data-content="description"></div>
<div data-list="requirements">
	<span data-content="name"></span>/
	<span data-content="description"></span>
</div>
<div>
	<form action="/api/issueComments/" method="post" name="issueCommentForm">
		<div class="input-group">
			<textarea name="name" rows="3" class="form-control" style="resize:none;"></textarea>
			<span data-value="id" class="input-group-addon btn btn-primary" onclick="return submitComment(this);">Send</span>
		</div>
	</form>
</div>
<div data-exist="comments">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>index</th>
				<th>title</th>
				<th>author</th>
				<th>created</th>
			</tr>
		</thead>
		<tbody data-list="comments">
			<tr>
				<td data-content="id"></td>
				<td data-content="name" data-value="id" onclick="return detail(this)" data-toggle="modal" data-target="#modal"></td>
				<td data-content-prepend="user.realname">(<span data-content="user.name"></span>)</td>
				<td data-content="createdDate" data-format="date"></td>
			</tr>
		</tbody>
	</table>
</div>

<script type="text/javascript">
function submitComment(elem) {
	$elem = $(elem);
	$form = $("form[name='issueCommentForm']");
	$form.attr("action", $form.attr("action")+$elem.attr("value"));
	ajax.submit($form.get(), {}, function(data) {
		if(data != "") alert("Comment Write");
		else alert("failed");
	});
}
</script>