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
		<h1 class="demos-title">登陆报单系统</h1>
	</header>
	<form id="loginForm">
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

		</div>

		<div class="weui-btn-area">
			<a class="weui-btn weui-btn_primary" id="showTooltips" >登录</a>
			<a href="/weui-register" class="weui-btn weui-btn_default">注册</a>
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
/*  	$("#showTooltips").click(function() {
		var tel = $('#tel').val();
		var code = $('#code').val();
		if (!tel || !/1[3|4|5|7|8]\d{9}/.test(tel))
			$.toptip('请输入手机号');
		else if (!code || !/\d{6}/.test(code))
			$.toptip('请输入六位手机验证码');
		else
			$.toptip('提交成功', 'success');
	});  */
 	
 	$("#showTooltips").click(function() {
	    $.ajax({
		      url: '/loginRegister/login',
		      type: 'POST',
		      data : $('#loginForm').serialize(),
		      success: function(response){
		    	 if(response.message == 'failed'){
		    		 $.toptip('用户名密码不正确', 'error');
		    	 }else{
		    		 $.toptip('登录成功', 'success');
		    		 window.location.href = response.view;
		    	 }
		    	}
		    });
		    return false; 
	}); 
	
</script>
</body>
</html>
