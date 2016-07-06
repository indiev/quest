<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<style type="text/css">
[action=charge] {
	background-color: SlateBlue;
	color:white;
}

[action=deposit] {
	background-color: Orange;
	color: white;
}

[action=receiveWait] {
	background-color: Orange;
	color: white;
}

[action=withdraw] {
	background-color: PaleGreen;
	color: green;
}

[action=depositCancel] {
	background-color: Orange;
	color: white;
}

[action=give] {
	background-color: OrangeRed;
	color: white;
}

[action=receive] {
	background-color: PaleGreen;
	color: green;
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
						<div data-content-append="point" data-format="point"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
