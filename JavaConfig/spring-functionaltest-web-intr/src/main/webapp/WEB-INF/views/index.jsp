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
  <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
  <div class="container">
<div id="wrapper">

  <h1 id="screenTitle">INTR 国際化</h1>

  <div>[INTR01]
    Localeをユーザ端末（またはブラウザ）の設定によって切り替える場合</div>
  <ul>
    <li>intr0101001 <a id="intr0101001_jsp" href="${pageContext.request.contextPath}/jsp/0101/001">JSP</a> / <a id="intr0101001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/001">Thymeleaf</a></li>
    <li>intr0101002 <a id="intr0101002_jsp" href="${pageContext.request.contextPath}/jsp/0101/002">JSP</a> / <a id="intr0101002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/002">Thymeleaf</a></li>
    <li>intr0101003 <a id="intr0101003_jsp" href="${pageContext.request.contextPath}/jsp/0101/003">JSP</a> / <a id="intr0101003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/003">Thymeleaf</a></li>
    <li>intr0101004 <a id="intr0101004_jsp" href="${pageContext.request.contextPath}/jsp/0101/004">JSP</a> / <a id="intr0101004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/004">Thymeleaf</a></li>
    <li>intr0101005 <a id="intr0101005_jsp" href="${pageContext.request.contextPath}/jsp/0101/005">JSP</a> / <a id="intr0101005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/005">Thymeleaf</a></li>
    <li>intr0101006 <a id="intr0101006_jsp" href="${pageContext.request.contextPath}/jsp/0101/005">JSP</a> / <a id="intr0101006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/005">Thymeleaf</a></li>
    <li>intr0101007 <a id="intr0101007_jsp" href="${pageContext.request.contextPath}/jsp/0101/005">JSP</a> / <a id="intr0101007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/005">Thymeleaf</a></li>
    <li>intr0102001 <a id="intr0102001_jsp" href="${pageContext.request.contextPath}/jsp/0102/001">JSP</a> / <a id="intr0102001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/001">Thymeleaf</a></li>
    <li>intr0102002 <a id="intr0102002_jsp" href="${pageContext.request.contextPath}/jsp/0102/002">JSP</a> / <a id="intr0102002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/002">Thymeleaf</a></li>
    <li>intr0102003 <a id="intr0102003_jsp" href="${pageContext.request.contextPath}/jsp/0102/003">JSP</a> / <a id="intr0102003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/003">Thymeleaf</a></li>
  </ul>

  <div>[INTR02] Localeを画面操作等で動的に変更する場合</div>
  <ul>
    <li>intr0201001 <a id="intr0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> / <a id="intr0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001">Thymeleaf</a></li>
    <li>intr0201002 <a id="intr0201002_jsp" href="${pageContext.request.contextPath}/jsp/0201/002">JSP</a> / <a id="intr0201002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/002">Thymeleaf</a></li>
    <li>intr0201003 <a id="intr0201003_jsp" href="${pageContext.request.contextPath}/jsp/0201/003">JSP</a> / <a id="intr0201003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/003">Thymeleaf</a></li>
    <li>intr0201004 <a id="intr0201004_jsp" href="${pageContext.request.contextPath}/jsp/0201/004">JSP</a> / <a id="intr0201004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/004">Thymeleaf</a></li>
    <li>intr0201005 <a id="intr0201005_jsp" href="${pageContext.request.contextPath}/jsp/0201/005">JSP</a> / <a id="intr0201005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/005">Thymeleaf</a></li>
    <li>intr0201006 <a id="intr0201006_jsp" href="${pageContext.request.contextPath}/jsp/0201/006">JSP</a> / <a id="intr0201006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/006">Thymeleaf</a></li>
    <li>intr0201007 <a id="intr0201007_jsp" href="${pageContext.request.contextPath}/jsp/0201/007">JSP</a> / <a id="intr0201007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/007">Thymeleaf</a></li>
    <li>intr0201008 <a id="intr0201008_jsp" href="${pageContext.request.contextPath}/jsp/0201/008">JSP</a> / <a id="intr0201008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/008">Thymeleaf</a></li>
    <li>intr0202001 <a id="intr0202001_jsp" href="${pageContext.request.contextPath}/jsp/0202/001">JSP</a> / <a id="intr0202001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/001">Thymeleaf</a></li>
    <li>intr0202002 <a id="intr0202002_jsp" href="${pageContext.request.contextPath}/jsp/0202/002">JSP</a> / <a id="intr0202002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/002">Thymeleaf</a></li>
    <li>intr0202003 <a id="intr0202003_jsp" href="${pageContext.request.contextPath}/jsp/0202/003">JSP</a> / <a id="intr0202003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/003">Thymeleaf</a></li>
    <li>intr0202004 <a id="intr0202004_jsp" href="${pageContext.request.contextPath}/jsp/0202/004">JSP</a> / <a id="intr0202004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/004">Thymeleaf</a></li>
    <li>intr0202005 <a id="intr0202005_jsp" href="${pageContext.request.contextPath}/jsp/0202/005">JSP</a> / <a id="intr0202005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/005">Thymeleaf</a></li>
    <li>intr0202006 <a id="intr0202006_jsp" href="${pageContext.request.contextPath}/jsp/0202/006">JSP</a> / <a id="intr0202006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/006">Thymeleaf</a></li>
  </ul>

  <div>[INTR03] エラーページを国際化する場合</div>
  <ul>
    <li>intr0301-login <a id="intr0301-login_jsp" href="${pageContext.request.contextPath}/jsp/0301/001/login">JSP</a> / <a id="intr0301-login_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001/login">Thymeleaf</a></li>
    <li>intr0301001 <a id="intr0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001/goToError">JSP</a> / <a id="intr0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001/goToError">Thymeleaf</a></li>
    <li>intr0301002 <a id="intr0301002_jsp" href="${pageContext.request.contextPath}/jsp/0301/002/goToError">JSP</a> / <a id="intr0301002_thymeleaf" href="${pageContext.request.contextPath}/jsthymeleafp/0301/002/goToError">Thymeleaf</a></li>
  </ul>

  <div>セッションに格納されたロケールの削除</div>
  <ul>
    <li><a id="removeLocale" href="${pageContext.request.contextPath}/intr/removeLocale">Remove
        locale from the session</a></li>
  </ul>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>