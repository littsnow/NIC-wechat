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
<title>提交业务信息</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/common.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/register.css" />
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
function config() {
	$.ajax({
		type : 'post',
		url : " http://9dceb4a0.ngrok.io/NIC-wechat/work/config",
		data : {'url' :location.href.split('#')[0]},
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			var obj = eval(data[0]);
			wx.config({
				debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId : obj.appId, // 必填，公众号的唯一标识
				timestamp : obj.timestamp, // 必填，生成签名的时间戳
				nonceStr : obj.nonceStr, // 必填，生成签名的随机串
				signature : obj.signature,// 必填，签名，见附录1
				jsApiList : [ 'chooseImage', 'uploadImage',
						'downloadImage','checkJsApi' ]
			});
		}
	});
}
function isWeiXin5() {
    var ua = window.navigator.userAgent.toLowerCase();
    var reg = /MicroMessenger\/[5-9]/i;
    return reg.test(ua);
}


window.onload = function() {
    //     if (isWeiXin5() == false) {
    //           alert("您的微信版本低于5.0，无法使用微信支付功能，请先升级！");
    //         }
    config();
};


function takePicture(){
    wx.chooseImage({
        count: 1, // 默认9
        sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
        success: function (res) {
            var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片

            wx.uploadImage({
                localId: localIds.toString(), // 需要上传的图片的本地ID，由chooseImage接口获得
                isShowProgressTips: 1, // 默认为1，显示进度提示
                success: function (res) {
                    var mediaId = res.serverId; // 返回图片的服务器端ID，即mediaId
                    alert("上传成功");
                    //将获取到的 mediaId 传入后台 方法savePicture
                    $.ajax({
						   type: "POST",
						   url: "<%=request.getContextPath()%>/work/savePicture",//请求的后台地址
						   data: {"mediaId":mediaId},//前台传给后台的参数
						   success: function(filename){//filename:返回值
							  	$("#fileName").val(filename);
							  	var name=$("[id='fileName']").val(); 
						   },
						   error:function(XMLHttpRequest, textStatus, errorThrown) {  
		                       /* alert(XMLHttpRequest.status);  
		                       alert(XMLHttpRequest.readyState);  
		                       alert(textStatus);   */
		                       alert("未上传成功");
		                   }  
						});
                   // alert(ll);
                   <%--  $.post("<%=request.getContextPath()%>/work/savePicture",{mediaId:mediaId},
                    	function(filename){
                    	alert(filename);
	                        if(filename == 'success'){
	                        	alert('上传成功')
	                        }else{
	                            alert(res.msg)
	                        }
                    }) --%>
                },
                fail: function (res) {
                    alertModal('上传图片失败，请重试');
                };
            }); 
        };
    });
}
</script>
</head>
<body style="font-size: 50px;">
	 <div class="register">
		<div class="regTop">
			<span>提交报修信息</span>
		</div>
		<div class="content">
			<div class="point">
				<span style="font-size: 45px;">用户须完善信息之后才能提交业务信息。</span>
			</div>
			<form action="${pageContext.request.contextPath }/work/addwork"
				method="post">
				<div class="message">
					<select name="worktype">
						<option value="choose">选择业务类型</option>
						<option value="1">技术支持</option>
						<option value="2">设备报修</option>
						<option value="3">日常运维</option>
					</select>
					<table style="width: 100%; font-size: 40px;">
						<tr>
							<td style="width: 30%;"><label>设备名称：</label></td>
							<td><input type="text" placeholder="请输入设备名称"
								name="devicename" /></td>
						</tr>
						<tr>
							<td><label>具体描述：</label></td>
							<td><input type=text placeholder="请输入具体描述"
								name="description" /></td>
						</tr>
						<tr>
							<td><label>具体地点：</label></td>
							<td><input type=text placeholder="请输入具体地点" name="location" /></td>
						</tr>
						<tr align="center">
							<td colspan="6" align="center" ><input type="button" value="上传图片"
								onclick="takePicture()" /></td>
						</tr>
						<td><input type="hidden" id="fileName"  name="filename" /></td>
					</table>
				</div>
				<button class="submit" type="submit">提交信息</button>
			</form>
		</div>
	</div> 
</body>
</html>