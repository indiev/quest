/**
 * 
 */
var test = {
	list:function(list) {
		console.log(list);
		$table = $("<table>").css("border", "solid 1px");
		$("body").append($table);
		
		$thead = $("<thead>");
		$thead_tr = $("<tr>");
		$thead.append($thead_tr);
		$table.append($thead);
		$tbody = $("<tbody>");
		$table.append($tbody);
		
		for(i  in list) {
			$tr = $("<tr>");
			for(key in list[i]) {
				if(i == 0) $thead_tr.append($("<th>").append(key));
				$td = $("<td>");
				if(key.indexOf("Date") >= 0) $td.append($("<span>").html($.datepicker.formatDate('yy년 mm월 dd일', new Date(list[i][key]))));
				else $td.append($("<span>").html(list[i][key]));			
				$tr.append($td)
			}
			if(i == 0) {
				$thead_tr.append($("<th>").append("수정"));
				$thead_tr.append($("<th>").append("삭제"));
			}
			$tr.append($("<td>").append($("<input>").attr("type", "button").val("수정")));
			$tr.append($("<td>").append($("<input>").attr("type", "button").val("삭제")));
			$tbody.append($tr);
		}
	}
}