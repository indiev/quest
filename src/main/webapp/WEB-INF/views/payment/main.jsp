<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div><h3>재정 현황</h3></div>
현재 포인트 : 1000 p
<hr>
<div><h3>포인트 결제 하기</h3></div>
<div>
	<label for="paymentType">결제수단 선택</label>
	<select name="paymentType" class="paymentType">
		<option>신용 카드</option>
		<option>페이팔</option>
		<option>스크릴</option>
	</select>
	
	<label for="paymentType">입금 금액</label>
	<input type="number">
	
</div>

<script type="text/javascript">
$(document).ready(function() {
	
});

</script>