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

<h1 id="screenTitle">認可コードグラント</h1>

<!-- 中項目ID : 01 : 全スコープに権限を持つクライアントに対する操作 : registration_all -->
<!-- 中項目ID : 02 : READスコープのみ権限を持つクライアントに対する操作 : registration_read -->
<!-- 中項目ID : 03 : 設定スコープと認可サーバ側の設定が合わないクライアントに対する操作 : registration_wrong -->
<!-- 中項目ID : 04 : 認可サーバ側は全スコープを許容するが、クライアント側でスコープを絞っている場合の操作 : registration_partial -->
<div id="todoFrom">
    <form:form action="${pageContext.request.contextPath}/jsp/auth/todo/${id}/list" method="get">
        <b>SCOPE :</b>
        <input type="radio" name="registrationId" value="registration_all" checked> ALL
        <input type="radio" name="registrationId" value="registration_read"> READ ONLY
        <input type="radio" name="registrationId" value="registration_wrong"> MISSMATCH
        <input type="radio" name="registrationId" value="registration_partial"> PARTIAL
        <br>
        <b>RESOURCE PROTECT :</b>
        <input type="radio" name="resourceProtect" value="intercepturl" checked> INTERCEPT URL
        <input type="radio" name="resourceProtect" value="annotation"> METHOD ANNOTATION
        <br>
        <input type="submit" value="todo list" name="list">
    </form:form>
</div>
<jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>

</html>