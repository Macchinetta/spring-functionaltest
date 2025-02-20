<div id="wrapper">
    <h1 id="screenTitle">ログインフォーム(AutenticationSystemError)</h1>

    <c:choose>
        <c:when test="${param.containsKey('error')}">
            <span id="loginError" style="color: red"> <t:messagesPanel messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" panelClassName="alert alert-danger" /> </span>
        </c:when>
        <c:when test="${param.systemError != null}">
            <span id="loginSystemError" style="color: red">システムエラーが発生しました。</span>
        </c:when>
    </c:choose>

    <form:form action="${pageContext.request.contextPath}/athn/1601/authenticate" cssClass="form-horizontal" method="post">
        <legend>ログイン</legend>
        <div class="form-group">
            <label for="userName" class="col col-md-2 control-label">Administrator Name</label>
            <div class="col col-md-3">
                <input type="text" class="form-control input-sm" id="username" name="username" />
            </div>
            <div class="col col-md-3"></div>
        </div>
        <div class="form-group">
            <label for="password" class="col col-md-2 control-label">Password</label>
            <div class="col col-md-3">
                <input type="password" class="form-control input-sm" id="password" name="password" />
            </div>
            <div class="col col-md-3"></div>
        </div>
        <div class="form-group">
            <input type="submit" id="login" class="btn btn-default" value="Login" />
        </div>
    </form:form>
</div>
