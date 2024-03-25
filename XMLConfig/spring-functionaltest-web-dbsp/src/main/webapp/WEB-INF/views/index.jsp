<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<c:set var="titleKey" value="title.dbsp.index" />
<c:set var="functionId" value="dbsp" />

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

      <h1 id="screenTitle">DBSP 二重送信の防止</h1>

      <div>[DBSP02] PRG(Post-Redirect-Get)パターンの適用</div>
      <ul>
        <li>dbsp0201001 (<a id="dbsp0201001_jsp" href="${pageContext.request.contextPath}/jsp/0201/001/create?form">JSP</a> / <a id="dbsp0201001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0201/001/create?form">Thymeleaf</a> )</li>
      </ul>

      <div>[DBSP03] トランザクショントークンチェックの適用</div>
      <ul>
        <li>dbsp0301001 (<a id="dbsp0301001_jsp" href="${pageContext.request.contextPath}/jsp/0301/001">JSP</a> / <a id="dbsp0301001_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/001">Thymeleaf</a> )</li>
      </ul>
      <ul>
        <li>dbsp0301003 (<a id="dbsp0301003_jsp" href="${pageContext.request.contextPath}/jsp/0301/003">JSP</a> / <a id="dbsp0301003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/003">Thymeleaf</a> )</li>
      </ul>
      <ul>
        <li>dbsp0301005 (<a id="dbsp0301005_jsp" href="${pageContext.request.contextPath}/jsp/0301/005">JSP</a> / <a id="dbsp0301005_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/005">Thymeleaf</a> )</li>
      </ul>
      <ul>
        <li>dbsp0301006 (JSP / <a id="dbsp0301006_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0301/006">Thymeleaf</a> )</li>
      </ul>
      <ul>
        <li>dbsp0302002 (<a id="dbsp0302002_jsp" href="${pageContext.request.contextPath}/jsp/0302/002">JSP</a> / <a id="dbsp0302002_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0302/002">Thymeleaf</a> )</li>
      </ul>
      <ul>
        <li>dbsp0303003 (<a id="dbsp0303003_jsp" href="${pageContext.request.contextPath}/jsp/0303/003">JSP</a> / <a id="dbsp0303003_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0303/003">Thymeleaf</a> )</li>
      </ul>
      <ul>
        <li>dbsp0303004 (<a id="dbsp0303004_jsp" href="${pageContext.request.contextPath}/jsp/0303/004">JSP</a> / <a id="dbsp0303004_thymeleaf" href="${pageContext.request.contextPath}/thymeleaf/0303/004">Thymeleaf</a> )</li>
      </ul>

      <br>

      <a id="spring-functionaltest-web" href="${baseWeb}">spring-functionaltest-web へ移動</a>

    </div>
  </div>
  <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>
</html>