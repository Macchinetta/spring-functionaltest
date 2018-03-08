<div id="wrapper">
  <h3 id="screenTitle">ユーザ登録確認画面</h3>

  <form:form action="${pageContext.request.contextPath}/cspr/notUseDeniedHandler"
    cssClass="form-horizontal" method="post" modelAttribute="userForm">
    <fieldset>
      <legend class="originalLegend">ユーザ情報</legend>
      <div class="form-group">
        <div class="col col-md-2">
          <label class="userRegister" for="userName">ユーザ名</label>
        </div>
        <div class="col col-md-4">
          <span id="userName">${f:h(userForm.userName)}</span>
          <form:hidden path="userName" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">
          <label class="userRegister" for="email">Email</label>
        </div>
        <div class="col col-md-4">
          <span id="email">${f:h(userForm.email)}</span>
          <form:hidden path="email" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">
          <label class="userRegister" for="password">パスワード</label>
        </div>
        <div class="col col-md-4">
          <span id="password">**********</span>
          <form:hidden path="password" />
          <form:hidden path="confirmPassword" />
        </div>
      </div>
      <div class="form-group col col-md-5">
        <form:button name="returnToRegisterNouUseDeniedHandler" class="btn btn-default">戻る</form:button>
        <form:button class="btn btn-default">登録</form:button>
      </div>
    </fieldset>

  </form:form>

</div>