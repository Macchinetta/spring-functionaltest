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
  <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
  <div class="container">
<div id="wrapper">

  <h1 id="screenTitle">AJAX Ajax機能</h1>

  <div>[AJAX01] Controllerの実装</div>
  <ul>
    <li>ajax0101001 (<a id="ajax0101001_jsp" href="${pageContext.request.contextPath}/jsp/0101/001">JSP</a> / <a id="ajax0101001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0101/001">Thymeleaf</a> )</li>
    <li>ajax0102001 (<a id="ajax0102001_jsp" href="${pageContext.request.contextPath}/jsp/0102/001">JSP</a> / <a id="ajax0102001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0102/001">Thymeleaf</a> )</li>
    <li>ajax0103001 (<a id="ajax0103001_jsp" href="${pageContext.request.contextPath}/jsp/0103/001">JSP</a> / <a id="ajax0103001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0103/001">Thymeleaf</a> )</li>
    <li>ajax0104001001 (<a id="ajax0104001001_jsp" href="${pageContext.request.contextPath}/jsp/0104/001/001">JSP</a> / <a id="ajax0104001001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0104/001/001">Thymeleaf</a> )</li>
    <li>ajax0104001 (<a id="ajax0104001_jsp" href="${pageContext.request.contextPath}/jsp/0104/001">JSP</a> / <a id="ajax0104001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0104/001">Thymeleaf</a> )</li>
    <li><a id="deleteAll" href="javascript:document.messageBoardsDeleteForm.submit();">ajax0104001
        初期化(REST API)</a></li>
  </ul>

  <div>[AJAX02] 入力エラーのハンドリング</div>
  <ul>
    <li>ajax0201001 (<a id="ajax0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001">JSP</a> / <a id="ajax0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001">Thymeleaf</a> )</li>
    <li>ajax0201002 (<a id="ajax0201002_jsp" href="${pageContext.request.contextPath}/jsp/0201/002">JSP</a> / <a id="ajax0201002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/002">Thymeleaf</a> )</li>
    <li>ajax0201003 (<a id="ajax0201003_jsp" href="${pageContext.request.contextPath}/jsp/0201/003">JSP</a> / <a id="ajax0201003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/003">Thymeleaf</a> )</li>
    <li>ajax0202001 (<a id="ajax0202001_jsp" href="${pageContext.request.contextPath}/jsp/0202/001">JSP</a> / <a id="ajax0202001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/001">Thymeleaf</a> )</li>
    <li>ajax0202002 (<a id="ajax0202002_jsp" href="${pageContext.request.contextPath}/jsp/0202/002">JSP</a> / <a id="ajax0202002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0202/002">Thymeleaf</a> )</li>
  </ul>

  <div>[AJAX03] 業務エラーのハンドリング</div>
  <ul>
    <li>ajax0301001 first (<a id="ajax0301001001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001/001">JSP</a> / <a id="ajax0301001001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001/001">Thymeleaf</a> )</li>
    <li>ajax0301001 second (<a id="ajax0301001002_jsp" href="${pageContext.request.contextPath}/jsp/0301/001/002">JSP</a> / <a id="ajax0301001002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001/002">Thymeleaf</a> )</li>
    <li>ajax0302001 (<a id="ajax0302001_jsp" href="${pageContext.request.contextPath}/jsp/0302/001">JSP</a> / <a id="ajax0302001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/001">Thymeleaf</a> )</li>
  </ul>
  <form name="messageBoardsDeleteForm"
    action="${pageContext.request.contextPath}/ajax/testdata/messageBoards?_method=DELETE"
    method="post"></form>
</div>

  <br>

  <a id="spring-functionaltest-web" href="${baseWeb}">spring-functionaltest-web へ移動</a>

  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>