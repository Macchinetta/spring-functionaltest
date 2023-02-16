<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.sydt.index" />
<c:set var="functionId" value="sydt" />

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

  <h1 id="screenTitle">STPR 文字列処理</h1>

  <div>[SYDT01] ClockFactory</div>
  <ul>
    <li><a id="sydt0101001" href="${pageContext.request.contextPath}/sydt/0101/001">sydt0101001</a></li>
    <li><a id="sydt0102001" href="${pageContext.request.contextPath}/sydt/0102/001">sydt0102001</a></li>
    <li><a id="sydt0103001" href="${pageContext.request.contextPath}/sydt/0103/001">sydt0103001</a></li>
    <li><a id="sydt0104001" href="${pageContext.request.contextPath}/sydt/0104/001">sydt0104001</a></li>
    <li><a id="sydt0105001" href="${pageContext.request.contextPath}/sydt/0105/001">sydt0105001</a></li>
  </ul>
  
  <div>[SYDT02] Unit Test</div>
  <ul>
    <li><a id="sydt0201001" href="${pageContext.request.contextPath}/sydt/0201/001">sydt0201001</a> ※seleniumでは確認しない</li>
  </ul>
  
  <div>[SYDT03] Integration Test</div>
  この試験やろうと思ったらBean定義しては駄目だけど、どうするかね
  
  
  <div>[SYDT04] Integration Test</div>
  defaultClockFctoryとの比較を行う感じにすればよさそう

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>