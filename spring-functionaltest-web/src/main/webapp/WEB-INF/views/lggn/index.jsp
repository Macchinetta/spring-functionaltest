<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.lggn.index" />
<c:set var="functionId" value="lggn" />

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

  <h1 id="screenTitle">LGGN ロギング</h1>

  <div>[LGGN04] ログ出力の拡張機能</div>
  <ul>
    <li><a id="lggn0401001" href="${pageContext.request.contextPath}/lggn/0401/001">lggn0401001</a>
    </li>
    <li><a id="lggn0401002" href="${pageContext.request.contextPath}/lggn/0401/002">lggn0401002</a>
    </li>
    <li><a id="lggn0401003" href="${pageContext.request.contextPath}/lggn/0401/003">lggn0401003</a>
    </li>
    <li><a id="lggn0401004" href="${pageContext.request.contextPath}/lggn/0401/004">lggn0401004</a>
    </li>
    <li><a id="lggn0401005" href="${pageContext.request.contextPath}/lggn/0401/005">lggn0401005</a>
    </li>
    <li><a id="lggn0401006" href="${pageContext.request.contextPath}/lggn/0401/006">lggn0401006</a>
    </li>
    <li><a id="lggn0401007" href="${pageContext.request.contextPath}/lggn/0401/007">lggn0401007</a>
    </li>
    <li><a id="lggn0401008" href="${pageContext.request.contextPath}/lggn/0401/008">lggn0401008</a>
    </li>
    <li><a id="lggn0401009" href="${pageContext.request.contextPath}/lggn/0401/009">lggn0401009</a>
    </li>
    <li><a id="lggn0402001" href="${pageContext.request.contextPath}/lggn/0402/001">lggn0402001</a>
    </li>
    <li><a id="lggn0402002" href="${pageContext.request.contextPath}/lggn/0402/002">lggn0402002</a>
    </li>
  </ul>

</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>