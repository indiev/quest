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
						<label id="name_label" for="name">name</label> 
						<input type="text" name="name" id="name" class="name form-control" >
						<label id="description_label" for="description">description</label> 
						<input type="text" name="description" id="description" class="description form-control" >
						<input type="button" class = "btn btn-default" value="수정" onclick="updateRecord()">
						<input type="button" class = "btn btn-default" value="삭제" onclick="removeRecord()">
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var skillList = [];
	var selectedRecord = {};
	var apiUrl = "/api/skills/";
	
	$(document).ready(function() {
		list();
	});
	
	function drawEditRecord(record) {
		$updateForm= $(".updateRecordForm");
		$updateForm.find(".recordId").text("ID:"+"");
		$updateForm.find(".name").val("");
		$updateForm.find(".description").val("");

		$addForm = $(".addSubRecordForm");
		$addForm.find(".name").val("");
		$addForm.find(".description").val("");
		if(record==null)
			return;
						
		$updateForm.find(".recordId").text("ID:"+record.id);
		$updateForm.find(".name").val(record.name);
		$updateForm.find(".description").val(record.description);
		
		if(record.parentId !=null)
			$addForm.hide();
		else
			$addForm.show();
	}
	
	function list() {
		$("#jstree").append($("<ul class='skill-content'>"));
		 ajax.get(apiUrl, {}, function(list){
		 	ajax.get("/admin/skill/node/list", {}, function(html) {
		 		$("ul.skill-content").loadTemplate($(html).clone(), list);
		 		$("#jstree").jstree();
		 		skillList = list;
			});
		 });
	
		 $('#jstree').on('select_node.jstree', function (e, data) {
				var i, j, r = [];
			    for(i = 0, j = data.selected.length; i < j; i++) {
			    	data.instance.get_node(data.selected[i]).name
			    	r.push(data.instance.get_node(data.selected[i]).text);
			    }
			    
			    $(skillList).each(function(i,elem){
			    	if(elem.name == r[0])
			    		return selectedRecord = elem;
			    	else if(elem.childs!=undefined && elem.childs.length!=0) {
			    		$(elem.childs).each(function(j,sub_elem){
			    			if(sub_elem.name == r[0])
			    				return selectedRecord = sub_elem;
			    		});
			    	}
			    });
			    console.log(selectedRecord);
				drawEditRecord(selectedRecord);
			});
	 }
	
	function updateRecord() {
		$div = $(".updateRecordForm");
		
		if($div.find(".name").val() == "") {
			alert("이름은 입력 해야만 합니다.")
			return;
		}

		selectedRecord.name = $div.find(".name").val();
		if($div.find(".description").val()!="")
			selectedRecord.description = $div.find(".description").val();
		
		ajax.put(apiUrl+selectedRecord.id, selectedRecord, function(result){
			alert(result);
			init();
		});
	}
	
	function addRecord() {
		var skill = {};
		skill.name = "새로운 레코드 ";
		skill.description = "";
		
		ajax.get(apiUrl,{},function(data){
			skill.name  += data.length;
			ajax.post(apiUrl, skill, function(result){
				if(result!= null) alert("새로운 레코드를 생성하였습니다.");
				else alert(result.mssege);
				init();
			});
		});
		
	}
	
	function removeRecord() {
		if(selectedRecord==null)
			return;
		
		ajax.del(apiUrl+selectedRecord.id, {}, function(result){
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