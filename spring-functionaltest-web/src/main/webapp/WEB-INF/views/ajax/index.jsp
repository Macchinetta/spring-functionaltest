<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.ajax.index" />
<c:set var="functionId" value="ajax" />

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

  <h1 id="screenTitle">AJAX Ajax機能</h1>

  <div>[AJAX01] Controllerの実装</div>
  <ul>
    <li><a id="ajax0101001" href="${pageContext.request.contextPath}/ajax/0101/001">ajax0101001</a></li>
    <li><a id="ajax0102001" href="${pageContext.request.contextPath}/ajax/0102/001">ajax0102001</a></li>
    <li><a id="ajax0103001" href="${pageContext.request.contextPath}/ajax/0103/001">ajax0103001</a></li>
    <li><a id="ajax0104001001" href="${pageContext.request.contextPath}/ajax/0104/001/001">ajax0104001001</a></li>
    <li><a id="ajax0104001" href="${pageContext.request.contextPath}/ajax/0104/001">ajax0104001</a></li>
    <li><a id="deleteAll" href="javascript:document.messageBoardsDeleteForm.submit();">ajax0104001
        初期化(REST API)</a></li>
  </ul>

  <div>[AJAX02] 入力エラーのハンドリング</div>
  <ul>
    <li><a id="ajax0201001" href="${pageContext.request.contextPath}/ajax/0201/001">ajax0201001</a></li>
    <li><a id="ajax0201002" href="${pageContext.request.contextPath}/ajax/0201/002">ajax0201002</a></li>
    <li><a id="ajax0201003" href="${pageContext.request.contextPath}/ajax/0201/003">ajax0201003</a></li>
    <li><a id="ajax0202001" href="${pageContext.request.contextPath}/ajax/0202/001">ajax0202001</a></li>
    <li><a id="ajax0202002" href="${pageContext.request.contextPath}/ajax/0202/002">ajax0202002</a></li>
  </ul>

  <div>[AJAX03] 業務エラーのハンドリング</div>
  <ul>
    <li><a id="ajax0301001001" href="${pageContext.request.contextPath}/ajax/0301/001/001">ajax0301001
        first</a></li>
    <li><a id="ajax0301001002" href="${pageContext.request.contextPath}/ajax/0301/001/002">ajax0301001
        second</a></li>
    <li><a id="ajax0302001" href="${pageContext.request.contextPath}/ajax/0302/001">ajax0302001</a></li>
  </ul>
  <form name="messageBoardsDeleteForm"
    action="${pageContext.request.contextPath}/ajax/testdata/messageBoards?_method=DELETE"
    method="post"></form>
</div>

  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>