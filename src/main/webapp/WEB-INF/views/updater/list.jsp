<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="updater-content">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>origin</th>
				<th>alternation</th>
				<th>type</th>
				<th>created</th>
				<th>approval</th>
			</tr>
		</thead>
		<tbody>
			<tr data-value="id">
				<td data-content="name" data-value="id"></td>
				<td data-content="alternation"></td>
				<td data-content="type"></td>
				<td data-content="createdDate" data-format="date"></td>
				<td data-content="approval"></td>
			</tr>
		</tbody>
	</table>
</div>
<script type="text/javascript">
$(function() {
	/* 
	
	ajax.get("/api/updater", {}, function(list){
		$template = $("div.updater-content table tbody");
		$template.loadTemplate($template.children(), list);
	}); */
});
</script>