<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<script data-main="/resources/js/main" src="/resources/js/test.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	/* area 로드. ajax.get()......*/
	ajax.get("/api/area/list", {}, function(areas){
		test.list(areas);
	});
});

</script>

