<div id="wrapper">
    <h1 id="screenTitle">暗号化(TextEncryptor)</h1>
    <br>
    <t:messagesPanel />
    <form:form
        action="${pageContext.request.contextPath}/encr/0102/001/encryptByTextEncryptorForSameResult"
        cssClass="form-horizontal" method="post"
        modelAttribute="encryptionDataForm">
        <legend>同一の暗号結果</legend>
        <div class="form-group">
            <form:label path="rawText"
                class="col col-md-2 control-label">平文</form:label>
            <div class="col col-md-3">
                <form:input path="rawText"
                    cssClass="form-control input-sm" />
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