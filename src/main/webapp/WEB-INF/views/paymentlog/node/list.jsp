<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<style type="text/css">
[action=buy] {
	background-color: SlateBlue;
	color:white;
}

</style>

<div class="col-md-10">
	<div class="panel panel-default">
		<div class="panel-body" data-template-bind='[{"attribute": "action", "value": "action.value"}]'>
			<div class="container">
				<div class="row">
					<div class="col-md-2">
						<div data-content-append="createdDate" data-format="date"></div>
					</div>
					
					<div class="col-md-2">
						<div data-content-append="action.name"></div>
					</div>
					
					<div class="col-md-4">
						<div data-content-append="name"></div>
					</div>
					
					<div class="col-md-2">
						<div data-content-append="money" data-format="money"></div>
					</div>
					
					<div class="col-md-2">
						<div data-content-append="point" data-format="point"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
