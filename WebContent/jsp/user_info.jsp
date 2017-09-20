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
    <title>完善用户信息</title>
<style type="text/css">
body{
    font-size: 1em;
    font-family: "Microsoft YaHei";
    color: #535353;
    box-sizing: border-box;
}
*{
    margin: 0;
    padding: 0;
}
a{
    text-decoration: none;
    color:#374782;
}
input{
    outline: none;
}


body{
    background: #efeff4;
    width: 100%;
    height: 100%;
}
.regTop{
    width: 100%;
    padding:8% 0 6%;
    background: #50b4f9;
    text-align: center;
    color: #ffffff;
    position: relative;
}
.back{
    position: absolute;
    left: 5%;
    top: 50%;
    color: #ffffff;
}
.point{
    padding: 6% 5%;
}
.content form input:not(:nth-child(6)){
    border: 0;
    border-bottom: 1px solid #c3c3c5;
}
.content form{
    width: 100%;
    height: 35.21%;
}
.message{
    background: #ffffff;
    padding:2% 5% 0 5%;
    position: relative;
}
.message input{
    width: 90%;
    padding: 4% 0 4% 10%;
    font-size: 0.875em;
    font-family: "Microsoft YaHei";
}
.message .icons b{
    position: absolute;
    width: 18%;
    height: 4%;
    top: 7%;
    left: 7%;
}
.message .icons b img{
    width: 100%;
}
.message .icons b:nth-child(2){
    width: 5%;
    top: 26%;
    left: 6%;
}
.message .icons b:nth-child(3){
    top: 43%;
}
.message .icons b:nth-child(4){
    top: 61%;
}
.code{
    position: absolute;
    top: 60%;
    right:10%;
    color: #21a9f5;
    font-size: 0.875em;
    font-family: "Microsoft YaHei";
}
select{
    width: 55%;
    padding: 3% 2%;
    margin: 8% 5%;
    font-size: 1em;
    color: #909093;
    border: solid 1px #909093;
    font-family: "Microsoft YaHei";
}
.submit{
    width: 84%;
    margin: 4% 8%;
    background: #21a9f5;
    color: #ffffff;
    border: 0;
    padding: 3.25% 0;
    font-size: 1em;
    font-family: "Microsoft YaHei";
}
form .agree input[type="checkbox"] :default{
    outline: 5px solid #21a9f5;
}

</style>
<script type="text/javascript">
$(function() {
	$("#btn-submit").click(function() {
		var depart = $("#depart").val();
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
							<td style="width: 30%; "><input type="text" value="${openid}"
								name="openid" /></td>
							<td></td>
						</tr>
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
								 
			                        
			                        <option value="${item.departmentName }">${item.departmentName }</option>
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