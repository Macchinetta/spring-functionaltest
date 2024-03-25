<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.excn.jpaPessimisticLockView" />
<c:set var="functionId" value="excn" />

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
<div id="wrapper">
  <h1 id="screenTitle">悲観的排他ロック</h1>

  <form:form method="post" action="${pageContext.request.contextPath}/jsp/0502/002"
    modelAttribute="stockForm" cssClass="form-horizontal">

    <div class="form-group">
      <table class="table table-striped">

        <thead>
          <tr>
            <th class="itemCode">itemCode</th>
            <th class="itemName">itemName</th>
            <th class="quantity">quantity</th>
            <th class="version">version</th>
          </tr>
        </thead>

        <tbody>
          <tr>
            <td id="result_itemCode" class="itemCode">${f:h(stockForm.itemCode)}</td>
            <td id="result_itemName" class="itemName">${f:h(stockForm.itemName)}</td>
            <td id="result_quantity" class="quantity">${f:h(stockForm.quantity)}</td>
            <td id="result_version" class="version">${f:h(stockForm.version)}</td>
          </tr>
        </tbody>

      </table>
    </div>

    <div class="form-group">
      <form:label path="purchasingQuantity" class="col-sm-2 control-label">PurchasingQuantity</form:label>
      <div class="col-sm-10">
        <form:input path="purchasingQuantity" class="form-control" placeholder="PurchasingQuantity" />
        <form:errors path="purchasingQuantity" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <form:label path="sleepMillis" class="col-sm-2 control-label">LockTime(millis)</form:label>
      <div class="col-sm-10">
        <form:input path="sleepMillis" class="form-control" placeholder="LockTime(millis)" />
        <form:errors path="sleepMillis" class="text-danger" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:hidden path="itemCode" class="btn btn-default" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:hidden path="version" class="btn btn-default" />
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <form:button id="buy" name="buy" class="btn btn-default">buy</form:button>
      </div>
    </div>

  </form:form>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>