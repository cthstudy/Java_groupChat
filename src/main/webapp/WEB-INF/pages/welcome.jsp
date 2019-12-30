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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8" name="viewport" content="width=device-width,initial-scale=1" ><!-- 自适应 -->
<title>welcome</title>
<link rel="stylesheet" href="css/sty.css"/>
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css"/>
<script type="text/javascript" src="js/jquery.min.js"> </script>
<script type="text/javascript" src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="wrap">
		<a class="t"></a>
		<div class="my_box">
			<form id="uploadForm">
				<input class="jide" name="imgsrc" type="hidden" value="" />
				<img src="img/9.jpg" class="my_pic"/>
			</form>
		</div>
		<form method="post" action="chat/action_Groups" > 
			<input type="hidden" name="imgsrc" style="" class="imgsrc"/>
			<input type="text" name="userId" required="required" class="form-control userName" placeholder="请取个昵称把~ O(∩_∩)O ~" style="width: 335px;"/>
			<button type="submit" style="" class="btn-action">进入聊天</button>
		</form>
		<div class="pic_box" id="pic_box">
			<div class="header">
				<p style="padding-top: 15px;">设置头像</p>
				<span class="close">x</span>
			</div>
			<span class="xiantiao"></span>
			<ul>
				<li><img src="img/0.png"/></li>
				<li><img src="img/1.png"/></li>
				<li><img src="img/3.png"/></li>
				<li><img src="img/4.png"/></li>
				<li><img src="img/5.png"/></li>
				<li><img src="img/6.png"/></li>
				<li><img src="img/7.png"/></li>
				<li><img src="img/8.jpg"/></li>
				<li><img src="img/9.jpg"/></li>
				<li><img src="img/10.jpg"/></li>
				<li><img src="img/11.jpg"/></li>
				<li><img src="img/12.jpg"/></li>
				<li><img src="img/13.jpg"/></li>
				<li><img src="img/14.jpg"/></li>
			</ul>
			<div class="bt_box">
				<a class="gb" href="javascript:">关闭</a> 
				<a class="queren" href="javascript:">保存头像</a> 
			</div>
		</div>
	</div>
<script type="text/javascript">
	$(function(){
		$(".pic_box").animate({
			'top':'15px',
		},300);
		$(".imgsrc").val("img/9.jpg");
	})
	$(".close,.gb").click(function(){
		$(".pic_box").animate({
			'top':'-1000px'
		},500);
	}),
	$(".my_box").click(function(){
		$(".pic_box").animate({
			'top':'15px',
		},300);
	}),
	$(".queren").click(function(){
		var src = $(".imgsrc").val();
		//$.ajax与index.php交互,在php中判断文件,保存至数据库即可
		
		/*if(src != ""){
			$.ajax({
				url:"index.php",
				type:'post',
				data:{'imgsrc':src}, 
	            success:function(data){
	            	$(".my_pic").attr('src',src);
	            	$(".pic_box").animate({
						'top':'-1000px'
					},500);
					console.log(data);
	            }
			})
		}else{
			return false;
		}*/

		/**
		 * 后台我是用Thinkphp框架写的代码如下
		$show=M('user')->getByusername($_SESSION['_username']);
		if(!empty($_POST['imgsrc'])){
			$_POST['id']=$show['id'];
			$x=$info->create();
			$res=$info->save();
			if($res){
				echo json_encode($_POST['imgsrc']);
			}
		}
		 */


		//效果展示,在服务器中把这一段删除,用上面那一段
		if(src != ""){
			$(".my_pic").attr('src',src);
			$(".pic_box").animate({
				'top':'-1000px'
			},500);
		}else{
			return false;
		}
		
	});
	var $box = document.getElementById('pic_box');
	var $li = $box.getElementsByTagName('li');
	var index = 0;
	for(var i=0;i<$li.length;i++){
		$li[i].index=i;
		$li[i].onclick=function(){
			$li[index].style.borderRadius="15%";
			this.style.borderRadius="50%";
			index = this.index;
		}
	}
	$(".pic_box li img").click(function(){
		var src=$(this).attr("src");
		$(".jide").val(src);
		$(".imgsrc").val(src);
	})
</script>
</body>
</html>