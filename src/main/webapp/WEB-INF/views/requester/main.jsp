<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>

<script type="text/javascript">
$(document).ready(function() {
	getMainRequesterDetail();
});

function getMainRequesterDetail() {
	ajax.get("/api/requester/ref/user/"+user.id, {}, function(requester) {
		//location.replace("/requester/"+requester[0].id);
		console.log(requester);
	});
}

</script>