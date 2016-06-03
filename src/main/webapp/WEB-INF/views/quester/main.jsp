<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>

<script type="text/javascript">
$(document).ready(function() {
	getMainQuesterDetail();
});

function getMainQuesterDetail() {
	ajax.get("/api/quester/user", {}, function(quester) {
		location.replace("/quester/"+quester[0].id);
	});
}

</script>