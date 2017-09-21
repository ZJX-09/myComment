<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
		<title></title>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/all.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/pop.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/jquery.validate.css"/>
		<script type="text/javascript" src="${basePath}/js/common/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="${basePath}/js/common/validation/jquery.validate.js"></script>
		<script type="text/javascript" src="${basePath}/js/common/validation/messages_zh.js"></script>
		<script type="text/javascript" src="${basePath}/js/common/common.js"></script>
		<script type="text/javascript" src="${basePath}/js/content/businessAdd.js"></script>
	</head>
	<body style="background: #e1e9eb;">
		<form id="mainForm" method="post" action="${basePath}/businesses" enctype="multipart/form-data">
			<input type="hidden" id="message" value="${pageCode.msg}"/>
			<input type="hidden" id="basePath" value="${basePath}"/>
			<div class="right">
				<div class="current">当前位置：<a href="###">内容管理</a> &gt; 商户管理</div>
				<div class="rightCont">
					<p class="g_title fix">新增商户</p>
					<table class="tab1" width="100%">
						<tbody>
							<tr>
								<td align="right" width="15%">标题<font color="red">*</font>：</td>
								<td width="30%">
									<input name="title"  class="allInput" style="width:100%;" type="text"/>
								</td>
								<td align="right" width="15%">副标题<font color="red">*</font>：</td>
								<td width="30%">
									<input name="subtitle"  class="allInput" style="width:100%;" type="text"/>
								</td>
							</tr>
							<tr>
								<td align="right" width="10%">城市<font color="red">*</font>：</td>
								<td width="30%">
									<select name="city">
										<c:forEach items="${cityList}" var="item">
											<option value="${item.code}">${item.name}</option>
										</c:forEach>
									</select>
								</td>
								<td align="right" width="10%">类别<font color="red">*</font>：</td>
								<td width="30%">
									<select name="category">
										<c:forEach items="${categoryList}" var="item">
											<option value="${item.code}">${item.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right" width="10%">上传图片：</td>
								<td width="30%">
									<input name="imgFile" class="allInput" style="width:100%;" type="file"/>
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<%--<td align="right" width="10%">价格(单位：元)<font color="red">*</font>：</td>
								<td width="30%">
									<input name="price"  class="allInput" style="width:100%;" type="text"/>
								</td>--%>
								<td align="right" width="10%">距离(单位：米)<font color="red">*</font>：</td>
								<td width="30%">
									<input name="distance" class="allInput" style="width:100%;" type="text"/>
								</td>
							</tr>
							<tr>
								<td align="right">描述<font color="red">*</font>：</td>
								<td width="30%" colspan="3">
									<textarea name="desc" rows="5" style="width:100%;"></textarea>
								</td>
							</tr>
						</tbody>
					</table>
					<div style="text-align: center; margin-top: 30px;">
						<input class="tabSub" value="保     存" type="button" onclick="add();"/>&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="tabSub" value="返     回" type="button" onclick="goback();"/>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>