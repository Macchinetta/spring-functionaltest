<div id="wrapper">
    <h1 id="screenTitle">[入力]サロゲートペアを考慮した文字列長の取得ができることを確認</h1>
    <br />
    <t:messagesPanel />
    <form:form action="${pageContext.request.contextPath}/stpr/0301/001/lengthConsideringSurrogate" cssClass="form-horizontal" method="post" modelAttribute="stringProcessing03Form">
        <div class="form-group">
            <form:label path="targetValue" class="col col-md-2 control-label">対象文字列</form:label>
            <div class="col col-md-3">
                <form:input path="targetValue" cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <form:button id="get" class="btn btn-default">文字列長の取得</form:button>
        </div>
    </form:form>
</div>
