<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>

<div class="panel" style="padding:10px; margin-top:15px; max-width:900px;">
	<div class="row" style="position:relative;">
		<div class="col-md-10 vertical-line" style="position:initial;">
			<p><a data-content="name" data-href="id" data-format="link" data-format-target="href" class="load h4"></a></p>
			<div class="row">
				<div class="col-md-10">
					<div data-content="description" class="ellipsis-line2" style="font-size:12pt;"></div>
				</div>
				<div class="col-md-2">
					<div data-list="rewards"><span data-content-prepend="hwan">H</span></div>
					<div data-content="qualification"></div>
				</div>
			</div>
			<div style="position:absolute; bottom:0px;">
				<ul data-list="areas" class="list-inline pull-left" style="padding-left:10px;"><li data-content="name" data-title="name" class="label label-info ellipsis-label"></li></ul>
				<ul data-list="works" class="list-inline pull-left" style="padding-left:10px;"><li data-content="name" data-title="name" class="label label-warning ellipsis-label"></li></ul>
				<ul data-list="skills" class="list-inline pull-left" style="padding-left:10px;"><li data-content="name" data-title="name" class="label label-danger ellipsis-label"></li></ul>
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
