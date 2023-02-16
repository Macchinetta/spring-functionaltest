<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.cdls.index" />
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
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />
  <div class="container">
<div id="wrapper">

  <h1 id="screenTitle">CDLS コードリスト機能</h1>

  <div>[CDLS01] コードリストの読み込み</div>
  <ul>
    <li><a id="cdls0101001" href="${pageContext.request.contextPath}/cdls/0101/001">cdls0101001</a></li>
    <li><a id="cdls0101002" href="${pageContext.request.contextPath}/cdls/0101/002">cdls0101002</a></li>
    <li><a id="cdls0102001" href="${pageContext.request.contextPath}/cdls/0102/001">cdls0102001</a></li>
    <li><a id="cdls0102002" href="${pageContext.request.contextPath}/cdls/0102/002">cdls0102002</a></li>
    <li><a id="cdls0103001" href="${pageContext.request.contextPath}/cdls/0103/001">cdls0103001</a></li>
    <li><a id="cdls0103002" href="${pageContext.request.contextPath}/cdls/0103/002">cdls0103002</a></li>
    <li><a id="cdls0104001" href="${pageContext.request.contextPath}/cdls/0104/001">cdls0104001</a></li>
    <li><a id="cdls0104002" href="${pageContext.request.contextPath}/cdls/0104/002">cdls0104002</a></li>
    <li><a id="cdls0105001" href="${pageContext.request.contextPath}/cdls/0105/001?locale=ja">cdls0105001</a></li>
    <li><a id="cdls0105002" href="${pageContext.request.contextPath}/cdls/0105/002?locale=en">cdls0105002</a></li>
    <li><a id="cdls0105003" href="${pageContext.request.contextPath}/cdls/0105/003?locale=ja">cdls0105003</a></li>
    <li><a id="cdls0105004" href="${pageContext.request.contextPath}/cdls/0105/004?locale=en">cdls0105004</a></li>
    <li><a id="cdls0105005" href="${pageContext.request.contextPath}/cdls/0105/005?locale=ja">cdls0105005</a></li>
    <li><a id="cdls0105006" href="${pageContext.request.contextPath}/cdls/0105/006?locale=en">cdls0105006</a></li>
    <li><a id="cdls0105007" href="${pageContext.request.contextPath}/cdls/0105/007?locale=ja">cdls0105007</a></li>
    <li><a id="cdls0105008" href="${pageContext.request.contextPath}/cdls/0105/008?locale=en">cdls0105008</a></li>
    <li><a id="cdls0106001" href="${pageContext.request.contextPath}/cdls/0106/001">cdls0106001</a></li>
    <li><a id="cdls0107001" href="${pageContext.request.contextPath}/cdls/0107/001">cdls0107001</a></li>
    <li><a id="cdls0107002" href="${pageContext.request.contextPath}/cdls/0107/002">cdls0107002</a></li>
  </ul>
  <div>[CDLS02] コードリストのリロード</div>
  <ul>
    <li><a id="cdls0201001" href="${pageContext.request.contextPath}/cdls/0201/001">cdls0201001</a></li>
    <li><a id="cdls0201002" href="${pageContext.request.contextPath}/cdls/0201/002">cdls0201002</a></li>
  </ul>
  <div>[CDLS03] コードリストの独自カスタマイズ</div>
  <ul>
    <li><a id="cdls0301001" href="${pageContext.request.contextPath}/cdls/0301/001">cdls0301001</a></li>
  </ul>
  <div>[CDLS04] コードリストの記述方法のバリエーション</div>
  <ul>
    <li><a id="cdls0401001" href="${pageContext.request.contextPath}/cdls/0401/001?locale=ja">cdls0401001</a></li>
    <li><a id="cdls0401002" href="${pageContext.request.contextPath}/cdls/0401/002?locale=en">cdls0401002</a></li>
    <li><a id="cdls0402001" href="${pageContext.request.contextPath}/cdls/0402/001?locale=ja">cdls0402001</a></li>
    <li><a id="cdls0402002" href="${pageContext.request.contextPath}/cdls/0402/002?locale=en">cdls0402002</a></li>
    <li><a id="cdls0403001" href="${pageContext.request.contextPath}/cdls/0403/001">cdls0403001</a></li>
    <li><a id="cdls0403002" href="${pageContext.request.contextPath}/cdls/0403/002">cdls0403002</a></li>
    <li><a id="cdls0403003" href="${pageContext.request.contextPath}/cdls/0403/003">cdls0403003</a></li>
  </ul>
  <div>[CDLS05] SpEL式でのコードリスト参照</div>
  <ul>
    <li><a id="cdls0501001" href="${pageContext.request.contextPath}/cdls/0501/001">cdls0501001</a></li>
    <li><a id="cdls0501002" href="${pageContext.request.contextPath}/cdls/0501/002?locale=ja">cdls0501002</a></li>
    <li><a id="cdls0501003" href="${pageContext.request.contextPath}/cdls/0501/003?locale=en">cdls0501003</a></li>
    <li><a id="cdls0501004"
      href="${pageContext.request.contextPath}/cdls/0501/004?locale=de">cdls0501004</a></li>
  </ul>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>