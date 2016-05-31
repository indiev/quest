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
	<div>
		<ul data-list="areas">
			<li data-content="name" class="badge label-info"></li>
		</ul>
	</div>
	<div>
		<ul data-list="works">
			<li data-content="name" class="badge label-warn"></li>
		</ul>
	</div>
	<div>
		<ul data-list="skills">
			<li data-content="name" class="badge label-danger"></li>
		</ul>
	</div>
	<div><span data-content="description"></span></div>
	<div>
		<b>지원한 퀘스터(드래그앤드롭으로 이동하기? 오른쪽 마우스 클릭시 메뉴 뜨고 상세보기 가능)</b>
		<ul data-list="applicants" class="list-inline">
			<li data-content="name" data-value="id" onclick="accept(this.value)"></li>
		</ul>
	</div>
	<div>
		<b>선정 퀘스터</b>
		<ul data-list="questers" class="list-inline">
			<li data-content="name" data-value="id" onclick="remove(this.value)"></li>
		</ul>
	</div>
	<button type="button" class="btn btn-default" data-value="id" onclick="next(this.value);">진행</button>
</div>