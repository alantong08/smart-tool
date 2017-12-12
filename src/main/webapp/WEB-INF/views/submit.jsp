<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title报单系统</title>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
   <!--   <link rel="stylesheet" type="text/css" href="/easyui/demo/demo.css">-->
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
</head>
<body>
    <h2>报单系统</h2>
    <div class="demo-info" style="margin-bottom:10px">
        <div class="demo-tip icon-tip">&nbsp;</div>
        <div>Type in input box and submit the form.</div>
    </div>
    
    <div class="easyui-panel" title="Ajax Form" style="width:300px;padding:10px;">
        <form id="ff" action="tess4j/test/" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>Name:</td>
                    <td><input name="name" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input name="email" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>Phone:</td>
                    <td><input name="phone" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>File:</td>
                    <td><input name="file" class="f1 easyui-filebox"></input></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Submit"></input></td>
                </tr>
            </table>
        </form>
    </div>
    <style scoped>
        .f1{
            width:200px;
        }
    </style>
    <script type="text/javascript">
        $(function(){
            $('#ff').form({
                success:function(data){
                    $.messager.alert('Info', data, 'info');
                }
            });
        });
    </script>
</body>
</html>