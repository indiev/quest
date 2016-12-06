/**
 * 
 */
var func = {
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
	},
	
	timeDifference:function(current, previous) {
	    var msPerMinute = 60 * 1000;
	    var msPerHour = msPerMinute * 60;
	    var msPerDay = msPerHour * 24;
	    var msPerMonth = msPerDay * 30;
	    var msPerYear = msPerDay * 365;
	
	    var elapsed = current - previous;
	    
	    if(elapsed < 0) return null;
	    if(elapsed < msPerMinute)
	    	return Math.round(elapsed/1000) + "초";   
	    else if(elapsed < msPerHour) 
	         return Math.round(elapsed/msPerMinute) + "분";   
	    else if(elapsed < msPerDay )
	         return Math.round(elapsed/msPerHour ) + "시간";   
	    else if(elapsed < msPerMonth)
	        return Math.round(elapsed/msPerDay) + "일";   
	    else if (elapsed < msPerYear)
	        return Math.round(elapsed/msPerMonth) + "달";   
	    else
	        return Math.round(elapsed/msPerYear ) + "년";   
	}
}