<div id="wrapper">

    <h1 id="screenTitle">[入力]指定した正規化形式で正規化する</h1>
    <br>
    <t:messagesPanel />
    <form:form action="${pageContext.request.contextPath}/stpr/normalizer"
        cssClass="form-horizontal" method="post"
        modelAttribute="stringProcessing04Form">
        <div class="form-group">
            <form:label path="targetValue" class="col col-md-2 control-label">対象文字列</form:label>
            <div class="col col-md-3">
                <form:input path="targetValue" cssClass="form-control input-sm" />
            </div>
        </div>
        <div class="form-group">
            <form:label path="normalizationForm"
                class="col col-md-2 control-label">正規化形式</form:label>
            <div class="col col-md-3">
                <form:select path="normalizationForm"
                    cssClass="form-control input-sm">
                    <form:options items="${normalizationFormMap}" />
                </form:select>
            </div>
        </div>
        <div class="form-group">
            <form:button id="normalizer" class="btn btn-default">変換</form:button>
        </div>
    </form:form>
</div>
