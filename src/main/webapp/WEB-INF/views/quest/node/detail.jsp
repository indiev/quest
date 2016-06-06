<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="container-fluid quest-detail-node">
	<div class="panel panel-info">
		<div class="panel-heading">
			<h3 data-content="name" class="panel-title"></h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<ul data-list="areas" class="pull-left">
						<li data-content="name" class="badge label-info"></li>
					</ul>
					<ul data-list="works" class="pull-left">
						<li data-content="name" class="badge label-warning"></li>
					</ul>
					<ul data-list="skills" class="pull-left">
						<li data-content="name" class="badge label-danger"></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div data-content="requester.name" class="col-md-7"></div>
				<div data-content="createdDate" data-format="date" class="col-md-2"></div>
				<div class="col-md-1">약 <span data-content="duration"></span>일 소요</div>
				<div data-content-prepend="recruitmentEndDate" data-format="date" class="col-md-2"> 마감</div>
			</div>
			<div class="row">
				<div data-content="reward" class="col-md-6"></div>
				<div data-content="qualification" class="col-md-6"></div>
			</div>
			<div class="row">
				<div class="col-md-12">
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
					<p>지원자 (<span data-content="applicants.length"></span>명 지원)</p>
					<div data-list="applicants" class="list-group">
						<a data-content="name" data-value="id" class="list-group-item" href="#" onclick="accept(this.value)"></a>
					</div>
				</div>
				<div class="col-md-3">
					<p>퀘스터  (<span data-content="questers.length"></span>명 선정)</p>
					<div data-list="questers" class="list-group">
						<a data-content="name" data-value="id" class="list-group-item" href="#" onclick="remove(this.value)"></a>
					</div>
				</div>
				<div class="col-md-6">
				</div>
			</div>
			<div class="row">
				<div data-content="description" class="col-md-12"></div>
			</div>
		</div>
		<button type="button" class="btn btn-default" data-value="id" onclick="next(this.value);">진행</button>
	</div>
</div>