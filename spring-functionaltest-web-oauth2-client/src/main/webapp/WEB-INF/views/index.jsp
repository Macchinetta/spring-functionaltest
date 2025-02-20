<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Spring Security - OAuth2</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css" type="text/css" />
</head>

<body>
    <div>
        <h1 id="screenTitle">Spring Security - OAuth2</h1>

        <ul>
            <li><a id="todoappNoAuth" href="${pageContext.request.contextPath}/noauth/todo/list">TODOアプリ - 認可処理不要</a></li>
            <li><a id="authorizationCode" href="${pageContext.request.contextPath}/auth/todo/01/index">TODOアプリ - 認可処理要 - 認可コードグラント</a></li>
            <li><a id="accessTokenCheck" href="${pageContext.request.contextPath}/auth/todo/02/index">TODOアプリ - 認可処理要 - アクセストークンチェック</a></li>
            <li><a id="errorHandler" href="${pageContext.request.contextPath}/auth/todo/03/index">TODOアプリ - 例外処理</a></li>
        </ul>
    </div>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>

</html>