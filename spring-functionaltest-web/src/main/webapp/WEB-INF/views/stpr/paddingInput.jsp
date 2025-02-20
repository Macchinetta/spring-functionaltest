<div id="wrapper">
    <h1 id="screenTitle">[入力]指定したフォーマットでパディングされた文字列を取得できることを確認する</h1>
    <br />
    <t:messagesPanel />
    <form:form action="${pageContext.request.contextPath}/stpr/0201/001/padding" cssClass="form-horizontal" method="post" modelAttribute="stringProcessing0201001Form">
        <div class="form-group">
            <form:label path="targetValueInt" class="col col-md-2 control-label">パディング対象数値</form:label>
            <div class="col col-md-3">
                <form:input path="targetValueInt" cssClass="form-control input-sm" />
            </div>
            <div class="col col-md-3">
                <form:errors path="targetValueInt" class="text-danger" />
            </div>
        </div>
        <div class="form-group">
            <form:label path="format" class="col col-md-2 control-label">パディングフォーマット</form:label>
            <div class="col col-md-3">
                <form:input path="format" cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <form:button id="padding" class="btn btn-default">パディング</form:button>
        </div>
    </form:form>
</div>
