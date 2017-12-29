<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>报单系统</title>
    <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">

<link rel="stylesheet" href="../lib/weui.min.css">
<link rel="stylesheet" href="../css/jquery-weui.css">
<link rel="stylesheet" href="../css/demos.css">

  </head>

  <body ontouchstart>
    <header class='demos-header'>
      <h1 class="demos-title">欢迎您 </h1>
      <p class='demos-sub-title'>本app提供报单查询服务</p>
    </header>

    <div class="weui-tab">
      <div class="weui-tab__bd">
        <div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
        <div class="weui-grids">
      	<a href="/weuiUploadChart" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_button.png" alt="">
        </div>
        <p class="weui-grid__label">
          报单
        </p>
      </a>
      <a href="/weiuiSearchBar" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_cell.png" alt="">
        </div>
        <p class="weui-grid__label">
          查询
        </p>
      </a>
      <a href="form.html" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_cell.png" alt="">
        </div>
        <p class="weui-grid__label">
          群规
        </p>
      </a>
      <a href="flex.html" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_cell.png" alt="">
        </div>
        <p class="weui-grid__label">
          福利
        </p>
      </a>
      <a href="toast.html" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_toast.png" alt="">
        </div>
        <p class="weui-grid__label">
          招商黑名单
        </p>
      </a>
      <a href="dialog.html" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_dialog.png" alt="">
        </div>
        <p class="weui-grid__label">
          MCC码查询
        </p>
      </a>
      <a href="progress.html" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_progress.png" alt="">
        </div>
        <p class="weui-grid__label">
          酒店
        </p>
      </a>
      <a href="msg.html" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_msg.png" alt="">
        </div>
        <p class="weui-grid__label">
          里程
        </p>
      </a>
      <a href="article.html" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="images/icon_nav_article.png" alt="">
        </div>
        <p class="weui-grid__label">
          小知识
        </p>
      </a>
    </div>
        </div>
        <div id="tab2" class="weui-tab__bd-item">
          <h1>页面二</h1>
        </div>
        <div id="tab3" class="weui-tab__bd-item">
          <h1>页面三</h1>
        </div>
        <div id="tab4" class="weui-tab__bd-item">
          <h1>页面四</h1>
        </div>
      </div>

 <%@ include file="footer.jsp"%>
 
<script src="../lib/jquery-2.1.4.js"></script>
<script src="../lib/fastclick.js"></script>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
<script src="../js/jquery-weui.js"></script>

  </body>
</html>
