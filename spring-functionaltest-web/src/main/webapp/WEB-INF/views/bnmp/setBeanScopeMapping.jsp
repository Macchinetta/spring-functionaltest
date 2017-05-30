<div id="wrapper">
    <h1 id="screenTitle">Beanの適用範囲設定</h1>
    <br>
    <form:form
        action="${pageContext.request.contextPath}/bnmp/setBeanScopeMapping"
        cssClass="form-horizontal" method="post"
        modelAttribute="scopeMappingForm">
        <legend>Beanマッピング適用範囲設定方法確認</legend>
        <div class="form-group">
            <div class="col col-md-2">First Name</div>
            <div class="col col-md-3">
                <form:input path="firstName"
                    cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <div class="col col-md-2">Last Name(除外対象)</div>
            <div class="col col-md-3">
                <form:input path="lastName"
                    cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <div class="col col-md-2">age</div>
            <div class="col col-md-3">
                <form:input path="age" cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <div class="col col-md-2">birthDate(yyyyMMdd)(除外対象)</div>
            <div class="col col-md-3">
                <form:input path="birthDate"
                    cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <form:button name="scopeSetting" class="btn btn-default">適用範囲設定</form:button>
        </div>
    </form:form>
</div>