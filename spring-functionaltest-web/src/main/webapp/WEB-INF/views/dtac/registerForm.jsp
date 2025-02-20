<form:form modelAttribute="userForm" action="${pageContext.request.contextPath}/dtac/user/register">
    <fieldset>
        <legend>ユーザ登録</legend>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <label>ユーザ名</label>
                </div>
                <div class="col-md-4">
                    <form:input path="username" class="form-control" value="${f:h(user.username)}" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label>パスワード</label>
                </div>
                <div class="col-md-4">
                    <form:input path="password" class="form-control" value="${f:h(user.password)}" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label>有効/無効</label>
                </div>
                <div class="col-md-4">
                    <form:input path="enabled" class="form-control" value="${f:h(user.enabled)}" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <label>権限</label>
                </div>
                <div class="col-md-4">
                    <form:input path="authority" class="form-control" value="${f:h(user.enabled)}" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-4">
                    <form:button name="register" class="btn btn-default">登録</form:button>
                    <form:button name="cancel" class="btn btn-default">キャンセル</form:button>
                </div>
            </div>
        </div>
    </fieldset>
</form:form>
