<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>System Error!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/vendor/bootstrap/dist/css/bootstrap-theme.min.css">
</head>
<body>
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />
  <div id="wrapper">
    <h1>System Error!</h1>
    <div class="alert alert-danger">
      <ul>
        <li><c:if test="${!empty errorCode}">[${f:h(errorCode)}]</c:if> <spring:message
            code="e.sf.cmmn.9001" /></li>
      </ul>
      <c:if test="${!empty exceptionForExceptionResolver.message}">
        <ul>
          <li>${f:h(exceptionForExceptionResolver.message)}</li>
        </ul>
      </c:if>
    </div>
    <br> <br> <br> <br> <br> <br> <br> <br> <br> <br>
    <br> <br> <br> <br>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>