<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<script type="text/javascript">
function addQuester(form) {
	ajax.submit(form, function(data) {
		console.log(data);
		if(data != null) {
			alert("ìë¡ì´ íì¤í°ë¥¼ ìì±íììµëë¤.");
			location.href = '/';
		}
		else {
			alert(data.mssege);
		}
	});
	return false;
}
</script>



<form role="form" action="/api/quester" method="POST" onsubmit="return addQuester(this);">
	<div class="container-pluid">
		
		<div class="form-group">
			<label for="realname" class="sr-only">ëë¤ì</label>
			<input type="text" id="name" name="name" class="form-control" placeholder="ì´ë¦">
		</div>
		
		<input type="submit" id="addQuester_submit" name="addQuester_submit" class="btn btn-default center-block" value="ìë¡ì´ íì¤í° ìì±">
	</div>
</form>