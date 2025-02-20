<div id="wrapper">
    <h1 id="screenTitle">アクセスポリシ定義が正常に機能していることを確認する。</h1>

    <fieldset>
        <table class="table table-bordered table-condensed">
            <tr>
                <th>UserName</th>
                <td>
                    <span id="username">
                        <c:if test="${!empty username}">
                            <sec:authentication property="principal.username" />
                        </c:if>
                    </span>
                </td>
            </tr>
            <tr>
                <th>Only Account and Administrator User link</th>
                <td>
                    <span> <a id="accountsLink" href="${pageContext.request.contextPath}/athr/0601/001/accounts">Go To Account Page Top Page</a> </span>
                </td>
            </tr>
            <tr>
                <th>Only Administrator Manager link</th>
                <td>
                    <span> <a id="mangerLink" href="${pageContext.request.contextPath}/athr/0601/001/manager">Go To Manager Page</a> </span>
                </td>
            </tr>
            <tr>
                <th>Authenticated User link</th>
                <td>
                    <span> <a id="allLink" href="${pageContext.request.contextPath}/athr/0601/001/all">Go To Authenticated User Page</a> </span>
                </td>
            </tr>
        </table>
    </fieldset>
    <form:form action="${pageContext.request.contextPath}/athr/0201/logout" method="post">
        <input type="submit" id="logout" class="btn btn-default" value="Logout" />
    </form:form>
</div>
