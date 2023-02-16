<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.djpa.cart" />
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
<form:form modelAttribute="cartForm" action="${pageContext.request.contextPath}/djpa/order/place"
  method="post">


  <table class="table table-striped table-bordered table-condensed">
    <thead>
      <tr>
        <th>Sr.No.</th>
        <th>Item Code</th>
        <th>Item Name</th>
        <th>Item Price</th>
        <th>Quantity to purchase</th>
      </tr>
    </thead>
    <c:forEach items="${itemList}" var="item" varStatus="rowStatus">
      <tr>
        <td class="no" id="no">${rowStatus.count}</td>
        <td id="itemCode_${rowStatus.count}"><form:input
            path="items[${rowStatus.index}].itemCode" value="${f:h(item.itemCode)}" readonly="true"
            style="background-color: whitesmoke;" /></td>

        <td id="itName_${status.count}"><form:input path="items[${rowStatus.index}].itemName"
            value="${f:h(item.itemName)}" readonly="true" style="background-color: whitesmoke;" /></td>

        <td id="itPrice_${status.count}"><form:input path="items[${rowStatus.index}].itemPrice"
            value="${f:h(item.itemPrice)}" readonly="true" style="background-color: whitesmoke;" /></td>

        <td id="itQty_${status.count}"><form:input path="items[${rowStatus.index}].quantity"
            value="" /></td>
      </tr>
    </c:forEach>
  </table>
  <div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
      <form:textarea path="memo" />
    </div>
  </div>
  <div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
      <form:button name="place" class="btn btn-default">Place Order</form:button>
    </div>
    <div class="col-md-4">
      <form:button name="directItem2Order" class="btn btn-default">Add Item To Order</form:button>
    </div>
  </div>
</form:form>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>