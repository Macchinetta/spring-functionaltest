<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dmly.registerForm" />
<c:set var="functionId" value="dmly" />

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
<form:form modelAttribute="deliveryOrderForm"
  action="${pageContext.request.contextPath}/dmly/deliveryorder/register">
  <fieldset>
    <legend>配達オーダ登録</legend>
    <div class="container">
      <div class="row">
        <div class="col-md-2">
          <label>配達No</label>
        </div>
        <div class="col-md-4">
          <form:input path="deliveryNo" class="form-control"
            value="${f:h(deliveryOrder.deliveryNo)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>配達種別</label>
        </div>
        <div class="col-md-4">
          <form:select class="form-control" path="deliveryTypeName">
            <c:forEach var="deliveryType" items="${deliveryTypeList}" varStatus="rowStatus">
              <option value="${f:h(deliveryType.deliveryTypeName)}"
                ${deliveryType.deliveryTypeName == deliveryOrder.deliveryType.deliveryTypeName ? 'selected' : ''}>${f:h(deliveryType.deliveryTypeName)}</option>
            </c:forEach>
          </form:select>
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>送り主名</label>
        </div>
        <div class="col-md-4">
          <form:input path="senderName" class="form-control"
            value="${f:h(deliveryOrder.senderName)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>送り主住所</label>
        </div>
        <div class="col-md-4">
          <form:input path="senderAddress" class="form-control"
            value="${f:h(deliveryOrder.senderAddress)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>送り先名</label>
        </div>
        <div class="col-md-4">
          <form:input path="recieverName" class="form-control"
            value="${f:h(deliveryOrder.recieverName)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>送り先住所</label>
        </div>
        <div class="col-md-4">
          <form:input path="recieverAddress" class="form-control"
            value="${f:h(deliveryOrder.recieverAddress)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>受付日時</label>
        </div>
        <div class="col-md-4">
          <javatime:format value="${deliveryOrder.acceptDatetime}" pattern="yyyy/MM/dd HH:mm:ss"
            var="acceptDatetime" />
          <form:input path="acceptDatetime" class="form-control" value="${acceptDatetime}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>完了日時</label>
        </div>
        <div class="col-md-4">
          <javatime:format value="${deliveryOrder.completionDatetime}" pattern="yyyy/MM/dd HH:mm:ss"
            var="completionDatetime" />
          <form:input path="completionDatetime" class="form-control" value="${completionDatetime}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>配達者</label>
        </div>
        <div class="col-md-4">
          <form:input path="deliveryDriver" class="form-control"
            value="${f:h(deliveryOrder.deliveryDriver)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>状態</label>
        </div>
        <div class="col-md-4">
          <form:select class="form-control" path="deliveryStatus">
            <c:forEach var="deliveryStatus" items="${deliveryStatusList}" varStatus="rowStatus">
              <option value="${f:h(deliveryStatus.deliveryStatus)}"
                ${deliveryStatus.deliveryStatus == deliveryOrder.deliveryStatus ? 'selected' : ''}>${f:h(deliveryStatus.deliveryStatus)}</option>
            </c:forEach>
          </form:select>
        </div>
      </div>
      <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-4">
          <form:button name="register" class="btn btn-default">登録</form:button>
          <form:button name="cancel" class="btn btn-default">キャンセル</form:button>
        </div>
      </div>
    </div>
  </fieldset>
</form:form>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>