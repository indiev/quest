<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<form role="form" action="/api/payments/buy" method="PUT" onsubmit="return buy(this);">
	<div class="container-pluid">
		<div class="form-gorup">
			<input type="number" name="point" id="point" class="form-control" placeholder="결제 포인트양">
		</div>
		 
		<input type="submit" class="btn btn-default" value="포인트  결제">
		
	</div>
</form>
<script type="text/javascript">
function buy(form) {
	ajax.submit(form, {} ,function(result){
		if(result!= null) {
			console.log(result);
			alert("새로운  포인트를 구매 하였습니다..");
		}
		else alert(data.mssege);
	});
	return false;
}
</script>