<div id="wrapper">
  <h1 id="screenTitle">ユーザ登録確認画面</h1>
  <form:form action="${pageContext.request.contextPath}/dbsp/0201/001/create" method="post"
    modelAttribute="userCreateForm">
    FirstName:${f:h(userCreateForm.firstName)}<br>
    <form:hidden path="firstName" />
    LastName:${f:h(userCreateForm.lastName)}<br>
    <form:hidden path="lastName" />
    FilePath:${f:h(userCreateForm.filePath)}<br>
    <form:hidden path="FilePath" />
    <form:button name="create">Create User</form:button>
  </form:form>
</div>