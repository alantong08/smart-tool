<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" type="text/css"
	href="/easyui/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/mobile.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.mobile.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		var userLogin = $.parseJSON('${userLogin}');
		if (!$.isEmptyObject(userLogin)) {
			if (userLogin.registered == false) {
				$("#mobile").numberbox('setValue', userLogin.mobile);
				$("#password").passwordbox('setValue', userLogin.password);
				alert("用户名密码错误,请确认");
			}
		}

	});
</script>
</head>
<body>
	<div class="easyui-navpanel">
		<header>
			<div class="m-toolbar">
				<span class="m-title">登陆报单系统</span>
			</div>
		</header>

		<div
			style="margin: 20px auto; width: 100px; height: 100px; border-radius: 100px; overflow: hidden">
			<img src="/easyui/images/login1.jpg"
				style="margin: 0; width: 100%; height: 100%;">
		</div>

		<form id="loginForm" method="post" action="/user/loginPage">
			<div style="padding: 0 20px">
				<div style="margin-bottom: 10px">
					<input id="mobile" name="mobile" class="easyui-numberbox"
						data-options="prompt:'请输入手机号',iconCls:'icon-man'"
						data-options="required:true" style="width: 100%; height: 38px">
				</div>
				<div>
					<input id="password" name="password" class="easyui-passwordbox"
						data-options="prompt:'请输入密码'" style="width: 100%; height: 38px">
				</div>
				<div style="text-align: center; margin-top: 10px">
					<span id="message"></span>
				</div>
				<div style="text-align: center; margin-top: 30px">
					<a id="submit" onclick="$('#loginForm').submit()"
						class="easyui-linkbutton" style="width: 100%; height: 40px"><span
						style="font-size: 16px">登陆</span></a>
				</div>
				<div style="text-align: center; margin-top: 30px">
					<a href="/register" id="register" class="easyui-linkbutton"
						style="width: 100px; height: 35px"><span
						style="font-size: 16px">注册</span></a>
				</div>
			</div>
		</form>

	</div>
</body>
</html>