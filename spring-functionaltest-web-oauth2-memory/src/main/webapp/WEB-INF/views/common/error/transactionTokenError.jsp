<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Transaction Token Error!</title>
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
        <h1>Transaction Token Error!</h1>
        <div class="alert alert-danger">
            <ul><li><c:if test="${!empty exceptionCode}">[${f:h(exceptionCode)}]</c:if>
            <spring:message code="e.sf.cmmn.7001" /></li></ul>
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