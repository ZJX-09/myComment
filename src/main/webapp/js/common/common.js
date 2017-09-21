var common = window.common || {};

/**
 * 展示指定的消息内容。
 */
common.showMessage = function(msg) {
	if(msg) {
		alert(msg);
	}
}

/**
 * 对jQuery的ajax方法的二次封装
 */
common.ajax = function(param) {
	/*进入自己的逻辑，处理参数*/
	var mergeParam = $.extend({
		timeout : 10000
	} , param , {
		complete : function(response) {
			var url = response.getResponseHeader("url");
			if(url) {
				location.href = url;
			} else {
				if(param.complete && typeof param.complete == "function") {
					param.complete();
				}
			}
		}
	});
	$.ajax(mergeParam);
}

/**
 * 页面返回码定义，与后台PageCode定义对应
 */
common.pageCode = {
		"ADD_SUCCESS" : 1000,
		"MODIFY_SUCCESS" : 1100,
    	"MODIFY_FAIL" : 1101,
		"REMOVE_SUCCESS" : 1200,
		"VALIDATE_PWD_FAIL" :1700
}

common.menuPrefix = {

	"PREFIX_MENU" : "MENU_",
	"PREFIX_ACTION" : "ACTION_"
}

common.message = {

	"NOT_ACTION":"动作不能排序"

}


