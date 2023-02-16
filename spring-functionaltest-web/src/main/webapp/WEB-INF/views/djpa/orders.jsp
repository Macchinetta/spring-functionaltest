<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.djpa.orders" />
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
<div class="row">
  <h1 id="screenTitle"></h1>


  <div>
    <table class="table table-striped table-bordered table-condensed">
      <thead>
        <form:form modelAttribute="orderForm" action="${pageContext.request.contextPath}/djpa/order"
          method="get">
          <tr>
            <th align="left"><form:input path="orderId" class="form-control form-input"
                value="" /></th>
            <th><form:button name="fetch" class="btn btn-default btn-right">Search By Order ID</form:button>
              <form:button name="cmnCondFetch" class="btn btn-default btn-right">Search Using Entity with Common Condition</form:button></th>
          </tr>
          <tr>
            <th align="left"><form:input path="orderStatus.statusName"
                class="form-control form-input" value="" /></th>
            <th><form:button name="searchByStatus" class="btn btn-default btn-right">Search By Order Status</form:button></th>
          </tr>
          <tr>
            <th colspan="2"><form:button name="create" class="btn btn-default btn-right">Place Order</form:button></th>
          </tr>
          <tr>
            <th colspan="2"><form:button name="fetchSummary" class="btn btn-default btn-right">Fetch Result in Custom Object</form:button></th>
          </tr>
        </form:form>
        <form:form modelAttribute="orderForm"
          action="${pageContext.request.contextPath}/djpa/order/delete" method="delete">
          <tr>
            <th align="left" style="width: 150px"><form:input id="deleteId" path="orderId"
                class="form-control form-input" value="" /></th>
            <th colspan="4"><form:button name="delete" class="btn btn-default btn-right">Delete By Order ID</form:button></th>
          </tr>
        </form:form>
      </thead>

    </table>

  </div>
  <c:if test="${null ne orderList && orderList.size() >0}">
    <table class="table table-striped table-bordered table-condensed">
      <thead>
        <tr>
          <th>Sr.No.</th>
          <th>Order Id</th>
          <th>Order Memo</th>
          <th>Order Amount</th>
          <th>Order Status</th>
          <th>Can Be Deleted ?</th>
        </tr>
      </thead>
      **${cmnCodnSrchFlag }**
      <c:forEach items="${orderList}" var="order" varStatus="rowStatus">
        <tr>
          <td class="no" id="no">${rowStatus.count}</td>
          <c:choose>
            <c:when test="${cmnCodnSrchFlag }">
              <td id="orderId_${rowStatus.count}"><a id="id${f:h(rowStatus.count)}"
                href="${pageContext.request.contextPath}/djpa/order/cmnCondFetch/${f:h(order.orderId)}/detail">${f:h(order.orderId)}</a></td>
            </c:when>
            <c:otherwise>
              <td id="orderId_${rowStatus.count}"><a id="id${f:h(rowStatus.count)}"
                href="${pageContext.request.contextPath}/djpa/order/${f:h(order.orderId)}/detail">${f:h(order.orderId)}</a></td>
            </c:otherwise>

          </c:choose>

          <td id="memo_${rowStatus.count}">${f:h(order.orderMemo)}</td>
          <td id="amount_${rowStatus.count}">${f:h(order.orderAmount)}</td>
          <td id="status_${rowStatus.count}">${f:h(order.orderStatus.statusName)}</td>

          <c:choose>
            <c:when test="${order.logicalDelete}">
              <c:set var="canDelete" value="Yes"></c:set>
            </c:when>
            <c:otherwise>
              <c:set var="canDelete" value="No"></c:set>
            </c:otherwise>
          </c:choose>
          <td id="logicalDel_${rowStatus.count}">${f:h(canDelete)}</td>
        </tr>
      </c:forEach>
    </table>
  </c:if>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>