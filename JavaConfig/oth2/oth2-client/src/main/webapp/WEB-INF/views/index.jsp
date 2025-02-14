<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Security - OAuth2</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css" type="text/css">
</head>
<div>
    <h1 id="screenTitle">Spring Security - OAuth2</h1>

    <ul>
        <li>TODOアプリ - 認可処理不要 (<a id="todoappNoAuth_jsp"
                href="${pageContext.request.contextPath}/jsp/noauth/todo/list">JSP</a> / Thymeleaf)</li>
        <li>TODOアプリ - 認可処理要 - 認可コードグラント (<a id="authorizationCode_jsp"
                href="${pageContext.request.contextPath}/jsp/auth/todo/01/index">JSP</a> / Thymeleaf)</li>
        <li>TODOアプリ - 認可処理要 - アクセストークンチェック (<a id="accessTokenCheck_jsp"
                href="${pageContext.request.contextPath}/jsp/auth/todo/02/index">JSP</a> / Thymeleaf)</li>
        <li>TODOアプリ - 例外処理 (<a id="errorHandler_jsp"
                href="${pageContext.request.contextPath}/jsp/auth/todo/03/index">JSP</a> / Thymeleaf)</li>
    </ul>
</div>
<jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>

</html>