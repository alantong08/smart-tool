<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>jQuery EasyUI Mobile</title>
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
                <div class="m-right">
                    <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" outline="true" onclick="$('#ff').form('reset')" style="width:60px">Reset</a>
                </div>
            </div>
        </header>
        <form id="ff">
            <div style="margin-bottom:10px">
                <input class="easyui-textbox" label="Full name:" prompt="Full name" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input class="easyui-datebox" label="Birthday:" prompt="Birthday" data-options="editable:false,panelWidth:220,panelHeight:240,iconWidth:30" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input class="easyui-passwordbox" label="Password:" prompt="Password" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input class="easyui-numberbox" label="Number:" prompt="Number" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input class="easyui-textbox" label="商户名" prompt="Number" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input class="easyui-filebox" label="订单截图:" prompt="" style="width:100%">
            </div>

        </form>
    </div>
</body>
</html>