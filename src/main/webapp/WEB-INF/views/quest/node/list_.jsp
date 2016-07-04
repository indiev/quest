<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="col-md-3">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-href="id" data-format="link" data-format-target="href" class="load"><b data-content="name"></b></a>
				<button name="applyButton" data-value="id" onclick="apply(this.value)">지원</button>
				<button name="deleteButton" data-value="id" onclick="del(this.value)">삭제</button>
			</h3>
		</div>
		<div class="panel-body">
			<p data-content="description"></p>
			<ul data-list="areas" class="list-inline pull-left">
				<li data-content="name" class="badge label-info"></li>
			</ul>
			<ul data-list="works" class="list-inline pull-left">
				<li data-content="name" class="badge label-warn"></li>
			</ul>
			<ul data-list="skills" class="list-inline pull-left">
				<li data-content="name" class="badge label-danger"></li>
			</ul>
		</div>
		<ul class="list-group">
			<li class="list-group-item">
				<span data-content="requester.name"></span> |
				<span data-content-append="applicants.length">지원자 : </span> 
				<!-- <span data-content-append="questers.length">모집자 : </span> -->
			</li>
			<li class="list-group-item">
				<span data-content-append="recruitmentEndDate" data-format="remain">마감 : </span>
			</li>
			<!-- <li class="list-group-item">
				<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span> 기간: <span data-content-prepend="duration">일</span>
			</li> -->
			<li class="list-group-item">
				<span class="glyphicon glyphicon-usd" aria-hidden="true"></span> 보상: <span data-list="rewarsd"><span data-content="name"></span></span>
			</li>
			<!-- <li class="list-group-item">
				<span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> 자격: <span data-content="qualification"></span>
			</li> -->
		</ul>
	</div>
</div>