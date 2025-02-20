<div id="wrapper">
    <h1 id="screenTitle">単項目チェック</h1>

    <form:form method="post" action="${pageContext.request.contextPath}/vldt/0101/007" modelAttribute="simpleValidationByInclusiveForm" class="form-horizontal">
        <div class="form-group">
            <form:label path="priceDefault" class="col-sm-2 control-label">price(inclusive-omit)</form:label>
            <div class="col-sm-10">
                <form:input path="priceDefault" class="form-control" placeholder="1-100" />
                <form:errors path="priceDefault" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="priceFalse" class="col-sm-2 control-label">price(inclusive = false)</form:label>
            <div class="col-sm-10">
                <form:input path="priceFalse" class="form-control" placeholder="1-99" />
                <form:errors path="priceFalse" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <form:button id="validate" name="validate" class="btn btn-default">validate</form:button>
            </div>
        </div>
    </form:form>
</div>
