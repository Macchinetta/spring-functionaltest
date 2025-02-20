<div id="wrapper">
    <h1 id="screenTitle">[入力]サプレスされた文字列を取得できることを確認する</h1>
    <br />
    <t:messagesPanel />
    <form:form action="${pageContext.request.contextPath}/stpr/0201/002/suppress" cssClass="form-horizontal" method="post" modelAttribute="stringProcessing0201002Form">
        <div class="form-group">
            <form:label path="targetValueStr" class="col col-md-2 control-label">サプレス対象数値</form:label>
            <div class="col col-md-3">
                <form:input path="targetValueStr" cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <form:label path="format" class="col col-md-2 control-label">サプレスフォーマット</form:label>
            <div class="col col-md-3">
                <form:input path="format" cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <form:button id="suppress" class="btn btn-default">サプレス</form:button>
        </div>
    </form:form>
</div>
