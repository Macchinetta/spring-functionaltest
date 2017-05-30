<div id="wrapper">

    <h1 id="screenTitle">get todo</h1>
    <br>
    <t:messagesPanel />
    <form:form
        action="${pageContext.request.contextPath}/soap/todo/${f:h(proxy)}/get"
        cssClass="form-horizontal" method="post"
        modelAttribute="todoForm">

        <div class="form-group">
            <form:label path="todoId"
                class="col col-md-2 control-label">todoId</form:label>
            <div class="col col-md-3">
                <form:input path="todoId"
                    cssClass="form-control input-sm" />
            </div>
        </div>

        <div class="form-group">
            <form:button id="get" class="btn btn-default">get</form:button>
        </div>

    </form:form>

</div>
