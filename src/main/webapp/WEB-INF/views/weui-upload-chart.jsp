<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>jQuery WeUI</title>
    <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description" content="report system">

<link rel="stylesheet" href="/weui/lib/weui.min.css">
<link rel="stylesheet" href="/weui/css/jquery-weui.css">
<link rel="stylesheet" href="/weui/demos/css/demos.css">

  </head>

  <body ontouchstart>
    <header class='demos-header'>
      <h2 class="demos-title">报单系统</h2>
    </header>

   	<form id="ff"  action="/user/tess4j/submit/" method="post" enctype="multipart/form-data">
		<div class="weui-cells weui-cells_form">
	      <div class="weui-cell weui-cell_select weui-cell_select-after">
	        <div class="weui-cell__hd">
	          <label for="" class="weui-label">参与助攻:</label>
	        </div>
	        <div class="weui-cell__bd">
	          <select class="weui-select" id="activityType" name="activityType">
					<option value="1">是，记入月底助攻金额</option>
					<option value="2">否，选择高费率自用</option>
					<option value="3">幸运星待遇</option>
					<option value="4">高端群特供撸卡</option>
					<option value="5">小娇周周特别活动0.1</option>
	          </select>
	        </div>
	      </div>

			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">订单截图:</label>
				</div>
				<div class="weui-uploader__input-box">
					<input class="weui-uploader__input" id="file-order" name="file-order"  type="file"  placeholder="请上传文件">
				</div>
			</div>

		</div>

		<div class="weui-btn-area">
			<a class="weui-btn weui-btn_primary" id="showTooltips" onclick="$('#ff').submit()">提交</a>
		
		</div>
	</form>

 <%@ include file="footer.jsp"%>
 
<script src="/weui/lib/jquery-2.1.4.js"></script>
<script src="/weui/lib/fastclick.js"></script>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
<script src="/weui/js/jquery-weui.js"></script>
<script>
 	/**$("#showTooltips").click(function() {
	    $.ajax({
		      url: '/user/tess4j/submit/',
		      type: 'POST',
		      data : $('#ff').serialize(),
		      success: function(response){
		    	 if(response.message == 'failed'){
		    		 $.toptip('上传订单出现问题', 'error');
		    	 }else{
		    		 window.location.href = response.view;
		    	 }
		    	}
		    });
		    return false; 
	}); 
	**/
</script>
  </body>
</html>
