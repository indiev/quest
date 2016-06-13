<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="issue-content">
	<div>
		<form class="form-inline" name="questSearchForm" role="form" action="/api/quest/all/search/" method="GET" onsubmit="return list();">
	 		<div class="input-group form-search">
	 			<label for="searchKeyword" class="sr-only">검색</label>
	 			<input type="text" name="searchKeyword" id="searchKeyword" class="form-control input-sm search-query" placeholder="검색" />
	 			<span class="input-group-btn">
	 				<button class="btn btn-default btn-sm" type="button" onclick="list()"><span class="glyphicon-search glyphicon" aria-hidden="true"></span></button>
	 				<button type="button" class="btn btn-default btn-sm" name="addButton" data-toggle="modal" data-target="#modal"><span class="glyphicon-plus glyphicon" aria-hidden="true"></span></button>
	 			</span>
	 		</div>
 		</form>
	 </div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>index</th>
				<th>title</th>
				<th>author</th>
				<th>type</th>
				<th>created</th>
			</tr>
		</thead>
		<tbody>
			<tr data-value="id" data-format="link" data-format-target="value">
				<td data-content="id"></td>
				<td data-content="name" data-value="id" onclick="return detail(this)" data-toggle="modal" data-target="#modal"></td>
				<td data-content-prepend="user.realname">(<span data-content="user.name"></span>)</td>
				<td data-content-prepend="type.name">(<span data-content="type.value"></span>)</td>
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
	$("button[name='addButton']").click(function(){
		$("#modal").find("div.modal-body").load("/issue/add");
	});
});
function detail(elem) {
	var value = $(elem).attr("value");
	$modal = $("body div.modal");
	$modal.find("div.modal-title").html("이슈 등록");
	ajax.get("/api/issue/" + value, {}, function(issue) {
		$modal.find("div.modal-body").loadTemplate("/issue/" + value, issue);
	});
}
</script>