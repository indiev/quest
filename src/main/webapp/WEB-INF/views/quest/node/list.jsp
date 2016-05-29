<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="col-md-3">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-content="name" data-href="id" data-format="link" data-format-target="href"></a>
				<button name="applyButton" data-value="id" onclick="apply(this.value)">지원</button>
			</h3>
		</div>
		<div class="panel-body">
			<p data-content="description"></p>
			<ul data-content="areas" data-format="nestedTemplateFormatter" data-format-options="#AreaTemplate" class="list-inline pull-left"></ul>
			<ul data-content="works" data-format="nestedTemplateFormatter" data-format-options="#WorkTemplate" class="list-inline pull-left"></ul>
			<ul data-content="skills" data-format="nestedTemplateFormatter" data-format-options="#SkillTemplate" class="list-inline pull-left"></ul>
		</div>
		<ul class="list-group">
			<li class="list-group-item">
				<span data-content="requester.user.realname"></span> |
				<span data-content-append="applicants.length">지원자수 : </span> | 
				<span data-content-append="questers.length">모집자수 : </span>
			</li>
			<li class="list-group-item">
				<span data-content-append="recruitmentEndDate" data-format="date">마감 : </span>
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
<script type="text/html" id="AreaTemplate">
	<li data-content="name" class="badge label-info"></li>
</script>
<script type="text/html" id="WorkTemplate">
	<li data-content="name" class="badge label-warn"></li>
</script>
<script type="text/html" id="SkillTemplate">
	<li data-content="name" class="badge label-danger"></li>
</script>