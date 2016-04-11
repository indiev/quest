<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<nav id="sidebar-top" class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#sidebar-top .navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
       	<a class="navbar-brand" href="/">Quest</a>
	</div>
	<div class="collapse navbar-collapse">
		<!-- <p class="navbar-text pull-right">v0.1</p> -->
		<ul class="nav navbar-nav">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">사용자 <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#">정보</a></li>
					<li><a href="/quester/add">퀘스터 생성</a></li>
					<li><a href="/quester/select">메인 퀘스터 선택</a></li>
				</ul>
				
			</li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">퀘스트 <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="/quest/list">list</a></li>
					<li><a href="/quest/request">request</a></li>
				</ul>
			</li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">분야 <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="/area/list">list</a></li>
				</ul>
			</li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">관리자 <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="/admin/area/add">분야</a></li>
					<li><a href="/admin/work/add">업무</a></li>
					<li><a href="/admin/skill/add">스킬</a></li>
				</ul>
			</li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">디자인 <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="/design/quest/list">quest-list</a></li>
					<li><a href="/design/quest/detail">queset-detail</a></li>
				</ul>
			</li>
			<li><a href="/">etc</a></li>
		</ul>
		<form class="navbar-form navbar-left" role="search">
	       	<div class="input-group">
	       		<label for="search" class="sr-only">검색</label>
	       		<input type="text" class="form-control input-sm" id="search" name="search" placeholder="검색"/>
	       		<span class="input-group-btn">
					<button class="btn btn-default btn-sm" type="button">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					</button>
				</span>
	       	</div>
		</form>
		<div class="navbar-right btn-group">
			<button type="button" class="btn btn-sm navbar-btn btn-primary" name="loginButton" data-toggle="modal" data-target="#logDialog">로그인</button>
			<button type="button" class="btn btn-sm navbar-btn btn-danger" name="logoutButton">로그아웃</button>
			<button type="button" class="btn btn-sm navbar-btn btn-info" name="joinButton" data-toggle="modal" data-target="#logDialog">회원가입</button>
			<button type="button" class="btn btn-sm navbar-btn btn-warning" name="findButton" data-toggle="modal" data-target="#logDialog">아이디·비밀번호 찾기</button>
		</div>
	</div>
</nav>

<div class="modal fade" id="logDialog" role="dialog" aria-labelledby="loginHeader" aria-hidden="true" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("button[name='loginButton']").click(function(){
		$("#logDialog > .modal-dialog").addClass("modal-sm");
		$("#logDialog").find("div.modal-body").load("/user/login");
	});
	$("button[name='logoutButton']").click(function(){
		alert("로그아웃");
	});
	$("button[name='joinButton']").click(function(){
		$("#logDialog > .modal-dialog").removeClass("modal-sm");
		$("#logDialog").find("div.modal-body").load("/user/join");
	});
	$("button[name='findButton']").click(function(){
		$("#logDialog > .modal-dialog").removeClass("modal-sm");
		$("#logDialog").find("div.modal-body").empty();
	});
});

</script>