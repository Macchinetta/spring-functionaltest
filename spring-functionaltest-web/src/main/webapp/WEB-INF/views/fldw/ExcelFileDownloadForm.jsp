<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.fldw.ExcelFileDownloadForm" />
<c:set var="functionId" value="fldw" />

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

  <h1 id="screenTitle">Excelファイルのダウンロード</h1>

  <form:form method="POST" action="${pageContext.request.contextPath}/fldw/0201/001"
    modelAttribute="contentDownloadForm" cssClass="form-horizontal">
    <fieldset>
      <legend>新規口座開設用情報入力</legend>
      <div class="form-group">
        <div class="col col-md-2">名前</div>
        <div class="col col-md-3">
          <form:input path="name" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">住所</div>
        <div class="col col-md-3">
          <form:input path="address" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">生年月日(yyyyMMdd)</div>
        <div class="col col-md-3">
          <form:input path="birthdate" cssClass="quantity form-control input-sm" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-10 col-md-offset-2">
          <input type="submit" id="downloadButton" name="excel" value="ダウンロード"
            class="btn btn-default">
        </div>
      </div>
    </fieldset>
  </form:form>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>