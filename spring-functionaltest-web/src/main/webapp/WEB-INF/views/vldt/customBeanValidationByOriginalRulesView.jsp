<div id="wrapper">
    <h1 id="screenTitle">独自のBeanValidationアノテーションの作成</h1>

    <form:form method="post"
        action="${pageContext.request.contextPath}/vldt/0402/001"
        modelAttribute="customBeanValidationByOriginalRulesForm"
        class="form-horizontal">

        <div class="form-group">
            <form:label path="isbnCode" class="col-sm-2 control-label">ISBNCode</form:label>
            <div class="col-sm-10">
                <form:input path="isbnCode" class="form-control"
                    placeholder="ISBNCode" />
                <form:errors path="isbnCode" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <form:button id="validate" name="validate"
                    class="btn btn-default">validate</form:button>
            </div>
        </div>

    </form:form>

</div>
