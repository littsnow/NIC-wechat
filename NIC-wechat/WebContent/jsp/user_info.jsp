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
            <form action="${pageContext.request.contextPath }/user/weixinOauth" method="post">
                <div class="message">
                    <input type="text" placeholder="请输入您的姓名"  name="name" />
                    <input type=tel placeholder="请输入手机号" pattern="0-9]{11}" name="telephone"/>
                    <select name="job">
                        <option value="choose">选择部门</option>
                        <option value="boss">老板</option>
                        <option value="staff">员工</option>
                    </select>
                    <div class="icons">
                        <b><img src="images/zc-2.jpg" alt=""/></b>
                        <b><img src="images/zc-1.jpg" alt=""/></b>
                    </div>
                </div>
                <button class="submit" type="submit">提交信息</button>
            </form>
        </div>
    </div>
</body>
</html>