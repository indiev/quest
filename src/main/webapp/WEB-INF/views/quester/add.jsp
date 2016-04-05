<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<script type="text/javascript">
function addQuester(form) {
	ajax.submit(form, function(data) {
		console.log(data);
		if(data != null) {
			alert("새로운 퀘스터를 생성하였습니다.");
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
	
	<div class="form-group">
		<label for="name">닉네임</label>
		<input type="text" id="name" name="name" class="form-control" placeholder="닉네임">
	</div>
	
	<div class="form-group">
		<label for="area">관심분야</label>
		
	</div>
	
	<div class="form-group">
		<label for="name">스킬</label>
		<input type="text" id="name" name="area" class="form-control" placeholder="관심분야">
	</div>
	
	<input type="submit" id="addQuester_submit" name="addQuester_submit" class="btn btn-default center-block" value="새 퀘스터 생성">
</form>

<div class="dropdown">
  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
    Dropdown
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Action</a></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another action</a></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Something else here</a></li>
    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Separated link</a></li>
  </ul>
</div>