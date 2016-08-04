<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="container-fluid quest-detail-node">
	<div class="row">
		<div class="col-md-9">
			<div class="panel panel-default" style="margin-top:20px; position: relative; padding-bottom:15px;">
				<div class="panel-body">
					<p data-content="name" class="h3"></p>
					<hr/>
					<div class="row">
						<div class="col-md-6 vertical-line">
							<div style="padding-bottom:20px;">
								<ul data-list="areas" class="list-inline pull-left" style="padding-left:10px;"><li data-content="name" class="label label-info"></li></ul>
								<ul data-list="works" class="list-inline pull-left" style="padding-left:10px;"><li data-content="name" class="label label-warning"></li></ul>
								<ul data-list="skills" class="list-inline pull-left" style="padding-left:10px;"><li data-content="name" class="label label-danger"></li></ul>
							</div>
							<div class="panel panel-default" style="clear:left; margin-top:15px">
								<!-- <div class="panel-heading" style="font-weight:bold;">내용</div> -->
								<div class="panel-body" data-content="description"></div>
							</div>
							<div class="panel panel-default">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-2 vertical-line">
											<span class="h4">보상</span>
										</div>
										<div class="col-md-10">
											<span class="glyphicon glyphicon-gift pull-left"></span>
											<ul data-list="rewards" class="list-inline pull-left">
												<li data-content-prepend="hwan">H</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-body">
									<div><span class="glyphicon glyphicon-calendar"></span> 약 <span data-content="duration"></span>일 소요</div>
									<div>자격 : <span data-content="qualification" class="pull-left"></span></div>
									<div>위치 : </div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading"><strong>요구사항</strong></div>
								<ul class="list-group" data-list="requirements">
									<li class="list-group-item">
										<h5 class="list-group-item-heading"><strong data-content="name"></strong></h5>
										<blockquote class="list-group-item-text" data-content="description" style="font-size:10pt; padding:6px 12px"></blockquote>
									</li>
								</ul>
							</div>
						</div>
						<div class="col-md-6">
							<div class="panel panel-default">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-6">
											<table class="table table-hover table-condensed">
												<thead>
													<tr>
														<th>지원자 (<span data-content="applicants.length"></span>명 지원)</th>
													</tr>
												</thead>
												<tbody data-list="applicants">
													<tr>
														<td data-content-prepend="name" data-value="id" onclick="accept(this.value)"></td>
													</tr>
												</tbody>
											</table>
										</div>
										<div class="col-md-6">
											<table class="table table-hover table-condensed">
												<thead>
													<tr>
														<th>퀘스터  (<span data-content="questers.length"></span>명 선정)</th>
													</tr>
												</thead>
												<tbody data-list="questers">
													<tr>
														<td data-content="name" data-value="id" onclick="remove(this.value)"></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-body">
									<div data-content-append="requester.name"><span class="glyphicon glyphicon-user"></span></div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading"><strong>계약</strong></div>
								<div class="panel-body">
									<p>리퀘스터 패널티 : <span data-content="contract.requesterPenalty"></span>% / 조건 : </p>
									<p>퀘스터 패널티 : <span data-content="contract.questerPenalty"></span>% / 조건 : </p>
								</div>
								<ul class="list-group" data-list="contract.provisions">
									<li class="list-group-item">
										<h5 class="list-group-item-heading"><strong data-content="name"></strong></h5>
										<blockquote class="list-group-item-text" data-content="description" style="font-size:10pt; padding:6px 12px"></blockquote>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div style="border:1px solid; position:absolute; top:0px; right:0px; padding:15px;">
						<span>~일 전 글 (<span data-content="createdDate" data-format="date"></span>)</span>
						<span class="glyphicon glyphicon-calendar"></span>
						<span data-content-prepend="recruitmentEndDate" data-format="date"></span> 마감(~일 남음)
						<button type="button" class="btn btn-default" data-value="id" onclick="apply(this.value);">지원</button>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12" style="text-align:center;">
						<button type="button" class="btn btn-default btn-lg" data-value="id" onclick="next(this.value);">진행</button>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="panel panel-default" style="margin-top:20px; position: relative; padding-bottom:15px;">
				<div class="panel-body">
				</div>
			</div>
		</div>
	</div>
</div>
<template name="applicant-addon-menu">
	<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
	<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
</template>
<template name="quester-addon-menu">
	<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
	<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
</template>