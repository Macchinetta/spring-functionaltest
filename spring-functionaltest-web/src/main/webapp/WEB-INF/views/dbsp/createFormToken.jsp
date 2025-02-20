<div id="wrapper">
    <h1 id="screenTitle">ユーザ登録画面</h1>
    <form:form action="${pageContext.request.contextPath}/dbsp/0302/001/create?confirm" method="post" modelAttribute="userCreateForm">
        <form:label path="firstName">FirstName:</form:label>
        <form:input path="firstName" />
        <br />
        <form:label path="lastName">LastName:</form:label>
        <form:input path="lastName" />
        <br />
        <form:button name="confirm">Confirm Create User</form:button>
    </form:form>
</div>
