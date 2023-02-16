<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.spsm.index" />
<c:set var="functionId" value="spsm" />

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

  <h1 id="screenTitle">Spring Security セッション管理</h1>

  <div>[spsm02] セッション利用時のセキュリティ対策</div>
  <ul>
    <li><a id="spsm0201001"
      href="${pageContext.request.contextPath}/spsm/0201/001?loginSuccess">spsm0201001</a></li>
    <li><a id="spsm0202001"
      href="${pageContext.request.contextPath}/spsm/0202/001?loginSuccess">spsm0202001</a></li>
    <li><a id="spsm0202002"
      href="${pageContext.request.contextPath}/spsm/0202/002?loginSuccess">spsm0202002</a></li>


  </ul>
  <div>[spsm03] セッションのライフサイクル制御</div>
  <ul>
    <li>REST APIで実施するため画面なし</li>
  </ul>
  <div>[spsm04] セッションタイムアウトの制御</div>
  <ul>
    <li><a id="spsm0403001001"
      href="${pageContext.request.contextPath}/spsm/0403/001/001/login">spsm0403001</a></li>
    <li><a id="spsm0403001002"
      href="${pageContext.request.contextPath}/spsm/0403/001/002/login">spsm0403001(Invalid
        Session)</a></li>
  </ul>
  <div>[spsm05] 多重ログインの制御</div>
  <ul>
    <li><a id="spsm0501001" href="${pageContext.request.contextPath}/spsm/0501/001/login">spsm0501001</a></li>
    <li><a id="spsm0501001nologin"
      href="${pageContext.request.contextPath}/spsm/0501/001/noLogin">spsm0501001nologin</a></li>

    <li><a id="spsm0501002" href="${pageContext.request.contextPath}/spsm/0501/002/login">spsm0501002</a></li>
  </ul>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>