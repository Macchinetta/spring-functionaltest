<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.intr.index" />
<c:set var="functionId" value="intr" />

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

  <h1 id="screenTitle">INTR 国際化</h1>

  <div>[INTR01]
    Localeをユーザ端末（またはブラウザ）の設定によって切り替える場合</div>
  <ul>
    <li><a id="intr0101001" href="${pageContext.request.contextPath}/intr/0101/001">intr0101001</a></li>
    <li><a id="intr0101002" href="${pageContext.request.contextPath}/intr/0101/002">intr0101002</a></li>
    <li><a id="intr0101003" href="${pageContext.request.contextPath}/intr/0101/003">intr0101003</a></li>
    <li><a id="intr0101004" href="${pageContext.request.contextPath}/intr/0101/004">intr0101004</a></li>
    <li><a id="intr0101005" href="${pageContext.request.contextPath}/intr/0101/005">intr0101005</a></li>
    <li><a id="intr0101006" href="${pageContext.request.contextPath}/intr/0101/005">intr0101006</a></li>
    <li><a id="intr0101007" href="${pageContext.request.contextPath}/intr/0101/005">intr0101007</a></li>
    <li><a id="intr0102001" href="${pageContext.request.contextPath}/intr/0102/001">intr0102001</a></li>
    <li><a id="intr0102002" href="${pageContext.request.contextPath}/intr/0102/002">intr0102002</a></li>
    <li><a id="intr0102003" href="${pageContext.request.contextPath}/intr/0102/003">intr0102003</a></li>
  </ul>

  <div>[INTR02] Localeを画面操作等で動的に変更する場合</div>
  <ul>
    <li><a id="intr0201001" href="${pageContext.request.contextPath}/intr/0201/001">intr0201001</a></li>
    <li><a id="intr0201002" href="${pageContext.request.contextPath}/intr/0201/002">intr0201002</a></li>
    <li><a id="intr0201003" href="${pageContext.request.contextPath}/intr/0201/003">intr0201003</a></li>
    <li><a id="intr0201004" href="${pageContext.request.contextPath}/intr/0201/004">intr0201004</a></li>
    <li><a id="intr0201005" href="${pageContext.request.contextPath}/intr/0201/005">intr0201005</a></li>
    <li><a id="intr0201006" href="${pageContext.request.contextPath}/intr/0201/006">intr0201006</a></li>
    <li><a id="intr0201007" href="${pageContext.request.contextPath}/intr/0201/007">intr0201007</a></li>
    <li><a id="intr0201008" href="${pageContext.request.contextPath}/intr/0201/008">intr0201008</a></li>
    <li><a id="intr0202001" href="${pageContext.request.contextPath}/intr/0202/001">intr0202001</a></li>
    <li><a id="intr0202002" href="${pageContext.request.contextPath}/intr/0202/002">intr0202002</a></li>
    <li><a id="intr0202003" href="${pageContext.request.contextPath}/intr/0202/003">intr0202003</a></li>
    <li><a id="intr0202004" href="${pageContext.request.contextPath}/intr/0202/004">intr0202004</a></li>
    <li><a id="intr0202005" href="${pageContext.request.contextPath}/intr/0202/005">intr0202005</a></li>
    <li><a id="intr0202006" href="${pageContext.request.contextPath}/intr/0202/006">intr0202006</a></li>
  </ul>

  <div>[INTR03] エラーページを国際化する場合</div>
  <ul>
    <li><a id="intr0301-login" href="${pageContext.request.contextPath}/intr/0301/001/login">intr0301-login</a></li>
    <li><a id="intr0301001" href="${pageContext.request.contextPath}/intr/0301/001/goToError">intr0301001</a></li>
    <li><a id="intr0301002" href="${pageContext.request.contextPath}/intr/0301/002/goToError">intr0301002</a></li>
  </ul>

  <div>セッションに格納されたロケールの削除</div>
  <ul>
    <li><a id="removeLocale" href="${pageContext.request.contextPath}/intr/removeLocale">Remove
        locale from the session</a></li>
  </ul>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>