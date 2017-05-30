<div id="wrapper">
    <h1 id="screenTitle">エラーメッセージの定義</h1>

    <form:form method="post"
        action="${pageContext.request.contextPath}/vldt/0302/001"
        modelAttribute="defineMessageByMessageSourceForm"
        class="form-horizontal">

        <div class="form-group">
            <form:label path="userName" class="col-sm-2 control-label">UserName</form:label>
            <div class="col-sm-10">
                <form:input path="userName" class="form-control"
                    placeholder="UserName" />
                <form:errors path="userName" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="email" class="col-sm-2 control-label">Email</form:label>
            <div class="col-sm-10">
                <form:input path="email" class="form-control"
                    placeholder="Email" />
                <form:errors path="email" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <form:button id="validate" name="validate"
                    class="btn btn-default">validate</form:button>
            </div>
        </div>

    </form:form>

</div>
