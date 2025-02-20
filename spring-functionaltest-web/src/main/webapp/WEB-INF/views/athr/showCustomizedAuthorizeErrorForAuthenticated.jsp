<div id="wrapper">
    <h1 id="screenTitle">リクエスト方式により、認可エラー時の処理が使い分けれられることを確認する。</h1>
    <div id="xMLHttpRequestResult" class="hidden alert">
        <ul>
            <li id="returnStatus"></li>
            <li id="contentType"></li>
        </ul>
    </div>
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
                <th>Access Deny link</th>
                <td>
                    <span> <a id="denyLink" href="${pageContext.request.contextPath}/athr/0701/001/deny/">Go To Access Denied Page</a> </span>
                </td>
            </tr>
        </table>
    </fieldset>
    <form:form>
        <input id="ajaxBtn" type="button" class="btn btn-default" value="Ajaxによる送信" onclick="return athr.search()" />
    </form:form>

    <form:form action="${pageContext.request.contextPath}/athr/0701/logout" method="post">
        <input type="submit" id="logout" class="btn btn-default" value="Logout" />
    </form:form>
</div>
