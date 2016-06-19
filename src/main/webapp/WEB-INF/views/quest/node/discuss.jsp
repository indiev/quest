<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<table class="table table-bordered">
	<thead>
	</thead>
	<tbody>
		<tr>
			<th rowspan="2">패널티</th>
			<th>리퀘스터</th>
			<td><span data-content="contract.requesterPenalty" data-model="Contract" data-value="contract.id" data-attribute="requesterPenalty" class="updater"></span>%</td>
			<td></td>
		</tr>
		<tr>
			<th>퀘스터</th>
			<td><span data-content="contract.questerPenalty" data-model="Contract" data-value="contract.id" data-attribute="questerPenalty" class="updater"></span>%</td>
			<td></td>
		</tr>
		<tr>
			<th>기간</th>
			<td><span data-content="duration" data-model="Quest" data-value="id" data-attribute="duration" class="updater"></span>일</td>
			<th>보상</th>
			<td data-list="rewards">
				<span data-content="hwan" data-model="reward" data-value="id" data-attribute="hwan" class="updater"></span>
			</td>
		</tr>
	</tbody>
</table>
<div class="row">
	<div class="col-md-6">
		<p>조항 <span class="glyphicon glyphicon-plus"></span></p>
		<div data-list="contract.provisions" class="list-group">
			<a data-content="name" data-model="Provision" data-value="id" data-attribute="name" href="#" class="list-group-item updater"></a>
		</div>
	</div>
	<div class="col-md-6">
		<p>요구사항 <span class="glyphicon glyphicon-plus"></span></p>
		<div data-list="requirements" class="list-group">
			<a href="#" class="list-group-item">
				<h5 data-content="name" data-model="Requirement" data-value="id" data-attribute="name" class="updater"></h5>
				<p data-content="description" data-model="Requirement" data-value="id" data-attribute="description" class="updater"></p>
			</a>
		</div>
	</div>
</div>
<hr>
<div>
	퀘스터 리스트
	<ul data-list="questers">
		<li data-content="name"></li>
	</ul>
</div>
<button type="button" data-value="contract.id" class="btn btn-default" disabled onclick="agree(this.value)">협의완료</button>
<div>총 <span data-content="questers.length"></span>명 중에 <span data-content="contract.agreedUsers.length"></span>명 동의</div>
<template>
	<span class="editButtons">
		<span class="glyphicon glyphicon-list list"></span>
		<span class="glyphicon glyphicon-pencil edit"></span>
		<span class="glyphicon glyphicon-remove remove"></span>
	</span>
</template>