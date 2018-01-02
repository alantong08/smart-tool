<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>报单管理</title>

	<link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/demo.css">
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.edatagrid.js"></script>
</head>
<body>
    <h2>报单查询</h2>
    
    <table id="dg"  title="报单记录" style="width:80%;height:500px"
            toolbar="#toolbar" pagination="true" idField="id"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="userName" width="50" editor="{type:'validatebox',options:{required:true}}">姓名</th>
                <th field="nickName" width="50" editor="{type:'validatebox',options:{required:true}}">群昵称</th>
                <th field="scanDate" width="50" editor="{type:'validatebox',options:{required:true}}">扫码日期</th>
                <th field="orderNum" width="100" editor="{type:'validatebox',options:{required:true,validType:'number'}}">订单号</th>
                <th field="merchantName" width="50" editor="{type:'validatebox',options:{required:true}}">商户名</th>
                <th field="actualPrice" width="50" editor="{type:'validatebox',options:{required:true}}">扫码金额</th>
                <th field="transferType" width="50" editor="{type:'validatebox',options:{required:true}}">交易方式</th>
                <th field="activityType" width="50" editor="{type:'validatebox',options:{required:true}}">参与助攻</th>
                <th field="rate" width="50" editor="{type:'validatebox',options:{required:true}}">费率</th>
                <th field="alipayAccount" width="70" editor="{type:'validatebox',options:{required:true}}">支付宝</th>
                <th field="comments" width="50" editor="{type:'validatebox',options:{required:false}}">备注</th>

            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow')">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#dg').edatagrid('cancelRow')">取消</a>
    </div>
<script type="text/javascript">
        $(function(){
            $('#dg').edatagrid({
                url: '/admin/getOrderList',
                saveUrl: '/admin/saveOrderList',
            });
        });
    </script>
    
</body>
</html>