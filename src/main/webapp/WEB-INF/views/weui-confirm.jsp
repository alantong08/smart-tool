<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>报单系统</title>
    <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">

<link rel="stylesheet" href="/lib/weui.min.css">
<link rel="stylesheet" href="/css/jquery-weui.css">
<link rel="stylesheet" href="/demos/css/demos.css">

  </head>

  <body ontouchstart>
      <header class='demos-header'>
      	<p class='demos-sub-title'>报单数据确认</p>
      </header>

   	<form id="ff"  method="post" >
		<div class="weui-cells weui-cells_form">
	      <div class="weui-cell">
	        <div class="weui-cell__hd"><label class="weui-label">扫码日期：</label></div>
	        <div class="weui-cell__bd"><input class="weui-input" type="date" id="scanDate" name="scanDate" /></div>
	      </div>
	      <div class="weui-cell">
	        <div class="weui-cell__hd"><label class="weui-label">姓名:</label></div>
	        <div class="weui-cell__bd"><input class="weui-input" type="text" id="name" name="name"/></div>
	      </div>
	      <div class="weui-cell">
	        <div class="weui-cell__hd"><label class="weui-label">群昵称:</label></div>
	        <div class="weui-cell__bd"><input class="weui-input" type="text" id="nickName" name="nickName"/></div>
	      </div>
	      <div class="weui-cell">
	        <div class="weui-cell__hd"><label class="weui-label">订单号:</label></div>
	        <div class="weui-cell__bd"><input class="weui-input" type="number" id="orderNum" name="orderNum"/></div>
	      </div>
	      <div class="weui-cell weui-cell_select weui-cell_select-after">
	        <div class="weui-cell__hd"><label class="weui-label">商户名:</label></div>
	        <div class="weui-cell__bd">
	         	<select class="weui-select"  id="merchantName" name="merchantName"></select>
	      	</div>
		  </div>
	      <div class="weui-cell">
	        <div class="weui-cell__hd"><label class="weui-label">扫码金额:</label></div>
 			<div class="weui-cell__bd"><input class="weui-input" type="text" id="actualPrice" name="actualPrice"/></div>
		  </div>
	      <div class="weui-cell">
	        <div class="weui-cell__hd"><label class="weui-label">优惠金额:</label></div>
 			<div class="weui-cell__bd"><input class="weui-input" type="text" id="discountedPrice" name="discountedPrice"/></div>
		  </div>
		  <div class="weui-cell  weui-cell_select weui-cell_select-after">
	        <div class="weui-cell__hd"><label class="weui-label">交易方式:</label></div>
 			<div class="weui-cell__bd">	          
 			<select class="weui-select" id="transferType" name="transferType">
					<option value="1">融E联</option>
					<option value="2">微信</option>
					<option value="3">支付宝</option>
	          </select>
	          </div>
		  </div>	
		  <div class="weui-cell weui-cell_select weui-cell_select-after">
	        <div class="weui-cell__hd"><label class="weui-label">参与助攻:</label></div>
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
	        <div class="weui-cell__hd"><label class="weui-label">费率:</label></div>
 			<div class="weui-cell__bd"><input class="weui-input" type="text" id="rate" name="rate"/></div>
		  </div>
	      <div class="weui-cell">
	        <div class="weui-cell__hd"><label class="weui-label">支付宝:</label></div>
 			<div class="weui-cell__bd"><input class="weui-input" type="text" id="alipayAccount" name="alipayAccount"/></div>
		  </div>
	      <div class="weui-cell">
	        <div class="weui-cell__hd"><label class="weui-label">备注:</label></div>
 			<div class="weui-cell__bd"><input class="weui-input" type="text" id="comments" name="comments"/></div>
		  </div>		  		  		  		  
		<div class="weui-btn-area">
			<a class="weui-btn weui-btn_primary" id="showTooltips" onclick="$('#ff').submit()">提交</a>
		
		</div>
		</div>
	</form>

 <%@ include file="footer.jsp"%>
 
<script src="/lib/jquery-2.1.4.js"></script>
<script src="/lib/fastclick.js"></script>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
<script src="/js/jquery-weui.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		var orderDetail = $.parseJSON('${billDetail}');
		
		$("#scanDate").val(orderDetail.scanDate);
		$("#name").val(orderDetail.name);
		$("#nickName").val(orderDetail.nickName);
		$("#orderNum").val(orderDetail.orderNum);
		
		$("#actualPrice").val(orderDetail.actualPrice);
		$("#discountedPrice").val(orderDetail.discountedPrice);
		$("#transferType").val(orderDetail.transferType);
		$("#activityType").val(orderDetail.activityType);
		$("#rate").val(orderDetail.rate);
		$("#alipayAccount").val(orderDetail.alipayAccount);
		$("#comments").val(orderDetail.comments);
		
		$.post("/user/tess4j/merchant", function(data){
			for(var i=0;i<data.length;i++){
				$("#merchantName").append("<option value='"+data[i].merchantId+"'>"+data[i].merchantName+"</option>");
			}
			$("#merchantName").val(orderDetail.merchantName);
		});
	});
	

	$(function() {
		$('#ff').submit(function(){
		    $.ajax({
		      url: '/user/tess4j/saveBillOrder/',
		      type: 'POST',
		      data : $('#ff').serialize(),
		      success: function(response){
		    	  if(response.status == true){
		    		  window.location.href = response.view;
		    	 }else{
		    		 alert("出现异常，请联系管理员");
		    	 }
		    	}
		    });
		    return false;
		});
	});
</script>
  </body>
</html>
