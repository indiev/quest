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
								<label id="relatedSkill_label" for="relatedSkill">relatedSkill</label>
							</div>
							<div class="row">
								<select name="relatedSkill"></select>
							</div>
							
							<div class="row">
								<button type="button" class="btn btn-default form-control relatedSkill" onclick="return false;"></button>
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
	var workList = [];
	var selectedRecord = {};
	var apiUrl = "/api/works/";
	
	$(document).ready(function() {
		list();
		$("select[name='relatedSkill']").change(function() { console.log(selectedRecord.skills); addSelectValue(this, "relatedSkill", selectedRecord.skills); });
	});
	
	function drawEditRecord(record) {
		$updateForm= $(".updateRecordForm");
		$updateForm.find(".recordId").text("ID:"+"");
		$updateForm.find(".name").val("");
		$updateForm.find(".description").val("");
		$updateForm.find("select[name='parentId']").empty();
		$updateForm.find("select[name='parentId']").append($('<option>').html("없음").val(null));
		$.each(workList, function(i, elem) {
			$option = $("<option>");
			$option.html(elem.name).val(elem.id);
			$updateForm.find("select[name='parentId']").append($option);
		});
		$("button.relatedSkill").empty();
		
		$addForm = $(".addSubRecordForm");
		$addForm.find(".name").val("");
		$addForm.find(".description").val("");
		if(record==null)
			return;
						
		$updateForm.find(".recordId").text("ID:"+record.id);
		$updateForm.find(".name").val(record.name);
		$updateForm.find(".description").val(record.description);
		ajax.get("/api/skills",{},function(list){ selectInputList("relatedSkill", list, "스킬"); });
		
		$.each(record.skills, function(i, elem) {
			$("button.relatedSkill").append(function() {
				$node = htmlBadge(elem.name, elem.id, "releatedSkill");
				$node.find("span.glyphicon-remove").click(function(){ 
					record.skills.splice(inArrayObject(elem.id, record.skills), 1);
					$(this).parent().remove();
				});
				return $node;
			});
		});
		
		if(record.parentId != undefined) {
			$updateForm.find("select[name='parentId']").val(record.parentId);
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
		$("#jstree").append($("<ul class='work-content'>"));
		 ajax.get(apiUrl+"parents", {}, function(list){
		 	ajax.get("/admin/work/node/list", {}, function(html) {
		 		$("ul.work-content").loadTemplate($(html).clone(), list);
		 		$("#jstree").jstree();
		 		workList = list;
			});
		 });
	
		 $('#jstree').on('select_node.jstree', function (e, data) {
				var i, j, r = [];
				for(i = 0, j = data.selected.length; i < j; i++) {
					r.push(data.selected[i]);
				}
				
			    $(workList).each(function(i,elem) {
			    	if(elem.id == r[0]) 
			    		return selectedRecord = jQuery.extend(true, {}, elem);
			    	else if(elem.childs!=undefined && elem.childs.length!=0) {
			    		$(elem.childs).each(function(j,sub_elem) {
			    			if(sub_elem.id == r[0]) {
			    				sub_elem.parentId = elem.id;
			    				return selectedRecord = jQuery.extend(true, {}, sub_elem);
			    			}
			    		});
			    	}
			    });
			    console.log(selectedRecord);
				drawEditRecord(selectedRecord);
			});
	 }
	
	function updateRecord() {
		$form = $(".updateRecordForm");
		
		if($form.find(".name").val() == "") {
			alert("이름은 입력 해야만 합니다.")
			return;
		}

		selectedRecord.name = $form.find(".name").val();
		if($form.find(".description").val()!="")
			selectedRecord.description = $form.find(".description").val();
		
		console.log(selectedRecord);
		ajax.put(apiUrl+selectedRecord.id, selectedRecord, function(result){
			alert(result);
			init();
		});
	}
	
	function addSubRecord() {
		$form = $(".addSubRecordForm");
		
		if($form.find(".name").val() == "") {
			alert("이름은 입력 해야만 합니다.")
			return;
		}
		
		var work = {};
		work.name = $form.find(".name").val();
		work.description = $form.find(".description").val();
		
		console.log(work);
		
		ajax.post("/api/works/parents/"+selectedRecord.id, work, function(result){
			if(result!= null) alert("새로운 레코드를 생성하였습니다.");
			else alert(result.mssege);
			init();
		});
	}
	
	function addRecord() {
		var work = {};
		work.parent = null;
		work.name = "새로운 레코드 ";
		work.description = "";
		
		ajax.get(apiUrl, {}, function(data){
			work.name  += data.length;
			ajax.post(apiUrl, work, function(result){
				if(result!= null) alert("새로운 레코드를 생성하였습니다.");
				else alert(result.mssege);
				init();
			});
		});
		
	}
	
	function removeRecord() {
		if(selectedRecord==null)
			return;
		
		ajax.del(apiUrl + selectedRecord.id, {}, function(result) {
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
	
	function inArrayObject(value, list) {
		return list.map(function(e) { return e.id; }).indexOf(value);
	}

	function addSelectValue(elem, category, list) {
		var text = $(elem).find(":selected").html();
		var value = Number($(elem).val());
		if(value != "" && inArrayObject(value, list) == -1) {
			list.push({"id":value});
			$("button." + category).append(function() {
				$node = htmlBadge(text, value, category);
				$node.find("span.glyphicon-remove").click(function(){ 
					list.splice(inArrayObject(value, list), 1);
					$(this).parent().remove();
				});
				return $node;
			});
		}
	}

	function selectInputList(name, list, defaultText) {
		var select = $("select[name='" + name + "']");
		select.empty();
		select.append($("<option>").html(defaultText).val(null));
		$.each(list, function(i, node) {
			option = $("<option>");
			if(!(name == "typeId" || name == "subPortfolioType"))
				option.html(node.name).val(node.id);
			else
				option.html(node.name).val(node.id).attr("id", this.value);
			select.append(option);
		});
	}

	function htmlBadge(text, value, category) {
		$removeIcon = $("<span>").addClass("glyphicon glyphicon-remove").css("cursor", "pointer");
		return $("<span>").addClass("badge " + category).append(text + "&nbsp;").append($removeIcon);
	}
	 
</script>