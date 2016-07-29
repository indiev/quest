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
						
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-5">
									<div class="panel panel-default relatedSkillList">
										<div class="panel-heading">
												<h3 class="panel-title">
													relatedSkillList
												</h3>
										</div>
										<div class="panel-body">
											<div class="radio">
												<ul class="relatedSkillList">
												</ul>
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-md-2">
									<div class="row">
										<button class="btn btn-success skillAddbutton">addSkill</button>
									</div>
									<div class="row">
										<button class="btn btn-danger skillRemovebutton">remvoeSkill</button>
									</div>
								</div>
								<div class="col-md-5">
									<div class="panel panel-default non-relatedSkillList">
										<div class="panel-heading">
												<h3 class="panel-title">
													non-relatedSkillList
												</h3>
										</div>
										<div class="panel-body">
											<div class="radio">
												<ul class="non-relatedSkillList">
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						
						<input type="button" class = "btn btn-default" value="수정" onclick="updateRecord()">
						<input type="button" class = "btn btn-default" value="삭제" onclick="removeRecord()">
						
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
	});
	
	function drawEditRecord(record) {
		$updateForm = $(".updateRecordForm");
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
		
		$relatedSkillList = $("ul.relatedSkillList");
		$relatedSkillList.empty();
		ajax.get("/api/wokrs/"+record.id+"/skills", {}, function(list){
			$.each(list, function(index, elem){
				$input = $("<input type='radio' name='chk_skill'>"+elem.name+"</input>").attr('id',elem.id).val(elem.id);
				$li = $("<li>").append($input);
				$relatedSkillList.append($li);
			});
		});
		
		$nonRelatedSkillList = $("ul.non-relatedSkillList");
		$nonRelatedSkillList.empty();
		ajax.get("/api/skills", {}, function(list){
			$.each(list, function(index, elem){
				$input = $("<input type='radio' name='chk_skill'>"+elem.name+"</input>").attr('id',elem.id).val(elem.id);
				$li = $("<li>").append($input);
				$nonRelatedSkillList.append($li);
			});
		});
		
		
		if(record.parentId !=null)
			$addForm.hide();
		else
			$addForm.show();
	}
	
	function list() {
		$("#jstree").append($("<ul class='work-content'>"));
		 ajax.get(apiUrl, {}, function(list){
		 	ajax.get("/admin/work/node/list", {}, function(html) {
		 		$("ul.work-content").loadTemplate($(html).clone(), list);
		 		$("#jstree").jstree();
		 		workList = list;
			});
		 });
	
		 $('#jstree').on('select_node.jstree', function (e, data) {
				var i, j, r = [];
			    for(i = 0, j = data.selected.length; i < j; i++) {
			    	data.instance.get_node(data.selected[i]).name
			    	r.push(data.instance.get_node(data.selected[i]).text);
			    }
			    
			    $(workList).each(function(i,elem){
			    	if(elem.name == r[0])
			    		return selectedRecord = elem;
			    	else if(elem.childs.length!=0) {
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
	
	function addSubRecord() {
		$div = $(".addSubRecordForm");
		
		if($div.find(".name").val() == "") {
			alert("이름은 입력 해야만 합니다.")
			return;
		}
		
		var work = {};
		work.parentId = selectedRecord.id;
		work.name = $div.find(".name").val();
		work.description = $div.find(".description").val();
		
		ajax.post(apiUrl, work, function(result){
			if(result!= null) alert("새로운 레코드를 생성하였습니다.");
			else alert(result.mssege);
			init();
		});
	}
	
	function addRecord() {
		var work = {};
		work.parentId = null;
		work.name = "새로운 레코드 ";
		work.description = "";
		
		ajax.get(apiUrl,{},function(data){
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