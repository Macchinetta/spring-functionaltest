<div id="wrapper">
    <h1 id="screenTitle">マッピング処理に失敗</h1>
    <br>
    <form:form
        action="${pageContext.request.contextPath}/bnmp/mappingFailed"
        cssClass="form-horizontal" method="post"
        modelAttribute="mappingFailedForm">
        <legend>マッピング処理に失敗する場合の確認</legend>
        <div class="form-group">
            <div class="col col-md-3">birthDate(yyyyMMdd)</div>
            <div class="col col-md-3">
                <form:input path="birthDate"
                    cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <form:button name="mappingFailed" class="btn btn-default">設定</form:button>
        </div>
    </form:form>
</div>