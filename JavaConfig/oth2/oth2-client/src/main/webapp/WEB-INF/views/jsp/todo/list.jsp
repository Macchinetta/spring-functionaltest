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

    <h1 id="screenTitle">Todo List</h1>

    <div id="todoForm">
        <t:messagesPanel />

        <form:form action="${pageContext.request.contextPath}/jsp/auth/todo/${id}/create" method="post"
            modelAttribute="todoForm">
            <form:input path="todoTitle" />
            <form:errors path="todoTitle" cssClass="text-error" />
            <form:hidden path="registrationId" value="${f:h(registrationId)}" />
            <form:hidden path="resourceProtect" value="${f:h(resourceProtect)}" />
            <form:button id="create">Create Todo</form:button>
        </form:form>

        <br />

        <form:form action="${pageContext.request.contextPath}/jsp/auth/todo/${id}/list" method="get"
            modelAttribute="todoForm">
            <form:hidden path="registrationId" value="${f:h(registrationId)}" />
            <form:hidden path="resourceProtect" value="${f:h(resourceProtect)}" />
            <form:button id="list">TODO List</form:button>
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
                            <form:form action="${pageContext.request.contextPath}/jsp/auth/todo/${id}/finish"
                                method="post" modelAttribute="todoForm" cssClass="inline">
                                <form:hidden path="todoId" value="${f:h(todo.todoId)}" />
                                <form:hidden path="registrationId" value="${f:h(registrationId)}" />
                                <form:hidden path="resourceProtect" value="${f:h(resourceProtect)}" />
                                <form:button>Finish</form:button>
                            </form:form>
                        </c:otherwise>
                    </c:choose>
                    <form:form action="${pageContext.request.contextPath}/jsp/auth/todo/${id}/delete" method="post"
                        modelAttribute="todoForm" cssClass="inline">
                        <form:hidden path="todoId" value="${f:h(todo.todoId)}" />
                        <form:hidden path="registrationId" value="${f:h(registrationId)}" />
                        <form:hidden path="resourceProtect" value="${f:h(resourceProtect)}" />
                        <form:button>Delete</form:button>
                    </form:form>
                </li>
            </c:forEach>
        </ul>
    </div>

    <hr />
    <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>

</html>