<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>퀘스트</title>
<link rel="stylesheet" href="/resources/css/normalize.css">
<link rel="stylesheet" href="/resources/assets/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/assets/lib/bootflat/css/bootflat.min.css"> <!-- 부트스트랩 테마 Bootflat CSS -->
<link rel="stylesheet" href="/resources/css/custom.css">
</head>
<body>
<!-- navbar-inverse -->
<div class="container-fluid">
	<div id="sidebar-top"></div>
	<div id="main" class="row" style="padding-top:50px;"></div>
</div>

<script data-main="/resources/js/main" src="/resources/assets/lib/jquery/jquery-2.1.0.min.js"></script>
<script data-main="/resources/js/main" src="/resources/assets/lib/bootstrap/js/bootstrap.min.js"></script>
<script data-main="/resources/js/main" src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script data-main="/resources/js/main" src="/resources/assets/lib/pjax/jquery.pjax.js"></script>
<script data-main="/resources/js/main" src="/resources/js/ajax.js"></script>
<script data-main="/resources/js/main" src="/resources/js/function.js"></script>
<!-- 부트스트랩 테마 Bootflat JS -->
<script src="/resources/assets/lib/bootflat/js/icheck.min.js"></script>
<script src="/resources/assets/lib/bootflat/js/jquery.fs.selecter.min.js"></script>
<script src="/resources/assets/lib/bootflat/js/jquery.fs.stepper.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#sidebar-top").load("/sidebar-top");
	$("#main").load("/${mainContent}");
});
$(document).pjax("a", "#main");
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