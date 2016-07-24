<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>index</th>
				<th>title</th>
				<th>author</th>
				<th>created</th>
			</tr>
		</thead>
		<tbody>
			<tr data-value="id" data-format="link" data-format-target="value">
				<td data-content="id"></td>
				<td data-content="name" data-value="id" onclick="return detail(this)" data-toggle="modal" data-target="#modal"></td>
				<td data-content-prepend="user.realname">(<span data-content="user.name"></span>)</td>
				<td data-content="createdDate" data-format="date"></td>
			</tr>
		</tbody>
	</table>
</div>