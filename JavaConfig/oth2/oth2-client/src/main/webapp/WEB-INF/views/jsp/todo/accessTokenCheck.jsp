<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Todo List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css" type="text/css" />
</head>

<body>
    <div id="wrapper">
        <a id="back" href="${pageContext.request.contextPath}">HOME画面へ戻る</a>
    </div>

    <hr />

    <h1 id="screenTitle">Todo List - TokenCheck</h1>

    <div id="todoForm">
        <form:form action="${pageContext.request.contextPath}/jsp/auth/todo/${id}/list" method="get"
            modelAttribute="todoForm">
            <form:hidden path="registrationId" value="${f:h(registrationId)}" />
            <form:hidden path="resourceProtect" value="${f:h(resourceProtect)}" />
            <form:button id="list">TODO List</form:button>
        </form:form>
        <form:form action="${pageContext.request.contextPath}/jsp/auth/todo/${id}/02/001" method="get"
            modelAttribute="todoForm">
            <form:hidden path="registrationId" value="${f:h(registrationId)}" />
            <form:hidden path="resourceProtect" value="${f:h(resourceProtect)}" />
            <form:button id="tokenProcessing">トークンの加工</form:button>
        </form:form>
    </div>

    <br />
    <hr />

    <div id="todoList">
        <ul>
            <c:forEach items="${todos}" var="todo">
                <li>
                    <c:choose>
                        <c:when test="${todo.finished}">
                            <span class="strike">${f:h(todo.todoTitle)}</span>
                        </c:when>
                        <c:otherwise>
                            ${f:h(todo.todoTitle)}
                            <form:form action="${pageContext.request.contextPath}/jsp/auth/todo/finish" method="post"
                                modelAttribute="todoForm" cssClass="inline">
                                <form:hidden path="todoId" value="${f:h(todo.todoId)}" />
                                <form:button>Finish</form:button>
                            </form:form>
                        </c:otherwise>
                    </c:choose>
                    <form:form action="${pageContext.request.contextPath}/jsp/auth/todo/delete" method="post"
                        modelAttribute="todoForm" cssClass="inline">
                        <form:hidden path="todoId" value="${f:h(todo.todoId)}" />
                        <form:button>Delete</form:button>
                    </form:form>
                </li>
            </c:forEach>
        </ul>
    </div>

    <!-- OTH202用 - アクセストークンチェック時に表示する -->
    <div>
        <c:if test="${not empty accessTokenIssued}">
            <h2>Access Token</h2>
            <p>Access Token Issued : <span id="accessTokenIssued"><javatime:format value="${accessTokenIssued}" style="SM" /></span></p>
            <p>Access Token Expires : <span id="accessTokenExpires"><javatime:format value="${accessTokenExpires}" style="SM" /></span></p>
            <p>Access Token Scopes : <span id="accessTokenScopes">${accessTokenScopes}</span></p>
            <p>Access Token Type : <span id="accessTokenType">${accessTokenType}</span></p>
            <p>Access Token Value : <span id="accessTokenValue">${accessTokeValue}</span></p>

            <h2>Refresh Token</h2>
            <p>Refresh Token Issued : <span id="refreshTokenIssued"><javatime:format value="${refreshTokenIssued}" style="SM" /></span></p>
            <p>Refresh Token Expires : <span id="refreshTokenExpires"><javatime:format value="${refreshTokenExpires}" style="SM" /></span></p>
            <p>Refresh Token Value : <span id="refreshTokenValue">${refreshTokeValue}</span></p>
        </c:if>
    </div>
    <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>

</html>