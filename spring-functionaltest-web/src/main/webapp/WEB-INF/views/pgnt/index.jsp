<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.pgnt.index" />
<c:set var="functionId" value="pgnt" />

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

  <h1 id="screenTitle">PGNT ページネーション</h1>

  <div>[PGNT01] アプリケーションの設定</div>
  <ul>
    <li><a id="pgnt0101001" href="${pageContext.request.contextPath}/pgnt/0101/001">pgnt0101001</a></li>
    <li><a id="pgnt0101002" href="${pageContext.request.contextPath}/pgnt/0101/002">pgnt0101002</a></li>
    <li><a id="pgnt0101003" href="${pageContext.request.contextPath}/pgnt/0101/003">pgnt0101003</a></li>
    <li><a id="pgnt0102001" href="${pageContext.request.contextPath}/pgnt/0102/001">pgnt0102001</a></li>
    <li><a id="pgnt0102002" href="${pageContext.request.contextPath}/pgnt/0102/002">pgnt0102002</a></li>
  </ul>

  <div>[PGNT02] ページ検索の実装</div>
  <ul>
    <li><a id="pgnt0202001" href="${pageContext.request.contextPath}/pgnt/0202/001">pgnt0202001</a></li>
    <li><a id="pgnt0202002" href="${pageContext.request.contextPath}/pgnt/0202/002">pgnt0202002</a></li>
    <li><a id="pgnt0202003" href="${pageContext.request.contextPath}/pgnt/0202/003">pgnt0202003</a></li>
    <li><a id="pgnt0202004" href="${pageContext.request.contextPath}/pgnt/0202/004">pgnt0202004</a></li>
    <li><a id="pgnt0202005" href="${pageContext.request.contextPath}/pgnt/0202/005">pgnt0202005</a></li>
  </ul>

  <div>[PGNT05] Serviceの実装(Mybatis3編)</div>
  <ul>
    <li><a id="pgnt0502001" href="${pageContext.request.contextPath}/pgnt/0502/001">pgnt0502001</a></li>
    <li><a id="pgnt0502002" href="${pageContext.request.contextPath}/pgnt/0502/002">pgnt0502002</a></li>
    <li><a id="pgnt0502003" href="${pageContext.request.contextPath}/pgnt/0502/003">pgnt0502003</a></li>
  </ul>

  <div>[PGNT06] JSPの実装(基本編)</div>
  <ul>
    <li><a id="pgnt0602001" href="${pageContext.request.contextPath}/pgnt/0602/001">pgnt0602001</a></li>
    <li><a id="pgnt0602002" href="${pageContext.request.contextPath}/pgnt/0602/002">pgnt0602002</a></li>
    <li><a id="pgnt0602003" href="${pageContext.request.contextPath}/pgnt/0602/003">pgnt0602003</a></li>
    <li><a id="pgnt0602004" href="${pageContext.request.contextPath}/pgnt/0602/004">pgnt0602004</a></li>
    <li><a id="pgnt0602005" href="${pageContext.request.contextPath}/pgnt/0602/005">pgnt0602005</a></li>
    <li><a id="pgnt0602006" href="${pageContext.request.contextPath}/pgnt/0602/006">pgnt0602006</a></li>
    <li><a id="pgnt0602007" href="${pageContext.request.contextPath}/pgnt/0602/007">pgnt0602007</a></li>
    <li><a id="pgnt0602008" href="${pageContext.request.contextPath}/pgnt/0602/008">pgnt0602008</a></li>
    <li><a id="pgnt0602009" href="${pageContext.request.contextPath}/pgnt/0602/009">pgnt0602009</a></li>
    <li><a id="pgnt0602010" href="${pageContext.request.contextPath}/pgnt/0602/010">pgnt0602010</a></li>
    <li><a id="pgnt0602011" href="${pageContext.request.contextPath}/pgnt/0602/011">pgnt0602011</a></li>
    <li><a id="pgnt0603001" href="${pageContext.request.contextPath}/pgnt/0603/001">pgnt0603001</a></li>
    <li><a id="pgnt0603002" href="${pageContext.request.contextPath}/pgnt/0603/002">pgnt0603002</a></li>
  </ul>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>