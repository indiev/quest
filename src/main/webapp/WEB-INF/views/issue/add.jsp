<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="issue-content">
	<form role="form" action="/api/issues" method="post" onsubmit="return add(this);">
		<div class="form-group">
			<label for="name" class="sr-only">종류</label>
			<select name="typeId" id="type" class="form-control">
				<option data-content="value" data-value="id"></option>
			</select>
		</div>
		<div class="form-group">
			<label for="name" class="sr-only">제목</label>
			<input type="text" name="name" id="name" class="form-control" placeholder="제목">
		</div>
		<div class="form-group">
			<label for="description" class="sr-only">내용</label>
			<textarea name="description" id="description" rows="3" class="form-control" style="resize:none;" placeholder="내용"></textarea>
		</div>
		<button type="submit" class="btn btn-default">추가</button>
	</form>
</div>

<script type="text/javascript">
function add(form) {
	ajax.submit(form, {}, function(result) {
		if(result != "") alert("Issue를 추가했습니다.");
		else alert("추가할 수 없습니다.");
	});
	return false;
}
$(document).ready(function() {
	ajax.get("/api/codes", {'model':'issue', 'attribute':'type'}, function(list) {
		$select = $("div.issue-content").find("select[name='typeId']");
		$select.loadTemplate($select.children(), list);
	});
});
</script>
