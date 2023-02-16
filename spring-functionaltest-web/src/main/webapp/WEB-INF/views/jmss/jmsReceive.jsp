<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.jmss.jmsReceive" />
<c:set var="functionId" value="jmss" />

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
<div id="wrapper">

  <h1 id="screenTitle">メッセージ受信</h1>

  <!-- 受信したデータを取得する場合 -->
  <c:if test="${not empty uniqueIdentifier}">
    <form:form method="POST" action="${pageContext.request.contextPath}/jmss/receivemessage"
      modelAttribute="jmsReceivingForm" cssClass="form-horizontal">
            ユニークキー：<span id="uniqueIdentifier"> ${f:h(uniqueIdentifier)} </span>


      <c:if test="${not empty receiveCount}">
             受信件数：<span id="receiveCount"> ${f:h(receiveCount)} </span>
      </c:if>

      <c:if test="${not empty priority}">
             JMSメッセージ優先度（priority）：<span id="receiveCount">
          ${f:h(priority)} </span>
      </c:if>

      <c:if test="${not empty deliveryMode}">
             JMSメッセージデリバリモード（deliveryMode）：<span id="receiveCount">
          ${f:h(deliveryMode)} </span>
      </c:if>

      <c:if test="${not empty receiveQueue}">
             受信したQueue（receiveQueue）：<span id="receiveQueue">
          ${f:h(receiveQueue)} </span>
      </c:if>

    </form:form>
  </c:if>

  <!-- 受信したデータが存在しなかった場合 -->
  <c:if test="${empty uniqueIdentifier}">
    <c:if test="${empty initFlg}">
           ユニークキー：<span id="uniqueIdentifier"> Not Received! </span>
    </c:if>
  </c:if>

  <!-- 受信したデータを取得しにいく -->
  <c:if test="${empty uniqueIdentifier}">
    <c:if test="${not empty initFlg}">
      <form:form method="POST" action="${pageContext.request.contextPath}/jmss/receivemessage"
        modelAttribute="jmsReceivingForm" cssClass="form-horizontal">
        <form:hidden path="jmsTodoId" />
        <form:hidden path="testCase" />
        <div class="form-group">
          <button id="receiveMessage" name="receiveMessage" class="btn btn-default">結果取得</button>
        </div>
        <%--              <form:input path="uniqueIdentifier"  /> --%>
      </form:form>
    </c:if>
  </c:if>
</div>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>