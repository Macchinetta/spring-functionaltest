<div id="wrapper">

    <h1 id="screenTitle">[入力]サロゲートペアを考慮した指定範囲の文字列取得ができることを確認</h1>
    <br>
    <t:messagesPanel />
    <form:form
        action="${pageContext.request.contextPath}/stpr/0302/001/substringConsideringSurrogate"
        cssClass="form-horizontal" method="post"
        modelAttribute="stringProcessing03Form">
        <div class="form-group">
            <form:label path="targetValue"
                class="col col-md-2 control-label">対象文字列</form:label>
            <div class="col col-md-3">
                <form:input path="targetValue"
                    cssClass="form-control input-sm" />
            </div>
            <div class="col col-md-3">
                <form:errors path="targetValue" class="text-danger" />
            </div>
        </div>
        <div class="form-group">
            <form:label path="startIndex"
                class="col col-md-2 control-label">開始位置（0起算）</form:label>
            <div class="col col-md-3">
                <form:input path="startIndex"
                    cssClass="form-control input-sm" />
            </div>
            <div class="col col-md-3">
                <form:errors path="startIndex" class="text-danger" />
            </div>
        </div>
        <div class="form-group">
            <form:label path="endIndex"
                class="col col-md-2 control-label">終了位置（0起算）</form:label>
            <div class="col col-md-3">
                <form:input path="endIndex"
                    cssClass="form-control input-sm" />
            </div>
            <div class="col col-md-3">
                <form:errors path="endIndex" class="text-danger" />
            </div>
        </div>
        <div class="form-group">
            <form:button id="get" class="btn btn-default">文字列の取得</form:button>
        </div>
    </form:form>
</div>
