<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE" />
        <title>权限维护</title>
        <link href="${basePath}/css/all.css" rel="stylesheet" type="text/css" />
        <link href="${basePath}/css/pop.css" rel="stylesheet" type="text/css" />
        <link href="${basePath}/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet" />
        <link href="${basePath}/css/jquery.validate.css" type="text/css" rel="stylesheet" />
        <link href="${basePath}/css/rmenu.css" type="text/css" rel="stylesheet" />
        <script src="${basePath}/js/common/jquery-1.8.3.js" type="text/javascript"></script>
        <script src="${basePath }/js/common/jquery.ztree.all.js" type="text/javascript"></script>
        <script src="${basePath}/js/common/validation/jquery.validate.js" type="text/javascript"></script>
		<script src="${basePath}/js/common/validation/messages_zh.js" type="text/javascript"></script>
        <script src="${basePath}/js/common/common.js" type="text/javascript"></script>
        <script src="${basePath}/js/system/auth.js" type="text/javascript"></script>
        <style type="text/css">
            
        </style>
    </head>
    <body style="background: #e1e9eb;">
		<input type="hidden" value="${basePath}" id="basePath"/>
        <!-- 用户维护页面 -->
        <div class="wishlistBox" id="userMaintain" style="display:none;left:450px;top:150px;">
            <div class="personRigTop persongBgimg" style="height:200px;width:480px;">
                <div class="persongRightTit" style="width:480px;" id="userTitle">&nbsp;&nbsp;新增用户</div>
                <div class="persongRigCon">
                	<form id="userForm">
	                    <table class="x-form-table">
	                        <tbody>
	                        <tr class="line">
	                            <td class="left" width="10%"><em class="required">*</em><label>用户名：</label></td>
	                            <td width="90%">
	                                <input type="hidden" id="userId"/>
	                                <input type="text" class="normal-input" id="userName" name="userName" style="width: 240px;"/>
	                            </td>
	                        </tr>
	                        <tr class="line">
	                            <td class="left"><em class="required">*</em><label>中文名：</label></td>
	                            <td>
	                                <input type="text" class="normal-input" name="chName" id="chName" style="width: 240px;"/>
	                            </td>
	                        </tr>
	                        <tr class="line">
	                            <td class="left"><label>密码：</label></td>
	                            <td>默认密码与用户名相同。<font color="red">请创建后立刻登录修改默认密码！</font></td>
	                        </tr>
	                        <tr>
	                            <td class="left"></td>
	                            <td class="submit">
	                                <input class="tabSub" type="button" value="提交" onclick="saveUser();"/>
	                                <input class="tabSub" type="reset" value="关闭" onclick="closeUser();"/>
	                            </td>
	                        </tr>
	                        </tbody>
	                    </table>
                    </form>
                </div>
            </div>
        </div>


        <!-- 用户组维护页面 -->
        <div class="wishlistBox" id="groupMaintain" style="display:none;left:450px;top:150px;">
            <div class="personRigTop persongBgimg" style="height:200px;width:480px;">
                <div class="persongRightTit" style="width:480px;" id="groupTitle">&nbsp;&nbsp;新增用户组</div>
                <div class="persongRigCon">
                	<form id="groupForm">
	                    <table class="x-form-table">
	                        <tbody>
	                        <tr class="line">
	                            <td class="left" width="10%"><em class="required">*</em><label>用户组名：</label></td>
	                            <td width="90%">
	                                <input type="hidden" id="groupId"/>
	                                <input type="text" class="normal-input" id="groupName" name="groupName" style="width: 240px;"/>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td class="left"></td>
	                            <td class="submit">
	                                <input class="tabSub" type="button" value="提交" onclick="saveGroup();"/>
	                                <input class="tabSub" type="reset" value="关闭" onclick="closeGroup();"/>
	                            </td>
	                        </tr>
	                        </tbody>
	                    </table>
                    </form>
                </div>
            </div>
        </div>

        <!-- 菜单维护页面 -->
        <div class="wishlistBox" id="menuMaintain" style="display:none;left:450px;top:150px;">
            <div class="personRigTop persongBgimg" style="height:200px;width:480px;">
                <div class="persongRightTit" style="width:480px;" id="menuTitle">&nbsp;&nbsp;新增菜单</div>
                <div class="persongRigCon">
	                <form id="menuForm">
	                    <table class="x-form-table">
	                        <tbody>
	                        <tr class="line">
	                            <td class="left" width="10%"><em class="required">*</em><label>菜单名称：</label></td>
	                            <td width="90%">
	                                <input type="hidden" id="menuId"/>
	                                <input type="text" class="normal-input" id="menuName" name="menuName" style="width: 240px;"/>
	                            </td>
	                        </tr>
	                        <tr class="line">
	                            <td class="left" width="10%"><label>url：</label></td>
	                            <td width="90%">
	                                <input type="text" class="normal-input" id="url" style="width: 240px;"/>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td class="left"></td>
	                            <td class="submit">
	                                <input class="tabSub" type="button" value="提交" onclick="saveMenu();"/>
	                                <input class="tabSub" type="reset" value="关闭" onclick="closeMenu();"/>
	                            </td>
	                        </tr>
	                        </tbody>
	                    </table>
                    </form>
                </div>
            </div>
        </div>
        
        <!-- 动作维护页面 -->
        <div class="wishlistBox" id="actionMaintain" style="display:none;left:450px;top:150px;">
            <div class="personRigTop persongBgimg" style="height:200px;width:480px;">
                <div class="persongRightTit" style="width:480px;" id="actionTitle">&nbsp;&nbsp;新增动作</div>
                <div class="persongRigCon">
	                <form id="actionForm">
	                    <table class="x-form-table">
	                        <tbody>
	                        <tr class="line">
	                            <td class="left" width="10%"><em class="required">*</em><label>动作名称：</label></td>
	                            <td width="90%">
	                                <input type="hidden" id="actionId"/>
	                                <input type="text" class="normal-input" id="actionName" name="actionName" style="width: 240px;"/>
	                            </td>
	                        </tr>
	                        <tr class="line">
	                            <td class="left" width="10%"><em class="required">*</em><label>url：</label></td>
	                            <td width="90%">
	                                <input type="text" class="normal-input" id="actionUrl" name="actionUrl" style="width: 240px;"/>
	                            </td>
	                        </tr>
	                        <tr class="line">
	                            <td class="left" width="10%"><label>HTTP动作：</label></td>
	                            <td width="90%">
	                                <select id="httpMethod" style="width: 240px;">
	                                	<option value=""> === 不限制HTTP动作 === </option>
	                                	<c:forEach items="${httpMethodList}" var="item">
	                                		<option value="${item.code}">${item.name}</option>
	                                	</c:forEach>
	                                </select>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td class="left"></td>
	                            <td class="submit">
	                                <input class="tabSub" type="button" value="提交" onclick="saveAction();"/>
	                                <input class="tabSub" type="reset" value="关闭" onclick="closeAction();"/>
	                            </td>
	                        </tr>
	                        </tbody>
	                    </table>
                    </form>
                </div>
            </div>
        </div>


        <!-- 蒙板 -->
        <div class="window" id="cover"><div class="tip"></div></div>

        <!-- 用户树右键菜单 -->
        <div id="rMenu" class="rMenu" onmouseout="divOut();" onmouseover="divOver()">
            <ul class="rMenuUi">
                <li class="rMenuLi" onmousemove="move(this);" onclick="initAddUser();">新增</li>
                <li class="rMenuLi disabled" onmousemove="move(this);" onclick="initModifyUser();">修改</li>
                <li class="rMenuLi disabled" onmousemove="move(this);" onclick="resetPassword();">重置密码</li>
                <li class="rMenuLi disabled" onmousemove="move(this);" onclick="removeUser();">删除</li>
            </ul>
        </div>

        <!-- 用户组树右键菜单 -->
        <div id="groupMenu" class="rMenu" onmouseout="divOut();" onmouseover="divOver()">
            <ul class="rMenuUi">
                <li class="rMenuLi" onmousemove="move(this);" onclick="initAddGroup();">新增</li>
                <li class="rMenuLi disabled" onmousemove="move(this);" onclick="initModifyGroup();">修改</li>
                <li class="rMenuLi disabled" onmousemove="move(this);" onclick="removeGroup();">删除</li>
            </ul>
        </div>

        <!-- 菜单树右键菜单 -->
        <div id="menuMenu" class="rMenu" onmouseout="divOut();" onmouseover="divOver()">
            <ul class="rMenuUi">
                <li class="rMenuLi menuClass" onmousemove="move(this);" onclick="initAddMenu();">新增菜单</li>
                <li class="rMenuLi disabled menuClass" onmousemove="move(this);" onclick="initAddAction();">新增动作</li>
                <li class="rMenuLi disabled" onmousemove="move(this);" onclick="modifyOfMenu();">修改</li>
                <li class="rMenuLi disabled" onmousemove="move(this);" onclick="removeOfMenu();">删除</li>
            </ul>
        </div>

		<!-- 三棵权限树 -->
        <table border="0" style="height:550px" align="left" width="100%">
            <tr>
                <td width="30%" align="left" valign="top" style="border-right:#999999 1px dashed">
                    <ul id="user" class="ztree" style="width:260px; overflow:auto;"></ul>
                </td>
                <td width="30%" align="left" valign="top" style="border-right:#999999 1px dashed">
                    <ul id="group" class="ztree" style="width:260px; overflow:auto;"></ul>
                </td>
                <td width="40%" align="left" valign="top" style="border-right:#999999 1px dashed">
                    <ul id="menu" class="ztree" style="width:260px; overflow:auto;"></ul>
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="right"><input type="button" class="tabSub" value="分配用户组" onclick="assignGroup();"/></td>
                <td align="right"><input type="button" class="tabSub" value="分配菜单" onclick="assignMenu();"/></td>
            </tr>
        </table>
    </body>
</html>