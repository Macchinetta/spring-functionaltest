<div id="wrapper">

    <h3 id="screenTitle">ユーザ認証画面</h3>

    <c:if test="${param.containsKey('error')}">
        <t:messagesPanel messagesType="danger"
            messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" />
    </c:if>

    <form:form action="${pageContext.request.contextPath}/cspr/authenticate"
        method="post">
        <fieldset>
            <legend>ログイン</legend>
            <br>
            <div class="form-group">
                <div class="col col-md-2"><label for="userName">ユーザ名</label></div>
                <div class="col col-md-3">
                    <input type="text" class="form-control input-sm" id="username" name="username">
                </div>
            </div>
            <br>
            <div class="form-group">
                <div class="col col-md-2"><label for="password">パスワード</label></div>
                <div class="col col-md-3">
                    <input type="password" class="form-control input-sm" id="password" name="password">
                </div>
            </div>
            <br><br>
            <input type="submit" class="btn btn-default" id="committerLogin" value="ログイン">
        </fieldset>
    </form:form>

</div>
