<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
</head>
<body>
    <div class="register">
        <div class="regTop" style="font-size: 40px;">
            <span>完善用户信息</span>
        </div>
        <div class="content">
            <div class="point">
                <span>用户必须完善信息之后才能提交相关业务信息。</span>
            </div>
            <form action="${pageContext.request.contextPath }/user/adduser" method="post">
                <div class="message">
                    <table style="width: 100%; font-size: 40px;">
	                	<tr>
	                		<td style="width:30%;"><label>姓名：</label></td>
	                		<td><input type="text" placeholder="请输入设备名称"  name="name" /></td>
	                	</tr>
	                	<tr>
	                		<td> <label>联系方式：</label></td>
	                		<td><input type=tel placeholder="请输入具体描述" name="telephone"/></td>
	                	</tr>
                	</table>
                    <select name="department">
                        <option value="qwe">选择部门</option>
                        <option value="qwe">计算机系</option>
                        <option value="asd">化工系</option>
                    </select>
                    
                </div>
                <button class="submit" type="submit">提交信息</button>
            </form>
        </div>
    </div>
</body>
</html>