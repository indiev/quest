/**
 * 
 */

var ajax = {
	error:function (xhr, ajaxOptions, thrownError) {
    	if(xhr.status == 901) {
    		alert("로그인이 필요합니다.");http://mainia.tistory.com/941
    		location.href = "/";
    	} else {
    		alert('error trying.\r' + xhr.status + "/" + thrownError);
    	}
    },
    toArrayForUrl:function(urlString) {
    	var data = {};
    	urlString = urlString.replace(/\+/g, ' ');
    	var keynvalues = urlString.split('&');
    	for(var i in keynvalues) {
    		var keynvalue = keynvalues[i].split('=');
    		keynvalue[0] = decodeURIComponent(keynvalue[0]);
    		keynvalue[1] = decodeURIComponent(keynvalue[1]);
    		if(typeof keynvalue[0].split('Id')[1] != "undefined" && keynvalue[0].split('Id')[1] == "") data[keynvalue[0].split('Id')[0]] = {id:keynvalue[1]};
    		else data[keynvalue[0]] = keynvalue[1];
    	}
    	return data;
    },
	submit:function(form, cb) {
		$form = $(form);
		var data = ajax.toArrayForUrl($form.serialize());
		var headers = {};
//		data['_method'] = $form.attr("method");
		data = JSON.stringify(data);
		headers['_method'] = $form.attr("method");
		$.ajax({
		    type:$form.attr("method"),
		    url:$form.attr("action"),
		    cache:false,
		    data:data,
		    dataType:'json',
		    contentType:'application/json; charset=utf-8',
		    success:cb,
		    error:ajax.error
		});
	},
	get:function(u, data, cb) {
		$.ajax({
		    type:'GET',
		    url:u,
		    cache:false,
		    dataType:'json',
		    data:data,
		    contentType:'application/json; charset=utf-8',
		    success:cb,
		    error:ajax.error
		});
	},
	post:function (url, data, cb) {
        $.ajax({
            type:'POST',
            url:url,
            cache:false,
            data:data,
            /*contentType:'application/json; charset=utf-8',*/
            success:cb,
            error:ajax.error
        });
    },
	put:function (url, data, cb) {
        var k = '_method',
            v = 'PUT';
        data[k] = v;
        var headers = {};
        headers[k] = v;
        $.ajax({
            type:'POST',
            url:url,
            cache:false,
            data:data,
            headers:headers,
            success:cb,
            error:ajax.error
        });
    }
}