<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Not Acceptable Error!</title>
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
    <h1 id="screenTitle">accessDeniedPage</h1>
    <br> <a id="springTestTop" href="${pageContext.request.contextPath}">Spring Functional
      Test</a> <br> <br> <br> <br> <br> <br> <br> <br> <br>
    <br> <br> <br> <br>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>
