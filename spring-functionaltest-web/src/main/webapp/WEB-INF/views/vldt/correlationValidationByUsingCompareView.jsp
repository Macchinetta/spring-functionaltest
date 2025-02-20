<div id="wrapper">
    <h1 id="screenTitle">共通ライブラリのチェックルールの拡張</h1>

    <form:form method="post" action="${pageContext.request.contextPath}/vldt/0801/001" modelAttribute="confirmFormUsingCompare" class="form-horizontal">
        <div class="form-group">
            <form:label path="password" class="col-sm-2 control-label">Password</form:label>
            <div class="col-sm-10">
                <form:password path="password" class="form-control" placeholder="Password" />
                <form:errors path="password" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="confirmPassword" class="col-sm-2 control-label">ConfirmPassword</form:label>
            <div class="col-sm-10">
                <form:password path="confirmPassword" class="form-control" placeholder="ConfirmPassword" />
                <form:errors path="confirmPassword" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <form:button id="validate" name="validate" class="btn btn-default">validate</form:button>
            </div>
        </div>
    </form:form>
</div>
