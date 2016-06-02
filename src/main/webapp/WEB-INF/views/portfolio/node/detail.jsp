<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="mainWrap center-block">
	<div class="container-fluid portfolio-detail-node">
		<div>
			유형:<span data-content="type.name"></span>
		</div>
		<div>
			타이틀:<span data-content="name"></span>
		</div>
		<div>
			place:<span data-content="place"></span>
		</div>
		<div data-content-append="level">level:</div>
		<div data-content-append="result">result:</div>
		<div data-content-append="startDate" data-format="date">시작일자:</div>
		<div data-content-append="endDate" data-format="date">종료일자:</div>
		<div data-content-append="description">설명:</div>
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
		<div data-list="subPortfolios" data-template="/portfolio/node/list" class="subPortfolios"></div>
	</div>
</div>