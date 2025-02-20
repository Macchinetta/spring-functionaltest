<form:form modelAttribute="todoForm" action="${pageContext.request.contextPath}/dam3/todo/register">
    <fieldset>
        <legend>Todo Registration Complete</legend>
        <c:choose>
            <c:when test="${verifyBooleanPrimitiveRet == true}">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4">
                            <label>boolean Value As returned:</label>
                        </div>
                        <div class="col-md-4">
                            <div id="returnRes">${ regResult }</div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:when test="${verifyIntegerPrimitiveRet == true}">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4">
                            <label>int Value As returned:</label>
                        </div>
                        <div class="col-md-4">
                            <div id="returnRes">${ regResult }</div>
                        </div>
                    </div>
                </div>
            </c:when>
        </c:choose>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <label>Todo ID</label>
                </div>
                <div class="col-md-4">
                    <form:input path="todoId" class="form-control" value="${f:h(todo.todoId)}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>Category Name</label>
                </div>
                <div class="col-md-4">
                    <form:input path="todoCategory" class="form-control" value="${f:h(todo.todoCategory)}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>Todo Title</label>
                </div>
                <div class="col-md-4">
                    <form:input path="todoTitle" class="form-control" value="${f:h(todo.todoTitle)}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>Todo Status</label>
                </div>
                <div class="col-md-4">
                    <c:choose>
                        <c:when test="${todo.finished == true}">
                            <form:input path="finished" class="form-control" value="Complete" readonly="true" style="background-color: whitesmoke" />
                        </c:when>
                        <c:otherwise>
                            <form:input path="finished" class="form-control" value="In-Complete" readonly="true" style="background-color: whitesmoke" />
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>Creation Date</label>
                </div>
                <div class="col-md-4">
                    <fmt:formatDate value="${todo.createdAt}" pattern="yyyy/MM/dd" var="createdAt" />
                    <form:input path="createdAt" class="form-control" value="${createdAt}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>Version</label>
                </div>
                <div class="col-md-4">
                    <form:input path="version" class="form-control" value="${f:h(todo.version)}" readonly="true" style="background-color: whitesmoke" />
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-11"></div>
                <div class="col-md-1">
                    <form:button name="back" class="button btn btn-default">戻る</form:button>
                </div>
            </div>
        </div>
    </fieldset>
</form:form>
