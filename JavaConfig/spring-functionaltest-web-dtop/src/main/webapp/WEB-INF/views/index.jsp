<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dtop.index" />
<c:set var="functionId" value="dtop" />

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

  <h1 id="screenTitle">DTOP 日付操作(Joda Time)</h1>

  <div>[DTOP01] 日付取得</div>
  <ul>    
    <li>dtop0101001 (<a id="dtop0101001_jsp" href="${pageContext.request.contextPath}/jsp/0101/001">JSP</a> / <a id="dtop0101001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/001">Thymeleaf</a> )</li>
    <li>dtop0101002 (<a id="dtop0101002_jsp" href="${pageContext.request.contextPath}/jsp/0101/002">JSP</a> / <a id="dtop0101002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/002">Thymeleaf</a> )</li>
    <li>dtop0101003 (<a id="dtop0101003_jsp" href="${pageContext.request.contextPath}/jsp/0101/003">JSP</a> / <a id="dtop0101003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/003">Thymeleaf</a> )</li>
    <li>dtop0101004 (<a id="dtop0101004_jsp" href="${pageContext.request.contextPath}/jsp/0101/004">JSP</a> / <a id="dtop0101004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/004">Thymeleaf</a> )</li>
    <li>dtop0101005 (<a id="dtop0101005_jsp" href="${pageContext.request.contextPath}/jsp/0101/005">JSP</a> / <a id="dtop0101005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/005">Thymeleaf</a> )</li>
    <li>dtop0102001 (<a id="dtop0102001_jsp" href="${pageContext.request.contextPath}/jsp/0102/001">JSP</a> / <a id="dtop0102001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/001">Thymeleaf</a> )</li>
    <li>dtop0102002 (<a id="dtop0102002_jsp" href="${pageContext.request.contextPath}/jsp/0102/002">JSP</a> / <a id="dtop0102002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/002">Thymeleaf</a> )</li>
    <li>dtop0103001 (<a id="dtop0103001_jsp" href="${pageContext.request.contextPath}/jsp/0103/001">JSP</a> / <a id="dtop0103001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0103/001">Thymeleaf</a> )</li>
  </ul>

  <div>[DTOP02] 型変換</div>
  <ul>
    <li>dtop0201001 (<a id="dtop0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> / <a id="dtop0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001">Thymeleaf</a> )</li>
    <li>dtop0201002 (<a id="dtop0201002_jsp" href="${pageContext.request.contextPath}/jsp/0201/002">JSP</a> / <a id="dtop0201002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/002">Thymeleaf</a> )</li>
    <li>dtop0202001 (<a id="dtop0202001_jsp" href="${pageContext.request.contextPath}/jsp/0202/001">JSP</a> / <a id="dtop0202001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/001">Thymeleaf</a> )</li>
  </ul>

  <div>[DTOP03] 日付取得</div>
  <ul>
    <li>dtop0301001 (<a id="dtop0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> / <a id="dtop0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001">Thymeleaf</a> )</li>
    <li>dtop0301002 (<a id="dtop0301002_jsp" href="${pageContext.request.contextPath}/jsp/0301/002">JSP</a> / <a id="dtop0301002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/002">Thymeleaf</a> )</li>
    <li>dtop0301003 (<a id="dtop0301003_jsp" href="${pageContext.request.contextPath}/jsp/0301/003">JSP</a> / <a id="dtop0301003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/003">Thymeleaf</a> )</li>
    <li>dtop0301004 (<a id="dtop0301004_jsp" href="${pageContext.request.contextPath}/jsp/0301/004">JSP</a> / <a id="dtop0301004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/004">Thymeleaf</a> )</li>
    <li>dtop0301005 (<a id="dtop0301005_jsp" href="${pageContext.request.contextPath}/jsp/0301/005">JSP</a> / <a id="dtop0301005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/005">Thymeleaf</a> )</li>
    <li>dtop0301006 (<a id="dtop0301006_jsp" href="${pageContext.request.contextPath}/jsp/0301/006">JSP</a> / <a id="dtop0301006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/006">Thymeleaf</a> )</li>
    <li>dtop0302001 (<a id="dtop0302001_jsp" href="${pageContext.request.contextPath}/jsp/0302/001">JSP</a> / <a id="dtop0302001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/001">Thymeleaf</a> )</li>
    <li>dtop0302002 (<a id="dtop0302002_jsp" href="${pageContext.request.contextPath}/jsp/0302/002">JSP</a> / <a id="dtop0302002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/002">Thymeleaf</a> )</li>
    <li>dtop0303001 (<a id="dtop0303001_jsp" href="${pageContext.request.contextPath}/jsp/0303/001">JSP</a> / <a id="dtop0303001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0303/001">Thymeleaf</a> )</li>
    <li>dtop0303002 (<a id="dtop0303002_jsp" href="${pageContext.request.contextPath}/jsp/0303/002">JSP</a> / <a id="dtop0303002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0303/002">Thymeleaf</a> )</li>
  </ul>

  <div>[DTOP04] 期間の取得</div>
  <ul>
    <li>dtop0401001 (<a id="dtop0401001_jsp" href="${pageContext.request.contextPath}/jsp/0401/001">JSP</a> / <a id="dtop0401001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/001">Thymeleaf</a> )</li>
    <li>dtop0401002 (<a id="dtop0401002_jsp" href="${pageContext.request.contextPath}/jsp/0401/002">JSP</a> / <a id="dtop0401002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/002">Thymeleaf</a> )</li>
    <li>dtop0401003 (<a id="dtop0401003_jsp" href="${pageContext.request.contextPath}/jsp/0401/003">JSP</a> / <a id="dtop0401003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/003">Thymeleaf</a> )</li>
    <li>dtop0401004 (<a id="dtop0401004_jsp" href="${pageContext.request.contextPath}/jsp/0401/004">JSP</a> / <a id="dtop0401004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/004">Thymeleaf</a> )</li>
    <li>dtop0401005 (<a id="dtop0401005_jsp" href="${pageContext.request.contextPath}/jsp/0401/005">JSP</a> / <a id="dtop0401005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/005">Thymeleaf</a> )</li>
    <li>dtop0401006 (<a id="dtop0401006_jsp" href="${pageContext.request.contextPath}/jsp/0401/006">JSP</a> / <a id="dtop0401006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/006">Thymeleaf</a> )</li>
    <li>dtop0401007 (<a id="dtop0401007_jsp" href="${pageContext.request.contextPath}/jsp/0401/007">JSP</a> / <a id="dtop0401007_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/007">Thymeleaf</a> )</li>
    <li>dtop0401008 (<a id="dtop0401008_jsp" href="${pageContext.request.contextPath}/jsp/0401/008">JSP</a> / <a id="dtop0401008_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0401/008">Thymeleaf</a> )</li>
    <li>dtop0402001 (<a id="dtop0402001_jsp" href="${pageContext.request.contextPath}/jsp/0402/001">JSP</a> / <a id="dtop0402001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0402/001">Thymeleaf</a> )</li>
    <li>dtop0402002 (<a id="dtop0402002_jsp" href="${pageContext.request.contextPath}/jsp/0402/002">JSP</a> / <a id="dtop0402002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0402/002">Thymeleaf</a> )</li>
  </ul>

  <div>[DTOP05] JSP Tag Library</div>
  <ul>
    <li>dtop0501001 (<a id="dtop0501001_jsp" href="${pageContext.request.contextPath}/jsp/0501/001">JSP</a> / <a id="dtop0501001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/001">Thymeleaf</a> )</li>
    <li>dtop0501002 (<a id="dtop0501002_jsp" href="${pageContext.request.contextPath}/jsp/0501/002">JSP</a> / <a id="dtop0501002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/002">Thymeleaf</a> )</li>
    <li>dtop0501003 (<a id="dtop0501003_jsp" href="${pageContext.request.contextPath}/jsp/0501/003">JSP</a> / <a id="dtop0501003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/003">Thymeleaf</a> )</li>
    <li>dtop0501004 (<a id="dtop0501004_jsp" href="${pageContext.request.contextPath}/jsp/0501/004">JSP</a> / <a id="dtop0501004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0501/004">Thymeleaf</a> )</li>
  </ul>


  <div>[DTOP06] 和暦</div>
  <ul>
    <li>dtop0601001 (<a id="dtop0601001_jsp" href="${pageContext.request.contextPath}/jsp/0601/001">JSP</a> / <a id="dtop0601001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/001">Thymeleaf</a> )</li>
    <li>dtop0601002 (<a id="dtop0601002_jsp" href="${pageContext.request.contextPath}/jsp/0601/002">JSP</a> / <a id="dtop0601002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/002">Thymeleaf</a> )</li>
    <li>dtop0601003 (<a id="dtop0601003_jsp" href="${pageContext.request.contextPath}/jsp/0601/003">JSP</a> / <a id="dtop0601003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/003">Thymeleaf</a> )</li>
    <li>dtop0601004 (<a id="dtop0601004_jsp" href="${pageContext.request.contextPath}/jsp/0601/004">JSP</a> / <a id="dtop0601004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0601/004">Thymeleaf</a> )</li>
  </ul>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>