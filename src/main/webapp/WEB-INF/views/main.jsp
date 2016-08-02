<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="poom">
<title>Quest</title>
<link rel="icon" href="/resources/images/favicon.ico">
<link rel="stylesheet" href="/resources/css/normalize.css">
<link rel="stylesheet" href="/resources/assets/lib/bootstrap/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="/resources/assets/lib/bootflat/css/bootflat.min.css">  --><!-- 부트스트랩 테마 Bootflat CSS -->
<link rel="stylesheet" href="/resources/css/custom.css">
</head>
<body>
<header class="sidebar-top"></header>
<div class="container-fluid">
	<main class="row"></main>
</div>
<!-- <footer class="footer navbar-fixed-bottom"></footer> -->
<div class="modal fade" id="modal" role="dialog" aria-labelledby="modalHeader" aria-hidden="true" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="close"><span aria-hidden="true">&times;</span></button>
				<div class="modal-title h4" id="modalHeader"></div>
			</div> -->
			<div class="modal-body"></div>
			<!-- <div class="modal-footer"></div> -->
		</div>
	</div>
</div>
<div class="modal fade" id="modal-sm" role="dialog" aria-labelledby="modalsmHeader" aria-hidden="true" tabindex="-1">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<!-- <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="close"><span aria-hidden="true">&times;</span></button>
				<div class="modal-title h4" id="modalsmHeader"></div>
			</div> -->
			<div class="modal-body"></div>
		</div>
	</div>
</div>
<!-- Javascript -->
<script data-main="/resources/js/main" src="/resources/assets/lib/jquery/jquery-2.1.0.min.js"></script>
<script data-main="/resources/js/main" src="/resources/assets/lib/bootstrap/js/bootstrap.min.js"></script>
<script data-main="/resources/js/main" src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script data-main="/resources/js/main" src="/resources/assets/lib/pjax/jquery.pjax.js"></script>
<!-- <script data-main="/resources/js/main" src="/resources/assets/lib/jquery-loadTemplate/jquery.loadTemplate-1.4.4.min.js"></script> -->
<script data-main="/resources/js/main" src="/resources/assets/lib/jquery-loadTemplate/jquery.loadTemplate-1.4.4.js"></script>
<!-- 부트스트랩 테마 Bootflat JS -->
<script src="/resources/assets/lib/bootflat/js/icheck.min.js"></script>
<script src="/resources/assets/lib/bootflat/js/jquery.fs.selecter.min.js"></script>
<script src="/resources/assets/lib/bootflat/js/jquery.fs.stepper.min.js"></script>
<script data-main="/resources/js/main" src="/resources/js/ajax.js"></script>
<script data-main="/resources/js/main" src="/resources/js/validation.js"></script>
<script data-main="/resources/js/main" src="/resources/js/function.js"></script>
<script type="text/javascript">
var user = null;
$(document).ready(function(){
	/* $("header").load("/header"); */
	/* $("footer").load("/footer"); */
	$("main").load("${mainContent}");
	ajax.get("/api/users/me", {}, function(data) { 
		user = data;
		$.addTemplateFormatter({
			userlink: function (value) { return "/user/" + value; },
			questerlink: function (value) { return "/quester/" + value; },
			requesterlink: function (value) { return "/requester/" + value; }
	    });
		$("header").loadTemplate("/header", user);
		/* $("header").loadTemplate($("header").children(), user); */
	});
});
$(document).pjax("a.load", "main");

/*
$(document).on("click", "a", function(){
	if($(this).attr("href") == "/") return ;
	else if($(this).attr("href") != "#") {
		$("#main").load($(this).attr("href"));
		return false;
	} else {
		return true;
	}
});
*/
</script>
</body>
</html>