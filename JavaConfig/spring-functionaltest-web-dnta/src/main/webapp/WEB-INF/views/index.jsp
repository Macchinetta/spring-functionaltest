<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dnta.index" />
<c:set var="functionId" value="dnta" />

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
<link rel="stylesheet_jsp" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css">
<link rel="stylesheet_jsp" href="${pageContext.request.contextPath}/resources/app/css/${functionId}.css">
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

  <h1 id="screenTitle">DNTA 日付操作(Date and Time API)</h1>

  <div>[DNTA01] 日時取得</div>
  <ul>
    <li>dnta0101001 <a id="dnta0101001_jsp" href="${pageContext.request.contextPath}/jsp/0101/001">JSP</a> / <a id="dnta0101001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/001">Thymeleaf</a></li>
    <li>dnta0101002 <a id="dnta0101002_jsp" href="${pageContext.request.contextPath}/jsp/0101/002">JSP</a> / <a id="dnta0101002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/002">Thymeleaf</a></li>
    <li>dnta0101003 <a id="dnta0101003_jsp" href="${pageContext.request.contextPath}/jsp/0101/003">JSP</a> / <a id="dnta0101003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/003">Thymeleaf</a></li>
    <li>dnta0102001 <a id="dnta0102001_jsp" href="${pageContext.request.contextPath}/jsp/0102/001">JSP</a> / <a id="dnta0102001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/001">Thymeleaf</a></li>
    <li>dnta0102002 <a id="dnta0102002_jsp" href="${pageContext.request.contextPath}/jsp/0102/002">JSP</a> / <a id="dnta0102002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/002">Thymeleaf</a></li>
    <li>dnta0102003 <a id="dnta0102003_jsp" href="${pageContext.request.contextPath}/jsp/0102/002">JSP</a> / <a id="dnta0102003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/002">Thymeleaf</a></li>
    <li>dnta0102004 <a id="dnta0102004_jsp" href="${pageContext.request.contextPath}/jsp/0102/002">JSP</a> / <a id="dnta0102004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/002">Thymeleaf</a></li>
    <li>dnta0102005 <a id="dnta0102005_jsp" href="${pageContext.request.contextPath}/jsp/0102/002">JSP</a> / <a id="dnta0102005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/002">Thymeleaf</a></li>
    <li>dnta0103001 <a id="dnta0103001_jsp" href="${pageContext.request.contextPath}/jsp/0103/001">JSP</a> / <a id="dnta0103001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0103/001">Thymeleaf</a></li>
    <li>dnta0103002 <a id="dnta0103002_jsp" href="${pageContext.request.contextPath}/jsp/0103/001">JSP</a> / <a id="dnta0103002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0103/001">Thymeleaf</a></li>
    <li>dnta0103003 <a id="dnta0103003_jsp" href="${pageContext.request.contextPath}/jsp/0103/001">JSP</a> / <a id="dnta0103003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0103/001">Thymeleaf</a></li>
    <li>dnta0103004 <a id="dnta0103004_jsp" href="${pageContext.request.contextPath}/jsp/0103/001">JSP</a> / <a id="dnta0103004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0103/001">Thymeleaf</a></li>
    <li>dnta0103005 <a id="dnta0103005_jsp" href="${pageContext.request.contextPath}/jsp/0103/001">JSP</a> / <a id="dnta0103005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0103/001">Thymeleaf</a></li>
  </ul>

  <div>[DNTA02] 期間</div>
  <ul>
    <li>dnta0201001 <a id="dnta0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> / <a id="dnta0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001">Thymeleaf</a></li>
    <li>dnta0201002 <a id="dnta0201002_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> / <a id="dnta0201002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001">Thymeleaf</a></li>
    <li>dnta0202001 <a id="dnta0202001_jsp" href="${pageContext.request.contextPath}/jsp/0202/001">JSP</a> / <a id="dnta0202001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/001">Thymeleaf</a></li>
    <li>dnta0202002 <a id="dnta0202002_jsp" href="${pageContext.request.contextPath}/jsp/0202/001">JSP</a> / <a id="dnta0202002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/001">Thymeleaf</a></li>
  </ul>

  <div>[DNTA03] 型変換</div>
  <ul>
    <li>dnta0301001 <a id="dnta0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> / <a id="dnta0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001">Thymeleaf</a></li>
    <li>dnta0301002 <a id="dnta0301002_jsp" href="${pageContext.request.contextPath}/jsp/0301/002">JSP</a> / <a id="dnta0301002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/002">Thymeleaf</a></li>
    <li>dnta0301003 <a id="dnta0301003_jsp" href="${pageContext.request.contextPath}/jsp/0301/003">JSP</a> / <a id="dnta0301003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/003">Thymeleaf</a></li>
    <li>dnta0301004 <a id="dnta0301004_jsp" href="${pageContext.request.contextPath}/jsp/0301/004">JSP</a> / <a id="dnta0301004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/004">Thymeleaf</a></li>
    <li>dnta0301005 <a id="dnta0301005_jsp" href="${pageContext.request.contextPath}/jsp/0301/005">JSP</a> / <a id="dnta0301005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/005">Thymeleaf</a></li>
    <li>dnta0301006 <a id="dnta0301006_jsp" href="${pageContext.request.contextPath}/jsp/0301/006">JSP</a> / <a id="dnta0301006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/006">Thymeleaf</a></li>
    <li>dnta0301007 <a id="dnta0301007_jsp" href="${pageContext.request.contextPath}/jsp/0301/006">JSP</a> / <a id="dnta0301007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/006">Thymeleaf</a></li>
    <li>dnta0301008 <a id="dnta0301008_jsp" href="${pageContext.request.contextPath}/jsp/0301/006">JSP</a> / <a id="dnta0301008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/006">Thymeleaf</a></li>
    <li>dnta0301009 <a id="dnta0301009_jsp" href="${pageContext.request.contextPath}/jsp/0301/006">JSP</a> / <a id="dnta0301009_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/006">Thymeleaf</a></li>
    <li>dnta0302001 <a id="dnta0302001_jsp" href="${pageContext.request.contextPath}/jsp/0302/001">JSP</a> / <a id="dnta0302001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/001">Thymeleaf</a></li>
    <li>dnta0302002 <a id="dnta0302002_jsp" href="${pageContext.request.contextPath}/jsp/0302/002">JSP</a> / <a id="dnta0302002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/002">Thymeleaf</a></li>
    <li>dnta0303001 <a id="dnta0303001_jsp" href="${pageContext.request.contextPath}/jsp/0303/001">JSP</a> / <a id="dnta0303001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0303/001">Thymeleaf</a></li>
    <li>dnta0303002 <a id="dnta0303002_jsp" href="${pageContext.request.contextPath}/jsp/0303/002">JSP</a> / <a id="dnta0303002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0303/002">Thymeleaf</a></li>
    <li>dnta0303003 <a id="dnta0303003_jsp" href="${pageContext.request.contextPath}/jsp/0303/003">JSP</a> / <a id="dnta0303003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0303/003">Thymeleaf</a></li>
    <li>dnta0303004 <a id="dnta0303004_jsp" href="${pageContext.request.contextPath}/jsp/0303/004">JSP</a> / <a id="dnta0303004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0303/004">Thymeleaf</a></li>
    <li>dnta0303005 <a id="dnta0303005_jsp" href="${pageContext.request.contextPath}/jsp/0303/005">JSP</a> / <a id="dnta0303005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0303/005">Thymeleaf</a></li>
    <li>dnta0303006 <a id="dnta0303006_jsp" href="${pageContext.request.contextPath}/jsp/0303/006">JSP</a> / <a id="dnta0303006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0303/006">Thymeleaf</a></li>
    <li>dnta0304001 <a id="dnta0304001_jsp" href="${pageContext.request.contextPath}/jsp/0304/001">JSP</a> / <a id="dnta0304001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0304/001">Thymeleaf</a></li>
    <li>dnta0305001 <a id="dnta0305001_jsp" href="${pageContext.request.contextPath}/jsp/0305/001">JSP</a> / <a id="dnta0305001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0305/001">Thymeleaf</a></li>
    <li>dnta0305002 <a id="dnta0305002_jsp" href="${pageContext.request.contextPath}/jsp/0305/002">JSP</a> / <a id="dnta0305002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0305/002">Thymeleaf</a></li>
    <li>dnta0305003 <a id="dnta0305003_jsp" href="${pageContext.request.contextPath}/jsp/0305/003">JSP</a> / <a id="dnta0305003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0305/003">Thymeleaf</a></li>
    <li>dnta0306001 <a id="dnta0306001_jsp" href="${pageContext.request.contextPath}/jsp/0306/001">JSP</a> / <a id="dnta0306001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0306/001">Thymeleaf</a></li>
    <li>dnta0306002 <a id="dnta0306002_jsp" href="${pageContext.request.contextPath}/jsp/0306/002">JSP</a> / <a id="dnta0306002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0306/002">Thymeleaf</a></li>
  </ul>

  <div>[DNTA04] 日付操作</div>
  <ul>
    <li>dnta0401001 <a id="dnta0401001_jsp" href="${pageContext.request.contextPath}/jsp/0401/001">JSP</a> / <a id="dnta0401001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/001">Thymeleaf</a></li>
    <li>dnta0401002 <a id="dnta0401002_jsp" href="${pageContext.request.contextPath}/jsp/0401/002">JSP</a> / <a id="dnta0401002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/002">Thymeleaf</a></li>
    <li>dnta0401003 <a id="dnta0401003_jsp" href="${pageContext.request.contextPath}/jsp/0401/003">JSP</a> / <a id="dnta0401003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/003">Thymeleaf</a></li>
    <li>dnta0401004 <a id="dnta0401004_jsp" href="${pageContext.request.contextPath}/jsp/0401/004">JSP</a> / <a id="dnta0401004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/004">Thymeleaf</a></li>
    <li>dnta0401005 <a id="dnta0401005_jsp" href="${pageContext.request.contextPath}/jsp/0401/005">JSP</a> / <a id="dnta0401005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/005">Thymeleaf</a></li>
    <li>dnta0401006 <a id="dnta0401006_jsp" href="${pageContext.request.contextPath}/jsp/0401/006">JSP</a> / <a id="dnta0401006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/006">Thymeleaf</a></li>
    <li>dnta0401007 <a id="dnta0401007_jsp" href="${pageContext.request.contextPath}/jsp/0401/007">JSP</a> / <a id="dnta0401007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/007">Thymeleaf</a></li>
    <li>dnta0401008 <a id="dnta0401008_jsp" href="${pageContext.request.contextPath}/jsp/0401/008">JSP</a> / <a id="dnta0401008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/008">Thymeleaf</a></li>
    <li>dnta0401009 <a id="dnta0401009_jsp" href="${pageContext.request.contextPath}/jsp/0401/008">JSP</a> / <a id="dnta0401009_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/008">Thymeleaf</a></li>
    <li>dnta0402001 <a id="dnta0402001_jsp" href="${pageContext.request.contextPath}/jsp/0402/001">JSP</a> / <a id="dnta0402001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0402/001">Thymeleaf</a></li>
    <li>dnta0402002 <a id="dnta0402002_jsp" href="${pageContext.request.contextPath}/jsp/0402/002">JSP</a> / <a id="dnta0402002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0402/002">Thymeleaf</a></li>
    <li>dnta0403001 <a id="dnta0403001_jsp" href="${pageContext.request.contextPath}/jsp/0403/001">JSP</a> / <a id="dnta0403001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0403/001">Thymeleaf</a></li>
    <li>dnta0403002 <a id="dnta0403002_jsp" href="${pageContext.request.contextPath}/jsp/0403/002">JSP</a> / <a id="dnta0403002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0403/002">Thymeleaf</a></li>
    <li>dnta0403003 <a id="dnta0403003_jsp" href="${pageContext.request.contextPath}/jsp/0403/003">JSP</a> / <a id="dnta0403003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0403/003">Thymeleaf</a></li>
    <li>dnta0403004 <a id="dnta0403004_jsp" href="${pageContext.request.contextPath}/jsp/0403/004">JSP</a> / <a id="dnta0403004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0403/004">Thymeleaf</a></li>
    <li>dnta0403005 <a id="dnta0403005_jsp" href="${pageContext.request.contextPath}/jsp/0403/005">JSP</a> / <a id="dnta0403005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0403/005">Thymeleaf</a></li>
    <li>dnta0403006 <a id="dnta0403006_jsp" href="${pageContext.request.contextPath}/jsp/0403/006">JSP</a> / <a id="dnta0403006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0403/006">Thymeleaf</a></li>
    <li>dnta0403007 <a id="dnta0403007_jsp" href="${pageContext.request.contextPath}/jsp/0403/007">JSP</a> / <a id="dnta0403007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0403/007">Thymeleaf</a></li>
    <li>dnta0404001 <a id="dnta0404001_jsp" href="${pageContext.request.contextPath}/jsp/0404/001">JSP</a> / <a id="dnta0404001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0404/001">Thymeleaf</a></li>
    <li>dnta0404002 <a id="dnta0404002_jsp" href="${pageContext.request.contextPath}/jsp/0404/002">JSP</a> / <a id="dnta0404002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0404/002">Thymeleaf</a></li>
  </ul>

  <div>[DNTA05] 和暦</div>
  <ul>
    <li>dnta0501001 <a id="dnta0501001_jsp" href="${pageContext.request.contextPath}/jsp/0501/001">JSP</a> / <a id="dnta0501001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/001">Thymeleaf</a></li>
    <li>dnta0501002 <a id="dnta0501002_jsp" href="${pageContext.request.contextPath}/jsp/0501/002">JSP</a> / <a id="dnta0501002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/002">Thymeleaf</a></li>
    <li>dnta0502001 <a id="dnta0502001_jsp" href="${pageContext.request.contextPath}/jsp/0502/001">JSP</a> / <a id="dnta0502001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0502/001">Thymeleaf</a></li>
    <li>dnta0502002 <a id="dnta0502002_jsp" href="${pageContext.request.contextPath}/jsp/0502/002">JSP</a> / <a id="dnta0502002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0502/002">Thymeleaf</a></li>
    <li>dnta0503001 <a id="dnta0503001_jsp" href="${pageContext.request.contextPath}/jsp/0503/001">JSP</a> / <a id="dnta0503001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0503/001">Thymeleaf</a></li>
    <li>dnta0504001 <a id="dnta0504001_jsp" href="${pageContext.request.contextPath}/jsp/0504/001">JSP</a> / <a id="dnta0504001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0504/001">Thymeleaf</a></li>
  </ul>

  <div>[DNTA06] Thymeleafのダイアレクト</div>
  <ul>
    <li>dnta0601001 <a id="dnta0601001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/001">Thymeleaf</a></li>
    <li>dnta0601002 <a id="dnta0601002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/002">Thymeleaf</a></li>
    <li>dnta0601003 <a id="dnta0601003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/003">Thymeleaf</a></li>
    <li>dnta0601004 <a id="dnta0601004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/004">Thymeleaf</a></li>
    <li>dnta0602001 <a id="dnta0602001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/001">Thymeleaf</a></li>
    <li>dnta0602002 <a id="dnta0602002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/002">Thymeleaf</a></li>
    <li>dnta0602003 <a id="dnta0602003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/003">Thymeleaf</a></li>
    <li>dnta0602004 <a id="dnta0602004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/004">Thymeleaf</a></li>
    <li>dnta0602005 <a id="dnta0602005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/005">Thymeleaf</a></li>
    <li>dnta0602006 <a id="dnta0602006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/006">Thymeleaf</a></li>
    <li>dnta0602007 <a id="dnta0602007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/007">Thymeleaf</a></li>
    <li>dnta0602008 <a id="dnta0602008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0602/008">Thymeleaf</a></li>
    <li>dnta0603001 <a id="dnta0603001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0603/001">Thymeleaf</a></li>
    <li>dnta0603002 <a id="dnta0603002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0603/002">Thymeleaf</a></li>
    <li>dnta0603003 <a id="dnta0603003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0603/003">Thymeleaf</a></li>
    <li>dnta0603004 <a id="dnta0603004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0603/004">Thymeleaf</a></li>
  </ul>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>