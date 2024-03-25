<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.cdls.validationCodeList" />
<c:set var="functionId" value="cdls" />

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

  <h1 id="screenTitle">コード値の入力チェック</h1>

  <div>CDLS0107001</div>
  <div>
    <form:form action="${pageContext.request.contextPath}/jsp/0107/001?post" method="post"
      modelAttribute="clOrderStatusForm">
      <form:select id="cdls0107001_jsp" path="id">
        <form:options items="${CL_ORDERSTATUS}" />
      </form:select>
      <form:errors id="errorCDLS0107001" path="id" />
      <button type="submit" id="submitCDLS0107001">Submit Codelist</button>
    </form:form>
  </div>
  <div>CDLS0107002</div>
  <div>
    <form:form action="${pageContext.request.contextPath}/jsp/0107/002?post" method="post"
      modelAttribute="clOrderStatusForm">
      <form:select id="cdls0107002_jsp" path="id">
        <form:option value="nonExistentValue" label="nonExistentValue" />
      </form:select>
      <form:errors id="errorCDLS0107002" path="id" />
      <button type="submit" id="submitCDLS0107002">Submit Codelist</button>
    </form:form>
  </div>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>