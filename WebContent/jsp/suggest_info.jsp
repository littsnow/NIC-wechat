<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>我要留言</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/common.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/register.css" />
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
</head>
<body style="font-size: 60px;">
	 <div class="register">
		<div class="regTop">
			<span>发布留言信息</span>
		</div>
		<div class="content">
			<div class="point">
				<span style="font-size: 45px;">用户须完善信息之后才能发布留言信息。</span>
			</div>
			<form action="${pageContext.request.contextPath }/suggest/addsuggest"
				method="post">
				<div class="message">
					<table style="width: 100%; font-size: 40px;">
						<tr>
							<td style="width: 30%;"><label>留言主题：</label></td>
							<td><input type="text" placeholder="请输入留言主题"
								name="title" /></td>
						</tr>
						<tr>
							<td><label>具体内容：</label></td>
							<td><input type=text placeholder="请输入具体内容"
								name="content" /></td>
						</tr>
					</table>
				</div>
				<button class="submit" type="submit">发布</button>
			</form>
		</div>
	</div> 
</body>
</html>