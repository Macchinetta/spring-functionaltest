<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>System Error!</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/cmmn.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/css/bootstrap-theme.min.css" />
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/jsp/layout/header.jsp" />
        <div id="wrapper">
            <h1>System Error!</h1>
            <div class="alert alert-danger">
                <ul>
                    <li><c:if test="${!empty errorCode}">[${f:h(errorCode)}]</c:if> <spring:message code="e.sf.fw.9001" /></li>
                </ul>
                <c:if test="${!empty errorMessage}">
                    <ul>
                        <li id="message">${f:h(errorMessage)}</li>
                    </ul>
                </c:if>
                <c:if test="${!empty statusCode}">
                    <ul>
                        <li id="statusCode">StatusCode: ${f:h(statusCode)}</li>
                    </ul>
                </c:if>
            </div>
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
        </div>
        <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
    </body>
</html>
