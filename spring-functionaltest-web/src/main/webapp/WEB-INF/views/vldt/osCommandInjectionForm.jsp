<div id="wrapper">
    <h1 id="screenTitle">OSコマンドインジェクション</h1>

    <form:form method="post" action="${pageContext.request.contextPath}/vldt/0701/001" modelAttribute="osCommandInjectionForm" class="form-horizontal">
        <div class="form-group">
            <form:label path="cmdStr" class="col-sm-2 control-label">Command:</form:label>
            <div class="col-sm-10">
                <form:input path="cmdStr" class="form-control" />
                <form:errors path="cmdStr" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="arg" class="col-sm-2 control-label">Argument:</form:label>
            <div class="col-sm-10">
                <form:input path="arg" class="form-control" />
                <form:errors path="arg" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <form:button id="validate" name="validate" class="btn btn-default">validate</form:button>
            </div>
        </div>
    </form:form>
</div>
