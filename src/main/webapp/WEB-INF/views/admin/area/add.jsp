<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<form role="form" action="/api/quester" method="POST" onsubmit="return addQuester(this);">
				<div class="form-group">
					<label for="name">이름</label>
					<input type="text" id="name" name="name" class="form-control" placeholder="분야명">
				</div>
			</form>
		</div>
		
		<div class="form-group">	
		<label for="area">분야</label>
		<div class="dropdown" id=areaList>
		  <button class="btn btn-default dropdown-toggle" type="button" id="areaMenu" data-toggle="dropdown" aria-expanded="true">
		    상위분야
		    <span class="caret"></span>
		  </button>
		  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
		  </ul>
		</div>
		
	</div>
		
		
	</div>
</div>

