<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/pintuer.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
<title>用户列表</title>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong class="icon-reorder">用户列表</strong>
		</div>
		<div class="padding border-bottom">
			<button type="button" class="button border-yellow"
				onclick="window.location.href='#adminrRegistered'">
				<span class="icon-plus-square-o"></span> 添加内容
			</button>
		</div>

		<table class="table table-hover text-center">

			<tr>
				<th width="10%">ID</th>
				<th width="20%">姓名</th>
				<th width="15%">电话</th>
				<th width="20%">部门</th>
				<th width="10%">标记</th>
				<th width="15%">操作</th>
			</tr>

			<c:forEach items="${userlist}" var="item" varStatus="status">
				<tr>
					<td>${item.userId }</td>
					<td>${item.userName }</td>
					<td>${item.userTelephone }</td>
					<td>${item.userDepartment }</td>
					<td>${item.userOpenid }</td>
					<td>
						<div class="button-group">
							<a class="button border-main" href="#add"> <span
								class="icon-edit"></span>修改
							</a> <a class="button border-red" href="javascript:void(0)"
								onclick="return del(1,1)"> <span class="icon-trash-o"></span>删除
							</a>
						</div>
					</td>
				</tr>
			</c:forEach>

			<!-- <tr>
				<td>1</td>
				<td>张三</td>
				<td>789</td>
				<td>一系</td>
				<td>1</td>
				<td><div class="button-group">
						<a class="button border-main" href="#add"><span
							class="icon-edit"></span> 修改</a> <a class="button border-red"
							href="javascript:void(0)" onclick="return del(1,1)"><span
							class="icon-trash-o"></span> 删除</a>
					</div></td>
			</tr>
			<tr>
				<td>2</td>
				<td>赵四</td>
				<td>456</td>
				<td>五系</td>
				<td>1</td>
				<td><div class="button-group">
						<a class="button border-main" href="#add"><span
							class="icon-edit"></span> 修改</a> <a class="button border-red"
							href="javascript:void(0)" onclick="return del(1,1)"><span
							class="icon-trash-o"></span> 删除</a>
					</div></td>
			</tr>
			<tr>
				<td>3</td>
				<td>王五</td>
				<td>123</td>
				<td>九系</td>
				<td>1</td>
				<td><div class="button-group">
						<a class="button border-main" href="#adminrRegistered"><span
							class="icon-edit"></span> 修改</a> <a class="button border-red"
							href="javascript:void(0)" onclick="return del(1,1)"><span
							class="icon-trash-o"></span> 删除</a>
					</div></td>
			</tr> -->

		</table>
	</div>
	<script type="text/javascript">
		function del(id, mid) {
			if (confirm("您确定要删除吗?")) {

			}
		}
	</script>
</body>
</html>