<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
<script src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script src="${pageContext.request.contextPath }/js/pintuer.js"></script>
</head>
<body>
<form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
      
       <!--  <if condition="$iscid eq 1"> -->
          <li>
            <select name="cid" class="input" style="width:200px; line-height:17px;" onchange="changesearch()">
              <option value="">请选择分类</option>
              <option value="">产品分类</option>
              <option value="">产品分类</option>
              <option value="">产品分类</option>
              <option value="">产品分类</option>
            </select>
          </li>
       <!--  </if> -->
        <li>
          <input type="text" placeholder="请输入搜索关键字" name="keywords" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a></li>
      </ul>
    </div>

    <div>
    <c:if test="${! empty tsList}">
    <div >
    <p>1. 技术支持</p>
    <br>
    <table class="table table-hover text-center" style="margin: 0 auto;align:center">
      <tr>
        <th style="text-align:left; padding-left:20px;">序号</th>
        <th>联系人</th>
        <th>相关照片</th>
        <th>部门</th>
        <th>具体地点</th>
       <!--  <th>需求描述</th> -->
        <th>提交时间</th>
        <th>完成时间</th>
        <th>进度</th>
        <th>处理人</th>
      </tr>
      <c:forEach items="${tsList }" var="s" varStatus="a">
        <tr>
        <!--   <td style="text-align:left; padding-left:20px;"><input type="checkbox" name="id[]" value="" />
           1</td> -->
           <td>${a.index+1}</td>
          <td>${s.userId}</td>
          <td width="10%"><img src="<%-- ${s.techsupportPicture } --%>" alt="" width="70" height="50" /></td>
          <td>${s.techsupportDepartment}</td>
          <td>${s.techsupportLocation}</td>
         <%--  <td> ${s.techsupportDescribe}</td> --%>
          <td>${s.techsupportUptime}</td>
          <c:if test="${! empty  s.techsupportEndtime}">
          <td>${s.techsupportEndtime}</td>
          </c:if>
          <c:if test="${empty s.techsupportEndtime }">
          <td></td>
          </c:if>
          <td>${tsStatus[a.index]}</td>
          <td>${tsManager[a.index]}</td>
        </tr>
       </c:forEach> 
   		</table>
   		</div>
   		</c:if>
   		
   		<c:if test="${! empty rpList}">
    <div>
    <p>2. 设备报修</p>
    <br>
    <table class="table table-hover text-center" style="margin: 0 auto;align:center">
      <tr>
        <th>序号</th>
        <th >联系人</th>
        <th>相关照片</th>
        <th>设备名称</th>
        <th>部门</th>
        <th>具体地点</th>
       <!--  <th>故障描述</th> -->
        <th>提交时间</th>
        <th >完成时间</th>
        <th>进度</th>
        <th>处理人</th>
      </tr>
      <c:forEach items="${rpList }" var="s" varStatus="a">
        <tr>
        <!--   <td style="text-align:left; padding-left:20px;"><input type="checkbox" name="id[]" value="" />
           1</td> -->
           <td>${a.index+1}</td>
          <td>${s.userId}</td>
          <td width="10%"><img src="<%-- ${s.repairPicture } --%>" alt="" width="70" height="50" /></td>
          <td>${s.repairDevicename}</td>
          <td>${s.repairDepartment}</td>
          <td>${s.repairLocation}</td>
          <%-- <td>${s.repairDescribe}</td> --%>
          <td>${s.repairUptime}</td>
          <c:if test="${!empty s.repairEndtime }">
          <td>${s.repairEndtime}</td></c:if>
          <c:if test="${empty s.repairEndtime }">
          <td></td></c:if>
          <td>${rpStatus[a.index]}</td>
          <td>${rpManager[a.index]}</td>
        </tr>
       </c:forEach> 
   		</table>
   		</div>
   		</c:if>
   		
   	<div>	
   	<c:if test="${!empty mtList}">
    
    <p>3. 日常运维</p>
    <br>
    <table class="table table-hover text-center" style="margin: 0 auto;align:center;">
      <tr>
        <th>序号</th>
        <th>联系人</th>
        <th>相关照片</th>
        <th>设备名称</th>
        <th>部门</th>
        <th>具体地点</th>
       <!--  <th>维护描述</th> -->
        <th>提交时间</th>
        <th>完成时间</th>
        <th>进度</th>
        <th>处理人</th>
      </tr>
      <c:forEach items="${mtList }" var="s" varStatus="a">
        <tr>
        <!--   <td style="text-align:left; padding-left:20px;"><input type="checkbox" name="id[]" value="" />
           1</td> -->
          <td>${a.index+1}</td>
          <td>${s.userId}</td>
          <td width="10%"><img src="<%-- ${s.maintenancePicture } --%>" alt="" width="70" height="50" /></td>
          <td>${s.maintenanceDevicename}</td>
          <td>${s.maintenanceDepartment}</td>
          <td>${s.maintenanceLocation}</td>
        <%--   <td>${s.maintenanceDescribe}</td> --%>
          <td>${s.maintenanceUptime}</td>
          <c:if test="${!empty s.maintenanceEndtime}">
          <td>${s.maintenanceEndtime}</td></c:if>
          <c:if test="${empty s.maintenanceEndtime}">
          <td></td></c:if>
          <td>${mtStatus[a.index]}</td>
          <td>${mtManager[a.index]}</td>
        </tr>
       </c:forEach> 
   		</table>
   		</c:if>
   		</div>
   		
   		</div>
   		<div>
      <table class="table table-hover text-center">
      <tr>
        <td colspan="8"><div class="pagelist"> <a href="">上一页</a> <span class="current">1</span><a href="">2</a><a href="">3</a><a href="">下一页</a><a href="">尾页</a> </div></td>
      </tr>
    </table>
  </div>
  </div>
</form>
<script type="text/javascript">

//搜索
function changesearch(){	 
		
}

//单个删除
function del(id,mid,iscid){
	if(confirm("您确定要删除吗?")){
		
	}
}

//全选
$("#checkall").click(function(){ 
  $("input[name='id[]']").each(function(){
	  if (this.checked) {
		  this.checked = false;
	  }
	  else {
		  this.checked = true;
	  }
  });
})

//批量删除
function DelSelect(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		var t=confirm("您确认要删除选中的内容吗？");
		if (t==false) return false;		
		$("#listform").submit();		
	}
	else{
		alert("请选择您要删除的内容!");
		return false;
	}
}

//批量排序
function sorts(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){	
		
		$("#listform").submit();		
	}
	else{
		alert("请选择要操作的内容!");
		return false;
	}
}


//批量首页显示
function changeishome(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		
		$("#listform").submit();	
	}
	else{
		alert("请选择要操作的内容!");		
	
		return false;
	}
}

//批量推荐
function changeisvouch(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		
		
		$("#listform").submit();	
	}
	else{
		alert("请选择要操作的内容!");	
		
		return false;
	}
}

//批量置顶
function changeistop(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){		
		
		$("#listform").submit();	
	}
	else{
		alert("请选择要操作的内容!");		
	
		return false;
	}
}


//批量移动
function changecate(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){		
		
		$("#listform").submit();		
	}
	else{
		alert("请选择要操作的内容!");
		
		return false;
	}
}

//批量复制
function changecopy(o){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){	
		var i = 0;
	    $("input[name='id[]']").each(function(){
	  		if (this.checked==true) {
				i++;
			}		
	    });
		if(i>1){ 
	    	alert("只能选择一条信息!");
			$(o).find("option:first").prop("selected","selected");
		}else{
		
			$("#listform").submit();		
		}	
	}
	else{
		alert("请选择要复制的内容!");
		$(o).find("option:first").prop("selected","selected");
		return false;
	}
}

</script>
</body>
</html>