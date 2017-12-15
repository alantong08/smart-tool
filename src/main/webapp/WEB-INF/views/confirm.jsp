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
    <script type="text/javascript">
		$(document).ready(function(){
			$("#date").datebox('setValue', parsedate());
		});
		
		function parsedate(){  
		    var date = new Date();  
		    var year = date.getFullYear();  
		    var month = date.getMonth()+1; //start from 0     
		    var day = date.getDate();   
		    return (month<10 ? '0' : '') + month + '/' +
		    (day<10 ? '0' : '') + day+"/"+year;
		}  
</script>
</head>


<body>
    <h2>报单系统</h2>    
    <div class="easyui-panel" title="Ajax Form" style="width:300px;padding:10px;">
        <form id="ff" action="tess4j/submit/" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>扫码日期:</td>
                    <td>
                    <input id="date" name ="date" class="f1 easyui-datebox"></input>
                    </td>
                </tr>
                <tr>
                    <td>姓名:</td>
                    <td><input name="name" class="f1 easyui-textbox"></input></td>
                </tr>
                <tr>
                    <td>群昵称:</td>
                    <td><input name="name" class="f1 easyui-textbox"></input></td>
                </tr>
				<tr>
					<td>订单号(阿信/阿联/阿宝):</td>
					<td><input name="file" class="f1 easyui-filebox"></input></td>
				</tr>
				<tr>
                    <td>商户名:</td>
                    <td>
                       <select name="merchants" class="f1 easyui-combobox"  >
		                <option value="">请选择</option>
		                <option value="AK">得利家用</option>
		                <option value="AZ">尚卫三</option>
		                <option value="AR">通盛摩托</option>
		            </select>
                    </td>
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