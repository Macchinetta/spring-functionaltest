<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.excn.index" />
<c:set var="functionId" value="excn" />

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

      <h1 id="screenTitle">排他制御</h1>

      <div>[EXCN03] Mybatis3使用時の実装方法</div>
      <ul>
        <li>excn0301001 (<a id="excn0301001" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a>)</li>
        <li>excn0302001 (<a id="excn0302001" href="${pageContext.request.contextPath}/jsp/0302/001">JSP</a>)</li>
        <li>excn0302002 (<a id="excn0302002" href="${pageContext.request.contextPath}/jsp/0302/002">JSP</a>)</li>
        <li>excn0303（excn0402で実施）</li>
      </ul>

      <div>[EXCN04] 排他エラーのハンドリング方法</div>
      <ul>
        <li>excn0401001（excn0302001で実施） (<a id="excn0401001" href="${pageContext.request.contextPath}/jsp/0302/001">JSP</a>)</li>
        <li>excn0401002（excn0302002で実施） (<a id="excn0401002" href="${pageContext.request.contextPath}/jsp/0302/002">JSP</a>)</li>
        <li>excn0402001 (<a id="excn0402001" href="${pageContext.request.contextPath}/jsp/0402/001">JSP</a>)</li>
        <li>excn0402002 (<a id="excn0402002" href="${pageContext.request.contextPath}/jsp/0402/002">JSP</a>)</li>
      </ul>

      <div>JPA Exclusion Error Handling</div>
      <ul>
        <li>excn0501001 (<a id="excn0501001" href="${pageContext.request.contextPath}/jsp/0501/001">JSP</a>)</li>
        <li>excn0502001 (<a id="excn0502001" href="${pageContext.request.contextPath}/jsp/0502/001">JSP</a>)</li>
        <li>excn0502002 (<a id="excn0502002" href="${pageContext.request.contextPath}/jsp/0502/002">JSP</a>)</li>
        <li>excn0602001 (<a id="excn0602001" href="${pageContext.request.contextPath}/jsp/0602/001">JSP</a>)</li>
        <li>excn0602002 (<a id="excn0602002" href="${pageContext.request.contextPath}/jsp/0602/002">JSP</a>)</li>
      </ul>

    </div>

    <a id="spring-functionaltest-web" href="${baseWeb}">spring-functionaltest-web へ移動</a>
  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>