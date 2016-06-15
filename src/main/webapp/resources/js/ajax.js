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
    serializeObject:function($form) {
    	keynvalues = $form.serializeArray();
    	var data = {};
    	for(var i in keynvalues) {
    		if($.isArray(keynvalues[i])) {
    			;
    		} else {
    			key = keynvalues[i].name;
        		value = keynvalues[i].value;
        		if(typeof key.split('Id')[1] != "undefined" && key.split('Id')[1] == "" && ["parentId", "refId"].indexOf(key) == -1) data[key.split('Id')[0]] = {id:value};
        		else data[key] = value;    			
    		}
    	}
    	return data;
    },
    submit:function(form, cb) {
    	this.submit(form, {}, cb);
    },
	submit:function(form, data, cb) {
		var formData = ajax.serializeObject($(form));
		var headers = {};
		$.extend(formData, data);
		formData = JSON.stringify(formData);
		headers['_method'] = $(form).attr("method");
		$.ajax({
		    type:$(form).attr("method"),
		    url:$(form).attr("action"),
		    cache:false,
		    data:formData,
		    /*dataType:'json',*/
		    contentType:'application/json; charset=utf-8',
		    success:cb,
		    error:ajax.error
		});
	},
	get:function(u, cb) {
		this.get(u, {}, cb);
	},
	get:function(u, data, cb) {
		$.ajax({
		    type:'GET',
		    url:u,
		    cache:false,
		    /*dataType:'json',*/
		    data:data,
		    contentType:'application/json; charset=utf-8',
		    success:cb,
		    error:ajax.error
		});
	},
	login:function (url, cb) {
		this.login(url, {}, cb);
	},
	login:function (url, data, cb) {
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
    post:function (url, cb) {
    	this.post(url, {}, cb);
    },
    post:function (url, data, cb) {
        $.ajax({
            type:'POST',
            url:url,
            cache:false,
            data:JSON.stringify(data),
            contentType:'application/json; charset=utf-8',
            success:cb,
            error:ajax.error
        });
    },
    put:function (url, cb) {
    	this.put(url, {}, cb);
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
    },
    del:function (url, cb) {
    	this.del(url, {}, cb);
    },
    del:function (url, data, cb) {
        var k = '_method',
            v = 'DELETE';
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