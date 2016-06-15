<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div>기간 : <span data-content="duration" data-model="Quest" data-value="id" data-attribute="duration" class="updater"></span>일</div>
<div>
	보상
	<div data-list="rewards">
		<span data-content="hwan" data-model="reward" data-value="id" data-attribute="hwan" class="updater"></span>
	</div>
</div>
<div>위약금 관련</div>
<div>퀘스터패널티 : <span data-content="contract.questerPenalty" data-model="Contract" data-value="contract.id" data-attribute="questerPenalty" class="updater"></span>%</div>
<div>리퀘스터패널티 : <span data-content="contract.requesterPenalty" data-model="Contract" data-value="contract.id" data-attribute="requesterPenalty" class="updater"></span>%</div>
<hr>
<div>
	<p>조항</p>
	<div data-list="contract.provisions" class="list-group">
		<a data-content="name" data-model="Provision" data-value="id" data-attribute="name" href="#" class="list-group-item updater"></a>
	</div>
</div>
<hr>
<div>
	<p>요구사항</p>
	<div data-list="requirements" class="list-group">
		<a href="#" class="list-group-item">
			<h5 data-content="name" data-model="Requirement" data-value="id" data-attribute="name" class="updater"></h5>
			<p data-content="description" data-model="Requirement" data-value="id" data-attribute="description" class="updater"></p>
		</a>
	</div>
</div>
<div>
	참여한 퀘스터들
	<ul data-list="questers">
		<li data-content="name"></li>
	</ul>
</div>
<div>협의 관련 게시판 또는 채팅 ajax로 가져오기. progress에서도 활용</div>
<button type="button" data-value="contract.id" class="btn btn-default" disabled onclick="agree(this.value)">협의완료</button>
<div>총 <span data-content="questers.length"></span>명 중에 <span data-content="contract.agreedUsers.length"></span>명 동의</div>
<template>
	<span class="editButtons">
		<span class="glyphicon glyphicon-list list"></span>
		<span class="glyphicon glyphicon-pencil edit"></span>
		<span class="glyphicon glyphicon-remove remove"></span>
	</span>
</template>