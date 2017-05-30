<div id="wrapper">
    <h1 id="screenTitle">ネストした単項目チェック</h1>

    <form:form method="post"
        action="${pageContext.request.contextPath}/vldt/0102/002"
        modelAttribute="nestedCollectionValidationForm"
        class="form-horizontal">

        <div class="form-group">
            <div class="col-sm-10">
                <form:errors path="addresses" class="text-danger" />
            </div>
        </div>

        <c:forEach items="${ nestedCollectionValidationForm.addresses }"
            varStatus="status">
            <legend>Address${ f:h(status.index + 1) }</legend>

            <div class="form-group">
                <form:label path="addresses[${ status.index }].userName"
                    class="col-sm-2 control-label">UserName</form:label>
                <div class="col-sm-10">
                    <form:input
                        path="addresses[${ status.index }].userName"
                        class="form-control" placeholder="UserName" />
                    <form:errors
                        path="addresses[${ status.index }].userName"
                        class="text-danger" />
                </div>
            </div>

            <div class="form-group">
                <form:label path="addresses[${ status.index }].postCode"
                    class="col-sm-2 control-label">PostCode</form:label>
                <div class="col-sm-10">
                    <form:input
                        path="addresses[${ status.index }].postCode"
                        class="form-control" placeholder="PostCode" />
                    <form:errors
                        path="addresses[${ status.index }].postCode"
                        class="text-danger" />
                </div>
            </div>

            <div class="form-group">
                <form:label path="addresses[${ status.index }].address"
                    class="col-sm-2 control-label">Address</form:label>
                <div class="col-sm-10">
                    <form:input
                        path="addresses[${ status.index }].address"
                        class="form-control" placeholder="Address" />
                    <form:errors
                        path="addresses[${ status.index }].address"
                        class="text-danger" />
                </div>
            </div>

            <c:if test="${ status.index > 0 }">
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <form:button id="remove" name="remove"
                            class="btn btn-default">remove</form:button>
                    </div>
                </div>
            </c:if>

        </c:forEach>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <form:button id="add" name="add"
                    class="btn btn-default">add</form:button>
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
