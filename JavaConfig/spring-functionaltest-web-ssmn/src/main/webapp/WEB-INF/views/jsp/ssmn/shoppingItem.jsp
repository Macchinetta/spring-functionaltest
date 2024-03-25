<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.ssmn.shoppingItem" />
<c:set var="functionId" value="ssmn" />

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<meta name="contextPath" content="${pageContext.request.contextPath}" />
<sec:csrfMetaTags />
<title><spring:message code="label.sf.cmmn.systemName" var="defaultTitle" /> <spring:message
    code="${titleKey}" text="${f:h(defaultTitle)}" /></title>
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/${functionId}.css">
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/app/js/cmmn.js"></script>
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/app/js/${functionId}.js"></script>
<script type="text/javascript"></script>
</head>
<body>
  <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
  <div class="container">
<!-- 複数の試験を1つのView&Controllerで実現するために相対パスを使用 -->
<div id="wrapper">
  <h1 id="screenTitle">商品</h1>
  <div id="cartLink">
    <a id="confirmCartLink" href="../cart" class="btn btn-info"><span
      class="glyphicon glyphicon-shopping-cart"></span> カートを確認</a>
  </div>
  <div id="itemListLink">
    <a id="continueShoppingLink" href="../items" class="btn btn-success"><span
      class="glyphicon glyphicon-play"></span> ショッピングを続ける</a>
  </div>
  <form:form id="cartItemForm" action="./cart" cssClass="form-horizontal" method="post"
    modelAttribute="cartItemForm">
    <div class="form-group">
      <label class="col col-md-2 control-label">商品番号</label>
      <div class="col col-md-10">
        ${f:h(item.itemId)}
        <form:hidden path="itemId" />
      </div>
    </div>
    <div class="form-group">
      <label class="col col-md-2 control-label">商品名</label>
      <div class="col col-md-10">${f:h(item.itemName)}</div>
    </div>
    <div class="form-group">
      <label class="col col-md-2 control-label">価格</label>
      <div class="col col-md-10">${f:h(item.price)}</div>
    </div>
    <div class="form-group">
      <label class="col col-md-2 control-label">商品概要</label>
      <div class="col col-md-10">${f:h(item.itemName)}</div>
    </div>
    <div class="form-group">
      <div class="col col-md-10 col-md-offset-2">
        <form:button name="add" class="btn btn-default">カートへ追加</form:button>
        <span id="resultMessage" class="text-success"></span>
      </div>
    </div>
  </form:form>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>