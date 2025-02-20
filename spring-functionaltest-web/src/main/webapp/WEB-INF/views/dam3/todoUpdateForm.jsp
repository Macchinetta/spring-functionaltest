<form:form modelAttribute="todoForm" action="${pageContext.request.contextPath}/dam3/todo/${todo.todoId}/update">
    <fieldset>
        <legend>Todo Update Form</legend>
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
                    <label>Todo Category</label>
                </div>
                <div class="col-md-4">
                    <form:input path="todoCategory" class="form-control" value="${f:h(todo.todoCategory)}" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>Todo Title</label>
                </div>
                <div class="col-md-4">
                    <form:input path="todoTitle" class="form-control" value="${f:h(todo.todoTitle)}" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>Todo Status</label>
                </div>
                <div class="col-md-4">
                    <form:input path="finished" class="form-control" value="${f:h(todo.finished)}" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>Creation date</label>
                </div>
                <div class="col-md-4">
                    <fmt:formatDate value="${todo.createdAt}" pattern="yyyy/MM/dd" var="createdAt" />
                    <%-- <joda:format pattern="yyyy/MM/dd" value="${todo.createdAt}" var="createdAt" /> --%>
                    <form:input path="createdAt" class="form-control" value="${createdAt}" />
                </div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <label>Completion date</label>
                </div>
                <div class="col-md-4">
                    <joda:format pattern="yyyy/MM/dd" value="${todo.completeAt}" var="completeAt" />
                    <form:input path="completeAt" class="form-control" value="${completeAt}" />
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
            <div class="row">
                <div class="col-md-4">
                    <label>Desc1 (BLOB)</label>
                </div>
                <div class="col-md-4">
                    <form:input path="description1" class="form-control" value="${f:h(todo.description1)}" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>Desc2 (CLOB)</label>
                </div>
                <div class="col-md-4">
                    <form:input path="description2" class="form-control" value="${f:h(todo.description2)}" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <form:button name="update" class="btn btn-default">Update</form:button>
                    <form:button name="updateOptimistic" class="btn btn-default">Update(Consider Version)</form:button>
                    <form:button name="delete" class="btn btn-default">Delete</form:button>
                    <form:button name="deleteByTodo" class="btn btn-default">Delete By Todo Obj</form:button>
                    <form:button name="cancel" class="btn btn-default">Cancel</form:button>
                </div>
            </div>
        </div>
    </fieldset>
</form:form>
