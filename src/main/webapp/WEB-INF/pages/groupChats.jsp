<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html >
<html>  
<head>
<base href="<%=basePath%>">
<title>专属聊天室</title>
<meta charset="UTF-8" name="viewport" content="width=device-width,initial-scale=1" ><!-- 自适应 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- Favicon --> 
<link rel="icon" href="dist/media/img/favicon.png" type="image/png">
<!-- Bundle Styles -->
<link rel="stylesheet" href="vendor/bundle.css">
<link rel="stylesheet" href="vendor/enjoyhint/enjoyhint.css">
<!-- App styles -->
<link rel="stylesheet" href="dist/css/app.min.css">
</head>
<body>
	<div class="page-loading"></div>
	<!-- 语音电话modal -->
	<div class="modal call fade" id="call" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered modal-dialog-zoom" role="document">
	        <div class="modal-content">
	            <div class="modal-body">
	                <div class="call">
	                    <div>
	                        <figure class="mb-4 avatar avatar-xl">
	                            <img src="./dist/media/img/me.jpg" class="rounded-circle" alt="image">
	                        </figure>
	                        <h4><span class="text-success">正在呼叫 亲爱的...</span></h4>
	                        <div class="action-button">
	                            <button type="button" class="btn btn-danger btn-floating btn-lg" data-dismiss="modal">
	                                <i data-feather="x"></i>
	                            </button>
	                            <button type="button" class="btn btn-success btn-pulse btn-floating btn-lg">
	                                <i data-feather="phone"></i>
	                            </button>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!-- 视频聊天modal -->
	<div class="modal call fade" id="videoCall" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered modal-dialog-zoom" role="document">
	        <div class="modal-content">
	            <div class="modal-body">
	                <div class="call">
	                    <div> 
	                        <figure class="mb-4 avatar avatar-xl">
	                            <img src="./dist/media/img/me.jpg" class="rounded-circle" alt="image">
	                        </figure>
	                        <h4> <span class="text-success">正在呼叫 亲爱的...</span></h4>
	                        <div class="action-button">
	                            <button type="button" class="btn btn-danger btn-floating btn-lg" data-dismiss="modal">
	                                <i data-feather="x"></i>
	                            </button>
	                            <button type="button" class="btn btn-success btn-pulse btn-floating btn-lg">
	                                <i data-feather="video"></i>
	                            </button>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- .视频聊天modal -->
	
	<!-- layout -->
	<div class="layout">
	
	    <!-- content -->
	    <div class="content">
	
	        <!-- chat -->
	        <div class="chat">
	            <div class="chat-header">
	                <div class="chat-header-user">
	                    <figure class="avatar">
	                        <img src="images/title.jpg" class="rounded-circle" alt="image">
	                    </figure>
	                    <div>
	                        <h5>甜甜的群</h5>
	                        <small class="text-success">
	                            <i>我轻轻的尝一口一说的爱我 还在回味 你给我的温柔</i>
	                        </small>
	                    </div>
	                </div>
	                <div class="chat-header-action">
	                    <ul class="list-inline">
	                        <li class="list-inline-item" data-toggle="tooltip" title="语音通话">
	                            <a href="#" class="btn btn-outline-light text-success" data-toggle="modal"
	                               data-target="#call">
	                                <i data-feather="phone"></i>
	                            </a>
	                        </li>
	                        <li class="list-inline-item" data-toggle="tooltip" title="视频通话">
	                            <a href="#" class="btn btn-outline-light text-warning" data-toggle="modal"
	                               data-target="#videoCall">
	                                <i data-feather="video"></i>
	                            </a>
	                        </li>
	                        <li class="list-inline-item">
	                            <a href="#" class="btn btn-outline-light" data-toggle="dropdown">
	                                <i data-feather="more-horizontal"></i>
	                            </a>
	                            <div class="dropdown-menu dropdown-menu-right">
	                                <a href="#" data-navigation-target="contact-information"
	                                   class="dropdown-item">资料</a>
	                                <a href="#" class="dropdown-item">添加到存档</a>
	                                <a href="#" class="dropdown-item">删除</a>
	                                <div class="dropdown-divider"></div>
	                                <a href="#" class="dropdown-item text-danger">加入黑名单</a>
	                            </div>
	                        </li>
	                    </ul>
	                </div>
	            </div>
				<!-- 聊天消息窗口 -->
	            <div class="chat-body"> <!-- .no-message -->
	                <div class="messages">
						<!-- 发送消息 -->
	                    <!-- <div class="message-item outgoing-message">
	                        <div class="message-avatar">
	                            <figure class="avatar">
	                                <img src="./dist/media/img/me.jpg" class="rounded-circle" alt="image">
	                            </figure>
	                            <div>
	                                <h5>默</h5>
	                                <div class="time">01:20 PM <i class="ti-double-check text-info"></i></div>
	                            </div>
	                        </div>
	                        <div class="message-content">
	                           	在在在吗?
	                        </div>
	                    </div>
						接收消息
						<div class="message-item">
							<div class="message-avatar">
								<figure class="avatar">
									<img src="./dist/media/img/qiao.jpg" class="rounded-circle" alt="image">
								</figure>
								<div>
									<h5>Byrom Guittet</h5>
									<div class="time">01:35 PM</div>
								</div>
							</div>
							<div class="message-content">
								I'm fine, how are you 😃
							</div>
						</div> -->
	                </div>
	            </div>
				<!-- 聊天输入框 -->
	            <div class="chat-footer">
	                <form>
	                    <div>
	                        <button class="btn btn-light mr-3" data-toggle="tooltip" title="表情" type="button">
	                            <i data-feather="smile"></i>
	                        </button>
	                    </div>
	                    <input type="text" class="form-control" id="input_send" placeholder="">
	                    <div class="form-buttons">
	                        <!-- <button class="btn btn-light" data-toggle="tooltip" title="文件" type="button">
	                            <i data-feather="paperclip"></i>
	                        </button> -->
	                        <button class="btn btn-light d-sm-none d-block" data-toggle="tooltip"
	                                title="语音" type="button">
	                            <i data-feather="mic"></i>
	                        </button>
	                        <button class="btn btn-primary" id="send" type="button">
	                            <i data-feather="send"></i>
	                        </button>
	                    </div>
	                </form>
	            </div>
	        </div>
	        <!-- ./ chat -->
	    </div>
	    <!-- ./ content -->
	</div>
	<!-- Bundle -->
	<script src="js/jquery.min.js"></script>
	<script src="./vendor/bundle.js"></script>
	<script src="./vendor/feather.min.js"></script>
	<script src="./vendor/enjoyhint/enjoyhint.min.js"></script>
	<!-- App scripts -->
	<script src="./dist/js/app.min.js"></script>
	<script type="text/javascript">
	var ws = null; 
	var valueMsg = null;
	$(function(){
			//建立连接	
			if(WebSocket){
				ws = new WebSocket("ws://223.244.87.50:90/chat_web/websockets");  
			}else{
				alert("浏览器不支持WebSocket")
			}
			
			/* 客户端向服务器端发消息 */
			ws.onopen=function(){ 
				//向对方传输数据 自己发
				//ws.send("${localhost_ip}加入!");
			}
			/* 接收对方传输数据 */
			ws.onmessage = function(e){
				/* onMessage的方法的返回内容 */
				//alert("收到数据:"+e.data+",输入数据:"+valueMsg); 
				if(e.data==valueMsg){ 
					//自己发消息, 把发消息的用户的头像 用户名消息传到后台
					var userId = "${userId}";
					var imgSrc = "${imgsrc}";
					$.ajax({
						url:"chat/ax_inUser",
						data:{userId:userId,imgSrc:imgSrc},
						success:function(re){
							//var arr = re.substring(1,re.length-1).split(",");
							//alert("发送者:"+"${userId}"+",传到后台的:"+arr[0]);
							//alert("${userId}发消息");
							send(userId,imgSrc,valueMsg);
						}
					})
				}else{
					//alert("收到数据:"+e.data+",输入数据:"+valueMsg);
					//收别人的消息
					$.ajax({
						url:"chat/ax_outUser",
						//data:{userId:"${userId}",imgSrc:"${imgsrc}"},
						success:function(re){
							var arr = re.substring(1,re.length-1).split(",");
							//alert("刚刚消息发送者:"+arr[0]+",接收者:"+"${userId}");
							//alert(arr[0]+"发来的消息"+"${userId}发消息"); 
							//show(arr[0],arr[1],e.data);
							show("曹天化","img/8.jpg",e.data);
							updateView(); 
						}
					})
				}
				/* showMessage(e.data); */
			}
			
			//单机发送按钮
			$("#send").click(function(){
				//输入框有值的时候才可以发出去值
				var inputVal = $(this).parent().prev().val();
				if(inputVal!=""){
					//send("images/me.jpg",inputVal);
					ws.send(inputVal); 
					valueMsg = $("#input_send").val(); 
					$("#input_send").val("");
					updateView(); 
				}
			})
		})
		
		/* 回车键发送消息 */
		/* $(document).keypress(function(e) {
		    var eCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
			if (eCode == 13){ 
				//输入框有值的时候才可以发出去值
				var inputVal = $("#input_send").val();
				if(inputVal.length!=0){
					//show("images/me.jpg",$("input").val());
					ws.send(inputVal);
					valueMsg = inputVal;
					$("#input_send").val("");
					updateView();  
				}
			}
		}); */
		//第一人称发送消息
		function send(userId,img,msg){
			$(".messages").append(
			'<div class="message-item outgoing-message">' +
				'<div class="message-avatar">'+
					'<figure class="avatar">'+
					    '<img src='+ img +' class="rounded-circle" alt="image">'+
					'</figure>'+
					'<div>'+
					    '<h5>'+userId+'</h5>'+
					    '<div class="time">刚刚</div>'+/* <i class="ti-double-check text-info"></i> */
					'</div>'+
				'</div>'+
				'<div class="message-content">'+
				   msg+
				'</div>'+
			'</div>');
		}
		
		//第一人称接收消息
		function show(userId,img,msg){
			$(".messages").append(
				'<div class="message-item">'+
					'<div class="message-avatar">'+
						'<figure class="avatar">'+
							'<img src='+ img +' class="rounded-circle" alt="image">'+
						'</figure>'+
						'<div>'+
							'<h5>'+userId+'</h5>'+
							'<div class="time">刚刚</div>'+
						'</div>'+
					'</div>'+
					'<div class="message-content">'+
						msg+
					'</div>'+
				'</div>');
		}
		
		//更新视图滑轮置于最底层
		function updateView(){
			var scrollHeight = $('.chat-body').prop("scrollHeight");
			$('.chat-body').animate({scrollTop:scrollHeight}, 400);
		}
	</script>
</body>
</html>