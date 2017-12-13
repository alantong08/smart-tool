<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>保单系统</title>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/metro/easyui.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/mobile.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.mobile.js"></script>
</head>
<body>
    <div class="easyui-navpanel" style="position:relative;padding:20px">
        <header>
            <div class="m-toolbar">
                <div class="m-title">报单系统</div>

        </header>
        <form id="ff">
            <div style="margin-bottom:10px">
               <select name="feilv" class="easyui-combobox" label="是否参与助攻:" style="width:100%" >
		                <option value="">请选择</option>
		                <option value="0.25">是，记入月底助攻金额</option>
		                <option value="0.4">否，选择高费率自用</option>、
		                <option value="0.1">幸运星待遇</option>
		                <option value="0.2">高端群特供噜卡</option>
		                <option value="0.1">小娇周周特别活动0.1</option>
		            </select>
            </div>
            <div style="margin-bottom:10px">
                <input class="easyui-filebox" label="订单截图:" prompt="" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <a href="#" class="easyui-linkbutton" style="width:100%">上传</a>
            </div>
        </form>
    </div>
</body>
</html>