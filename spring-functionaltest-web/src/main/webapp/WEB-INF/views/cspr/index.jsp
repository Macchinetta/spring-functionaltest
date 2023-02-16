<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.cspr.index" />
<c:set var="functionId" value="cspr" />

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

  <h1 id="screenTitle">CSRF対策</h1>

  <div>[CSPR01] CSRF対策機能の適用</div>
  <ul>
    <li><a id="cspr0101001" href="${pageContext.request.contextPath}/disabledcspr/0101/001">cspr0101001</a></li>
  </ul>


  <div>[CSPR02] HTMLフォーム使用時の連携</div>
  <ul>
    <li><a id="cspr0201001" href="${pageContext.request.contextPath}/cspr/0201/001">cspr0201001</a></li>
    <li><a id="cspr0201002" href="${pageContext.request.contextPath}/cspr/0201/002">cspr0201002</a></li>
    <li><a id="cspr0201003" href="${pageContext.request.contextPath}/cspr/0201/003">cspr0201003</a></li>
    <li><a id="cspr0201004" href="${pageContext.request.contextPath}/cspr/0201/004">cspr0201004</a></li>
    <li><a id="cspr0201005" href="${pageContext.request.contextPath}/cspr/0201/005">cspr0201005</a></li>
    <li><a id="cspr0201006" href="${pageContext.request.contextPath}/cspr/0201/006">cspr0201006</a></li>
    <li><a id="cspr0201007" href="${pageContext.request.contextPath}/cspr/0201/007">cspr0201007</a></li>
    <li><a id="cspr0201008" href="${pageContext.request.contextPath}/cspr/0201/008">cspr0201008</a></li>
  </ul>

  <div>[CSPR03] Ajax使用時の連携</div>
  <ul>
    <li><a id="cspr0301001" href="${pageContext.request.contextPath}/cspr/0301/001">cspr0301001</a></li>
    <li><a id="cspr0301002" href="${pageContext.request.contextPath}/cspr/0301/002">cspr0301002</a></li>
    <li><a id="cspr0301003" href="${pageContext.request.contextPath}/cspr/0301/003">cspr0301003</a></li>
    <li><a id="cspr0301004" href="${pageContext.request.contextPath}/cspr/0301/004">cspr0301004</a></li>
  </ul>
  <div>[CSPR03] マルチパートリクエスト時の留意点</div>
  <ul>
    <li><a id="cspr0401001" href="${pageContext.request.contextPath}/cspr/0401/001">cspr0401001</a></li>
    <li><a id="cspr0401002" href="${pageContext.request.contextPath}/cspr/0401/002">cspr0401002</a></li>
  </ul>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>