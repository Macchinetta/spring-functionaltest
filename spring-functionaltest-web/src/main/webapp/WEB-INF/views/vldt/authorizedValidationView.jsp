<div id="wrapper">
    <h1 id="screenTitle">認証されたユーザの単項目チェック</h1>

    <form:form method="post"
        action="${pageContext.request.contextPath}/vldt/0103/003/authentication"
        class="form-horizontal">

        <c:if test="${param.containsKey('error')}">
            <span id="accountLoginError">
                <t:messagesPanel messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" />
            </span>
        </c:if>

        <div class="form-group">
            <label for="userName" class="col-sm-2 control-label">UserName</label>
            <div class="col-sm-10">
                <input type="text" id="username" name="username"
                    class="form-control" placeholder="UserName" />
            </div>
        </div>

        <div class="form-group">
            <label for="userName" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
                <input type="text" id="password" name="password"
                    class="form-control" placeholder="Password" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" id="login" name="login"
                    class="btn btn-default">Login</button>
            </div>
        </div>

    </form:form>

    <form:form method="post"
        action="${pageContext.request.contextPath}/vldt/0103/003/logout"
        class="form-horizontal">

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" id="logout" name="logout"
                    class="btn btn-default">Logout</button>
            </div>
        </div>

    </form:form>

    <form:form method="post"
        action="${pageContext.request.contextPath}/vldt/0103/003"
        modelAttribute="authorizedValidationForm"
        class="form-horizontal">

        <div class="form-group">
            <form:label path="deliveryAddress"
                class="col-sm-2 control-label">DeliveryAddress</form:label>
            <div class="col-sm-10">
                <form:input path="deliveryAddress" class="form-control"
                    placeholder="DeliveryAddress" />
                <form:errors path="deliveryAddress" class="text-danger" />
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
