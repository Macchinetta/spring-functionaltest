<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dtop.getDateAndTime" />
<c:set var="functionId" value="dtop" />

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
  <h1 id="screenTitle">日付取得</h1>
  <br>
  <form action="${pageContext.request.contextPath}/dtop/getdate" class="form-horizontal"
    method="get">
    <fieldset>
      <legend>現在日付取得</legend>
      <div class="form-group">
        <button id="getNowDateTime" name="getNowDateTime" class="btn btn-default">DateTime取得</button>
        <button id="getNowLocalDate" name="getNowLocalDate" class="btn btn-default">LocalDate取得</button>
        <button id="getNowLocalTime" name="getNowLocalTime" class="btn btn-default">LocalTime取得</button>
        <button id="getNowDateMidnight" name="getNowDateMidnight" class="btn btn-default">DateMidnight取得</button>
        <button id="getWithTimeAtStartOfDay" name="getWithTimeAtStartOfDay" class="btn btn-default">TimeAtStartOfDay取得</button>
        <button id="getPartOfDateTime" name="getPartOfDateTime" class="btn btn-default">個別取得</button>
      </div>
    </fieldset>
  </form>
  <br />
  <form:form action="${pageContext.request.contextPath}/dtop/getdate" cssClass="form-horizontal"
    method="get" modelAttribute="getDateAndTimeForm">
    <legend>現在日付をTimeZoneを指定して取得</legend>
    <div class="form-group">
      <div class="col col-md-1">
        <form:label path="targetTimeZone">Timezone</form:label>
      </div>
      <div class="col col-md-3">
        <form:input path="targetTimeZone" cssClass="form-control input-sm" />
      </div>
    </div>
    <div class="form-group">
      <form:button name="getDateTimeSpecifiedTimezone" class="btn btn-default">TimeZone指定して取得</form:button>
    </div>
  </form:form>
</div>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>