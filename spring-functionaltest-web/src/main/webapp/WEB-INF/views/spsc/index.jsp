<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.spsc.index" />
<c:set var="functionId" value="spsc" />

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

  <h1 id="screenTitle">Spring Security 基本設定</h1>

  <div>[spsc01] spring security の基本設定</div>
  <ul>
    <li><a id="spsc0101001" href="${pageContext.request.contextPath}/spsc/0101/001">spsc0101001</a></li>
  </ul>
  <div>[spsc02] セキュリティヘッダ出力機能の適用</div>
  <ul>
    <li><a id="spsc0201001" href="${pageContext.request.contextPath}/spsc/0201/001">spsc0201001</a></li>
    <li><a id="spsc0201002" href="${pageContext.request.contextPath}/spsc/0201/002">spsc0201002</a></li>
    <li><a id="spsc0201003" href="${pageContext.request.contextPath}/spsc/0201/003">spsc0201003</a></li>
    <li><a id="spsc0201004" href="${pageContext.request.contextPath}/spsc/0201/004">spsc0201004</a></li>
    <li><a id="spsc0201005" href="${pageContext.request.contextPath}/spsc/0201/005">spsc0201005</a></li>
    <li><a id="spsc0201006" href="${pageContext.request.contextPath}/spsc/0201/006">spsc0201006</a></li>
  </ul>
  <div>[spsc03] カスタムヘッダの出力</div>
  <ul>
    <li><a id="spsc0301001" href="${pageContext.request.contextPath}/spsc/0301/001">spsc0301001</a></li>
  </ul>
  <div>[spsc04] リクエストパターン毎のセキュリティヘッダの出力</div>
  <ul>
    <li><a id="spsc0401001NotSecure"
      href="${pageContext.request.contextPath}/spsc/0401/001/notsecure/001">spsc0401001(Not
        Secure)</a></li>
    <li><a id="spsc0401001Secure"
      href="${pageContext.request.contextPath}/spsc/0401/001/secure/001">spsc0401001(Secure)</a></li>
  </ul>
  <div>[spsc05] CSPセキュリティヘッダの出力</div>
  <ul>
    <li><a id="spsc0501001" href="${pageContext.request.contextPath}/spsc/0501/001">spsc0501001</a></li>
    <li><a id="spsc0501002" href="${pageContext.request.contextPath}/spsc/0501/002">spsc0501002</a></li>
    <li><a id="spsc0501003" href="${pageContext.request.contextPath}/spsc/0501/003">spsc0501003</a></li>
    <li><a id="spsc0501004" href="${pageContext.request.contextPath}/spsc/0501/004">spsc0501004</a></li>
    <li><a id="spsc0501005" href="${pageContext.request.contextPath}/spsc/0501/005">spsc0501005</a></li>
  </ul>
  <div>[spsc06] HPKPセキュリティヘッダの出力(HTTPS環境のみ)</div>
  <ul>
    <li><a id="spsc0601001" href="${pageContext.request.contextPath}/spsc/0601/001">spsc0601001</a></li>
    <li><a id="spsc0601002" href="${pageContext.request.contextPath}/spsc/0601/002">spsc0601002</a></li>
    <li><a id="spsc0601003" href="${pageContext.request.contextPath}/spsc/0601/003">spsc0601003</a></li>
  </ul>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>