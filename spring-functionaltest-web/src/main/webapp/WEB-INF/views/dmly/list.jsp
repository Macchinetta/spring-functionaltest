<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dmly.list" />
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
<div class="row">

  <h1 id="screenTitle">配達オーダ一覧</h1>
  <div>
    <form:form modelAttribute="deliveryOrderListForm"
      action="${pageContext.request.contextPath}/dmly/deliveryorder/register">
      <form:button name="form" class="btn btn-default">登録</form:button>
    </form:form>
  </div>
  <br>
  <table class="table table-striped table-bordered table-condensed">
    <thead>
      <tr>
        <th class="no">NO</th>
        <th class="deliveryNo">配達No</th>
        <th class="deliveryType">配達種別</th>
        <th class="senderName">送り主名</th>
        <th class="senderAddress">送り主住所</th>
        <th class="recieverName">送り先名</th>
        <th class="recieverAddress">送り先住所</th>
        <th class="acceptDatetime">受付日時</th>
        <th class="completionDatetime">完了日時</th>
        <th class="deliveryDriver">配達者</th>
        <th class="deliveryStatus">状態</th>
      </tr>
    </thead>
    <c:forEach var="deliveryOrder" items="${page.content}" varStatus="rowStatus">
      <tr>
        <td class="no" id="no">${(page.number * page.size)
                            + rowStatus.count}</td>
        <td class="deliveryNo"><a id="deliveryNo_${rowStatus.count}"
          href="${pageContext.request.contextPath}/dmly/deliveryorder/${deliveryOrder.deliveryNo}/update">
            ${f:h(deliveryOrder.deliveryNo)}</a></td>
        <td class="deliveryType" id="deliveryType">${f:h(deliveryOrder.deliveryType.deliveryTypeName)}</td>
        <td class="senderName" id="senderName">${f:h(deliveryOrder.senderName)}</td>
        <td class="senderAddress" id="senderAddress">${f:h(deliveryOrder.senderAddress)}</td>
        <td class="recieverName" id="recieverName">${f:h(deliveryOrder.recieverName)}</td>
        <td class="recieverAddress" id="recieverAddress">${f:h(deliveryOrder.recieverAddress)}</td>
        <td class="acceptDatetime" id="acceptDatetime"><javatime:format
            value="${deliveryOrder.acceptDatetime}" pattern="yyyy/MM/dd hh:mm:ss" /></td>
        <td class="completionDatetime" id="completionDatetime"><javatime:format
            value="${deliveryOrder.completionDatetime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
        <td class="deriveryDriver" id="deliveryDriver">${f:h(deliveryOrder.deliveryDriver)}</td>
        <td class="deriveryStatus" id="deliveryStatus">${f:h(deliveryOrder.deliveryStatus)}</td>
      </tr>
    </c:forEach>
  </table>

  <div class="paginationPart" align="center">
    <t:pagination page="${page}" disabledHref="javascript:void(0);" outerElementClass="pagination"
      queryTmpl="page={page}&size={size}" />
  </div>

  <form:form modelAttribute="deliveryOrderListForm"
    action="${pageContext.request.contextPath}/dmly/deliveryorder/update">
    <fieldset>
      <legend>複数件更新</legend>
      <div class="container">
        <div class="row">
          <div class="col-md-11">
            <label class="form-label form-label">受付日時 : </label>
            <form:input path="fromAcceptDatetime" class="form-control form-input"
              value="${f:h(deliveryOrderListForm.fromAcceptDatetime)}" />
            <label class="form-label form-label"> ～ </label>
            <form:input path="toAcceptDatetime" class="form-control form-input"
              value="${f:h(deliveryOrderListForm.toAcceptDatetime)}" />
            <label class="form-label form-label">完了日時 : </label>
            <form:input path="updateCompletionDatetime" class="form-control form-input"
              value="${f:h(deliveryOrderListForm.updateCompletionDatetime)}" />
          </div>
          <div class="col-md-1">
            <form:button name="updateCriteria" class="btn btn-default">更新</form:button>
          </div>
        </div>
      </div>
    </fieldset>
  </form:form>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>