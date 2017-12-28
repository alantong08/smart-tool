<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>jQuery WeUI</title>
    <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">

<meta name="description" content="Write an awesome description for your new site here. You can edit this line in _config.yml. It will appear in your document head meta (for Google search results) and in your feed.xml site description.
">

<link rel="stylesheet" href="../lib/weui.min.css">
<link rel="stylesheet" href="../css/jquery-weui.css">
<link rel="stylesheet" href="css/demos.css">

  </head>

  <body ontouchstart>


    <header class='demos-header'>
      <h1 class="demos-title">Preview</h1>
    </header>

    <div class="weui-form-preview">
      <div class="weui-form-preview__hd">
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">付款金额</label>
          <em class="weui-form-preview__value">¥2400.00</em>
        </div>
      </div>
      <div class="weui-form-preview__bd">
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">商品</label>
          <span class="weui-form-preview__value">电动打蛋机</span>
        </div>
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">标题标题</label>
          <span class="weui-form-preview__value">名字名字名字</span>
        </div>
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">标题标题</label>
          <span class="weui-form-preview__value">很长很长的名字很长很长的名字很长很长的名字很长很长的名字很长很长的名字</span>
        </div>
      </div>
      <div class="weui-form-preview__ft">
        <a class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:">操作</a>
      </div>
    </div>
    <br>
    <div class="weui-form-preview">
      <div class="weui-form-preview__hd">
        <label class="weui-form-preview__label">付款金额</label>
        <em class="weui-form-preview__value">¥2400.00</em>
      </div>
      <div class="weui-form-preview__bd">
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">商品</label>
          <span class="weui-form-preview__value">电动打蛋机</span>
        </div>
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">标题标题</label>
          <span class="weui-form-preview__value">名字名字名字</span>
        </div>
        <div class="weui-form-preview__item">
          <label class="weui-form-preview__label">标题标题</label>
          <span class="weui-form-preview__value">很长很长的名字很长很长的名字很长很长的名字很长很长的名字很长很长的名字</span>
        </div>
      </div>
      <div class="weui-form-preview__ft">
        <a class="weui-form-preview__btn weui-form-preview__btn_default" href="javascript:">辅助操作</a>
        <button type="submit" class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:">操作</button>
      </div>
    </div>

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
