<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>

<div class="col-md-4">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-content="name" data-href="id" data-format="link" data-format-target="href" class="load"></a>
				<button name="deleteButton" data-value="id" onclick="del(this.value)">삭제</button>
			</h3>
		</div>

		<div class="panel-body">
			<div data-content-append="type.name" >유형:</div>
			<div data-content-append="startDate" data-format="date">시작일자:</div>
			<div data-content-append="endDate" data-format="date">종료일자:</div>
		</div>
	</div>
</div>
