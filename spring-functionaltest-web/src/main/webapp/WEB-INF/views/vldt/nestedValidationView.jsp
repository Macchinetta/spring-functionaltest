<div id="wrapper">
    <h1 id="screenTitle">ネストした単項目チェック</h1>

    <form:form method="post"
        action="${pageContext.request.contextPath}/vldt/0102/001"
        modelAttribute="nestedValidationForm"
        class="form-horizontal">

        <div class="form-group">
            <form:label path="reciverAddress.userName" class="col-sm-2 control-label">UserName</form:label>
            <div class="col-sm-10">
                <form:input path="reciverAddress.userName" class="form-control"
                    placeholder="UserName" />
                <form:errors path="reciverAddress.userName" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="reciverAddress.postCode" class="col-sm-2 control-label">PostCode</form:label>
            <div class="col-sm-10">
                <form:input path="reciverAddress.postCode" class="form-control"
                    placeholder="PostCode" />
                <form:errors path="reciverAddress.postCode" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="reciverAddress.address" class="col-sm-2 control-label">Address</form:label>
            <div class="col-sm-10">
                <form:input path="reciverAddress.address" class="form-control"
                    placeholder="Address" />
                <form:errors path="reciverAddress.address" class="text-danger" />
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
