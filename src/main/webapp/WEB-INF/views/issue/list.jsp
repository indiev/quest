<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="issue-content">
	<table class="table">
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
				<td data-content="name"></td>
				<td data-content="user.realname"></td>
				<td data-content="createdDate" data-format="date"></td>
			</tr>
		</tbody>
	</table>
</div>
<script type="text/javascript">
$(function() {
	$.addTemplateFormatter({
		date: function (value) { return $.datepicker.formatDate("yy년 mm월 dd일", new Date(value)); },
        link: function (value) { return "/issue/" + value; },
    });
	
	ajax.get("/api/issue", {}, function(list){
		$template = $("div.issue-content table tbody");
		$template.loadTemplate($template.children(), list);
	});
});
</script>