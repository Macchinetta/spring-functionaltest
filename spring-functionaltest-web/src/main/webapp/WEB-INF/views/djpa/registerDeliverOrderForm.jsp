<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.djpa.registerDeliverOrderForm" />
<c:set var="functionId" value="djpa" />

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
<form:form modelAttribute="deliveryOrderStatusForm"
  action="${pageContext.request.contextPath}/djpa/delivery/order/register">
  <fieldset>
    <legend>Deliver Order Details</legend>
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <label>Delivery ID.</label>
        </div>
        <div class="col-md-4">
          <form:input path="deliverNumber" class="form-control"
            value="${f:h(delOrder.deliverNumber)}" readonly="true"
            style="background-color: whitesmoke;" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Delivery Type</label>
        </div>
        <div class="col-md-4">
          <form:input path="deliveryType" class="form-control" value="${f:h(delOrder.deliveryType)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Sender Name</label>
        </div>
        <div class="col-md-4">
          <form:input path="senderName" class="form-control" value="${f:h(delOrder.senderName)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Sender Address</label>
        </div>
        <div class="col-md-4">
          <form:input path="senderAddress" class="form-control"
            value="${f:h(delOrder.senderAddress)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Receiver Name</label>
        </div>
        <div class="col-md-4">
          <form:input path="receiverName" class="form-control" value="${f:h(delOrder.receiverName)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Receiver Address</label>
        </div>
        <div class="col-md-4">
          <form:input path="receiverAddress" class="form-control"
            value="${f:h(delOrder.receiverAddress)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Acceptance Time</label>
        </div>
        <div class="col-md-4">
          <fmt:formatDate value="${delOrder.acceptDateTime}" pattern="yyyy/MM/dd" var="releaseDate" />
          <form:input path="acceptDateTime" class="form-control" value="${acceptDateTime}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Completion Time</label>
        </div>
        <div class="col-md-4">
          <fmt:formatDate value="${delOrder.completionDateTime}" pattern="yyyy/MM/dd"
            var="releaseDate" />
          <form:input path="completionDateTime" class="form-control" value="${completionDateTime}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4">
          <label>Driver Name</label>
        </div>
        <div class="col-md-4">
          <form:input path="deliveryDriver" class="form-control"
            value="${f:h(delOrder.deliveryDriver)}" />
        </div>
      </div>

      <div class="row">
        <div class="col-md-4">
          <label>Deliver Status</label>
        </div>
        <div class="col-md-4">
          <form:input path="deliveryStatus" class="form-control"
            value="${f:h(delOrder.deliveryStatus)}" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
          <form:button name="add" class="btn btn-default">Add</form:button>
        </div>
      </div>
    </div>
  </fieldset>
</form:form>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>