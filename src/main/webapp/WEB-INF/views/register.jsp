<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" type="text/css" href="/easyui/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/mobile.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.mobile.js"></script>
<script type="text/javascript">

	$(function() {
		$('#registerForm').submit(function(){
		    $.ajax({
		      url: '/loginRegister/register',
		      type: 'POST',
		      data : $('#registerForm').serialize(),
		      success: function(response){
		    	 if(response.registered == true){
		    	 	alert("手机号已被注册");
		    	 }else{
		    		 alert("注册成功，开始报单吧。");
		    		 window.location.href = response.view;
		    	 }
		    	}
		    });
		    return false;
		});
	});
</script>
</head>
<body>
	<div class="easyui-navpanel">
		<header>
			<div class="m-toolbar">
				<span class="m-title">注册报单系统</span>
			</div>
		</header>
		<div
			style="margin: 20px auto; width: 100px; height: 100px; border-radius: 100px; overflow: hidden">
			<img src="/easyui/images/login1.jpg"
				style="margin: 0; width: 100%; height: 100%;">
		</div>

		<form id="registerForm" >
			<div style="padding: 0 20px">
				<div style="margin-bottom: 10px">
					<input id="mobile" name="mobile" class="easyui-numberbox"
						data-options="required:true" label="手机号:"
						style="width: 100%; height: 38px">
				</div>
				<div style="margin-bottom: 10px">
					<input id="password" name="password" class="easyui-passwordbox"
						label="密码:" data-options="required:true"
						style="width: 100%; height: 38px">
				</div>
				<div style="margin-bottom: 10px">
					<input id="userName" name="userName" class="easyui-textbox"
						data-options="required:true" label="姓名:"
						style="width: 100%; height: 38px"></input>
				</div>
				<div style="margin-bottom: 10px">
					<input id="nickName" name="nickName" class="easyui-textbox"
						data-options="required:true" label="群昵称:"
						style="width: 100%; height: 38px"></input>
				</div>
				<div style="margin-bottom: 10px">
					<input id="alipayAccount" name="alipayAccount"
						class="easyui-textbox" data-options="required:true" label="支付宝:"
						style="width: 100%; height: 38px"></input>
				</div>

				<div style="text-align: center; margin-top: 10px">
					<a id="submit" onclick="$('#registerForm').submit()"
						class="easyui-linkbutton" style="width: 100%; height: 40px"><span
						style="font-size: 16px">注册</span></a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>