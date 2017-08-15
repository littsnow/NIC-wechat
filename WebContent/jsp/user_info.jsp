<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/register.css"/>
<script type="text/javascript">
$(function() {
	$("#btn-submit").click(function() {
		alert('123');
		var depart = $("#depart").val();
		alert(depart);
	/* var x = document.getElementById("band_ID").value; */
	/* 	alert(x); */
		if (depart == 0) {
			// if ($(".clsShow").html().toString() != "")//存在提示信息，则不允许提交表单
			alert('提交失败,请填写系部');
			return false;
		} else
			return true;
		
	});
});
</script>
</head>
<body  style="font-size: 60px;">
    <div class="register">
        <div class="regTop">
            <span>完善用户信息</span>
        </div>
        <div class="content">
            <div class="point">
                <span style="font-size: 45px;">用户须完善信息之后才能提交业务信息。</span>
            </div>
            <form action="${pageContext.request.contextPath }/user/adduser" method="post">
                <div class="message">
                    <table style="width: 100%; font-size: 40px;">
	                	<tr>
	                		<td style="width:30%;"><label>姓名：</label></td>
	                		<td><input type="text" placeholder="请输入姓名"  name="name" /></td>
	                	</tr>
	                	<tr>
	                		<td> <label>联系方式：</label></td>
	                		<td><input type=tel placeholder="请输入联系方式" name="telephone"/></td>
	                	</tr>
                	
                	<tr>
	                	<%-- <td colspan="6"
								style="text-align-last: center; text-align: center;"><select
								name="departmentName" id="departmentName" style="margin-center: 100%;">
									<option value="0">----请选择---</option>
									<c:forEach items="${listDepartment}" var="item" varStatus="status">
										<option value="${item.departmentName}">${item.departmentName}</option>
									</c:forEach>
							</select></td> --%>
						<td id="depart" colspan="6">
							 <select name="department">
							 	 <option value="0">选择部门</option>
								 <c:forEach items="${listDepartment}" var="item" varStatus="status">
								 
			                        
			                        <option value="${item.departmentId }">${item.departmentName }</option>
			                     </c:forEach>
	                    	</select> 
                    	</td>
					</tr>
                  
                    </table>
                </div>
                <button id=btn-submit class="submit" type="submit">提交信息</button>
            </form>
        </div>
    </div>
</body>
</html>