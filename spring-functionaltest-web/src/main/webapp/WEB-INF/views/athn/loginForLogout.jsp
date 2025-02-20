<div id="wrapper">
    <h3 id="screenTitle">ログインフォーム(loginForLogout)</h3>
    <c:if test="${param.containsKey('error')}">
        <t:messagesPanel messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" />
    </c:if>
    <div>
        ようこそ
        <sec:authorize access="isAuthenticated()">
            <span id="AuthenticateUserName">${f:h(principal.username)}</span>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <span id="AuthenticateUserName"><spring:message code="label.sf.cmmn.defaultUserName" /></span>
        </sec:authorize>
        さん
    </div>
    <br />

    <form:form action="${pageContext.request.contextPath}/athn/0701/001/authenticate" cssClass="form-horizontal" method="post">
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
