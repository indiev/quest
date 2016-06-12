<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="issueComment-content">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>index</th>
				<th>title</th>
				<th>author</th>
				<th>created</th>
			</tr>
		</thead>
		<tbody>
			<tr data-value="id" data-format="link" data-format-target="value">
				<td data-content="id"></td>
				<td data-content="name" data-value="id" onclick="return detail(this)" data-toggle="modal" data-target="#modal"></td>
				<td data-content-prepend="user.realname">(<span data-content="user.name"></span>)</td>
				<td data-content="createdDate" data-format="date"></td>
			</tr>
		</tbody>
	</table>
</div>

<script type="text/javascript">
$(function() {
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
        link: function (value) { return "/issueComment/" + value; },
    });
	
	ajax.get("/api/issue", {}, function(list){
		$template = $("div.issueComment-content table tbody");
		$template.loadTemplate($template.children(), list);
	});
});
function detail(elem) {
	var value = $(elem).attr("value");
	$modal = $("body div.modal");
	$modal.find("div.modal-title").html("이슈 등록");
	ajax.get("/api/issueComment/" + value, {}, function(issue) {
		$modal.find("div.modal-body").loadTemplate("/issueComment/" + value, issue);
	});
}
</script>