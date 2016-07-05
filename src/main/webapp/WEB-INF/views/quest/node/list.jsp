<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>

<div class="panel" style="padding:10px; width:900px;">
	<div class="row">
		<div class="col-md-10 vertical-line">
			<p><a data-content="name" data-href="id" data-format="link" data-format-target="href" class="load h4"></a></p>
			<div data-content="description" class="ellipsis-line2" style="font-size:12pt;"></div>
			<hr>
			<div class="row">
				<div class="col-md-8">
					<div data-list="rewards"><span data-content-prepend="hwan">H</span></div>
					<div data-content="qualification"></div>
				</div>
				<div class="col-md-4">
					<ul data-list="areas" class="list-inline"><li data-content="name" data-title="name" class="badge label-info ellipsis-badge"></li></ul>
					<ul data-list="works" class="list-inline"><li data-content="name" data-title="name" class="badge label-warn ellipsis-badge"></li></ul>
					<ul data-list="skills" class="list-inline"><li data-content="name" data-title="name" class="badge label-danger ellipsis-badge"></li></ul>
				</div>
			</div>
		</div>
		<div class="col-md-2 text-center">
			<p data-content-prepend="recruitmentEndDate" data-format="remain"></p>
			<p>
				<span data-content="applicants.length"></span> / <span data-content="questers.length"></span>명
			</p>
			<p><button name="applyButton" data-value="id" onclick="apply(this.value)" class="btn">지원</button></p>
		</div>
	</div>
</div>
