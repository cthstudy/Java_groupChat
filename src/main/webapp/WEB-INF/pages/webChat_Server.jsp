<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聊天页面-服务端</title>
<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="css/chat.css" />
<script type="text/javascript" src="js/jquery-3.0.0.js"></script> 
<script src="js/flexible.js"></script>
<script type="text/javascript" src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/chat.js" type="text/javascript" charset="utf-8"></script> 
<script type="text/javascript">
	//建立连接	
	var ws = null; 
	if(WebSocket){
		ws = new WebSocket("ws://localhost:8080/chat_web/websocket"); 
	}else{
		alert("浏览器不支持WebSocket")
	}
	
	/* 服务器端发消息 */
	ws.onopen=function(){ 
		//首次连接 主动向服务器发消息
		//显示新消息
		var arr ="${list}".substring(1,"${list}".length-1);
		arr = arr.split(",");
		for(var i=0;i<arr.length;i++){
			ws.send(arr[i]); 
			//ws.show(arr[i]);
		}
		/* ws.send("您好!天一工作室客服很高兴为你服务!"); */
		/* show("images/me.jpg","您好!天一工作室客服很高兴为你服务!") */
	}
	/* 客户端接收服务端的消息并显示 */
	ws.onmessage = function(e){
		/* onMessage的方法的返回内容 */
		send("images/qiao.jpg",e.data); 
		/* showMessage(event.data); */
	}
	
	/* 输入框中有值 才可以发送 */
	$(function(){
		$('.footer').on('keyup','input',function(){
			if($(this).val().length>0){
				$(this).next().css('background','#114F8E').prop('disabled',true);
				$(document).keypress(function(e) {
				    var eCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
					if (eCode == 13){ 
						if($("input").val().length!=0){
							show("images/me.jpg",$("input").val());
							$("input").val("");
						}
					}
				});
			}else{  
				$(this).next().css('background','#ddd').prop('disabled',false);
			}
		}) 
		/* 单机发送消息按钮 */
		$('.footer p').click(function(){
			show("images/me.jpg",$("input").val());
			$("input").val("");
		})
	})
	 
	var flag = false;
	/*个人视角接收数据   他人发送消息*/
	function send(headSrc,str){
		var html="<div class='send'><div class='msg'><img src="+headSrc+" />"+
		"<p><i class='msg_input'></i>"+str+"</p></div></div>";
		upView(html);
		flag = true;
	}
	/*个人视角发消息  他人接收消息*/
	function show(headSrc,str){
		var html="<div class='show'><div class='msg'><img src="+headSrc+" />"+
		"<p><i class='msg_input'></i>"+str+"</p></div></div>";
		upView(html);
	}
	/*更新视图*/
	function upView(html){
		$('.message').append(html);
		$('body').animate({scrollTop:$('.message').outerHeight()-window.innerHeight},200)
	}
	function sj(){
		return parseInt(Math.random()*10)
	}

</script>
</head>
<body>
	<header class="header">
		<a class="back" href="javascript:history.back()"></a>
		<c:forEach items="${map}" var="map">
			<h5 class="tit">${map.key}&nbsp;&nbsp;&nbsp;<span style="color:red;">${map.value}</span></h5>
		</c:forEach>
		<div class="right">.&nbsp;.&nbsp;.</div>
	</header>
	<div class="message">

		<div class="show"> 
			<!-- <div class="time">12/20 17:00</div>
			<div class="msg">
				<img src="images/qiao.jpg" alt="" />
				<p><i class="msg_input"></i>在吗</p>
			</div> -->
		</div>
		<%-- <c:forEach items="${list }" var="list">
			<div class="send">
				<!-- <div class="time">12/20 17:00</div> -->
				<div class="msg">
					<img src="./images/qiao.jpg" alt="" />
					<p><i clas="msg_input"></i>${list}</p>
				</div> 
			</div>
		</c:forEach> --%>
	</div>
	<div class="footer">
		<img src="images/hua.png" alt="" />
		<img src="images/xiaolian.png" alt="" />
		<input type="text"  />
		<p>发送</p>
	</div>
</body>
</html>