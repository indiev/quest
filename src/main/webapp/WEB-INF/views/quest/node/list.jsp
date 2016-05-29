<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="col-md-3">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title"><a data-format="link" data-format-target="href" data-href="id" data-content="name"></a><button name="applyButton" data-value="id" onclick="apply(this.value)">지원</button></h3>
		</div>
		<div class="panel-body">
			<p data-content="description"></p>
			<ul class="list-inline pull-left" data-format="nestedTemplateFormatter" data-content="areas">
				<li class="badge label-primary" data-content="name"></li>
			</ul>
			<ul class="list-inline pull-left" data-format="nestedTemplateFormatter" data-content="works">
				<li class="badge label-warning" data-content="name"></li>
			</ul>
			<ul class="list-inline pull-left" data-format="nestedTemplateFormatter" data-content="skills">
				<li class="badge label-danger" data-content="name"></li>
			</ul>
		</div>
		<ul class="list-group">
			<li class="list-group-item">
				<span data-content="requester.user.realname"></span> |
				<span data-content-append="applicants.length">지원자수 : </span> | 
				<span data-content-append="questers.length">모집자수 : </span>
			</li>
			<li class="list-group-item">
				<span data-format="date" data-content-append="recruitmentEndDate">마감 : </span>
			</li>
			<li class="list-group-item">
				<span class="glyphicon glyphicon-calendar" aria-hidden="true"></span> 기간: <span data-content-prepend="duration">일</span>
			</li>
			<li class="list-group-item">
				<span class="glyphicon glyphicon-usd" aria-hidden="true"></span> 보상: <span data-content="reward"></span>
			</li>
			<li class="list-group-item">
				<span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> 자격: <span data-content="qualification"></span>
			</li>
		</ul>
	</div>
</div>