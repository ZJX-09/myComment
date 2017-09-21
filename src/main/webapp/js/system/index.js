// 当前登录用户可以访问的菜单Map
var menuMap = {};

$(function(){
	common.ajax({
		// 向后台获取用户列表
		url : $("#basePath").val() + "/session/menus",
		success : function(data){
			// 根据parentId重新组装menuDtoList
			if(data && data.length > 0){

                $.each(data,function(i,value){
                    if (!menuMap[value.parentId]){
                        menuMap[value.parentId] = new Array();
                    }
                    menuMap[value.parentId].push(value);
                });
                initMenu();
			}
		}
	});

    $("#resetPwd").validate({
        submitHandler : function(form)
        {
            resetPassword();
        },
        rules : {
            "oldPassword" : "required",
            "newPassword" : "required",
            "newPasswordAgain" : {
                required : true,
                equalTo : "#newPassword",
            },
        },
        messages : {
            "oldPassword" : "请输入当前密码",
            "newPassword" : "请输入新密码",
            "newPasswordAgain" : {
                required : "请再次输入新密码",
                equalTo : "两次输入密码不相同",
            }
        },

    });

});

/**
 * 初始化菜单
 */
function initMenu(){

    var menuList = menuMap[0];
    $("#menuDiv").html();
    $.each(menuList,function(i,value){
        $("#menuDiv").append("<li onclick='clickMenu(this,"+ value.id +")'><a><span>"+ value.name +"</span></a></li>");
	});
}



/**
 * 方法描述:单击菜单（页面上部菜单），初始化子菜单（即页面左部菜单）
 */
function clickMenu(element,parentId){

    // 将同级节点的[选中样式]清空
    $("#menuDiv").children().attr("class", "");
    // 将当前单击的节点置为[选中样式]
    $(element).attr("class", "on");
    // 加载子菜单内容
    initSubMenu(parentId);
}

/**
 * 根据父菜单ID初始化子菜单
 */
function initSubMenu(parentId){
    var menuList = menuMap[parentId];
    $("#subMenuDiv").html("");
    $.each(menuList,function(i,value){
        $("#subMenuDiv").append("<h3 onclick=\"clickSubMenu(this,'" + value.url + "')\"><a>" + value.name + "</a></h3>");
    });

}
/**
 * 方法描述:单击子菜单（页面左部菜单），初始化主页面
 */
function clickSubMenu(element,path){

    // 将其他有[选中样式]的节点的样式清空
    $("#subMenuDiv").find(".on").attr("class", "");
    // 将当前单击的节点置为[选中样式]
    $(element).attr("class", "on");
    // 按指定地址加载主页面(iframe)
    $("#mainPage").attr("src",$("#basePath").val() + path);
}

/**
* 打开密码修改弹出层
*/
function openAddDiv(){
	$("#mengban").css("visibility","visible");
	$(".wishlistBox").show();
	$(".wishlistBox").find(".persongRightTit").html("&nbsp;&nbsp;修改密码");
	$("#submitVal").show();
}

/**
* 关闭密码修改弹出层
*/
function closeDiv(){
	$("#mengban").css("visibility","hidden");
	$("#oldPassword").val("");
	$("#newPassword").val("");
	$("#newPasswordAgain").val("");
	$(".wishlistBox").hide();
}

function resetPassword() {

    var basePah = $("#basePath").val();
    var name = $("#userName").val();
    var id = $("#userId").val();
    var oldPassword = $.md5( $("#oldPassword").val());
    var newPassword = $.md5( $("#newPassword").val());

    common.ajax({
        url : basePah + "/index/resetPwd",
        type : "POST",
        success : function (data) {

            if (data.code == common.pageCode.VALIDATE_PWD_FAIL){
                // 原始密码不正确
                alert(data.msg);
            }else if(data.code == common.pageCode.MODIFY_FAIL){
                alert(data.msg);
            }else{
                alert(data.msg);
                closeDiv();
            }

        },
        data : {
            "id" : id,
            "name" : name,
            "oldPassword" : oldPassword,
            "newPassword" : newPassword
        },
    });

}

/**
 * 退出登录
 */
function signOut(){

    if(confirm('您确认退出系统?')){

        $("#mainForm").submit();

	}

}