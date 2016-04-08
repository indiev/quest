<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div>분야추가</div>
			<div>1.상위 분야 선택</div>

			<div class="dropdown" id=areaList>
				<button class="btn btn-default dropdown-toggle" type="button"
					id="areaMenu" data-toggle="modal" data-target="#areaSelectDialog" aria-expanded="true">
					분야선택 <span class="caret"></span>
				</button>
			</div>

			<div>2.분야명</div>

		</div>
	</div>
</div>

<div class="modal fade" id="areaSelectDialog" role="dialog" aria-labelledby="areaSelectHeader" aria-hidden="true" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body"></div>
		</div>
	</div>
</div>


<script type="text/javascript">
$(document).ready(function() {
	$('#areaMenu').click(function(){
		console.log('aaa');
		$("#areaSelectDialog > .modal-dialog").addClass("modal-sm");
		$("#areaSelectDialog").find("div.modal-body").load("/area/list");
	});

});
</script>

