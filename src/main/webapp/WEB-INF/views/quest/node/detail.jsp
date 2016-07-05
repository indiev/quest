<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="container-fluid quest-detail-node">
	<div class="row">
		<div class="col-md-9">
			<div class="panel panel-info" style="margin-top:20px; position: relative; padding-bottom:15px;">
				<div class="panel-body">
					<p data-content="name" class="h3"></p>
					<div class="row">
						<div class="col-md-6">
							<div data-content-append="requester.name" class="pull-left"><span class="glyphicon glyphicon-user"></span></div>
							<span class="glyphicon glyphicon-gift pull-left"></span>
							<ul data-list="rewards" class="list-inline pull-left">
								<li data-content-prepend="hwan">H</li>
							</ul>
							<span data-content="qualification" class="pull-left"></span>
							<div class="row">
								<div class="col-md-12">
									<ul data-list="areas">
										<li data-content="name" class="badge label-info"></li>
									</ul>
									<ul data-list="works">
										<li data-content="name" class="badge label-warning"></li>
									</ul>
									<ul data-list="skills">
										<li data-content="name" class="badge label-danger"></li>
									</ul>
								</div>
							</div>
							<div data-content="createdDate" data-format="date" class="col-md-2"></div>
						</div>
						<div class="col-md-6">
							<div class="row" style="border:1px solid #000000; margin-left:15px; margin-right:15px;">
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
					<div class="row" style="margin-top:15px;">
						<div class="col-md-6">
							<div style="border:1px solid; margin-left:15px; margin-right:15px;">
								<div><span class="glyphicon glyphicon-calendar"></span> 약 <span data-content="duration"></span>일 소요</div>
								<div data-list="requirements">
									<p data-content="name" class="h4"></p>
									<span data-content="description"></span>
								</div>
							</div>
							<div style="border:1px solid; margin-left:15px; margin-right:15px;">
								<div data-content="description"></div>
							</div>
						</div>
						<div class="col-md-6">
							<div style="border:1px solid; margin-left:15px; margin-right:15px;">
								<div>
									<p>리퀘스터 패널티 : <span data-content-append="contract.requesterPenalty"></span>%</p>
									<p>퀘스터 패널티 : <span data-content-append="contract.questerPenalty"></span>%</p>
								</div>
								<div data-list="contract.provisions">
									<p data-content="name" class="h4"></p>
									<span data-content="description"></span>
								</div>
							</div>
						</div>
					</div>
					<div style="border:1px solid; position:absolute; top:0px; right:0px; padding:15px;">
						<span class="glyphicon glyphicon-calendar"></span>
						<span data-content-prepend="recruitmentEndDate" data-format="date"></span> 마감
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
			<div class="panel panel-info" style="margin-top:20px; position: relative; padding-bottom:15px;">
				<div class="panel-body">
					리퀘스터 정보
					<p data-content="requester.name"></p>
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