<div id="wrapper">
    <h1 id="screenTitle">疑似乱数(鍵)生成(BytesKeyGenerator)</h1>
    <br>
    <t:messagesPanel />
    <form:form
        action="${pageContext.request.contextPath}/encr/0202/001/generateSameBytesKey"
        cssClass="form-horizontal" method="post"
        modelAttribute="encryptionDataForm">
        <legend>疑似乱数(鍵)</legend>
        <div class="form-group">
            <form:label path="keyLength"
                class="col col-md-2 control-label">鍵長(バイト)</form:label>
            <div class="col col-md-3">
                <form:input path="keyLength"
                    cssClass="form-control input-sm" />
            </div>
            <div class="col col-md-3">
                <form:errors path="keyLength" cssClass="errorMessage" />
            </div>
        </div>
        <div class="form-group">
            <form:button id="generate" class="btn btn-default">疑似乱数(鍵)生成</form:button>
        </div>
    </form:form>
</div>