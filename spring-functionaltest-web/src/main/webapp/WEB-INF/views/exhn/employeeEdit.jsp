<div id="wrapper">
    <h3 id="screenTitle">社員情報編集画面</h3>

    <t:messagesPanel />

    <form:form action="${pageContext.request.contextPath}/exhn/${f:h(testNumber)}" cssClass="form-horizontal" method="post" modelAttribute="employeeForm">
        <fieldset>
            <legend>社員情報</legend>
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="employeeId">社員ID</label>
                </div>
                <div class="col col-md-4">
                    <form:input readonly="true" id="employeeId" path="employeeId" cssClass="form-control input-sm" />
                </div>
                <div class="col col-md-5">
                    <form:errors path="employeeId" cssClass="errorMessage" />
                </div>
            </div>
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="employeeName">名前</label>
                </div>
                <div class="col col-md-4">
                    <form:input id="employeeName" path="employeeName" cssClass="form-control input-sm" />
                </div>
                <div class="col col-md-5">
                    <form:errors path="employeeName" cssClass="errorMessage" />
                </div>
            </div>
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="email">Email</label>
                </div>
                <div class="col col-md-4">
                    <form:input id="email" path="email" cssClass="form-control input-sm" />
                </div>
                <div class="col col-md-5">
                    <form:errors path="email" cssClass="errorMessage" />
                </div>
            </div>
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="address">住所</label>
                </div>
                <div class="col col-md-4">
                    <form:input id="address" path="address" cssClass="form-control input-sm" />
                </div>
                <div class="col col-md-5">
                    <form:errors path="address" cssClass="errorMessage" />
                </div>
            </div>
            <div class="form-group col col-md-5">
                <form:button name="backToIndex" class="btn btn-default">戻る</form:button>
                <form:button name="update" class="btn btn-default">更新</form:button>
            </div>
        </fieldset>
    </form:form>
</div>
