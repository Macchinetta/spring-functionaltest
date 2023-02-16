<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.flup.index" />
<c:set var="functionId" value="flup" />

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

  <h1 id="screenTitle">FLUP ファイルアップロード</h1>

  <div>[FLUP01] アプリケーションの設定</div>
  <ul>
    <li><a id="flup0101001" href="${pageContext.request.contextPath}/flup/0101/001">flup0101001</a></li>
    <li><a id="flup0102001" href="${pageContext.request.contextPath}/flup/0102/001">flup0102001</a></li>
    <li><a id="flup0102002" href="${pageContext.request.contextPath}/flup/0102/002">flup0102002</a></li>
    <li><a id="flup0102003" href="${pageContext.request.contextPath}/flup/0102/003">flup0102003</a></li>
    <li><a id="flup0102004" href="${pageContext.request.contextPath}/flup/0102/004">flup0102004</a></li>
    <li><a id="flup0102005" href="${pageContext.request.contextPath}/flup/0102/005">flup0102005</a></li>
    <li><a id="flup0102006" href="${pageContext.request.contextPath}/flup/0102/006">flup0102006</a></li>
    <li><a id="flup0103001" href="${pageContext.request.contextPath}/flup/0103/001">flup0103001</a></li>
    <li><a id="flup0103002" href="${pageContext.request.contextPath}/flup/0103/002">flup0103002</a></li>
  </ul>

  <div>[FLUP02] 単一ファイルのアップロード</div>
  <ul>
    <li><a id="flup0201001" href="${pageContext.request.contextPath}/flup/0201/001">flup0201001</a></li>
    <li><a id="flup0202001" href="${pageContext.request.contextPath}/flup/0202/001">flup0202001</a></li>
  </ul>

  <div>[FLUP03] ファイルアップロードのBean Validation</div>
  <ul>
    <li><a id="flup0301001" href="${pageContext.request.contextPath}/flup/0301/001">flup0301001</a></li>
    <li><a id="flup0301002" href="${pageContext.request.contextPath}/flup/0301/002">flup0301002</a></li>
    <li><a id="flup0301003" href="${pageContext.request.contextPath}/flup/0301/003">flup0301003</a></li>
  </ul>

  <div>[FLUP04] 複数ファイルのアップロード</div>
  <ul>
    <li><a id="flup0401001" href="${pageContext.request.contextPath}/flup/0401/001">flup0401001</a></li>
    <li><a id="flup0401002" href="${pageContext.request.contextPath}/flup/0401/002">flup0401002</a></li>
    <li><a id="flup0401003" href="${pageContext.request.contextPath}/flup/0401/003">flup0401003</a></li>
    <li><a id="flup0401004" href="${pageContext.request.contextPath}/flup/0401/004">flup0401004</a></li>
    <li><a id="flup0402001" href="${pageContext.request.contextPath}/flup/0402/001">flup0402001</a></li>
    <li><a id="flup0402002" href="${pageContext.request.contextPath}/flup/0402/002">flup0402002</a></li>
  </ul>

  <div>[FLUP05] 仮アップロード</div>
  <ul>
    <li><a id="flup0501001" href="${pageContext.request.contextPath}/flup/0501/001">flup0501001</a></li>
    <li><a id="flup0501002" href="${pageContext.request.contextPath}/flup/0501/002">flup0501002</a></li>
  </ul>

  <div>[FLUP06] 仮アップロード時の不要ファイルのHousekeeping</div>
  <ul>
    <li><a id="flup0601001" href="${pageContext.request.contextPath}/flup/0601/001">flup0601001</a></li>
    <li><a id="flup0601002" href="${pageContext.request.contextPath}/flup/0601/002">flup0601002</a></li>
  </ul>

  <div>[FLUP08] アップロードしたファイルをWebサーバ上で実行する攻撃</div>
  <ul>
    <li><a id="flup0801001" href="${pageContext.request.contextPath}/flup/0801/001">flup0801001</a></li>
    <li><a id="flup0802001" href="${pageContext.request.contextPath}/flup/0802/001">flup0802001</a></li>
    <li><a id="flup0802002" href="${pageContext.request.contextPath}/flup/0802/002">flup0802002</a></li>
  </ul>

  <div>その他</div>
  <ul>
    <li><a id="temporaryFiles"
      href="${pageContext.request.contextPath}/flup/temporaryFiles?list">仮アップロード時の一時ファイル一覧</a></li>
  </ul>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>