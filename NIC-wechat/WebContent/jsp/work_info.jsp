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
<body style="font-size: 50px;">
    <div class="register">
        <div class="regTop" >
            <span>提交报修信息</span>
        </div>
        <div class="content">
            <div class="point">
                <span>用户必须完善信息之后才能提交相关业务信息。</span>
            </div>
            <form action="${pageContext.request.contextPath }/work/addwork" method="post">
                <div class="message">
                <select name="worktype">
                        <option value="choose">选择业务类型</option>
                        <option value="1">技术支持</option>
                        <option value="2">设备报修</option>
                        <option value="3">日常运维</option>
                    </select></br>
                <table style="width: 100%; font-size: 40px;">
                	<tr>
                		<td style="width:30%;"><label>设备名称：</label></td>
                		<td><input type="text" placeholder="请输入设备名称"  name="devicename" /></td>
                	</tr>
                	<tr>
                		<td> <label>具体描述：</label></td>
                		<td><input type=text placeholder="请输入具体描述" name="description"/></td>
                	</tr>
                	<tr>
                		<td> <label>具体地点：</label></td>
                		<td><input type=text placeholder="请输入具体地点"  name="location"/></td>
                	</tr>
                	<tr>
                		<td style="border: 1px solid blue;"><input type="text"  name="img" style="width:30%; float:left;"  value="" /></td>
                		<td ><input type="button"  value="+ 浏览上传"  style="float:left;"></td>
                	</tr>
                </table>
                
                </div>
                <button class="submit" type="submit">提交信息</button>
            </form>
        </div>
    </div>
</body>
</html>