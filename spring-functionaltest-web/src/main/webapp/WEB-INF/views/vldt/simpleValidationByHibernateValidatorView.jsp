<div id="wrapper">
    <h1 id="screenTitle">単項目チェック</h1>

    <form:form method="post" action="${pageContext.request.contextPath}/vldt/0101/002" modelAttribute="simpleValidationByHibernateValidatorForm" class="form-horizontal">
        <div class="form-group">
            <form:label path="address" class="col-sm-2 control-label">Address</form:label>
            <div class="col-sm-10">
                <form:input path="address" class="form-control" placeholder="Address" />
                <form:errors path="address" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <form:button id="validate" name="validate" class="btn btn-default">validate</form:button>
            </div>
        </div>
    </form:form>
</div>
