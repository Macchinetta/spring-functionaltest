<div id="wrapper">
    <h1 id="screenTitle">ログインしました(topForDispAuthentication)</h1>

    <table class="table table-bordered table-condensed">
        <tr>
            <th>User Name</th>
            <td>
                <span id="username"> <sec:authentication property="principal.account.username" /> </span>
            </td>
        </tr>
        <tr>
            <th>Last Name</th>
            <td>
                <span id="lastname"> <sec:authentication property="principal.account.lastName" /> </span>
            </td>
        </tr>
        <tr>
            <th>User Uuid</th>
            <td><span id="useruuid">${f:h(userUuid)}</span></td>
        </tr>
    </table>
    <form:form action="${pageContext.request.contextPath}/athn/${testNo}/logout" method="post">
        <input type="submit" id="logout" class="btn btn-default" value="Logout" />
    </form:form>
</div>
