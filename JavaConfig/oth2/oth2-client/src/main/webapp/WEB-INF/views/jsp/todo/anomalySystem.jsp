<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Security - OAuth2</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css" type="text/css">
</head>

<div id="wrapper">
    <a id="back" href="${pageContext.request.contextPath}">HOME画面へ戻る</a>
</div>

<hr>

<h1 id="screenTitle">例外処理</h1>

<!-- 中項目ID : 01 : 認可サーバからの応答がない -->
<!-- 小項目ID : 001 : トークンエンドポイントの誤り : registration_token_wrong-->
<!-- 小項目ID : 002 : 認可エンドポイントの誤り : registration_auth_wrong-->
<!-- 小項目ID : 003 : JWTエンドポイントの誤り（リソースサーバ側でエラー） : registration_all ※リソースサーバ側でエラー-->

<!-- 中項目ID : 02 : トークン有効期限が切れている -->
<!-- 小項目ID : 001 : （クライアント側は0201で試験しているのでリソースサーバのみ） 送信直前に一定時間待機 : registration_all -->

<div id="todoFrom">
    <ul>
        <li>トークンエンドポイント不正 (<a id="wrongTokenEndpoint_jsp"
                href="${pageContext.request.contextPath}/jsp/auth/todo/03/01/001">JSP</a> / Thymeleaf)</li>
        <li>認可エンドポイント不正 (<a id="wrongAuthEndpoint_jsp"
                href="${pageContext.request.contextPath}/jsp/auth/todo/03/01/002">JSP</a> / Thymeleaf)</li>
        <li>JWTエンドポイント不正 (<a id="wrongJWTEndpoint_jsp"
                href="${pageContext.request.contextPath}/jsp/auth/todo/03/01/003">JSP</a> / Thymeleaf)</li>
        <li>リクエスト送信時に遅延発生 (<a id="waitRequest_jsp"
                href="${pageContext.request.contextPath}/jsp/auth/todo/03/02/001">JSP</a> / Thymeleaf)</li>
        <li>クライアント側で認可サーバーを経由しない (<a id="notThroughAnAuthorizedServer_jsp"
                href="${pageContext.request.contextPath}/jsp/auth/todo/03/03/001">JSP</a> / Thymeleaf)</li>
    </ul>
</div>
<jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>

</html>