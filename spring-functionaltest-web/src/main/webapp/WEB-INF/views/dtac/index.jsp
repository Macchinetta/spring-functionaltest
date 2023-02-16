<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dtac.index" />
<c:set var="functionId" value="dtac" />

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

  <h1 id="screenTitle">データベースアクセス（共通編）</h1>

  <div>[DTAC01] データソースの設定</div>
  <ul>
    <li><a id="dtac0101001" href="${pageContext.request.contextPath}/dtac/0101/001">dtac0101001</a></li>
    <li><a id="dtac0101002" href="${pageContext.request.contextPath}/dtac/0101/002">dtac0101002</a></li>
    <li><a id="dtac0102001" href="${pageContext.request.contextPath}/dtac/0102/001">dtac0102001</a></li>
    <li><a id="dtac0102002" href="${pageContext.request.contextPath}/dtac/0102/002">dtac0102002</a></li>
    <li><a id="dtac0201001" href="${pageContext.request.contextPath}/dtac/0201/001">dtac0201001</a></li>
    <li><a id="dtac0201002" href="${pageContext.request.contextPath}/dtac/0201/002">dtac0201002</a></li>
  </ul>

  <div>[DTAC02] JDBCのDebug用ログの設定</div>
  <ul>
    <li><a id="dtac0201001" href="${pageContext.request.contextPath}/dtac/0201/001">dtac0201001</a></li>
  </ul>

  <div>[DTAC04] 例外ハンドリングについて</div>
  <ul>
    <li><a id="dtac0401001" href="${pageContext.request.contextPath}/dtac/0401/001">dtac0401001</a></li>
    <li><a id="dtac0401002" href="${pageContext.request.contextPath}/dtac/0401/002">dtac0401002</a></li>
  </ul>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>