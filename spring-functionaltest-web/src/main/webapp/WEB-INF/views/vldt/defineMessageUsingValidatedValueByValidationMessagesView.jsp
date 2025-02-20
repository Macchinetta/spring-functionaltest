<div id="wrapper">
    <h1 id="screenTitle">エラーメッセージの定義</h1>

    <form:form method="post" action="${pageContext.request.contextPath}/vldt/0305/001" modelAttribute="defineMessageUsingValidatedValueByValidationMessagesForm" class="form-horizontal">
        <form:errors path="*" element="div" cssClass="text-danger" />

        <h3 id="discriptionOfTest">Test of the error message using "\${validatedValue}"</h3>

        <div class="form-group">
            <form:label path="telNumber" class="col-sm-2 control-label">TelNumber</form:label>
            <div class="col-sm-10">
                <form:input path="telNumber" class="form-control" placeholder="TelNumber" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <form:button id="validate" name="validate" class="btn btn-default">validate</form:button>
            </div>
        </div>
    </form:form>
</div>
