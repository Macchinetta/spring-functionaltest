<div id="wrapper">
  <h1 id="screenTitle">管理者登録</h1>
  <br>
  <t:messagesPanel />
  <form:form action="${pageContext.request.contextPath}/athn/0502/001/createAdminUsingDelegating"
    cssClass="form-horizontal" method="post" modelAttribute="administratorForm">
    <legend>管理者登録</legend>
    <div class="form-group">
      <form:label path="username" class="col col-md-2 control-label">Administrator Name</form:label>
      <div class="col col-md-3">
        <form:input path="username" cssClass="form-control input-sm" />
      </div>
      <div class="col col-md-3">
        <form:errors path="username" cssClass="errorMessage" />
      </div>
    </div>
    <div class="form-group">
      <form:label path="password" class="col col-md-2 control-label">Password</form:label>
      <div class="col col-md-3">
        <form:password path="password" cssClass="form-control input-sm" />
      </div>
      <div class="col col-md-3">
        <form:errors path="password" cssClass="errorMessage" />
      </div>
    </div>
    <div class="form-group">
      <form:button id="create" class="btn btn-default">管理者登録</form:button>
    </div>
  </form:form>
</div>