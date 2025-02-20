<div id="wrapper">
    <h1 id="screenTitle">データ変換ロジックを作成してマッピング</h1>
    <br />
    <form:form action="${pageContext.request.contextPath}/bnmp/customConverter" cssClass="form-horizontal" method="post" modelAttribute="customConverterForm">
        <legend>カスタムコンバーターを使用してコピーができること</legend>
        <div class="form-group">
            <div class="col col-md-2">First Name</div>
            <div class="col col-md-3">
                <form:input path="firstName" cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <div class="col col-md-2">Last Name</div>
            <div class="col col-md-3">
                <form:input path="lastName" cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <div class="col col-md-2">Age</div>
            <div class="col col-md-3">
                <form:input path="age" cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <div class="col col-md-2">Birth Date(yyyyMMdd)</div>
            <div class="col col-md-3">
                <form:input path="birthDate" cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <form:button name="copyWithCustomConverter" class="btn btn-default">カスタムコンバーター使用してコピー</form:button>
        </div>
    </form:form>
</div>
