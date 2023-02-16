<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.ssmn.index" />
<c:set var="functionId" value="ssmn" />

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

  <h1 id="screenTitle">SSMN セッション管理</h1>

  <div>[SSMN03] @SessionAttributesアノテーションの使用</div>
  <ul>
    <li><a id="ssmn0301001" href="${pageContext.request.contextPath}/ssmn/0301/001">ssmn0301001</a></li>
    <li><a id="ssmn0301002" href="${pageContext.request.contextPath}/ssmn/0301/002">ssmn0301002</a></li>
    <li><a id="ssmn0301003" href="${pageContext.request.contextPath}/ssmn/0301/003">ssmn0301003</a></li>
    <li><a id="ssmn0301004" href="${pageContext.request.contextPath}/ssmn/0301/004">ssmn0301004</a></li>
    <li><a id="ssmn0301005" href="${pageContext.request.contextPath}/ssmn/0301/005">ssmn0301005</a></li>
    <li><a id="ssmn0301006" href="${pageContext.request.contextPath}/ssmn/0301/006">ssmn0301006</a></li>
    <li><a id="ssmn0301007" href="${pageContext.request.contextPath}/ssmn/0301/007">ssmn0301007</a></li>
    <li><a id="ssmn0301008" href="${pageContext.request.contextPath}/ssmn/0301/008">ssmn0301008</a></li>
    <li><a id="ssmn0301009" href="${pageContext.request.contextPath}/ssmn/0301/009">ssmn0301009</a></li>
    <li><a id="ssmn0302001" href="${pageContext.request.contextPath}/ssmn/0302/001">ssmn0302001</a></li>
    <li><a id="ssmn0302002" href="${pageContext.request.contextPath}/ssmn/0302/002">ssmn0302002</a></li>
  </ul>
  <div>[SSMN04] Spring FrameworkのsessionスコープのBeanの使用</div>
  <ul>
    <li><a id="ssmn0401001"
      href="${pageContext.request.contextPath}/ssmn/0401/001/shopping/items?init">ssmn0401001</a></li>
    <li><a id="ssmn0401002"
      href="${pageContext.request.contextPath}/ssmn/0401/002/shopping/items?init">ssmn0401002</a></li>
    <li><a id="ssmn0402001"
      href="${pageContext.request.contextPath}/ssmn/0402/001/shopping/items?init">ssmn0402001</a></li>
  </ul>
  <div>[SSMN06] 同一セッション内のリクエストの同期化</div>
  <ul>
    <li><a id="ssmn060100101"
      href="${pageContext.request.contextPath}/ssmn/0601/001/synchronism">ssmn0601001(次リクエスト実行)</a></li>
    <li><a id="ssmn060100102"
      href="${pageContext.request.contextPath}/ssmn/0601/001/synchronism/delay">ssmn0601001(最初リクエスト実行)</a></li>
  </ul>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>