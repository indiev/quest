<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<nav id="sidebar-top" class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#sidebar-top .navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
       	<a class="navbar-brand" href="/"><img src="/resources/images/quest_w.png" style="height:30px;"></a>
	</div>
	<div class="collapse navbar-collapse">
		<form class="navbar-form navbar-left" role="search">
	       	<div class="input-group">
	       		<label for="search" class="sr-only">검색</label>
	       		<input type="text" class="form-control input-sm" id="search" name="search" placeholder="검색" style="width:230px;"/>
	       		<span class="input-group-btn">
					<button class="btn btn-default btn-sm" type="button">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					</button>
				</span>
	       	</div>
		</form>
		<ul class="nav navbar-nav">
			<li><a href="/quest/add" class="laod"><strong>요청</strong></a></li>
			<li><a href="/quest/list" class="load"><strong>수행</strong></a></li>
			<!-- <li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"  role="button" aria-haspopup="true" aria-expanded="false">관리자 <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="/admin/area/edit" class="load">분야</a></li>
					<li><a href="/admin/work/edit" class="load">업무</a></li>
					<li><a href="/admin/skill/edit" class="load">스킬</a></li>
				</ul>
			</li> -->
		</ul>
		<div data-non-exist class="nav btn-group navbar-right" style="padding-right:10px;">
			<button type="button" class="btn btn-sm navbar-btn btn-primary" name="loginButton" data-toggle="modal" data-target="#modal-sm">로그인</button>
			<button type="button" class="btn btn-sm navbar-btn btn-info" name="joinButton" data-toggle="modal" data-target="#modal">회원가입</button>
		</div>
		<ul data-exist class="nav navbar-nav navbar-right" style="padding-right:10px;">
			<li><a href="#" class="laod"><span class="glyphicon glyphicon-exclamation-sign"></span></a></li>
			<li class="dropdown">
				<a data-content-prepend="name" data-format="name" href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li role="presentation"><a href="#">Messages <span class="badge">3</span></a></li>
					<li><a data-href="id" data-format="userlink" data-format-target="href" class="load">유저 정보</a></li>
					<li><a data-href="quester.id" data-format="questerlink" data-format-target="href" class="load">퀘스터 정보</a></li>
					<li><a href="/quester/add" class="load">퀘스터 생성</a></li>
					<li><a data-href="requester.id" data-format="requesterlink" data-format-target="href" class="load">리퀘스터 정보</a></li>
					<li><a href="/portfolio/list" class="load">유저 포트폴리오 정보</a></li>
					<li><a href="/portfolio/add" class="load">유저 포트폴리오 생성</a></li>
					<li><a href="/payment/buy" class="load">포인트 결제</a>
					<li><a href="/point/deposit" class="load">포인트 예치</a>
					<li><a href="/point/withdraw" class="load">포인트 예치 취소</a>
					<li><a href="/point/give" class="load">포인트 지급</a>
					<li><a href="/pointLog/list" class="load">포인트 거래 내역</a>
					<li><a href="/paymentLog/list" class="load">포인트 결제 및 환불, 환전 내역 정보</a>
					<li role="separator" class="divider"></li>
					<li><a href="#" class="logout">로그아웃</a></li>
				</ul>
				
			</li>
		</ul>
	</div>
</nav>

<script type="text/javascript">
$(document).ready(function(){
	$("li a.logout").click(function(){
		console.log(1);
		ajax.get("/api/logout", {}, function(result) {
			user = null;
			$("header").loadTemplate("/header", null);
		});
	});
	$("button[name='loginButton']").click(function(){
		/* $("#modal > .modal-dialog").addClass("modal-sm"); */
		$("#modal-sm").find("div.modal-body").load("/user/login");
	});
	$("button[name='joinButton']").click(function(){
		/* $("#modal > .modal-dialog").removeClass("modal-sm"); */
		$("#modal").find("div.modal-body").load("/user/join");
	});
});

</script>