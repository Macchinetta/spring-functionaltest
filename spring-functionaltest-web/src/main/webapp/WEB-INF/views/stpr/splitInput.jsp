<div id="wrapper">

    <h1 id="screenTitle">[入力]サロゲートペアを考慮した文字列分割ができることを確認</h1>
    <br>
    <t:messagesPanel />
    <form:form
        action="${pageContext.request.contextPath}/stpr/0303/001/split"
        cssClass="form-horizontal" method="post"
        modelAttribute="stringProcessing03Form">
        <div class="form-group">
            <form:label path="targetValue"
                class="col col-md-2 control-label">対象文字列</form:label>
            <div class="col col-md-3">
                <form:input path="targetValue"
                    cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <form:label path="splitRegex"
                class="col col-md-2 control-label">split文字</form:label>
            <div class="col col-md-3">
                <form:input path="splitRegex"
                    cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <form:button id="split" class="btn btn-default">分割</form:button>
        </div>
    </form:form>
</div>
