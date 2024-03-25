<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Security - OAuth2</title>
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/app/css/styles.css"
  type="text/css">
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
    <li><a id="wrongTokenEndpoint" href="${pageContext.request.contextPath}/auth/todo/03/01/001">トークンエンドポイント不正</a></li>
    <li><a id="wrongAuthEndpoint" href="${pageContext.request.contextPath}/auth/todo/03/01/002">認可エンドポイント不正</a></li>
    <li><a id="wrongJWTEndpoint" href="${pageContext.request.contextPath}/auth/todo/03/01/003">JWTエンドポイント不正</a></li>
    <li><a id="waitRequest" href="${pageContext.request.contextPath}/auth/todo/03/02/001">リクエスト送信時に遅延発生</a></li>
  </ul>
</div>
</body>
</html>
