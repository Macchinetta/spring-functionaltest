<div id="wrapper">
    <h1 id="screenTitle">相関項目チェック</h1>

    <form:form method="post" action="${pageContext.request.contextPath}/vldt/0201/002" modelAttribute="userForm" class="form-horizontal">
        <div class="form-group">
            <form:label path="password" class="col-sm-2 control-label">Password</form:label>
            <div class="col-sm-10">
                <form:password path="password" class="form-control" placeholder="Password" />
                <form:errors path="password" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="confirmPassword" class="col-sm-2 control-label">ConfirmPassword</form:label>
            <div class="col-sm-10">
                <form:password path="confirmPassword" class="form-control" placeholder="ConfirmPassword" />
                <form:errors path="confirmPassword" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <form:button id="validateUser" name="validateUser" class="btn btn-default">validateUser</form:button>
            </div>
        </div>
    </form:form>

    <form:form method="post" action="${pageContext.request.contextPath}/vldt/0201/002" modelAttribute="userDetailsForm" class="form-horizontal">
        <div class="form-group">
            <form:label path="age" class="col-sm-2 control-label">Age</form:label>
            <div class="col-sm-10">
                <form:input path="age" class="form-control" placeholder="Age" />
                <form:errors path="age" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <form:label path="dateOfBirth" class="col-sm-2 control-label">DateOfBirth</form:label>
            <div class="col-sm-10">
                <form:input path="dateOfBirth" class="form-control" placeholder="DateOfBirth" />
                <form:errors path="dateOfBirth" class="text-danger" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <form:button id="validateUserDetails" name="validateUserDetails" class="btn btn-default">validateUserDetails</form:button>
            </div>
        </div>
    </form:form>
</div>
