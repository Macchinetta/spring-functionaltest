<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.ssmn.shoppingOrderComplete" />
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
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />
  <div class="container">
<!-- 複数の試験を1つのView&Controllerで実現するために相対パスを使用 -->
<div id="wrapper">
  <h1 id="screenTitle">注文完了</h1>
  <t:messagesPanel />
  <div id="itemListLink">
    <a id="continueShoppingLink" href="./items" class="btn btn-success"><span
      class="glyphicon glyphicon-play"></span> ショッピングを続ける</a>
  </div>

  <table class="table table-striped table-bordered table-condensed">
    <tr>
      <th class="orderIdLabel">注文番号</th>
      <td>${f:h(order.orderId)}</td>
    </tr>
  </table>
  <table class="table table-striped table-bordered table-condensed">

    <thead>
      <tr>
        <th class="no">No</th>
        <th class="itemId">商品番号</th>
        <th class="itemName">商品名</th>
        <th class="quantity">個数</th>
        <th class="price">価格</th>
      </tr>
    </thead>

    <c:forEach var="orderItem" items="${order.orderItems}" varStatus="rowStatus">
      <tr>
        <td class="no">${rowStatus.count}</td>
        <td class="itemId">${f:h(orderItem.itemId)}</td>
        <td class="itemName">${f:h(orderItem.item.itemName)}</td>
        <td class="quantity">${f:h(orderItem.quantity)}</td>
        <td class="price"><fmt:formatNumber value="${orderItem.subtotalPrice}"
            pattern="###,###" /></td>
      </tr>
    </c:forEach>
    <tr>
      <td class="buttonArea" colspan="4"></td>
      <td class="totalPrice"><fmt:formatNumber value="${order.totalPrice}" pattern="###,###" /></td>
    </tr>

  </table>
  <!-- セッション内Form確認 -->
  <div id="checkFormInSession" style="visibility: hidden">${f:h(sessionScope['scopedTarget.cart'].getCartItems().size())}</div>
</div>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>