<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/pintuer.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/admin.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-3.3.0/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-3.3.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-3.3.0/css/bootstrap-theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-3.3.0/css/bootstrap-theme.min.css">

<script src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script src="${pageContext.request.contextPath }/js/pintuer.js"></script>
<!-- 可输入下拉框 -->
<script
	src="${pageContext.request.contextPath }/js/jquery.editable-select.min.js"></script>
<script type="text/javascript">
//搜索
$(function(){
	$("#searchArticleForm").off();
	$("#searchArticleForm").bind("submit",function(){
		var key=$(this).find("select[name=key]").val();
		
		var val1=$(this).find("select[name=val]").val();
		var val2=$(this).find("input[name=val]").val();
		if(val1==null)
			{

			$(".panel").load("${pageContext.request.contextPath }/feedback/searchLists",{
				key:key,
				val:val2
			});  
			
			}
		else{
			$(".panel").load("${pageContext.request.contextPath }/feedback/searchLists",{
				key:key,
				val:val1
			}); 
		}
		
		//阻止表单默认行为 （提交）
		return false;
	});
	
});
		
	function btnChange(){
		var childs=new Array();
		var departname=new Array();
		
		<c:forEach items="${dpNameList }" var="item">
		departname.push("${item}");
		</c:forEach>
		var parentEle=document.getElementById("parent");	
   
	 var childEle=document.getElementById("child");
	childEle.innerHTML="";//每次进来先清空子搜索列表
	/* alert("进入了"+parentEle.innerHTML) */;
	var parentValue=parentEle.options[parentEle.selectedIndex].value;
	/* alert("进入了123"+parentValue); */
	var ddl=document.getElementById("child");
	switch(parentValue){
	case "业务类型":
		ddl.style.display="inline-block";
		$("#inputsearch").hide();
	     childs=['技术支持','设备报修','日常运维'];
		break;
	case"部门":
		ddl.style.display="inline-block";
		$("#inputsearch").hide();
		childs=departname;
		break;
	default:
	   ddl.style.display="none";
	    $("#inputsearch").show();
	    $("#inputsearch").style.display="inline-block";
		
	}
	 
	   for(var i=0;i<childs.length;i++){
		   /* alert("for"); */
           var option=document.createElement('option'); //先创建option
           
           var textNode=document.createTextNode(childs[i]); //再把城市名作为子节点填入，也可用innerHTML
           childEle.appendChild(option);
           option.text = childs[i];
        
       } 
} 
</script>
</head>
<body>
	<%-- <form method="post" action="${pageContext.request.contextPath }/serviceDock/searchLists" id="listform">  --%>
	<div class="panel admin-panel">
		<div class="panel-head"></div>
		<form role="search" id="searchArticleForm" class="load" method="post"
			action="${pageContext.request.contextPath }/feedback/searchLists">
			<div class="padding border-bottom ">
				<ul class="search" style="padding-left: 10px;">

					<li><select id="parent" onchange='btnChange();' name="key"
						class="input" style="width: 200px; line-height: 17px;">
						<c:if test="${empty typekey}">
							<option value="">请选择分类</option>
						</c:if>
						<c:if test="${!empty typekey}">
							<option value="${typekey}">${typekey} </option>
						</c:if>
							<option value="业务类型">业务类型</option>
							<option value="部门">部门</option>
							<option value="地点">地点</option>
							<option value="提交时间">提交时间</option>
							<!--  <option value="keyword">关键字筛选</option> -->
					</select></li>
					<li><select id="child" type="text" name="val" class="input"
						style="width: 250px; line-height: 17px; display: inline-block;">
							<c:if test="${empty typeval}">
								<option value="">请下拉选择</option>
							</c:if>
							<c:if test="${!empty typeval}">
								<option value="${typeval}">${typeval} </option>
							</c:if>
					</select> 
						<c:if test="${empty typeval}">
							<input id="inputsearch" type="text" placeholder="请输入搜索关键字"
						name="val"
						style="font-size: 14px; padding: 10px; border: solid 1px #ddd; border-radius: 3px; width: 250px; line-height: 17px; display: none;" />
						
						</c:if>
						<c:if test="${!empty typeval}">
							<input id="inputsearch" type="text" placeholder=${typeval}
						name="val"
						style="font-size: 14px; padding: 10px; border: solid 1px #ddd; border-radius: 3px; width: 250px; line-height: 17px; display: none;" />
						
						</c:if>
						
						
						
						<input type="submit" style="width: 60px;"
						class="button border-main icon-search" value="查询" /></li>
				</ul>
			</div>
		</form>
	<form method="post"
						action="${pageContext.request.contextPath }/feedback/toUpdateStatus"
						id="subform">
						
		<select  name="status" id="status_name"
		 style="text-align-last: center; text-align: center;margin-center: 87%;margin-top:-5.5%;float:left; width: 118px;height: 43px; position: relative;left: 60%;top: 5%;">
			<option value="">--请选择状态--</option>
			<c:forEach items="${listStatus}" var="item" varStatus="status">
			<option value="${item.statusName}">${item.statusName}</option>
			</c:forEach>
		</select>				
		<select name="manager" id="manager_name" style="text-align-last: center; text-align: center;margin-center: 87%;margin-top:-5.5%;float:left; width: 128px;height: 43px; position: relative;left: 72%;top: 5%;">
			<c:if test="${!empty loginmanager }" >
			<option value="">${loginmanager.managerName}</option>
			</c:if>
			<c:if test="${empty loginmanager }" >
					<option value="">请选择处理人</option>
			</c:if>
			<c:forEach items="${listManager }" var="item" varStatus="status">
				<option value="${item.managerName}">${item.managerName}</option>
			</c:forEach>
		</select>				
		<div class="button-group" style="margin-top:-5.5%;float: left;margin-left: 85%;width: 200px;">
			<input type="submit" style="width: 60px;"
				class="button border-main icon-search submit" value="提交" />
			<input type="button" style="width: 60px;"
				class="button border-main icon-search feedback" value="反馈" />
		</div>				
						
		<div id="divtable">
			<table class="table table-hover text-center"
				style="margin: 0 auto; align: center; margin-top: 10px;">
				<tr>
					<th style="text-align: left; padding-left: 20px;">序号</th>
					<th><input type="hidden"></th>

					<th>联系人</th>
					<th>业务类型</th>
					<th>设备名称</th>
					<th>部门</th>
					<th>具体地点</th>
					<th>查看图片</th>
					<th>提交时间</th>
					<th>进度</th>
					<th>处理人</th>
				<!-- <th colspan="2">操作</th> -->
					</tr>
				<c:forEach items="${tsList }" var="s" varStatus="a">
					<%-- <form method="post"
						action="${pageContext.request.contextPath }/feedback/toUpdateStatus"
						id="subform"> --%>
					<tr>
						<td>${a.index+1}</td>
						<td><input type="checkbox" name="techsupportId"
							value="${s.techsupportId}"></td>
						<td>${tsUser[a.index]}</td>
						<td>技术支持</td>
						<td>${s.techsupportDevicename}</td>
						<td>${s.techsupportDepartment}</td>
						<td style="width:16%; word-wrap:break-word;word-break:break-all;">${s.techsupportLocation}</td>
						<c:if test="${!empty s.techsupportPicture }">
						<td><a
								href="${pageContext.request.contextPath }/load/picture?pName=${s.techsupportPicture}">点击查看</a></td>
						</c:if>
						<c:if test="${empty s.techsupportPicture }">
						<td>无图片</td>
						</c:if>
						<td>${s.techsupportUptime}</td>
						<td colspan="1"
							style="text-align-last: center; text-align: center;">
							<c:if test="${!empty tsStatus[a.index] }" >
								<input type="text" style="width: 100px;"  readonly="readonly"	value="${tsStatus[a.index]}" name="teastatus_name">
							</c:if>
							<c:if test="${empty tsStatus[a.index] }">
								<input type="text" style="width: 100px;"  readonly="readonly" placeholder="请选择" name="teastatus_name">
							</c:if>
						</td>
						<td colspan="1"
							style="text-align-last: center; text-align: center;">
							<c:if test="${!empty tsManagerList[a.index] }">
							<input type="text" style="width: 100px;"  readonly="readonly" 
									name="teamanager_name" value="${tsManagerList[a.index] }">
							</c:if>
							<c:if test="${empty tsManagerList[a.index] }">
							<input type="text" style="width: 100px;"  readonly="readonly" 
									placeholder="暂无处理人" name="teamanager_name">
							</c:if>
						</td>
						<td>
					
						</td>
					</tr>
					<!-- </form> -->
				</c:forEach>
				<c:forEach items="${rpList }" var="s" varStatus="b">
						<tr>
							<td id="rp">${(b.index+1)+(tsLen)}</td>
							<td><input type="checkbox" 	value="${s.repairId}" name="repairId"
						></td>
								
							
							<td>${rpUser[b.index]}</td>
							<td>设备报修</td>
							<td>${s.repairDevicename}</td>
							<td>${s.repairDepartment}</td>
							<td>${s.repairLocation}</td>
							<c:if test="${!empty s.repairPicture }">
						<td><a
								href="${pageContext.request.contextPath }/load/picture?pName=${s.repairPicture}">点击查看</a></td>
						</c:if>
						<c:if test="${empty s.repairPicture }">
						<td>无图片</td>
						</c:if>
							<td>${s.repairUptime}</td>
							<td colspan="1"
								style="text-align-last: center; text-align: center;">
								<c:if test="${!empty rpStatus[b.index] }" >
									<input type="text" style="width: 100px;"  readonly="readonly"	
									value="${rpStatus[b.index] }" name="rpstatus_name">
								</c:if>
								<c:if test="${empty rpStatus[b.index] }">
									<input type="text" style="width: 100px;"  readonly="readonly" 
									placeholder="请选择" name="rpstatus_name">
								</c:if>
							</td>
							<td colspan="1"
							style="text-align-last: center; text-align: center;">
								<c:if test="${!empty rpManagerList[b.index] }">
								<input type="text" style="width: 100px;"  readonly="readonly" 
										name="rpmanager_name" value="${rpManagerList[b.index] }">
								</c:if>
								<c:if test="${empty rpManagerList[b.index] }">
								<input type="text" style="width: 100px;"  readonly="readonly" 
										placeholder="暂无处理人" name="rpmanager_name">
								</c:if>
							
							</td>
						</tr>
				</c:forEach>
				<c:forEach items="${mtList }" var="s" varStatus="c">
					
						<tr>
							<td id="mt">${(c.index+1)+(tsLen)+(rpLen)}</td>
							<td><input type="checkbox" value="${s.maintenanceId}"
							name="maintenanceId"></td>
							
						<td>${mtUser[c.index]}</td>
							<td>日常运维</td>
							<td>${s.maintenanceDevicename}</td>
							<td>${s.maintenanceDepartment}</td>
							<td>${s.maintenanceLocation}</td>
							   <c:if test="${!empty s.maintenancePicture }">
						<td><a
								href="${pageContext.request.contextPath }/load/picture?pName=${s.maintenancePicture}">点击查看</a></td>
						</c:if>
						<c:if test="${empty s.maintenancePicture }">
						<td>无图片</td>
						</c:if>
							<td>${s.maintenanceUptime}</td>

							<td colspan="1"
							style="text-align-last: center; text-align: center;">
							<c:if test="${!empty mtStatus[c.index] }" >
									<input type="text" style="width: 100px;"  readonly="readonly"	
									value="${mtStatus[c.index] }" name="mtstatus_name">
								</c:if>
								<c:if test="${empty mtStatus[c.index] }">
									<input type="text" style="width: 100px;"  readonly="readonly" 
									placeholder="请选择" name="mtstatus_name">
								</c:if>
							
							</td>
							<td colspan="1"
							style="text-align-last: center; text-align: center;">
								<c:if test="${!empty mtManagerList[c.index] }">
								<input type="text" style="width: 100px;"  readonly="readonly" 
										name="mtmanager_name" value="${mtManagerList[c.index] }">
								</c:if>
								<c:if test="${empty mtManagerList[c.index] }">
								<input type="text" style="width: 100px;"  readonly="readonly" 
										placeholder="暂无处理人" name="mtmanager_name">
								</c:if>
							</td>
						</tr>
				</c:forEach>
			</table>
		</div>
	

	
</form>
	</div>
	<script type="text/javascript">
	$(".submit").click(function(){
		
		var subform=$('#subform');
		subform.attr('action','${pageContext.request.contextPath }/feedback/toUpdateStatus');
		subform.submit();
	});
	$(".feedback").click(function(){
		
		var subform=$('#subform');
		subform.attr('action','${pageContext.request.contextPath }/feedback/toaddinfo');
		subform.submit();
	});
	</script>
</body>
</html>