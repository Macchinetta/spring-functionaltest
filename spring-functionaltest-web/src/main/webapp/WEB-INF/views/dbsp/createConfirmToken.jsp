<div id="wrapper">
  <h1 id="screenTitle">ユーザ登録確認画面</h1>
  <form:form action="${pageContext.request.contextPath}/dbsp/0302/001/create" method="post"
    modelAttribute="userCreateForm">
    FirstName:${f:h(userCreateForm.firstName)}<br>
    <form:hidden path="firstName" />
    LastName:${f:h(userCreateForm.lastName)}<br>
    <form:hidden path="lastName" />
    <form:button name="create">Create User</form:button>
  </form:form>
</div>