<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.pgnt.index" />
<c:set var="functionId" value="pgnt" />

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

      <h1 id="screenTitle">PGNT ページネーション</h1>

      <div>[PGNT01] アプリケーションの設定</div>
      <ul>
        <li>pgnt0101001 (<a id="pgnt0101001_jsp" href="${pageContext.request.contextPath}/jsp/0101/001">JSP</a> / <a id="pgnt0101001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/001">Thymeleaf</a>)</li>
        <li>pgnt0101002 (<a id="pgnt0101002_jsp" href="${pageContext.request.contextPath}/jsp/0101/002">JSP</a> / <a id="pgnt0101002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/002">Thymeleaf</a>)</li>
        <li>pgnt0101003 (<a id="pgnt0101003_jsp" href="${pageContext.request.contextPath}/jsp/0101/003">JSP</a> / <a id="pgnt0101003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/003">Thymeleaf</a>)</li>
        <li>pgnt0102001 (<a id="pgnt0102001_jsp" href="${pageContext.request.contextPath}/jsp/0102/001">JSP</a> / <a id="pgnt0102001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/001">Thymeleaf</a>)</li>
        <li>pgnt0102002 (<a id="pgnt0102002_jsp" href="${pageContext.request.contextPath}/jsp/0102/002">JSP</a> / <a id="pgnt0102002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/002">Thymeleaf</a>)</li>
      </ul>

      <div>[PGNT02] ページ検索の実装</div>
      <ul>
        <li>pgnt0202001 (<a id="pgnt0202001_jsp" href="${pageContext.request.contextPath}/jsp/0202/001">JSP</a> / <a id="pgnt0202001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/001">Thymeleaf</a>)</li>
        <li>pgnt0202002 (<a id="pgnt0202002_jsp" href="${pageContext.request.contextPath}/jsp/0202/002">JSP</a> / <a id="pgnt0202002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/002">Thymeleaf</a>)</li>
        <li>pgnt0202003 (<a id="pgnt0202003_jsp" href="${pageContext.request.contextPath}/jsp/0202/003">JSP</a> / <a id="pgnt0202003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/003">Thymeleaf</a>)</li>
        <li>pgnt0202004 (<a id="pgnt0202004_jsp" href="${pageContext.request.contextPath}/jsp/0202/004">JSP</a> / <a id="pgnt0202004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/004">Thymeleaf</a>)</li>
        <li>pgnt0202005 (<a id="pgnt0202005_jsp" href="${pageContext.request.contextPath}/jsp/0202/005">JSP</a> / <a id="pgnt0202005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/005">Thymeleaf</a>)</li>
      </ul>

      <div>[PGNT05] Serviceの実装(Mybatis3編)</div>
      <ul>
        <li>pgnt0502001 (<a id="pgnt0502001_jsp" href="${pageContext.request.contextPath}/jsp/0502/001">JSP</a> / <a id="pgnt0502001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0502/001">Thymeleaf</a>)</li>
        <li>pgnt0502002 (<a id="pgnt0502002_jsp" href="${pageContext.request.contextPath}/jsp/0502/002">JSP</a> / <a id="pgnt0502002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0502/002">Thymeleaf</a>)</li>
        <li>pgnt0502003 (<a id="pgnt0502003_jsp" href="${pageContext.request.contextPath}/jsp/0502/003">JSP</a> / <a id="pgnt0502003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0502/003">Thymeleaf</a>)</li>
      </ul>

      <div>[PGNT06] JSPの実装(基本編)</div>
      <ul>
        <li>pgnt0602001 (<a id="pgnt0602001_jsp" href="${pageContext.request.contextPath}/jsp/0602/001">JSP</a> / <a id="pgnt0602001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/001">Thymeleaf</a>)</li>
        <li>pgnt0602002 (<a id="pgnt0602002_jsp" href="${pageContext.request.contextPath}/jsp/0602/002">JSP</a> / <a id="pgnt0602002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/002">Thymeleaf</a>)</li>
        <li>pgnt0602003 (<a id="pgnt0602003_jsp" href="${pageContext.request.contextPath}/jsp/0602/003">JSP</a> / <a id="pgnt0602003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/003">Thymeleaf</a>)</li>
        <li>pgnt0602004 (<a id="pgnt0602004_jsp" href="${pageContext.request.contextPath}/jsp/0602/004">JSP</a> / <a id="pgnt0602004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/004">Thymeleaf</a>)</li>
        <li>pgnt0602005 (<a id="pgnt0602005_jsp" href="${pageContext.request.contextPath}/jsp/0602/005">JSP</a> / <a id="pgnt0602005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/005">Thymeleaf</a>)</li>
        <li>pgnt0602006 (<a id="pgnt0602006_jsp" href="${pageContext.request.contextPath}/jsp/0602/006">JSP</a> / <a id="pgnt0602006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/006">Thymeleaf</a>)</li>
        <li>pgnt0602007 (<a id="pgnt0602007_jsp" href="${pageContext.request.contextPath}/jsp/0602/007">JSP</a> / <a id="pgnt0602007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/007">Thymeleaf</a>)</li>
        <li>pgnt0602008 (<a id="pgnt0602008_jsp" href="${pageContext.request.contextPath}/jsp/0602/008">JSP</a> / <a id="pgnt0602008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/008">Thymeleaf</a>)</li>
        <li>pgnt0602009 (<a id="pgnt0602009_jsp" href="${pageContext.request.contextPath}/jsp/0602/009">JSP</a> / <a id="pgnt0602009_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/009">Thymeleaf</a>)</li>
        <li>pgnt0602010 (<a id="pgnt0602010_jsp" href="${pageContext.request.contextPath}/jsp/0602/010">JSP</a> / <a id="pgnt0602010_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/010">Thymeleaf</a>)</li>
        <li>pgnt0602011 (<a id="pgnt0602011_jsp" href="${pageContext.request.contextPath}/jsp/0602/011">JSP</a> / <a id="pgnt0602011_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/011">Thymeleaf</a>)</li>
        <li>pgnt0603001 (<a id="pgnt0603001_jsp" href="${pageContext.request.contextPath}/jsp/0603/001">JSP</a> / <a id="pgnt0603001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0603/001">Thymeleaf</a>)</li>
        <li>pgnt0603002 (<a id="pgnt0603002_jsp" href="${pageContext.request.contextPath}/jsp/0603/002">JSP</a> / <a id="pgnt0603002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0603/002">Thymeleaf</a>)</li>
      </ul>

    </div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>