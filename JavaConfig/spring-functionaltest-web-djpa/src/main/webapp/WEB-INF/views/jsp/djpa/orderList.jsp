<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.djpa.orderList" />
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
  <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
  <div class="container">
<div class="row">
  <h1 id="screenTitle">書籍一覧</h1>
  <br>

  <table class="table table-striped table-bordered table-condensed">
    <form:form modelAttribute="deliveryOrderStatusForm"
      action="${pageContext.request.contextPath}/jsp/delivery/order" method="get">
      <thead>
        <tr>
          <th>Enter Delivery Status</th>
          <th style="left: auto;"><form:input path="delOrderStatus"
              class="form-control form-input" value="${f:h(deliveryOrderStatusForm.delOrderStatus)}" /></th>

        </tr>
        <tr>
          <th>Enter Delivery Number</th>
          <th style="left: auto;"><form:input path="deliverNumber"
              class="form-control form-input" value="${f:h(deliveryOrderStatusForm.deliverNumber)}" /></th>

        </tr>

        <tr>
          <th>Enter Delivery Type</th>
          <th style="left: auto;"><form:input path="deliveryType"
              class="form-control form-input" value="${f:h(deliveryOrderStatusForm.deliveryType)}" /></th>

        </tr>
        <tr>

          <th><form:button name="form" class="btn btn-default">Search By Order Status(@Query annotation)</form:button></th>
          <th><form:button name="methodNameConven" class="btn btn-default">Search By Status Code(Method Name Convention)</form:button></th>
        </tr>
        <tr>
          <th><form:button name="nativeQuery" class="btn btn-default">Search By Status Code(Using Native Query)</form:button></th>
          <th><form:button name="addMethodToIndRepo" class="btn btn-default">Search by Added individual custom method to entity specific Repository interface</form:button></th>
        </tr>
        <tr>
          <th><form:button name="matchCond" class="btn btn-default">Search Entities matching Conditions</form:button></th>
          <th><form:button name="dynaCond" class="btn btn-default">Search Entities matching Dynamic Conditions</form:button></th>
          <th><form:button name="orderDet" class="btn btn-default">Search Entities matching Dynamic Conditions</form:button></th>
        </tr>

        <tr>
          <th><form:select path="matchOption">
              <form:option id="forwardMatch" value="senderAddress_FW">Forward Match Sender Address</form:option>
              <form:option id="backwardMatch" value="receiverAddress_BK">Backward Match Receiver Address</form:option>
              <form:option id="partialMatch" value="senderName_PT">Partial Match Sender Name</form:option>
            </form:select></th>
          <th><form:button name="likeSrch" class="btn btn-default">Like Search</form:button></th>
        </tr>
        <tr>
          <th><form:input path="escapeSrchVal" class="form-control form-input"
              value="${f:h(deliveryOrderStatusForm.escapeSrchVal)}" /></th>
          <th><form:button name="escSrchMod" class="btn btn-default">Escape Search(%)</form:button></th>
          <th><form:button name="escSrchDash" class="btn btn-default">Escape Search(-)</form:button></th>
          <th><form:button name="srchMatchInLogic" class="btn btn-default">Escape Search( Match In Logic)</form:button></th>
        </tr>
      </thead>
    </form:form>
  </table>

  <c:choose>
    <c:when test="${empty isPageable || !isPageable || isPageable eq false}">
      <table class="table table-striped table-bordered table-condensed">
        <thead>
          <tr>
            <th>Sr. No.</th>
            <th>Delivery No.</th>
            <th>Delivery Type</th>
            <th>Sender Name</th>
            <th>Sender Address</th>
            <th>Receiver Name</th>
            <th>Receiver Address</th>
            <th>Acceptance Time</th>
            <th>Completion Time</th>
            <th>Driver Name</th>
            <th>Delivery Status</th>
          </tr>
        </thead>
        <c:forEach var="delOrder" items="${deliveryOrders}" varStatus="rowStatus">
          <tr>
            <td class="no" id="no">${rowStatus.count}</td>
            <td><a id="delOrder_${rowStatus.count}"
              href="${pageContext.request.contextPath}/jsp/delivery/order/${delOrder.deliverNumber}/update">
                ${f:h(delOrder.deliverNumber)}</a></td>
            <td id="delType">${f:h(delOrder.deliveryType)}</td>
            <td id="senderName">${f:h(delOrder.senderName)}</td>
            <td id="senderAddress">${f:h(delOrder.senderAddress)}</td>
            <td id="receiverName">${f:h(delOrder.receiverName)}</td>
            <td id="receiverAdress">${f:h(delOrder.receiverAddress)}</td>
            <td id="acceptTime"><fmt:formatDate value="${delOrder.acceptDateTime}"
                pattern="yyyy/MM/dd" /></td>
            <td id="competionTime"><fmt:formatDate value="${delOrder.completionDateTime}"
                pattern="yyyy/MM/dd" /></td>
            <td id="driverName">${f:h(delOrder.deliveryDriver)}</td>
            <td id="delStatus">${f:h(delOrder.deliveryStatus)}</td>
          </tr>
        </c:forEach>
      </table>
    </c:when>
    <c:when test="${!empty isPageable && null ne isPageable && isPageable}">
      <table class="table table-striped table-bordered table-condensed">
        <thead>
          <tr>
            <th>Sr. No.</th>
            <th>Delivery No.</th>
            <th>Delivery Type</th>
            <th>Sender Name</th>
            <th>Sender Address</th>
            <th>Receiver Name</th>
            <th>Receiver Address</th>
            <th>Acceptance Time</th>
            <th>Completion Time</th>
            <th>Driver Name</th>
            <th>Delivery Status</th>
          </tr>
        </thead>
        <c:forEach var="delOrder" items="${page.content}" varStatus="rowStatus">
          <tr>
            <td class="no" id="no">${rowStatus.count}</td>
            <td><a id="delOrder_${rowStatus.count}"
              href="${pageContext.request.contextPath}/jsp/delivery/order/${delOrder.deliverNumber}/update">
                ${f:h(delOrder.deliverNumber)}</a></td>
            <td id="delType">${f:h(delOrder.deliveryType)}</td>
            <td id="senderName">${f:h(delOrder.senderName)}</td>
            <td id="senderAddress">${f:h(delOrder.senderAddress)}</td>
            <td id="receiverName">${f:h(delOrder.receiverName)}</td>
            <td id="receiverAdress">${f:h(delOrder.receiverAddress)}</td>
            <td id="acceptTime"><fmt:formatDate value="${delOrder.acceptDateTime}"
                pattern="yyyy/MM/dd" /></td>
            <td id="competionTime"><fmt:formatDate value="${delOrder.completionDateTime}"
                pattern="yyyy/MM/dd" /></td>
            <td id="driverName">${f:h(delOrder.deliveryDriver)}</td>
            <td id="delStatus">${f:h(delOrder.deliveryStatus)}</td>
          </tr>
        </c:forEach>
      </table>
      <div class="paginationPart" align="center">
        <c:choose>
          <c:when test="${isMatchCondTC }">
            <t:pagination page="${page}" disabledHref="javascript:void(0);"
              outerElementClass="pagination"
              queryTmpl="page={page}&size={size}&matchCond=''&delOrderStatus=${f:h(deliveryOrderStatusForm.delOrderStatus)}" />
          </c:when>
          <c:when test="${modSearch }">
            <t:pagination page="${page}" disabledHref="javascript:void(0);"
              outerElementClass="pagination"
              queryTmpl="page={page}&size={size}&escSrchMod=''&escapeSrchVal=${f:h(deliveryOrderStatusForm.escapeSrchVal)}" />
          </c:when>
          <c:when test="${dashSrch }">
            <t:pagination page="${page}" disabledHref="javascript:void(0);"
              outerElementClass="pagination"
              queryTmpl="page={page}&size={size}&escSrchDash=''&escapeSrchVal=${f:h(deliveryOrderStatusForm.escapeSrchVal)}" />
          </c:when>
          <c:when test="${matchInLogic }">
            <t:pagination page="${page}" disabledHref="javascript:void(0);"
              outerElementClass="pagination"
              queryTmpl="page={page}&size={size}&srchMatchInLogic=''&escapeSrchVal=${f:h(deliveryOrderStatusForm.escapeSrchVal)}" />
          </c:when>
          <c:otherwise>
            <t:pagination page="${page}" disabledHref="javascript:void(0);"
              outerElementClass="pagination"
              queryTmpl="page={page}&size={size}&methodNameConven=''&delOrderStatus=${f:h(deliveryOrderStatusForm.delOrderStatus)}" />
          </c:otherwise>


        </c:choose>
      </div>
    </c:when>
  </c:choose>

  <a id="regDelOrder" href="${pageContext.request.contextPath}/jsp/delivery/order/register">Add
    Delivery Order</a>
  <hr>
  <a id="djpaLink" href="${pageContext.request.contextPath}">Back to [DJPA] JPA
    Confirmation</a>

  <hr />
</div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>