<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.prmn.index" />
<c:set var="functionId" value="prmn" />

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

  <h1 id="screenTitle">PRMN プロパティ管理</h1>

  <div>[PRMN01] プロパティ利用の設定方法</div>
  <ul>
    <li><a id="prmn0101001" href="${pageContext.request.contextPath}/prmn/0101/001">prmn0101001</a></li>
    <li><a id="prmn0101002" href="${pageContext.request.contextPath}/prmn/0101/002">prmn0101002</a></li>
    <li><a id="prmn0102001" href="${pageContext.request.contextPath}/prmn/0102/001">prmn0102001</a></li>
    <li><a id="prmn0102002" href="${pageContext.request.contextPath}/prmn/0102/002">prmn0102002</a></li>
    <li><a id="prmn0102003" href="${pageContext.request.contextPath}/prmn/0102/003">prmn0102003</a></li>
    <li><a id="prmn0103001" href="${pageContext.request.contextPath}/prmn/0103/001">prmn0103001</a></li>
  </ul>

  <div>[PRMN02] bean定義ファイルのプロパティの利用方法</div>
  <ul>
    <li><a id="prmn0201001" href="${pageContext.request.contextPath}/prmn/0201/001">prmn0201001</a></li>
    <li><a id="prmn0202001" href="${pageContext.request.contextPath}/prmn/0202/001">prmn0202001</a></li>
  </ul>

  <div>[PRMN03] Javaクラスでのプロパティ利用方法</div>
  <ul>
    <li><a id="prmn0301001" href="${pageContext.request.contextPath}/prmn/0301/001">prmn0301001</a></li>
    <li><a id="prmn0302001" href="${pageContext.request.contextPath}/prmn/0302/001">prmn0302001</a></li>
  </ul>

  <div>[PRMN04] プロパティ機能の拡張方法</div>
  <ul>
    <li><a id="prmn0401001" href="${pageContext.request.contextPath}/prmn/0401/001">prmn0401001</a></li>
  </ul>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>