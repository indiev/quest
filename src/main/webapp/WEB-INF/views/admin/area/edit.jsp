<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<link rel="stylesheet" href="/resources/assets/lib/jsTree/themes/default/style.min.css" />
<script src="/resources/assets/lib/jsTree/jstree.min.js"></script>

<div class="container">
	<div class="row">
		<div class="col-md-3">
			<div id="jstree">
			</div>
			<input type="button" class = "btn btn-default" value="상위 레코드 추가" onclick="addRecord()">
		</div>

		<div class="col-md-9">
			<div class="panel panel-default updateRecordForm">
				<div class="panel-heading">
					<h3 class="panel-title">
						<a class="recordId form-control">ID: </a>
					</h3>
				</div>
				<div class="panel-body">
					<form role="form">
						<div class="col-md-12">
							<div class="row">
								<label id="name_label" for="name">name</label>
							</div>
							<div class="row">
								<input type="text" name="name" id="name"
									class="name form-control">
							</div>
							<div class="row">
								<label id="description_label" for="description">description</label>
							</div>
							<div class="row">
								<input type="text" name="description" id="description"
									class="description form-control">
							</div>
							<div class="row">
								<label id="parentId_label" for="parentId">parentId</label>
							</div>
							<div class="row">
								<select name="parentId"></select>
							</div>
							<div class="row">
								<input type="button" class="btn btn-default" value="수정" onclick="updateRecord()"> 
								<input type="button" class="btn btn-default" value="삭제" onclick="removeRecord()">
							</div>
						</div>
					</form>
				</div>
			</div>
			
			<div class="panel panel-default addSubRecordForm">
				<div class="panel-heading">
					<h3 class="panel-title">서브 레코드 추가.</h3>
				</div>
				<div class="panel-body">
					<form role="form">
						<label id="name_label" for="name">name</label> 
						<input type="text" name="name" id="name" class="name form-control" >
						<label id="description_label" for="description">description</label> 
						<input type="text" name="description" id="description" class="description form-control" >
						<input type="button" class = "btn btn-default" value="추가" onclick="addSubRecord()">
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var areaList = [];
	var selectedRecord = {};
	var apiUrl = "/api/areas/";
	
	$(document).ready(function() {
		list();
	});
	
	function drawEditRecord(record) {
		$updateForm= $(".updateRecordForm");
		$updateForm.find(".recordId").text("ID:"+"");
		$updateForm.find(".name").val("");
		$updateForm.find(".description").val("");
		$updateForm.find("select[name='parentId']").empty();
		$updateForm.find("select[name='parentId']").append($('<option>').html("없음").val(null));
		$.each(areaList, function(i, elem) {
			$option = $("<option>");
			$option.html(elem.name).val(elem.id);
			$updateForm.find("select[name='parentId']").append($option);
		});
		
		
		$addForm = $(".addSubRecordForm");
		$addForm.find(".name").val("");
		$addForm.find(".description").val("");
		if(record==null)
			return;
						
		$updateForm.find(".recordId").text("ID:"+record.id);
		$updateForm.find(".name").val(record.name);
		$updateForm.find(".description").val(record.description);
		
		if(record.parent != null) {
			$updateForm.find("select[name='parentId']").val(record.parent.id);
			$updateForm.find("select[name='parentId']").removeAttr('disabled');
			$addForm.hide();
		}
		else {
			$updateForm.find("select[name='parentId']").val(null);
			$updateForm.find("select[name='parentId']").attr('disabled', 'true');
			$addForm.show();
		}
	}
	
	function list() {
		$("#jstree").append($("<ul class='area-content'>"));
		 ajax.get(apiUrl+"parents", {}, function(list){
		 	ajax.get("/admin/area/node/list", {}, function(html) {
		 		$("ul.area-content").loadTemplate($(html).clone(), list);
		 		$("#jstree").jstree();
		 		areaList = list;
			});
		 });
	
		 $('#jstree').on('select_node.jstree', function (e, data) {
				var i, j, r = [];
			    for(i = 0, j = data.selected.length; i < j; i++) {
			    	data.instance.get_node(data.selected[i]).name
			    	r.push(data.instance.get_node(data.selected[i]).text);
			    }
			    
			    $(areaList).each(function(i,elem) {
			    	if(elem.name == r[0]) {
			    		elem.parent = null;
			    		return selectedRecord = elem;
			    	}
			    	else if(elem.childs!=undefined && elem.childs.length!=0) {
			    		$(elem.childs).each(function(j,sub_elem) {
			    			if(sub_elem.name == r[0]) {
			    				sub_elem.parent = elem;
			    				return selectedRecord = sub_elem;
			    			}
			    		});
			    	}
			    });
			    console.log(selectedRecord);
				drawEditRecord(selectedRecord);
			});
	 }
	
	function updateRecord() {
		$updateForm = $(".updateRecordForm");
		
		if($updateForm.find(".name").val() == "") {
			alert("이름은 입력 해야만 합니다.")
			return;
		}

		selectedRecord.name = $updateForm.find(".name").val();
		if($updateForm.find(".description").val()!="")
			selectedRecord.description = $updateForm.find(".description").val();
		
		$updateForm.find("select[name='parentId']").
		
		console.log(selectedRecord);
		ajax.put(apiUrl+selectedRecord.id, selectedRecord, function(result){
			alert(result);
			init();
		});
	}
	
	function addSubRecord() {
		$div = $(".addSubRecordForm");
		
		if($div.find(".name").val() == "") {
			alert("이름은 입력 해야만 합니다.")
			return;
		}
		
		var area = {};
		area.parentId = selectedRecord.id;
		area.name = $div.find(".name").val();
		area.description = $div.find(".description").val();
		
		ajax.post(apiUrl, area, function(result){
			if(result!= null) alert("새로운 레코드를 생성하였습니다.");
			else alert(result.mssege);
			init();
		});
	}
	
	function addRecord() {
		var area = {};
		area.parentId = null;
		area.name = "새로운 레코드 ";
		area.description = "";
		
		ajax.get(apiUrl, {}, function(data){
			area.name  += data.length;
			ajax.post(apiUrl, area, function(result){
				if(result!= null) alert("새로운 레코드를 생성하였습니다.");
				else alert(result.mssege);
				init();
			});
		});
		
	}
	
	function removeRecord() {
		if(selectedRecord==null)
			return;
		
		ajax.del(apiUrl + selectedRecord.id, {}, function(result){
			if(result!= null) alert("선택한 레코드가 삭제되었습니다.");
			else alert(result.mssege);
			init();
		});
	}
	
	function init() {
		selectedRecord = null;
		drawEditRecord(selectedRecord);
		
		$.jstree.destroy ();
		list();
	}
	 
</script>