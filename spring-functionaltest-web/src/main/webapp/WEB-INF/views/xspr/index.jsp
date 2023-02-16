<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.xspr.index" />
<c:set var="functionId" value="xspr" />

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

  <h1 id="screenTitle">XSS対策</h1>

  <div>[XSPR01] Output Escaping</div>
  <ul>
    <li><a id="xspr0101" href="${pageContext.request.contextPath}/xspr/0101">xspr0101</a></li>
  </ul>

  <div>[XSPR02] JavaScript Escaping</div>
  <ul>
    <li><a id="xspr0201001" href="${pageContext.request.contextPath}/xspr/0201/001">xspr0201001</a></li>
    <li><a id="xspr0201002" href="${pageContext.request.contextPath}/xspr/0201/002">xspr0201002</a></li>
    <li><a id="xspr0201003" href="${pageContext.request.contextPath}/xspr/0201/003">xspr0201003</a></li>
  </ul>

  <div>[XSPR03] EventHandler Escaping</div>
  <ul>
    <li><a id="xspr0301001" href="${pageContext.request.contextPath}/xspr/0301/001">xspr0301001</a></li>
    <li><a id="xspr0301002" href="${pageContext.request.contextPath}/xspr/0301/002">xspr0301002</a></li>
    <li><a id="xspr0301003" href="${pageContext.request.contextPath}/xspr/0301/003">xspr0301003</a></li>
  </ul>

</div>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>