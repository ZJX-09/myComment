$(function() {
	// 初始化用户树
	initUserTree();
	// 初始化用户组树
	initGroupTree();
	// 初始化菜单树 
	initMenuTree();
	
	$("#userForm").validate({
		rules : {
			"userName" : "required",
			"chName" : "required"
		}
	});
	
	$("#groupForm").validate({
		rules : {
			"groupName" : "required"
		}
	});
	
	$("#menuForm").validate({
		rules : {
			"menuName" : "required"
		}
	});
	
	
	$("#actionForm").validate({
		rules : {
			"actionName" : "required",
			"actionUrl" : "required"
		}
	});
	
});

/**
 * 鼠标在菜单间移动时样式的切换
 */
function move(element) {
	$(".rMenuLiMove").addClass("rMenuLi");
	$(".rMenuLiMove").removeClass("rMenuLiMove");
	
	$(element).addClass("rMenuLiMove");
	$(element).removeClass("rMenuLi");
}

/**
 * 鼠标在弹出层上方时，解除鼠标按下的事件
 */
function divOver() {
	$("body").unbind("mousedown", mousedown);
}

/**
 * 单击鼠标事件：
 * 在页面任意地方单击鼠标，关闭右键弹出的菜单
 */
function mousedown() {
	$("#rMenu").css({
		"visibility" : "hidden"
	});
	$("#groupMenu").css({
		"visibility" : "hidden"
	});
	$("#menuMenu").css({
		"visibility" : "hidden"
	});
}

/**
 * 鼠标划出右键菜单层时，去除“鼠标经过菜单时”的样式。
 */
function divOut() {
	$("body").bind("mousedown", mousedown);
	
	$(".rMenuLiMove").addClass("rMenuLi");
	$(".rMenuLiMove").removeClass("rMenuLiMove");
}

/**
 * 右击时定位右键菜单展示的位置并显示
 */
function rightClick(event,rMenuId) {
	$("#" + rMenuId).css({
		"top" : event.clientY + "px",
		"left" : event.clientX + "px",
		"visibility" : "visible"
	});
}


/**
 * 清空用户维护界面里的内容
 */
function clearUser() {
	$("#userId").val("");
	$("#userName").val("");
	$("#chName").val("");
}

/**
 * 清空用户组维护界面里的内容
 */
function clearGroup() {
	$("#groupId").val("");
	$("#groupName").val("");
}

/**
 * 清空菜单维护界面里的内容
 */
function clearMenu() {
	$("#menuId").val("");
	$("#menuName").val("");
	$("#url").val("");
}

/**
 * 清空动作维护界面里的内容
 */
function clearAction() {
	$("#actionId").val("");
	$("#actionName").val("");
	$("#actionUrl").val("");
	$("#httpMethod").val("");
}

/**
 * 初始化用户树
 */
function initUserTree() {
	common.ajax({
		url : $("#basePath").val() + "/users",
		success : function(data) {
			var setting = {
				view : {
					dblClickExpand : true,// 定义双击展开
					showLine : true,
					selectedMulti : false
				},
				data : {
					simpleData : {		  // 数据格式根据id去组装树
						enable : true
					},
					key : {
						name : "chName"	  // 树中显示的字段名称
					}
				},
				callback : {
					onClick : function(event, treeId, treeNode) {
						selectUser();
					},
					onRightClick : userRightClick
				}
			};
			data.push({id:0,chName:"用户",open:true}); //向响应回来的data数组中插入一条数据
			$.fn.zTree.init($("#user"), setting, data);
		}
	});
}

/**
 * 在用户树上右击显示右键菜单同时选中节点
 */
function userRightClick(event, treeId, treeNode) {
	if(!treeNode) {
		return;
	}
	
	$.fn.zTree.getZTreeObj(treeId).selectNode(treeNode);
	rightClick(event,"rMenu");
	//参考这里咯
	if(treeNode.id === 0) {
		$(".disabled").hide();
	} else {
		$(".disabled").show();
		selectUser();
	}
}

/**
 * 选中用户后显示用户对应的用户组
 */
function selectUser() {
	var nodes = $.fn.zTree.getZTreeObj("user").getSelectedNodes();
	var groupTree = $.fn.zTree.getZTreeObj("group");
	// 先清空原本选中的选项
	var checkedNodes = groupTree.getCheckedNodes(true);
	if (checkedNodes.length > 0) {
		groupTree.checkNode(checkedNodes[0], false);
	}
	
	common.ajax({
		url : $("#basePath").val() + "/users/" + nodes[0].id,
		success : function(data) {
			// 如果当前选中的用户有用户组，则选中这个用户组
			if(data.groupId) {
				groupTree.checkNode(groupTree.getNodeByParam("id", data.groupId),true);
			}
		}
	});
}

/**
 * 初始化新增用户界面
 */
function initAddUser() {
	mousedown();
	clearUser();
	$("#userTitle").html("&nbsp;&nbsp;新增用户");
	$("#cover").show();
	$("#userMaintain").show();
}

/**
 * 初始化修改用户界面
 */
function initModifyUser() {
	mousedown();
	var nodes = $.fn.zTree.getZTreeObj("user").getSelectedNodes();
	common.ajax({
		url : $("#basePath").val() + "/users/" + nodes[0].id,
		success : function(data) {
			clearUser();
			$("#userId").val(data.id);
			$("#userName").val(data.name);
			$("#chName").val(data.chName);
			$("#userTitle").html("&nbsp;&nbsp;修改用户");
			$("#cover").show();
			$("#userMaintain").show();
		}
	});
}

/**
 * 保存用户，如果主键存在则修改，不存在则新增
 */
function saveUser() {
	if($("#userForm").valid()) {
		/*修改*/
		if ($("#userId").val()) {
			common.ajax({
				url : $("#basePath").val() + "/users/" + $("#userId").val(),
				type : "POST",
				success : function(data) {
					if (data.code === common.pageCode.MODIFY_SUCCESS) {
						initUserTree();
						closeUser();
					}
					common.showMessage(data.msg);
				},
				data : {
					"name" : $("#userName").val(),
                    "chName" : $("#chName").val(),
					"_method" : "PUT"
				}
			});
		} else {
			/*新增*/
			common.ajax({
				url : $("#basePath").val() + "/users",
				type : "POST",
				success : function(data) {
					if (data.code === common.pageCode.ADD_SUCCESS) {
						initUserTree();
						closeUser();
					}
					common.showMessage(data.msg);
				},
				data : {
					"name" : $("#userName").val(),
					"chName" : $("#chName").val(),
					"password" : $("#userName").val()
				}
			});
		}
	}
}

/**
 * 删除用户
 */
function removeUser() {
	mousedown();
	var nodes = $.fn.zTree.getZTreeObj("user").getSelectedNodes();
	if (confirm("确定要删除用户【" + nodes[0].chName + "】吗？")) {
		common.ajax({
			url : $("#basePath").val() + "/users/"+ nodes[0].id,
			type : "POST",
			success : function(data) {
				if (data.code === common.pageCode.REMOVE_SUCCESS) {
					initUserTree();
				}
				common.showMessage(data.msg);
			},
			data : {
				"_method" : "DELETE"
			}
		});
	}
}

/**
 * 重置密码
 */
function resetPassword() {
	mousedown();
	var nodes = $.fn.zTree.getZTreeObj("user").getSelectedNodes();

    if (confirm("确定要重置【" + nodes[0].chName + "】的密码吗？")) {

        common.ajax({
            url : $("#basePath").val() + "/users/" + nodes[0].id,
            type : "POST",
            success : function(data) {
                if(data.code === common.pageCode.MODIFY_SUCCESS) {
                    common.showMessage("重置密码成功！");
                } else {
                    common.showMessage(data.msg);
                }
            },
            data : {
                "_method" : "PUT",
                "password" : nodes[0].name
            }
        });


    }





}

/**
 * 为用户分配用户组
 */
function assignGroup() {
	var userNodes = $.fn.zTree.getZTreeObj("user").getSelectedNodes();
	var groupNodes = $.fn.zTree.getZTreeObj("group").getCheckedNodes();
	if (userNodes.length <= 0) {
		common.showMessage("未选中用户！");
		return;
	}
	if (userNodes[0].id == '0') {
		common.showMessage("不能为根节点分配用户组！");
		return;
	}
	if (groupNodes.length <= 0) {
		common.showMessage("未选择用户组！");
		return;
	}
	common.ajax({
		url : $("#basePath").val() + "/users/" + userNodes[0].id,
		type : "POST",
		success : function(data) {
			if(data.code === common.pageCode.MODIFY_SUCCESS) {
				common.showMessage("分配用户组成功！");
			} else {
				common.showMessage(data.msg);
			}
		},
		data : {
			"groupId" : groupNodes[0].id,
			"_method" : "PUT"
		}
	});
}

/**
 * 初始化用户组树
 */
function initGroupTree() {
	common.ajax({
		//ajax 默认GET请求
		url : $("#basePath").val() + "/groups",
		success : function(data) {
			var setting = {
				check : {
					enable : true,       //可选择
					chkStyle : "radio"   //单选
				},
				view : {
					dblClickExpand : true,// 定义双击展开
					showLine : true,
					selectedMulti : false
				},
				data : {
					simpleData : {
						enable : true
					}
				},
				callback : {
					onClick : function(event, treeId, treeNode) {
						selectGroup();
					},
					onRightClick : groupRightClick
				}
			};
			data.push({id:0,name:"用户组",open:true,nocheck:true});
			$.fn.zTree.init($("#group"), setting, data);
		}
	});
}

/**
 * 在用户组树上右击显示右键菜单同时选中节点
 */
function groupRightClick(event, treeId, treeNode) {
	if(!treeNode) {
		return;
	}
	if(treeNode.id === 0) {
		$(".disabled").hide();
	} else {
		$(".disabled").show();
		selectGroup();
	}
	rightClick(event,"groupMenu");
	$.fn.zTree.getZTreeObj(treeId).selectNode(treeNode);
}

/**
 * 初始化新增用户组界面
 */
function initAddGroup() {
	mousedown();
	clearGroup();
	$("#groupTitle").html("&nbsp;&nbsp;新增用户组");
	$("#cover").show();
	$("#groupMaintain").show();
}

/**
 * 初始化修改用户组界面
 */
function initModifyGroup() {
	mousedown();
	var nodes = $.fn.zTree.getZTreeObj("group").getSelectedNodes();
	common.ajax({
		url : $("#basePath").val() + "/groups/" + nodes[0].id,
		success : function(data) {
			clearGroup();
			$("#groupId").val(data.id);
			$("#groupName").val(data.name);
			$("#groupTitle").html("&nbsp;&nbsp;修改用户组");
			$("#cover").show();
            $("#groupMaintain").show();
		}
	});
}

/**
 * 保存用户组，如果主键存在则修改，不存在则新增
 */
function saveGroup() {
	if($("#groupForm").valid()) {
		if ($("#groupId").val()) {
			common.ajax({
				url : $("#basePath").val() + "/groups/" + $("#groupId").val(),
				type : "POST",
				success : function(data) {
					if (data.code === common.pageCode.MODIFY_SUCCESS) {
						initGroupTree();
						closeGroup();
					}
					common.showMessage(data.msg);
				},
				data : {
					"name" : $("#groupName").val(),
					"_method" : "PUT"
				}
			});
		} else {
			common.ajax({
				url : $("#basePath").val() + "/groups",
				type : "POST",
				success : function(data) {
					if (data.code === common.pageCode.ADD_SUCCESS) {
						initGroupTree();
						closeGroup();
					}
					common.showMessage(data.msg);
				},
				data : {
					"name" : $("#groupName").val()
				}
			});
		}
	}
}

/**
 * 删除用户组
 */
function removeGroup() {
	mousedown();
	var nodes = $.fn.zTree.getZTreeObj("group").getSelectedNodes();
	if (confirm("确定要删除用户组【" + nodes[0].name + "】吗？")) {
		common.ajax({
			url : $("#basePath").val() + "/groups/" + nodes[0].id,
			type : "POST",
			success : function(data) {
				if (data.code === common.pageCode.REMOVE_SUCCESS) {
					initGroupTree();
				}
				common.showMessage(data.msg);
			},
			data : {
				"_method" : "DELETE"
			}
		});
	}
}


/**
 * 初始化菜单树
 */
function initMenuTree() {
	// 初始化菜单树
	common.ajax({
		url : $("#basePath").val() + "/menus",
		success : function(data) {
			var setting = {
				edit : {
					enable : true,				//设置 zTree 是否处于编辑状态
					showRemoveBtn : false,
					showRenameBtn : false,
					drag : {
						isCopy : false,
                        isMove : true
					}
				},
				check : {
					enable : true
				},
				view : {
					dblClickExpand : true,// 定义双击展开
					showLine : true,
					selectedMulti : false
				},
				data : {
					simpleData : {
						enable : true,
						pIdKey : "comboParentId",    //父节点id设置成comboParentId，不使用默认pId
						idKey : "comboId",
					/*	 pIdKey : "parentId",
						 idKey : "id",*/
						key:{
							name:name
						}
					}
				},
				callback : {
					beforeDrop : beforeDrop,
					beforeDrag : beforeDrag,
					onRightClick : menuRightClick
				}
			};
			data.push({id:0,comboId:common.menuPrefix.PREFIX_MENU + "0",name:"菜单+动作",open:true,nocheck:true});
           /* data.push({id:0,name:"菜单",open:true,nocheck:true});*/
			$.fn.zTree.init($("#menu"), setting, data);
		}
	});
}

/**
 * 在菜单树上右击显示右键菜单同时选中节点
 */
function menuRightClick(event, treeId, treeNode) {
    if(!treeNode) {
        return;
    }

    rightClick(event,"menuMenu");
    $.fn.zTree.getZTreeObj(treeId).selectNode(treeNode);

    $(".rMenuLi").show();
    // 如果是动作节点，不显示【新增菜单】、【新增动作】
    if(treeNode.comboId.indexOf(common.menuPrefix.PREFIX_ACTION) == 0) {
        $(".menuClass").hide();
    }

    // 如果是根节点，不显示【修改】、【删除】
    if(treeNode.comboId === common.menuPrefix.PREFIX_MENU + "0") {
        $(".disabled").hide();
    }
}


/**
 * 初始化新增菜单界面
 */
function initAddMenu() {
	mousedown();
	clearMenu();
	$("#menuTitle").html("&nbsp;&nbsp;新增菜单");
	$("#cover").show();
	$("#menuMaintain").show();
}

/**
 * 初始化新增动作界面
 */
function initAddAction() {
	mousedown();
	clearAction();
	$("#actionTitle").html("&nbsp;&nbsp;新增动作");
	$("#cover").show();
	$("#actionMaintain").show();
}

/**
 * 菜单树上的修改按钮，点击时判断当前修改的是菜单还是动作
 */
function modifyOfMenu() {
	var nodes = $.fn.zTree.getZTreeObj("menu").getSelectedNodes();
	// 如果选中的是动作节点
	if(nodes[0].comboId.indexOf(common.menuPrefix.PREFIX_ACTION) == 0) {
		initModifyAction();
	} else if(nodes[0].comboId.indexOf(common.menuPrefix.PREFIX_MENU) == 0) {
		// 如果选中的是菜单节点
		initModifyMenu();
	} else {
		common.showMessage("选中了错误的节点！");
	}
}

/**
 * 初始化修改菜单界面
 */
function initModifyMenu() {
	mousedown();
	var nodes = $.fn.zTree.getZTreeObj("menu").getSelectedNodes();
	common.ajax({
		url : $("#basePath").val() + "/menus/" + nodes[0].id,
		success : function(data) {
			clearMenu();
			$("#menuId").val(data.id);
			$("#menuName").val(data.name);
			$("#url").val(data.url);
			$("#menuTitle").html("&nbsp;&nbsp;修改菜单");
			$("#cover").show();
			$("#menuMaintain").show();
		}
	});
}

/**
 * 初始化修改动作界面
 */
function initModifyAction() {
	mousedown();
	var nodes = $.fn.zTree.getZTreeObj("menu").getSelectedNodes();
	common.ajax({
		url : $("#basePath").val() + "/actions/" + nodes[0].id,
		success : function(data) {
			clearMenu();
			$("#actionId").val(data.id);
			$("#actionName").val(data.name);
			$("#actionUrl").val(data.url);
			$("#httpMethod").val(data.method);
			$("#actionTitle").html("&nbsp;&nbsp;修改动作");
			$("#cover").show();
			$("#actionMaintain").show();
		}
	});
}

/**
 * 关闭用户维护界面
 */
function closeUser() {
	$("#cover").hide();
	$("#userMaintain").hide();
}

/**
 * 关闭用户组维护界面
 */
function closeGroup() {
	$("#cover").hide();
	$("#groupMaintain").hide();
}

/**
 * 关闭菜单维护界面
 */
function closeMenu() {
	$("#cover").hide();
	$("#menuMaintain").hide();
}

/**
 * 关闭动作维护界面
 */
function closeAction() {
	$("#cover").hide();
	$("#actionMaintain").hide();
}

/**
 * 保存菜单，如果主键存在则修改，不存在则新增
 */
function saveMenu() {
	if($("#menuForm").valid()) {
		if ($("#menuId").val()) {
			common.ajax({
				url : $("#basePath").val() + "/menus/" + $("#menuId").val(),
				type : "POST",
				success : function(data) {
					if (data.code === common.pageCode.MODIFY_SUCCESS) {
						initMenuTree();
						closeMenu();
					}
					common.showMessage(data.msg);
				},
				data : {
					"name" : $("#menuName").val(),
					"url" : $("#url").val(),
					"_method" : "PUT"
				}
			});
		} else {
			var nodes = $.fn.zTree.getZTreeObj("menu").getSelectedNodes();
			var parentId = nodes[0].id;
			common.ajax({
				url : $("#basePath").val() + "/menus",
				type : "POST",
				success : function(data) {
					if (data.code === common.pageCode.ADD_SUCCESS) {
						initMenuTree();
						closeMenu();
					}
					common.showMessage(data.msg);
				},
				data : {
					"name" : $("#menuName").val(),
					"url" : $("#url").val(),
					"parentId" : parentId
				}
			});
		}
	}
}


/**
 * 保存动作，如果动作存在则修改，不存在则新增
 */
function saveAction() {
	if($("#actionForm").valid()) {
		if ($("#actionId").val()) {
			common.ajax({
				url : $("#basePath").val() + "/actions/" + $("#actionId").val(),
				type : "POST",
				success : function(data) {
					if (data.code === common.pageCode.MODIFY_SUCCESS) {
						initMenuTree();
						closeAction();
					}
					common.showMessage(data.msg);
				},
				data : {
					"name" : $("#actionName").val(),
					"url" : $("#actionUrl").val(),
					"method" : $("#httpMethod").val(),
					"_method" : "PUT"
				}
			});
		} else {
			var nodes = $.fn.zTree.getZTreeObj("menu").getSelectedNodes();
			var parentId = nodes[0].id;
			common.ajax({
				url : $("#basePath").val() + "/actions",
				type : "POST",
				success : function(data) {
					if (data.code === common.pageCode.ADD_SUCCESS) {
						initMenuTree();
						closeAction();
					}
					common.showMessage(data.msg);
				},
				data : {
					"name" : $("#actionName").val(),
					"url" : $("#actionUrl").val(),
					"method" : $("#httpMethod").val(),
					"menuId" : parentId
				}
			});
		}
	}
}

/**
 * 菜单树上的删除按钮，点击时判断当前删除的是菜单还是动作
 */
function removeOfMenu() {
	var nodes = $.fn.zTree.getZTreeObj("menu").getSelectedNodes();
	// 如果选中的是动作节点
	if(nodes[0].comboId.indexOf(common.menuPrefix.PREFIX_ACTION) == 0) {
		removeAction();
	} else if(nodes[0].comboId.indexOf(common.menuPrefix.PREFIX_MENU) == 0) {
		// 如果选中的是菜单节点
		removeMenu();
	} else {
		common.showMessage("选中了错误的节点！");
	}
}

/**
 * 删除菜单
 */
function removeMenu() {
	mousedown();
	var nodes = $.fn.zTree.getZTreeObj("menu").getSelectedNodes();
	if (confirm("将会连同菜单下的动作一起删除，确定要删除菜单【" + nodes[0].name + "】吗？")) {
		common.ajax({
			url : $("#basePath").val() + "/menus/" + nodes[0].id,
			type : "POST",
			success : function(data) {
				if (data.code === common.pageCode.REMOVE_SUCCESS) {
					initMenuTree();
				}
				common.showMessage(data.msg);
			},
			data : {
				"_method" : "DELETE"
			}
		});
	}
}

/**
 * 删除动作
 */
function removeAction() {
	mousedown();
	var nodes = $.fn.zTree.getZTreeObj("menu").getSelectedNodes();
	if (confirm("确定要删除动作【" + nodes[0].name + "】吗？")) {
		common.ajax({
			url : $("#basePath").val() + "/actions/" + nodes[0].id,
			type : "POST",
			success : function(data) {
				if (data.code === common.pageCode.REMOVE_SUCCESS) {
					initMenuTree();
				}
				common.showMessage(data.msg);
			},
			data : {
				"_method" : "DELETE"
			}
		});
	}
}

/**
 * 节点被拖拽之前的事件回调函数，
 * 返回false可以阻止拖拽
 */
function beforeDrag(treeId, treeNodes) {
	// 动作节点不参与排序
	if(treeNodes[0].comboId.indexOf(common.menuPrefix.PREFIX_ACTION) == 0) {
		common.showMessage(common.message.NOT_ACTION);
		return false;
	}
}

/**
 * 节点拖拽操作结束之前事件：
 * 将拖拽后的顺序提交，修改数据库中的顺序
 */
function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
	common.ajax({
		url : $("#basePath").val() + "/menus/" + treeNodes[0].id + "/" + targetNode.id + "/" + moveType,
		type : "POST",
		success : function(data) {
			initMenuTree();
			common.showMessage(data.msg);
		},
		data : {
			"_method" : "PUT"
		}
	});
	return true;
}

/**
 * 选中用户组后显示用户组对应的菜单
 */
function selectGroup() {
	var nodes = $.fn.zTree.getZTreeObj("group").getSelectedNodes();
	if (nodes.length > 0 && nodes[0].id != 0) {
		common.ajax({
			url : $("#basePath").val() + "/groups/" + nodes[0].id + "/menus",
			success : function(data) {
				if(data){
                    var menuTree = $.fn.zTree.getZTreeObj("menu");
                    menuTree.checkAllNodes(false);

                    // 将菜单树上,用户组对应的菜单节点勾选上
                    for (var i = 0; i < data.menuDtoList.length; i++) {
                        // 因为菜单树是一颗混合树,需要用组合ID(带前缀的ID)来选中对应的节点
                        menuTree.checkNode(menuTree.getNodeByParam("comboId", common.menuPrefix.PREFIX_MENU + data.menuDtoList[i].id), true);
                    }

                    // 将菜单树上,用户组对应的动作节点勾选上
                    for (var i = 0; i < data.actionDtoList.length; i++) {
                        // 因为菜单树是一颗混合树,需要用组合ID(带前缀的ID)来选中对应的节点
                        menuTree.checkNode(menuTree.getNodeByParam("comboId", common.menuPrefix.PREFIX_ACTION + data.actionDtoList[i].id), true);
                    }
				}

			}
		});
	}
}

/**
 * 为用户组分配菜单
 */
function assignMenu() {
	var groupNodes = $.fn.zTree.getZTreeObj("group").getSelectedNodes();
	var menuNodes = $.fn.zTree.getZTreeObj("menu").getCheckedNodes();
	if (groupNodes.length <= 0) {
		common.showMessage("未选中用户组！");
		return;
	}
	if (groupNodes[0].id == '0') {
		common.showMessage("不能为根节点分配菜单！");
		return;
	}
    if (menuNodes.length <= 0){
        common.showMessage("你还没有选择需要分配的菜单");
        return;
    }


	/*var param = {};
	for (var i = 0; i < menuNodes.length; i++) {

		param["menuIdList["+i+"]"] = menuNodes[i].id;
		// 为什么这么处理下标没有了？？？
        /!*param.menuIdList.push(menuNodes[i].id);*!/

	}*/


    var param = {};
    for (var i = 0; i < menuNodes.length; i++) {
        // 将勾选的菜单节点分成两组：因为在同一颗树上有两种节点：菜单节点，动作菜点
        if(menuNodes[i].comboId.indexOf(common.menuPrefix.PREFIX_MENU) == 0) {
            param["menuIdList[" + i + "]"] = menuNodes[i].id;
        } else if(menuNodes[i].comboId.indexOf(common.menuPrefix.PREFIX_ACTION) == 0) {
            param["actionIdList[" + i + "]"] = menuNodes[i].id;
        } else {
            common.showMessage("选中了错误的节点！");
        }
    }

	common.ajax({
		url : $("#basePath").val() + "/groups/" + groupNodes[0].id + "/menus",
		type: "POST",
		success : function(data) {
			common.showMessage(data.msg);
		},
		data : param
	});
}