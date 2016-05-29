<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="container-fluid quest-detail-node">
	<div><span data-content="name" class="h3"></span></div>
	<div>
		<span data-content="requester.user.realname"></span>
		<span data-content="createdDate" data-format="date" class="pull-right"></span>
	</div>
	<div><span data-content="reward"></span></div>
	<div><span data-content="qualification"></span></div>
	<div><span data-content="duration"></span></div>
	<div><span data-content="recruitmentEndDate" data-format="date"></span></div>
	<div><span data-content="areas" data-format="nestedTemplateFormatter" data-format-options="#AreaTemplate"></span></div>
	<div><span data-content="works" data-format="nestedTemplateFormatter" data-format-options="#WorkTemplate"></span></div>
	<div><span data-content="skills" data-format="nestedTemplateFormatter" data-format-options="#SkillTemplate"></span></div>
	<div><span data-content="description"></span></div>
	<div>
		<b>지원한 퀘스터(드래그앤드롭으로 이동하기? 오른쪽 마우스 클릭시 메뉴 뜨고 상세보기 가능)</b>
		<ul data-content="applicants" data-format="nestedTemplateFormatter" data-format-options="#ApplicantTemplate" data-binding-options='{"ignoreUndefined": true, "ignoreNull": true}' class="list-inline">
			<li data-content="name" data-value="id" onclick="accept(this.value)"></li>
		</ul>
	</div>
	<div>
		<b>선정 퀘스터</b>
		<ul data-content="questers" data-format="nestedTemplateFormatter" data-format-options="#QuesterTemplate" class="list-inline">
			<li>없음</li>
			<li data-content="name"></li>
		</ul>
	</div>
	<button type="button" class="btn btn-default" data-value="id" onclick="next(this.value);">진행</button>
</div>

<script type="text/html" id="AreaTemplate">
	<span data-content="name" class="badge label-info"></span>
</script>
<script type="text/html" id="WorkTemplate">
	<span data-content="name" class="badge label-warn"></span>
</script>
<script type="text/html" id="SkillTemplate">
	<span data-content="name" class="badge label-danger"></span>
</script>
<script type="text/html" id="ApplicantTemplate">
	<li data-content="name" class="badge label-info"></li>
</script>
<script type="text/html" id="QuesterTemplate">
	<li data-content="name" class="badge label-warn"></li>
</script>
