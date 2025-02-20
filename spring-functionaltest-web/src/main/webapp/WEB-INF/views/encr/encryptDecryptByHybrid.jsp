<div id="wrapper">
    <h1 id="screenTitle">暗復号(ハイブリッド暗号化方式)</h1>
    <br />
    <t:messagesPanel />
    <form:form action="${pageContext.request.contextPath}/encr/0401/001/encryptDecryptByHybrid" cssClass="form-horizontal" method="post" modelAttribute="encryptionDataForm">
        <legend>暗復号</legend>
        <div class="form-group">
            <form:label path="rawText" class="col col-md-2 control-label">平文</form:label>
            <div class="col col-md-3">
                <form:input path="rawText" cssClass="form-control input-sm" />
            </div>
            <div class="col col-md-3">
                <form:errors path="rawText" cssClass="errorMessage" />
            </div>
        </div>
        <div class="form-group">
            <form:button id="encrypt" class="btn btn-default">暗復号</form:button>
        </div>
    </form:form>
</div>
