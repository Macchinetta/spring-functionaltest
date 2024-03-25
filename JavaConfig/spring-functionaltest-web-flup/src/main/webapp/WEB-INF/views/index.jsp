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
  <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
  <div class="container">
<div id="wrapper">

  <h1 id="screenTitle">FLUP ファイルアップロード</h1>

  <div>[FLUP01] アプリケーションの設定</div>
  <ul>
    <li>flup0101001 <a id="flup0101001_jsp" href="${pageContext.request.contextPath}/jsp/0101/001">JSP</a> / <a id="flup0101001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/001">Thymeleaf</a></li>
    <li>flup0102001 <a id="flup0102001_jsp" href="${pageContext.request.contextPath}/jsp/0102/001">JSP</a> / <a id="flup0102001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/001">Thymeleaf</a></li>
    <li>flup0102002 <a id="flup0102002_jsp" href="${pageContext.request.contextPath}/jsp/0102/002">JSP</a> / <a id="flup0102002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/002">Thymeleaf</a></li>
    <li>flup0102003 <a id="flup0102003_jsp" href="${pageContext.request.contextPath}/jsp/0102/003">JSP</a> / <a id="flup0102003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/003">Thymeleaf</a></li>
    <li>flup0102004 <a id="flup0102004_jsp" href="${pageContext.request.contextPath}/jsp/0102/004">JSP</a> / <a id="flup0102004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/004">Thymeleaf</a></li>
    <li>flup0102005 <a id="flup0102005_jsp" href="${pageContext.request.contextPath}/jsp/0102/005">JSP</a> / <a id="flup0102005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/005">Thymeleaf</a></li>
    <li>flup0102006 <a id="flup0102006_jsp" href="${pageContext.request.contextPath}/jsp/0102/006">JSP</a> / <a id="flup0102006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/006">Thymeleaf</a></li>
    <li>flup0103001 <a id="flup0103001_jsp" href="${pageContext.request.contextPath}/jsp/0103/001">JSP</a> / <a id="flup0103001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0103/001">Thymeleaf</a></li>
    <li>flup0103002 <a id="flup0103002_jsp" href="${pageContext.request.contextPath}/jsp/0103/002">JSP</a> / <a id="flup0103002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0103/002">Thymeleaf</a></li>
  </ul>

  <div>[FLUP02] 単一ファイルのアップロード</div>
  <ul>
    <li>flup0201001 <a id="flup0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> / <a id="flup0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001">Thymeleaf</a></li>
    <li>flup0202001 <a id="flup0202001_jsp" href="${pageContext.request.contextPath}/jsp/0202/001">JSP</a> / <a id="flup0202001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/001">Thymeleaf</a></li>
  </ul>

  <div>[FLUP03] ファイルアップロードのBean Validation</div>
  <ul>
    <li>flup0301001 <a id="flup0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> / <a id="flup0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001">Thymeleaf</a></li>
    <li>flup0301002 <a id="flup0301002_jsp" href="${pageContext.request.contextPath}/jsp/0301/002">JSP</a> / <a id="flup0301002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/002">Thymeleaf</a></li>
    <li>flup0301003 <a id="flup0301003_jsp" href="${pageContext.request.contextPath}/jsp/0301/003">JSP</a> / <a id="flup0301003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/003">Thymeleaf</a></li>
  </ul>

  <div>[FLUP04] 複数ファイルのアップロード</div>
  <ul>
    <li>flup0401001 <a id="flup0401001_jsp" href="${pageContext.request.contextPath}/jsp/0401/001">JSP</a> / <a id="flup0401001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/001">Thymeleaf</a></li>
    <li>flup0401002 <a id="flup0401002_jsp" href="${pageContext.request.contextPath}/jsp/0401/002">JSP</a> / <a id="flup0401002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/002">Thymeleaf</a></li>
    <li>flup0401003 <a id="flup0401003_jsp" href="${pageContext.request.contextPath}/jsp/0401/003">JSP</a> / <a id="flup0401003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/003">Thymeleaf</a></li>
    <li>flup0401004 <a id="flup0401004_jsp" href="${pageContext.request.contextPath}/jsp/0401/004">JSP</a> / <a id="flup0401004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/004">Thymeleaf</a></li>
    <li>flup0402001 <a id="flup0402001_jsp" href="${pageContext.request.contextPath}/jsp/0402/001">JSP</a> / <a id="flup0402001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0402/001">Thymeleaf</a></li>
    <li>flup0402002 <a id="flup0402002_jsp" href="${pageContext.request.contextPath}/jsp/0402/002">JSP</a> / <a id="flup0402002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0402/002">Thymeleaf</a></li>
  </ul>

  <div>[FLUP05] 仮アップロード</div>
  <ul>
    <li>flup0501001 <a id="flup0501001_jsp" href="${pageContext.request.contextPath}/jsp/0501/001">JSP</a> / <a id="flup0501001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/001">Thymeleaf</a></li>
    <li>flup0501002 <a id="flup0501002_jsp" href="${pageContext.request.contextPath}/jsp/0501/002">JSP</a> / <a id="flup0501002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/002">Thymeleaf</a></li>
  </ul>

  <div>[FLUP06] 仮アップロード時の不要ファイルのHousekeeping</div>
  <ul>
    <li>flup0601001 <a id="flup0601001_jsp" href="${pageContext.request.contextPath}/jsp/0601/001">JSP</a> / <a id="flup0601001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/001">Thymeleaf</a></li>
    <li>flup0601002 <a id="flup0601002_jsp" href="${pageContext.request.contextPath}/jsp/0601/002">JSP</a> / <a id="flup0601002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/002">Thymeleaf</a></li>
  </ul>

  <div>[FLUP08] アップロードしたファイルをWebサーバ上で実行する攻撃</div>
  <ul>
    <li>flup0801001 <a id="flup0801001_jsp" href="${pageContext.request.contextPath}/jsp/0801/001">JSP</a> / <a id="flup0801001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0801/001">Thymeleaf</a></li>
    <li>flup0802001 <a id="flup0802001_jsp" href="${pageContext.request.contextPath}/jsp/0802/001">JSP</a> / <a id="flup0802001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0802/001">Thymeleaf</a></li>
    <li>flup0802002 <a id="flup0802002_jsp" href="${pageContext.request.contextPath}/jsp/0802/002">JSP</a> / <a id="flup0802002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0802/002">Thymeleaf</a></li>
  </ul>

  <div>その他</div>
  <ul>
    <li><a id="temporaryFiles"
      href="${pageContext.request.contextPath}/flup/temporaryFiles?list">仮アップロード時の一時ファイル一覧</a></li>
  </ul>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>