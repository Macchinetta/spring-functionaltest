<form:form modelAttribute="userIdForm" action="${pageContext.request.contextPath}/vldt/0601/regist"
  method="post">
  <fieldset>
    <legend>Method Validation確認 基本型(String) 入力画面</legend>
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <label>userId</label>
        </div>
        <div class="col-md-4">
          <form:input path="userId" class="form-control" />
        </div>
      </div>
      <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
          <form:button name="register" class="btn btn-default">登録</form:button>
        </div>
      </div>
    </div>
  </fieldset>
</form:form>