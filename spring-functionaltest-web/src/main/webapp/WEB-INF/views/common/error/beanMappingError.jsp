<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>dozer bean mapping Error!</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/app/css/styles.css">
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/app/css/cmmn.css">
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap-theme.min.css">
</head>
<body>
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />
  <div id="wrapper">
    <h1>dozer bean mapping Error!</h1>
    <div class="alert alert-danger">
      <c:choose>
        <c:when test="${empty exceptionCode}">
          <ul><li><spring:message code="e.sf.bnmp.0001" /></li></ul>
        </c:when>
        <c:otherwise>
          <ul><li>[${f:h(exceptionCode)}] <spring:message code="${exceptionCode}" /></li></ul>
        </c:otherwise>
      </c:choose>
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>