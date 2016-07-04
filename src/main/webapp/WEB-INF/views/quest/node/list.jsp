<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>

<div class="col-md-12">
	<div class="panel" style="padding:10px">
		<div class="row">
			<div class="col-md-7">
				<p><a data-content="name" data-href="id" data-format="link" data-format-target="href" class="load h4"></a></p>
				<p data-content="description" style="font-size:13pt;"></p>
			</div>
			<div class="col-md-3">
				<p>
					<span data-list="rewards"><span data-content-prepend="hwan">H</span></span>
					<span data-content="qualification"></span>
				</p>
				<p data-list="areas"><span data-content="name" class="badge label-info"></span></p>
				<p data-list="works"><span data-content="name" class="badge label-warn"></span></p>
				<p data-list="skills"><span data-content="name" class="badge label-danger"></span></p>
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
</div>