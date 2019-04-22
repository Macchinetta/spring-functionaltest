<form:form modelAttribute="userForm" action="${pageContext.request.contextPath}/dtac/user/register">
  <fieldset>
    <legend>ユーザ登録完了</legend>
    <div class="container">
      <div class="row">
        <div class="col-md-2">
          <label>ユーザ名</label>
        </div>
        <div class="col-md-4">
          <form:input path="username" class="form-control" value="${f:h(user.username)}"
            readonly="true" style="background-color: whitesmoke;" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>パスワード</label>
        </div>
        <div class="col-md-4">
          <form:input path="password" class="form-control" value="${f:h(user.password)}"
            readonly="true" style="background-color: whitesmoke;" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>有効/無効</label>
        </div>
        <div class="col-md-4">
          <form:input path="enabled" class="form-control" value="${f:h(user.enabled)}"
            readonly="true" style="background-color: whitesmoke;" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-2">
          <label>権限</label>
        </div>
        <div class="col-md-4">
          <form:input path="authority" class="form-control" value="${f:h(user.authority)}"
            readonly="true" style="background-color: whitesmoke;" />
        </div>
      </div>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-md-11"></div>
        <div class="col-md-1">
          <form:button name="back" class="button btn btn-default">戻る</form:button>
        </div>
      </div>
    </div>
  </fieldset>
</form:form>
