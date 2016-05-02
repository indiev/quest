$(function() {
	$("input[type='checkbox'][name='all']").change(function() {
		$(".item-box input[type='checkbox']").prop("checked", $(this).is(":checked"));
	});
	$(".item-box input[type='checkbox']").change(function() {
		$("input[type='checkbox'][name='all']").prop("checked", isAllAgreed());
	});

	$(".form_table input[type='radio']").change(function() {
		$(this).parent().parent().find(".selected").removeClass("selected");
		$(this).parent().addClass("selected");
	});
	$(".card_table td").click(function() {
		if($(this).find(".wait").length != 0) {
			alert("준비 중입니다.");
		} else {
			$(".card_table .selected").removeClass("selected");
			$(this).addClass("selected");
			$("input[type='radio']").attr("checked", false);
			$(this).find("input[type='radio']").attr("checked", true);
		}
	});
	$("input.number").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
             // Allow: Ctrl+A
            (e.keyCode == 65 && e.ctrlKey === true) ||
             // Allow: Ctrl+C
            (e.keyCode == 67 && e.ctrlKey === true) ||
             // Allow: Ctrl+X
            (e.keyCode == 88 && e.ctrlKey === true) ||
             // Allow: home, end, left, right
            (e.keyCode >= 35 && e.keyCode <= 39)) {
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
});
function validcheckbasic() {
	var result = true;
	$(":required").each(function(){
		var title = $(this).attr("title");
		if($(this).is("[type='checkbox']") && !$(this).is("[type='radio']") && $(this).val() == "") {
			$(this).focus();
			alert(title + " 항목을 확인해주세요.");
			result = false;
			return false;
		}
		var size = $(this).attr("size");
		if(typeof(size) != "undefined" && $(this).val().length != size) {
			$(this).focus();
			alert(title + ", " + size + "자를 입력해주세요.");
			result = false;
			return false;
		}
		if($(this).is("input[type='checkbox']:not(:checked)")) {
			$(this).focus();
			alert(title + "에 동의해야합니다");
			result = false;
			return false;
		}
	});
	return result;
}
function isAllAgreed() {
	var length_all = $(".item-box input[type='checkbox']").length;
	var length_checked = $(".item-box input[type='checkbox']:checked").length;
	if(length_all == length_checked) return true;
	else return false;
}
function validcheck1() {
	/*var result = true;
	$(".item-box input[type='checkbox']:not(:checked):required").each(function() {
		var title = $(this).attr("title");
		$(this).focus();
		alert(title + "에 동의해야합니다");
		result = false;
		return false;
	})
	return result;*/
	/*if(isAllAgreed()) {
		return true;
	} else {
		alert("전체약관에 동의해야합니다.");
		return false;
	}*/
}
function validcheck3() {
	if(validcheckbasic()) {
		if($("input[name='password']").val() != $("input[name='passwordCheck']").val()) {
			$("input[name='password']").val('');
			$("input[name='passwordCheck']").val('');
			$("input[name='password']").focus();
			alert("비밀번호가 다릅니다. 확인해주세요");
			return false;
		}
		return true;
	} else {
		return false;
	}
}
function validcheck4() {
	if(validcheckbasic()) {
		if(typeof($("input[name='card']:checked").val()) == "undefined") {
			alert("카드를 선택해주세요.");
			return false;
		} else return true;
	}
	return false;
}