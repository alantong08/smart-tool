<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>jQuery WeUI</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="../lib/weui.css">
<link rel="stylesheet" href="../css/jquery-weui.css">
<link rel="stylesheet" href="../css/demos.css">

</head>

<body ontouchstart>


	<header class='demos-header'>
		<h1 class="demos-title">注册报单系统</h1>
	</header>
	<form id="registerForm">
		<div class="weui-cells weui-cells_form">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">手机号</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" id="mobile" name="mobile" type="number" pattern="[0-9]*"
						placeholder="请输入手机号">
				</div>
			</div>

			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">密码</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" id="password" name="password" type="password" placeholder="请输入密码">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">姓名</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" id="name" name="name" type="text" placeholder="请输入姓名">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">群昵称</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" id="nickName" name="nickName" type="text" placeholder="请输入群昵称">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">支付宝</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" id="alipayAccount" name="alipayAccount" type="text" placeholder="请输入支付宝">
				</div>
			</div>

		</div>

		<div class="weui-btn-area">
			<a id="showTooltips" class="weui-btn weui-btn_primary">注册</a>
		</div>
	</form>
	<div class="weui-footer weui-footer_fixed-bottom">
  <p class="weui-footer__text">Copyright © 2008-2017 Alan, Tong</p>
</div>

<script src="../lib/jquery-2.1.4.js"></script>
<script src="../lib/fastclick.js"></script>
<script>
	$(function() {
		FastClick.attach(document.body);
	});
</script>
<script src="../js/jquery-weui.js"></script>
<script> 	
 	$("#showTooltips").click(function() {
	    $.ajax({
		      url: '/loginRegister/register',
		      type: 'POST',
		      data : $('#registerForm').serialize(),
		      success: function(response){
		    	 if(response.registered == true){
		    		 $.toptip('手机号已被注册', 'error');
		    	 }else{
		    		 $.toptip('注册成功', 'success');
		    	 }
		    	}
		    });
		    return false;
	}); 
	
</script>
</body>
</html>
