<div id="wrapper">
    <h1 id="screenTitle">Login Page(AccsessPolicyDenyAll)</h1>

    <form:form action="${pageContext.request.contextPath}/athr/0601/004/authenticate" method="post">
        <c:if test="${param.containsKey('error')}">
            <span id="staffLoginError"> <t:messagesPanel messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" /> </span>
        </c:if>
        <fieldset>
            <legend>Login</legend>
            <br />
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="userName">UserName</label>
                </div>
                <div class="col col-md-3">
                    <input type="text" class="form-control input-sm" id="username" name="username" />
                </div>
            </div>
            <br />
            <div class="form-group">
                <div class="col col-md-2">
                    <label for="password">Password</label>
                </div>
                <div class="col col-md-3">
                    <input type="password" class="form-control input-sm" id="password" name="password" />
                </div>
            </div>
            <br /> <br /> <input type="submit" id="login" class="btn btn-default" value="Login" />
        </fieldset>
    </form:form>
    <br />
    <form:form action="${pageContext.request.contextPath}/athr/0601/001/logout" method="post">
        <input type="submit" id="logout" class="btn btn-default" value="Logout" />
    </form:form>
</div>
