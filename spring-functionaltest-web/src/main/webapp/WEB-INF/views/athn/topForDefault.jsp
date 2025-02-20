<div id="wrapper">
    <h1 id="screenTitle">ログインが完了したことを確認する。</h1>

    <table class="table table-bordered table-condensed">
        <tr>
            <th>User Name</th>
            <td>
                <span id="username">
                    <c:if test="${!empty username}">
                        <sec:authentication property="principal.username" />
                    </c:if>
                </span>
            </td>
        </tr>
        <tr>
            <th>Test No</th>
            <td>
                <span id="testno"> <c:if test="${!empty testNo}"> ${testNo} </c:if> </span>
            </td>
        </tr>
    </table>
    <form:form action="${pageContext.request.contextPath}/athn/${testNo}/logout" method="post">
        <input type="submit" id="logout" class="btn btn-default" value="Logout" />
    </form:form>
</div>
