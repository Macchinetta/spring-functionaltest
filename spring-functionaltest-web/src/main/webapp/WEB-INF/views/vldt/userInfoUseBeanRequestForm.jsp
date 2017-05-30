<form:form modelAttribute="userInfoForm" action="${pageContext.request.contextPath}/vldt/0602/regist" method="post">
    <fieldset>
        <legend>Method Validation確認 JavaBean 入力画面</legend>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <label>userId</label>
                </div>
                <div class="col-md-4">
                    <form:input path="userId" class="form-control" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>visitMessage</label>
                </div>
                <div class="col-md-4">
                    <form:input path="visitMessage" class="form-control" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>visitDate</label>
                </div>
                <div class="col-md-4">
                    <form:input path="visitDate" class="form-control" />
                    <form:errors path="visitDate" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <form:button name="register" class="btn btn-default">登録</form:button>
                </div>
            </div>
        </div>
    </fieldset>
</form:form>