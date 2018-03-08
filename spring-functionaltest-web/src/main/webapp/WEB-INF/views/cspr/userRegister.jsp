<div id="wrapper">
  <h3 id="screenTitle">ユーザ登録画面</h3>

  <form:form action="${pageContext.request.contextPath}/cspr" cssClass="form-horizontal"
    method="post" modelAttribute="userForm">
    <fieldset>
      <legend>ユーザ情報</legend>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="userName">ユーザ名</label>
        </div>
        <div class="col col-md-4">
          <form:input id="userName" path="userName" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-5">
          <form:errors path="userName" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="email">Email</label>
        </div>
        <div class="col col-md-4">
          <form:input id="email" path="email" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-5">
          <form:errors path="email" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="password">パスワード</label>
        </div>
        <div class="col col-md-4">
          <form:password id="password" path="password" cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-5">
          <form:errors path="password" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group">
        <div class="col col-md-2">
          <label for="confirmPassword">確認パスワード</label>
        </div>
        <div class="col col-md-4">
          <form:password id="confirmPassword" path="confirmPassword"
            cssClass="form-control input-sm" />
        </div>
        <div class="col col-md-5">
          <form:errors path="confirmPassword" cssClass="errorMessage" />
        </div>
      </div>
      <div class="form-group col col-md-5">
        <form:button name="return" class="btn btn-default">戻る</form:button>
        <form:button name="confirm" class="btn btn-default">確認</form:button>
      </div>
    </fieldset>

  </form:form>

</div>