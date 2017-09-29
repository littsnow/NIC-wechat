<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
    <title>关于我们</title>
	<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/pintuer.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/admin.css">

</head>
<body  >
    
   <div style="float: left;">
   <img alt="" src="/upload/${pic}" style="width: 400px;height: 400px;">  
   </div>
   <div class="label" style="float: left; margin-left: 5%;margin-top: 3%;width: 500px;height: 200px;">
          <label style="font-size: 30px;">描述：</label>
        
        <div style="width: 100%;border: 1px solid #03b6fd; margin-top: 2%;" >
          <p style=" height:120px;width: 500px; margin: 15px;" readonly="readonly">${note}</p>
        </div>
         <label style="font-size: 30px;">联系方式：</label>
         <input value="${phone}">
        </div>
</body>
</html>